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
        List<ScheduleDto> scheduleDtos = new ArrayList<>();
        for (Schedule e : schedules) {
            ScheduleDto scheduleDto = new ScheduleDto(
                    e.getIntakes(),
                    e.getTime(),
                    e.getPatient().getEmail(),
                    e.getDrug().getDrugScientificName()
            );
            scheduleDtos.add(scheduleDto);

        }
        return scheduleDtos;
    }

    public ScheduleDto getScheduleDtoByDrugId(Long drugId) {
        Schedule e = scheduleRepository.findById(getScheduleIdByDrugId(drugId)).get();
        return new ScheduleDto(
                e.getIntakes(),
                e.getTime(),
                e.getPatient().getEmail(),
                e.getDrug().getDrugScientificName()
        );
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
                scheduleDto.getIntakes(),
                scheduleDto.getTime(),
                patientRepository.findByEmail(scheduleDto.getPatientEmail()).get(),
                drugRepository.findByDrugScientificName(scheduleDto.getDrugScientificName())
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
                if (scheduleDto.getPatientEmail().equals(x.getPatient().getEmail()) &&
                        scheduleDto.getDrugScientificName().equals(x.getDrug().getDrugScientificName())) {
                    return false;
                }
            }
        }
        return true;
    }

    public List<ScheduleDao> getScheduleByPatientId(Long patientId) {
        List<Schedule> schedules;
        List<ScheduleDao> scheduleDaos = new ArrayList<>();
        schedules = scheduleRepository.findByPatientId(patientId);
        for (Schedule schedule : schedules) {
            ScheduleDao scheduleDao = new ScheduleDao(
                    schedule.getIntakes(),
                    schedule.getTime(),
                    schedule.getStartDate(),
                    schedule.getPatient().getId(),
                    schedule.getDrug().getId()
            );
            scheduleDaos.add(scheduleDao);
        }
        return scheduleDaos;
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

    public void setVisibilityNone(String drugId) {
        Drug drug = drugRepository.findByDrugScientificName(drugId);
        drug.setScheduleButton(false);
        drugRepository.save(drug);
    }

    public Schedule getDrugInfo(Long scheduleId) {
        return scheduleRepository.findById(scheduleId).get();
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
        if (schedules.size() < 1) {
            return false;
        }
        return true;
    }

    public void setStockVisibility(Long id) {
        DrugStock drugStock = drugStockRepository.findById(id).get();
        Long drugId = drugStock.getDrug().getId();
        Drug drug = drugRepository.findById(drugId).get();
        drug.setStockButton(false);
        drugRepository.save(drug);
    }

    public Long getScheduleIdByDrugId(Long drugId) {
        Drug drug = drugRepository.findById(drugId).get();
        Long patientId = drug.getPatient().getId();
        Schedule schedule = scheduleRepository.findByPatientIdAndDrugId(patientId, drugId);
        return schedule.getId();
    }

    public Long getDrugStockId(Long scheduleId) {
        Long drugId = scheduleRepository.findById(scheduleId).get().getDrug().getId();
        DrugStock drugStock = drugStockRepository.findByDrugId(drugId);
        return drugStock.getId();
    }

    public void updateScheduleData(Schedule schedule) {
        Drug drug = drugRepository.findById(schedule.getDrug().getId()).get();
        Schedule schedule1 = scheduleRepository.
                findByPatientIdAndDrugId(drug.getPatient().getId(), drug.getId());

        schedule1.setIntakes(schedule.getIntakes());
        schedule1.setTime(schedule.getTime());
        scheduleRepository.save(schedule1);

    }

    public Long getPatientId(Schedule schedule) {
        Drug drug = drugRepository.findById(schedule.getDrug().getId()).get();
        return drug.getPatient().getId();
    }

    public boolean checkStock(Long id) {
        int count = 0;
        List<Drug> drug = drugRepository.findByPatientId(id);
        for (Drug item : drug
        ) {
            DrugStock drugStock = drugStockRepository.findByDrugId(item.getId());
            if (drugStock != null) {
                count++;
            }
        }
        if (count == 0) {
            return false;
        }
        return true;
    }

    public List<DrugStock> getStockInfo(Long id) {
        List<Drug> drugs = drugRepository.findByPatientId(id);
        List<DrugStock> drugStocks = new ArrayList<>();
        for (Drug item : drugs) {
            if (drugStockRepository.findByDrugId(item.getId()) != null) {
                drugStocks.add(drugStockRepository.findByDrugId(item.getId()));
            }
        }
        return drugStocks;
    }

    public DrugStock getDrugStockById(Long stockId) {
        return drugStockRepository.findById(stockId).get();
    }

    public DrugTimetableDto convertStockToDto(DrugStock drugStock) {
        Drug drug = drugRepository.findById(drugStock.getDrug().getId()).get();
        Long patientId = drug.getPatient().getId();
        Schedule schedule = scheduleRepository.findByPatientIdAndDrugId(patientId, drug.getId());
        return new DrugTimetableDto(
                drugStock.getId(),
                drugStock.getDrugCount(),
                new Date().toString(),
                drugStock.getRefillDate().toString(),
                schedule.getId()
        );
    }

    public StockDto convertStocksToDto(DrugStock drugStock) {
        return new StockDto(
                drugStock.getId(),
                drugStock.getDrugCount(),
                drugStock.getRefillDate().toString()
        );
    }

    public boolean checkIfStockExists(DrugTimetableDto drugTimetableDto) {
        Schedule schedule = scheduleRepository.findById(drugTimetableDto.getScheduleId()).get();
        Long drugId = schedule.getDrug().getId();
        if (drugStockRepository.findByDrugId(drugId) == null) {
            return false;
        }
        return true;
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

    public void deleteDrugStock(DrugStock drugStock) {
        Drug drug = drugRepository.findById(drugStock.getDrug().getId()).get();
        drug.setStockButton(true);
        drugStockRepository.deleteById(drugStock.getId());
    }

    public ScheduleDao convertDtoToDao(ScheduleDto scheduleDto) {
        Schedule schedule = scheduleRepository.findByPatientIdAndDrugId(patientRepository.findByEmail(scheduleDto.getPatientEmail()).get().getId(), drugRepository.findByDrugScientificName(scheduleDto.getDrugScientificName()).getId());
        ScheduleDao scheduleDao = new ScheduleDao(
                schedule.getIntakes(),
                schedule.getTime(),
                schedule.getStartDate(),
                schedule.getPatient().getId(),
                schedule.getDrug().getId()
        );
        return scheduleDao;
    }

    public void updateWithScheduleDtoData(ScheduleDto scheduleDto) {
        Drug drug = drugRepository.findByDrugScientificName(scheduleDto.getDrugScientificName());
        Schedule schedule1 = scheduleRepository.
                findByPatientIdAndDrugId(drug.getPatient().getId(), drug.getId());

        schedule1.setIntakes(scheduleDto.getIntakes());
        schedule1.setTime(scheduleDto.getTime());
        scheduleRepository.save(schedule1);
    }

    public List<ScheduleDao> deletePatientSchedules(String patientEmail) {
        List<Schedule> schedules = scheduleRepository.findByPatientId(patientRepository.findByEmail(patientEmail).get().getId());
        List<ScheduleDao> scheduleDaos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            scheduleDaos.add(new ScheduleDao(
                    schedule.getIntakes(),
                    schedule.getTime(),
                    schedule.getStartDate(),
                    schedule.getPatient().getId(),
                    schedule.getDrug().getId()
            ));
            scheduleRepository.deleteById(schedule.getId());

        }

        return scheduleDaos;
    }

    public ScheduleDao deletePatientDrugSchedule(Long patientId, Long drugId) {
        Schedule schedule = scheduleRepository.findByPatientIdAndDrugId(patientId, drugId);
        ScheduleDao scheduleDao = new ScheduleDao(
                schedule.getIntakes(),
                schedule.getTime(),
                schedule.getStartDate(),
                patientId,
                drugId
        );
        scheduleRepository.deleteById(schedule.getId());
        return scheduleDao;
    }
}
