package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.ScheduleDto.*;
import com.example.MedicalWebInput.Models.Drug;
import com.example.MedicalWebInput.Models.DrugStock;
import com.example.MedicalWebInput.Models.Patient;
import com.example.MedicalWebInput.Models.Schedule;
import com.example.MedicalWebInput.Repository.DrugRepository;
import com.example.MedicalWebInput.Repository.DrugStockRepository;
import com.example.MedicalWebInput.Repository.PatientRepository;
import com.example.MedicalWebInput.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private DrugStockRepository drugStockRepository;


    public List<ScheduleDTO> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleDTO> scheduleDTOS = new ArrayList<>();
        for (Schedule e : schedules) {
            ScheduleDTO scheduleDto = new ScheduleDTO(
                    e.getIntakes(),
                    e.getTime(),
                    e.getPatient().getEmail()
            );
            scheduleDTOS.add(scheduleDto);

        }
        return scheduleDTOS;
    }

    private List<LocalTime> convertStringToTime(String[] time) {
        List<LocalTime> localTimeList = new ArrayList<>();
        for (String item : time) {
            LocalTime localTime = LocalTime.parse(item);
            localTimeList.add(localTime);
        }
        return localTimeList;
    }

    public boolean checkScheduleData(ScheduleDTO scheduleDto) {
        List<Schedule> schedules = scheduleRepository.findAll();
        if (schedules.isEmpty()) {
            return false;
        }

        return false;
    }


    public PatientDrugInfoDto getPatientAndDrugInfo(Long patientId, Long drugId) {
        return new PatientDrugInfoDto(
                patientRepository.findById(patientId).get().getFirstName(),
                patientRepository.findById(patientId).get().getLastName(),
                drugRepository.findById(drugId).get().getDrugName()
        );
    }

    public boolean checkTime(String[] time) {
        if (time.length > 1) {
            List<LocalTime> localTIme = convertStringToTime(time);
            long distinctCount = localTIme.stream().distinct().count();

            return distinctCount < localTIme.size();

        }

        return false;
    }


    public Schedule getDrugInfo(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).get();
    }


    public boolean checKDate(String startDate) {
        Date date = convertStringToDate(startDate);
        Date currentDate = new Date();
        long diff = date.getTime() - currentDate.getTime();
        return diff >= 0;
    }

    private Date convertStringToDate(String startDate) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date = null;
        try {
            date = format.parse(startDate);
        } catch (ParseException e) {
            System.out.println(e.getMessage());
        }
        return date;
    }


    public void updateStartDate(DrugTimetableDto drugTimetableDto) {
        Schedule schedule = scheduleRepository.findById(drugTimetableDto.getScheduleId()).get();

        schedule.setStartDate(convertStringToDate(drugTimetableDto.getStartDate()));
        scheduleRepository.save(schedule);
    }

    public boolean checkIfNull(Long patientId) {
        List<Schedule> schedules = scheduleRepository.findByPatientId(patientId);
        return !schedules.isEmpty();
    }


    public Long getDrugStockId(Long scheduleId) {
        Long drugId = scheduleRepository.findById(scheduleId).get().getDrug().getId();
        DrugStock drugStock = drugStockRepository.findByDrugId(drugId);
        return drugStock.getId();
    }


    public DrugStock getDrugStockById(Long stockId) {
        return drugStockRepository.findById(stockId).get();
    }


    public boolean checkIfStockExists(DrugTimetableDto drugTimetableDto) {
        Schedule schedule = scheduleRepository.findById(drugTimetableDto.getScheduleId()).get();
        Long drugId = schedule.getDrug().getId();
        return drugStockRepository.findByDrugId(drugId) != null;
    }

    public void updateStockData(DrugTimetableDto drugTimetableDto) {
        Schedule schedule = scheduleRepository.findById(drugTimetableDto.getScheduleId()).get();
        DrugStock drugStock = drugStockRepository.findByDrugId(schedule.getDrug().getId());
        drugStock.setRefillDate(convertStringToDate(drugTimetableDto.getRefillDate()));
        drugStock.setDrugCount(drugTimetableDto.getDrugCount());
        drugStockRepository.save(drugStock);
    }

    public String getDrugId(DrugTimetableDto drugTimetableDto) {
        Schedule schedule = scheduleRepository.findById(drugTimetableDto.getScheduleId()).get();
        return schedule.getDrug().getId().toString();
    }

}
