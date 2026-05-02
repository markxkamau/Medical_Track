package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.HealthRecordDto.CreateHealthRecordDTO;
import com.example.MedicalWebInput.Services.HealthRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medical/api")
public class HealthRecordController {
    @Autowired
    private HealthRecordService hrService;

    @GetMapping("/all_health_records")
    public ResponseEntity<?> getAllHealthRecords() {
        return ResponseEntity.ok(hrService.getAllRecords());
    }

    @PostMapping("/create_health_record")
    public ResponseEntity<?> createHealthRecord(@RequestBody CreateHealthRecordDTO dto) {
        return ResponseEntity.ok(hrService.createRecord(dto));
    }
}