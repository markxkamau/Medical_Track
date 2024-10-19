package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.ScheduleDto.ScheduleDao;
import com.example.MedicalWebInput.Data.ScheduleDto.ScheduleDto;
import com.example.MedicalWebInput.Services.PatientService;
import com.example.MedicalWebInput.Services.ScheduleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical/api")
public class ScheduleController {
    @Autowired
    private ScheduleService scheduleService;


//    *************************************************************************
//    GetMappings
//    =========================================================================

    @GetMapping("/all_schedules")
    public ResponseEntity<?> getAllDrugSchedules() {
        return ResponseEntity.ok(scheduleService.getAllSchedules());
    }

    @GetMapping("/patient/{patientId}/schedules")
    public ResponseEntity<List<ScheduleDao>> getSchedulesForPatient(@PathVariable Long patientId) {
        return ResponseEntity.ok(scheduleService.getScheduleByPatientId(patientId));
    }


//    *************************************************************************
//    PostMappings
//    =========================================================================

    @PostMapping("/patient/new_schedule")
    public ResponseEntity<ScheduleDao> uploadNewScheduleData(@RequestBody ScheduleDto scheduleDto) {
        if (scheduleDto.getPatientEmail() == null || scheduleDto.getDrugScientificName() == null || scheduleDto.getIntakes() == null || scheduleDto.getTime() == null) {
            return null;
        }
        if (!scheduleService.checkScheduleData(scheduleDto)) {
            return null;
        }
        if (!scheduleService.checkTime(scheduleDto.getTime())) {
            return null;
        }
        scheduleService.addNewScheduleData(scheduleDto);
        scheduleService.setVisibilityNone(scheduleDto.getDrugScientificName());
        return ResponseEntity.ok(scheduleService.convertDtoToDao(scheduleDto));

    }


//    *************************************************************************
//    PutMappings
//    =========================================================================

    @PutMapping("/patient/new_schedule")
    public ResponseEntity<ScheduleDao> updateScheduleData(@RequestBody ScheduleDto scheduleDto) {
        if (scheduleDto.getPatientEmail() == null || scheduleDto.getDrugScientificName() == null || scheduleDto.getIntakes() == null || scheduleDto.getTime() == null) {
            return null;
        }
        if (!scheduleService.checkScheduleData(scheduleDto)) {
            scheduleService.updateWithScheduleDtoData(scheduleDto);
            return ResponseEntity.ok(scheduleService.convertDtoToDao(scheduleDto));
        }
        if (!scheduleService.checkTime(scheduleDto.getTime())) {
            return null;
        }
        return ResponseEntity.ok(scheduleService.convertDtoToDao(scheduleDto));

    }

    //    *************************************************************************
//    DeleteMappings
//    =========================================================================
    @DeleteMapping("/patient")
    public ResponseEntity<List<ScheduleDao>> deleteScheduleByPatientId(@RequestParam("patientEmail") String patientEmail) {
        return ResponseEntity.ok(scheduleService.deletePatientSchedules(patientEmail));
    }

    @DeleteMapping("/patient/{patientId}/drug/{drugId}")
    public ResponseEntity<ScheduleDao> deleteScheduleByPatientAndDrugId(@PathVariable Long patientId, @PathVariable Long drugId){
        return ResponseEntity.ok(scheduleService.deletePatientDrugSchedule(patientId, drugId));
    }
}

