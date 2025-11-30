package com.example.MedicalWebInput.Services.ServiceImpl;

import com.example.MedicalWebInput.Data.ScheduleDto.CreateScheduleDTO;
import com.example.MedicalWebInput.Data.ScheduleDto.ScheduleDTO;
import com.example.MedicalWebInput.Models.Schedule;
import com.example.MedicalWebInput.Repository.ScheduleRepository;
import com.example.MedicalWebInput.Services.ScheduleService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<ScheduleDTO> getAllSchedules() {
        return scheduleRepository.findAll().stream()
                .map(schedule -> modelMapper.map(schedule, ScheduleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleDTO> getSchedulesForPatient(Long patientId) {
        return scheduleRepository.findByPatientId(patientId).stream()
                .map(schedule -> modelMapper.map(schedule, ScheduleDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public ScheduleDTO getScheduleById(Long id) {
        return modelMapper.map(scheduleRepository.findById(id).get(), ScheduleDTO.class);
    }

    @Override
    public ScheduleDTO addNewSchedule(CreateScheduleDTO createScheduleDTO) {
        Schedule schedule = modelMapper.map(createScheduleDTO, Schedule.class);
        return modelMapper.map(scheduleRepository.save(schedule), ScheduleDTO.class);
    }

    @Override
    public ScheduleDTO updateScheduleDetails(Long id, CreateScheduleDTO createScheduleDTO) {
        Schedule schedule = scheduleRepository.findById(id).get();
        schedule.setIntakes(createScheduleDTO.getIntakes());
        schedule.setTime(createScheduleDTO.getTime());
        schedule.setStartDate(createScheduleDTO.getStartDate());
        return modelMapper.map(scheduleRepository.save(schedule), ScheduleDTO.class);
    }

    @Override
    public void deleteScheduleById(Long id) {
        scheduleRepository.deleteById(id);
    }
}
