package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.ScheduleDto.CreateScheduleDTO;
import com.example.MedicalWebInput.Services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
@RequestMapping("/medical/api")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping("/all_schedules")
    public ResponseEntity<?> getAllDrugSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    @PostMapping("/create_schedule")
    public ResponseEntity<?> uploadNewScheduleData(@RequestBody CreateScheduleDTO dto) {
        return ResponseEntity.ok(scheduleService.createSchedule(dto));
    }

    @PutMapping("/schedule/{scheduleId}")
    public ResponseEntity<?> updateSchedule(@PathVariable Long scheduleId, @RequestBody Map<String, Object> updates) {
        return ResponseEntity.ok(scheduleService.updateSchedule(scheduleId, updates));
    }
}