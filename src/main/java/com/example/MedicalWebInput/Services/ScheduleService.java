package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.ScheduleDto.PatientDrugInfoDto;
import com.example.MedicalWebInput.Data.ScheduleDto.ScheduleDto;
import com.example.MedicalWebInput.Models.Drug;
import com.example.MedicalWebInput.Models.Patient;
import com.example.MedicalWebInput.Models.Schedule;
import com.example.MedicalWebInput.Repository.DrugRepository;
import com.example.MedicalWebInput.Repository.PatientRepository;
import com.example.MedicalWebInput.Repository.ScheduleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DrugRepository drugRepository;


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
}
