package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.ScheduleDto.DrugTimetableDto;
import com.example.MedicalWebInput.Data.ScheduleDto.PatientDrugInfoDto;
import com.example.MedicalWebInput.Data.ScheduleDto.ScheduleDto;
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


    public List<ScheduleDto> getAllSchedules() {
        List<Schedule> schedules = scheduleRepository.findAll();
        List<ScheduleDto> scheduleDtos = null;
        for (Schedule e : schedules) {
            ScheduleDto scheduleDto = new ScheduleDto(
                    e.getId(),
                    e.getIntakes(),
                    e.getTime(),
                    e.getPatient().getId(),
                    e.getDrug().getId()
            );
            scheduleDtos.add(scheduleDto);

        }
        return scheduleDtos;
    }

    private String[] convertTimeToString(List<LocalTime> time) {
        List<String> empty = new ArrayList<>();
        for (LocalTime item : time) {
            empty.add(item.toString());
        }
        String[] strings = empty.toArray(new String[empty.size()]);
        return strings;
    }


    public void addNewScheduleData(ScheduleDto scheduleDto) {
        Schedule schedule = new Schedule(
                scheduleDto.getId(),
                scheduleDto.getIntakes(),
                scheduleDto.getTime(),
                patientRepository.findById(scheduleDto.getPatientId()).get(),
                drugRepository.findById(scheduleDto.getDrugId()).get()
        );
        scheduleRepository.save(schedule);
    }

    private List<LocalTime> convertStringToTime(String[] time) {
        List<LocalTime> localTimeList = new ArrayList<>();
        for (String item : time) {
            LocalTime localTime = LocalTime.parse(item);
            localTimeList.add(localTime);
        }
        return localTimeList;
    }

    public boolean checkScheduleData(ScheduleDto scheduleDto) {
        List<Schedule> schedules = scheduleRepository.findAll();
        if (schedules.size() == 0) {
            return true;
        } else {
            for (Schedule x : schedules) {
                if (scheduleDto.getPatientId().equals(x.getPatient().getId()) &&
                        scheduleDto.getDrugId().equals(x.getDrug().getId()))
//                        && scheduleDto.getTime().length == (x.getTime().length))
                {
                    return false;
                }
            }
        }
        return true;
    }

    public Patient getPatientById(Long patientId) {
        return patientRepository.findById(patientId).get();
    }

    public List<Schedule> getScheduleByPatientId(Long patientId) {
        return scheduleRepository.findByPatientId(patientId);
    }

    public List<Drug> getDrugByPatientId(Long patientId) {
        return drugRepository.findByPatientId(patientId);
    }


    public PatientDrugInfoDto getPatientAndDrugInfo(Long patientId, Long drugId) {
        PatientDrugInfoDto patientDrugInfoDto = new PatientDrugInfoDto(
                patientRepository.findById(patientId).get().getName(),
                drugRepository.findById(drugId).get().getDrugName()
        );
        return patientDrugInfoDto;
    }

    public boolean checkTime(String[] time) {
        if (time.length > 1) {
            List<LocalTime> localTIme = convertStringToTime(time);
            long distinctCount = localTIme.stream().distinct().count();

            if (distinctCount >= localTIme.size()) {
                return true;
            } else {
                return false;
            }

        }

        return true;
    }

    public void setVisibilityNone(Long drugId) {
        Drug drug = drugRepository.findById(drugId).get();
        drug.setScheduleButton(false);
        drugRepository.save(drug);
    }

    public Patient getDrugInfo(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).get();
        return patientRepository.findById(schedule.getPatient().getId()).get();
    }



    public boolean checKDate(String startDate) {
        Date date = convertStringToDate(startDate);
        Date currentDate = new Date();
        long diff = date.getTime() - currentDate.getTime();
        if (diff < 0) {
            return false;
        }
        return true;
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

    public void addNewDrugStock(DrugTimetableDto drugTimetableDto) {
        Drug drug = scheduleRepository.findById(drugTimetableDto.getScheduleId()).get().getDrug();
        DrugStock drugStock = new DrugStock(
                drugTimetableDto.getId(),
                drugTimetableDto.getDrugCount(),
                convertStringToDate(drugTimetableDto.getRefillDate()),
                drug
        );
        drugStockRepository.save(drugStock);
    }

    public void updateStartDate(DrugTimetableDto drugTimetableDto) {
        Schedule schedule = scheduleRepository.findById(drugTimetableDto.getScheduleId()).get();
        schedule.setStartDate(convertStringToDate(drugTimetableDto.getStartDate()));
        scheduleRepository.save(schedule);
    }

    public boolean checkIfNull(Long patientId) {
        List<Schedule> schedules = scheduleRepository.findByPatientId(patientId);
        if (schedules.size() < 1){
            return false;
        }
        return true;
    }
}
