package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.ScheduleDto.CreateScheduleDTO;
import com.example.MedicalWebInput.Data.ScheduleDto.ScheduleDTO;
import com.example.MedicalWebInput.Models.Schedule;
import com.example.MedicalWebInput.Repository.ScheduleRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<ScheduleDTO> getAllSchedules() {
        return scheduleRepository.findAll().stream().map(s -> modelMapper.map(s, ScheduleDTO.class))
                .collect(Collectors.toList());
    }

    public ScheduleDTO createSchedule(CreateScheduleDTO dto) {
        Schedule schedule = modelMapper.map(dto, Schedule.class);
        Schedule saved = scheduleRepository.save(schedule);
        return modelMapper.map(saved, ScheduleDTO.class);
    }

    public ScheduleDTO updateSchedule(Long scheduleId, Map<String, Object> updates) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("Schedule not found with id: " + scheduleId));

        // Update times
        if (updates.containsKey("times")) {
            schedule.setTimes((List<String>) updates.get("times"));
        }

        // Update frequency
        if (updates.containsKey("frequency")) {
            schedule.setFrequency((String) updates.get("frequency"));
        }

        // Update end date
        if (updates.containsKey("endDate")) {
            schedule.setEndDate((String) updates.get("endDate"));
        }

        // Update instructions
        if (updates.containsKey("instructions")) {
            schedule.setInstructions((String) updates.get("instructions"));
        }

        Schedule updated = scheduleRepository.save(schedule);
        return modelMapper.map(updated, ScheduleDTO.class);
    }


}