package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.ScheduleDto.CreateScheduleDTO;
import com.example.MedicalWebInput.Data.ScheduleDto.ScheduleDTO;
import com.example.MedicalWebInput.Models.Schedule;
import com.example.MedicalWebInput.Repository.ScheduleRepository;
import com.example.MedicalWebInput.Services.ServiceImpl.ScheduleServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class ScheduleServiceImplTest {

    @InjectMocks
    private ScheduleServiceImpl scheduleService;

    @Mock
    private ScheduleRepository scheduleRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void getAllSchedules() {
        Schedule schedule = new Schedule();
        schedule.setId(1L);

        when(scheduleRepository.findAll()).thenReturn(Collections.singletonList(schedule));

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(1L);

        when(modelMapper.map(schedule, ScheduleDTO.class)).thenReturn(scheduleDTO);

        List<ScheduleDTO> result = scheduleService.getAllSchedules();

        assertEquals(1, result.size());
        assertEquals(1L, result.get(0).getId());
    }

    @Test
    void getScheduleById() {
        Schedule schedule = new Schedule();
        schedule.setId(1L);

        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(1L);

        when(modelMapper.map(schedule, ScheduleDTO.class)).thenReturn(scheduleDTO);

        ScheduleDTO result = scheduleService.getScheduleById(1L);

        assertEquals(1L, result.getId());
    }

    @Test
    void addNewSchedule() {
        CreateScheduleDTO createScheduleDTO = new CreateScheduleDTO();
        createScheduleDTO.setId(1L);

        Schedule schedule = new Schedule();
        schedule.setId(1L);

        when(modelMapper.map(createScheduleDTO, Schedule.class)).thenReturn(schedule);
        when(scheduleRepository.save(schedule)).thenReturn(schedule);

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(1L);

        when(modelMapper.map(schedule, ScheduleDTO.class)).thenReturn(scheduleDTO);

        ScheduleDTO result = scheduleService.addNewSchedule(createScheduleDTO);

        assertEquals(1L, result.getId());
    }

    @Test
    void updateScheduleDetails() {
        CreateScheduleDTO createScheduleDTO = new CreateScheduleDTO();
        createScheduleDTO.setId(1L);

        Schedule schedule = new Schedule();
        schedule.setId(2L);

        when(scheduleRepository.findById(1L)).thenReturn(Optional.of(schedule));
        when(scheduleRepository.save(schedule)).thenReturn(schedule);

        ScheduleDTO scheduleDTO = new ScheduleDTO();
        scheduleDTO.setId(1L);

        when(modelMapper.map(schedule, ScheduleDTO.class)).thenReturn(scheduleDTO);

        ScheduleDTO result = scheduleService.updateScheduleDetails(1L, createScheduleDTO);

        assertEquals(1L, result.getId());
    }

    @Test
    void deleteScheduleById() {
        scheduleService.deleteScheduleById(1L);
        verify(scheduleRepository, times(1)).deleteById(1L);
    }
}
