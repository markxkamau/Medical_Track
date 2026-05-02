package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.PatientDto.*;
import com.example.MedicalWebInput.Services.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical/api")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    // *************************************************************************
    // GetMappings
    // =========================================================================

    // Must have admin rights
    @GetMapping("/all_patients")
    public ResponseEntity<List<PatientDTO>> getAllPatients() {
        return ResponseEntity.ok(patientService.convertToPatientDto(patientService.getAllPatients()));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<PatientDTO> getPatientInfo(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }

    // *************************************************************************
    // PostMappings
    // =========================================================================
    // {
    // "firstName": "string",
    // "lastName": "string",
    // "email": "string",
    // "condition": "string",
    // "password": "string",
    // "dateTime": "2024-12-16T23:58:43.638Z"
    // }
    @PostMapping("/new_patient")
    public ResponseEntity<?> registerOrUpdatePatient(@RequestBody CreatePatientDTO patientDto) {

        try {
            // 1. Validation (can be moved to a separate method/class)
            if (!patientService.isValidPatientData(patientDto)) {
                return ResponseEntity.badRequest().body("Invalid patient data. Please fill in all required fields.");
            }

            // 2. Check for existing patient
            if (patientService.checkEmailForPatient(patientDto.getEmail())) {
                // 3. Handle existing patient (using 409 Conflict status code)
                return ResponseEntity.status(HttpStatus.CONFLICT).body("A patient with this email already exists.");
                // Or, if you really want to return the existing patient data (be cautious!):
                // return
                // ResponseEntity.status(HttpStatus.CONFLICT).body(patientService.getPatientByEmailLimited(patientDto.getEmail()));
            }

            return ResponseEntity.ok(patientService.addNewPatient(patientDto));

        } catch (Exception e) {
            // 4. General exception handling
            return ResponseEntity.internalServerError().body(e.getMessage());
        }
    }

    // *************************************************************************
    // PutMappings
    // =========================================================================
    // {
    // "firstName": "string",
    // "lastName": "string",
    // "email": "string",
    // "condition": "string",
    // "password": "string",
    // "dateTime": "2024-12-16T23:58:43.638Z"
    // }
    @PutMapping("/new_patient")
    public ResponseEntity<?> updatePatientInfo(@RequestBody CreatePatientDTO patientDto) {
        try {
            // 1. Validation (can be moved to a separate method/class)
            if (!patientService.isValidPatientData(patientDto)) {
                return ResponseEntity.badRequest().body("Invalid patient data. Please fill in all required fields.");
            }

            // 2. Check for existing patient
            if (patientService.checkEmailForPatient(patientDto.getEmail())) {
                // 3. Handle existing patient (using 409 Conflict status code)

                return ResponseEntity.ok(patientService.updatePatientDetails(patientDto));
                // Or, if you really want to return the existing patient data (be cautious!):
                // return
                // ResponseEntity.status(HttpStatus.CONFLICT).body(patientService.getPatientByEmailLimited(patientDto.getEmail()));
            }

            return ResponseEntity.ok(patientService.addNewPatient(patientDto));

        } catch (Exception e) {
            // 4. General exception handling
            return ResponseEntity.internalServerError().body("An error occurred while processing your request.");
        }
    }

    // *************************************************************************
    // DeleteMappings
    // =========================================================================
    // {
    // "id": 0
    // }
    @DeleteMapping("/patient/{id}")
    public ResponseEntity<?> deletePatientInfo(@PathVariable Long id) {
        if (!patientService.checkForPatient(id)) {
            // Patient email doesn't exists
            return ResponseEntity.badRequest().body("No such patient in the database.");
        }
        return ResponseEntity.ok(patientService.deletePatientById(id));

    }

}
