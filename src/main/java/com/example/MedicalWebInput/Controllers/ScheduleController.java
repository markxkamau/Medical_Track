package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.ScheduleDto.CreateScheduleDTO;
import com.example.MedicalWebInput.Data.ScheduleDto.ScheduleDTO;
import com.example.MedicalWebInput.Services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/schedules")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<ScheduleDTO>> getAllDrugSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    @GetMapping("/patient/{patientId}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<List<ScheduleDTO>> getSchedulesForPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(scheduleService.getSchedulesForPatient(patientId));
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<ScheduleDTO> getSetScheduleForPatient(@PathVariable Long id) {
        return ResponseEntity.ok(scheduleService.getScheduleById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<ScheduleDTO> uploadNewScheduleData(@RequestBody CreateScheduleDTO scheduleDto) {
        return ResponseEntity.ok(scheduleService.addNewSchedule(scheduleDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<ScheduleDTO> updateScheduleData(@PathVariable Long id, @RequestBody CreateScheduleDTO scheduleDto) {
        return ResponseEntity.ok(scheduleService.updateScheduleDetails(id, scheduleDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<?> deleteScheduleByPatientId(@PathVariable Long id) {
        scheduleService.deleteScheduleById(id);
        return ResponseEntity.ok().build();
    }
}
