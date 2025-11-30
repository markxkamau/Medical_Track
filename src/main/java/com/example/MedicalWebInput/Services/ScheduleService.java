package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.ScheduleDto.CreateScheduleDTO;
import com.example.MedicalWebInput.Data.ScheduleDto.ScheduleDTO;

import java.util.List;

public interface ScheduleService {
    List<ScheduleDTO> getAllSchedules();

    List<ScheduleDTO> getSchedulesForPatient(Long patientId);

    ScheduleDTO getScheduleById(Long id);

    ScheduleDTO addNewSchedule(CreateScheduleDTO createScheduleDTO);

    ScheduleDTO updateScheduleDetails(Long id, CreateScheduleDTO createScheduleDTO);

    void deleteScheduleById(Long id);
}
