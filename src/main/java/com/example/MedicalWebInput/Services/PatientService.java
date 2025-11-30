package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.PatientDto.CreatePatientDTO;
import com.example.MedicalWebInput.Data.PatientDto.PatientDTO;
import com.example.MedicalWebInput.Models.Patient;

import java.util.List;

public interface PatientService {
    List<PatientDTO> getAllPatients();

    PatientDTO getPatientById(Long id);

    PatientDTO addNewPatient(CreatePatientDTO createPatientDTO);

    PatientDTO updatePatientDetails(Long id, CreatePatientDTO createPatientDTO);

    void deletePatientById(Long id);
}
