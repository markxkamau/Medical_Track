package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.PatientDto.*;
import com.example.MedicalWebInput.Models.Patient;
import com.example.MedicalWebInput.Services.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical/api")
public class PatientController {

    @Autowired
    private PatientService patientService;

//    *************************************************************************
//    GetMappings
//    =========================================================================

    //    Must have admin rights
    @GetMapping("/all_patients")
    public ResponseEntity<List<PatientDto>> getAllPatients() {
        return ResponseEntity.ok(patientService.convertToPatientDto(patientService.getAllPatients()));
    }

    @GetMapping("/patient/{id}")
    public ResponseEntity<BasicPatientDto> getPatientInfo(@PathVariable Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }


//    *************************************************************************
//    PostMappings
//    =========================================================================

    @PostMapping("/new_patient")
    public ResponseEntity<Patient> uploadPatientInfo(@RequestBody CreatePatientDto patientDto) {
        if (patientService.checkForPatient(patientDto.getEmail())) {
//            Patient email already exists
            return ResponseEntity.ok(patientService.getPatientByEmail(patientDto.getEmail()));
        }
        if (!patientService.checkPassword(patientDto.getPassword(), patientDto.getConfirmPassword())) {
//            Passwords not similar
            return ResponseEntity.ok(patientService.getPatientByEmail(patientDto.getEmail()));
        }
        if (patientDto.getDrugCount() < 1) {
//            Drug count less than one
            return ResponseEntity.ok(patientService.getPatientByEmail(patientDto.getEmail()));
        }
        Patient patient = patientService.convertToPatient(patientDto);
        patientService.addNewPatient(patient);

        return ResponseEntity.ok(patient);
    }


//    *************************************************************************
//    PutMappings
//    =========================================================================
    @PutMapping("/new_patient")
    public ResponseEntity<PatientDao> updatePatientInfo(@RequestBody CreatePatientDto patientDto) {
        if (!patientService.checkForPatient(patientDto.getEmail())) {
//            Patient email doesn't exists
            uploadPatientInfo(patientDto);
        }
        if (!patientService.checkPassword(patientDto.getPassword(), patientDto.getConfirmPassword())) {
//            Passwords not similar
            return ResponseEntity.ok(patientService.convertToPatientDao(patientService.getPatientByEmail(patientDto.getEmail())));
        }
        if (patientDto.getDrugCount() < 1) {
//            Drug count less than one
            return ResponseEntity.ok(patientService.convertToPatientDao(patientService.getPatientByEmail(patientDto.getEmail())));
        }
        Patient patient = patientService.getPatientByEmail(patientDto.getEmail());


        return ResponseEntity.ok(patientService.convertToPatientDao(patientService.updatePatientDetails(patientDto)));
    }

//    *************************************************************************
//    PutMappings
//    =========================================================================
    @DeleteMapping("/patient/{id}")
    public ResponseEntity<PatientDao> deletePatientInfo(@PathVariable Long id){
        if (!patientService.checkForPatient(patientService.getPatientInfoById(id).getEmail())) {
//            Patient email doesn't exists
            return ResponseEntity.ok(null);
        }
        return  ResponseEntity.ok(patientService.deletePatientById(id));

    }


}
