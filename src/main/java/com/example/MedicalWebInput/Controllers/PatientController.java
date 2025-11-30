package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.PatientDto.CreatePatientDTO;
import com.example.MedicalWebInput.Data.PatientDto.PatientDTO;
import com.example.MedicalWebInput.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        return ResponseEntity.ok(patientService.getAllPatients());
    }

    @GetMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<PatientDTO> getPatientInfo(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<PatientDTO> registerOrUpdatePatient(@RequestBody CreatePatientDTO patientDto) {
        return ResponseEntity.ok(patientService.addNewPatient(patientDto));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<PatientDTO> updatePatientInfo(@PathVariable Long id, @RequestBody CreatePatientDTO patientDto) {
        return ResponseEntity.ok(patientService.updatePatientDetails(id, patientDto));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deletePatientInfo(@PathVariable Long id) {
        patientService.deletePatientById(id);
        return ResponseEntity.ok().build();
    }
}
