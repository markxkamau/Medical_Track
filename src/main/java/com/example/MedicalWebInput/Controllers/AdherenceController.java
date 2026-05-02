package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.AdherenceDto.CreateAdherenceDTO;
import com.example.MedicalWebInput.Services.AdherenceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medical/api")
public class AdherenceController {
    @Autowired
    private AdherenceService adherenceService;

    @GetMapping("/all_adherence")
    public ResponseEntity<?> getAllAdherence() {
        return ResponseEntity.ok(adherenceService.getAllAdherence());
    }

    @PostMapping("/create_adherence")
    public ResponseEntity<?> createAdherence(@RequestBody CreateAdherenceDTO dto) {
        return ResponseEntity.ok(adherenceService.createAdherence(dto));
    }

    @PostMapping("/logDose")
    public ResponseEntity<?> logDose(@RequestBody CreateAdherenceDTO dto) {
        dto.setDosesTaken(dto.getDosesTaken() + 1);
        return ResponseEntity.ok(adherenceService.createAdherence(dto));
    }
}