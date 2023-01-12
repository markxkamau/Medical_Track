package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.PatientDto.CreatePatientDto;
import com.example.MedicalWebInput.Data.PatientDto.PatientDto;
import com.example.MedicalWebInput.Data.PatientDto.PatientLoginDto;
import com.example.MedicalWebInput.Models.Drug;
import com.example.MedicalWebInput.Models.Patient;
import com.example.MedicalWebInput.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DrugService drugService;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public boolean checkForPatient(String email) {
        if (!patientRepository.findByEmail(email).isEmpty()) {
            return true;
        }
        return false;
    }

    public void addNewPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public List<PatientDto> convertToPatientDto(List<Patient> allPatients) {
        List<PatientDto> patientDtos = new ArrayList<>();
        for (Patient item : allPatients) {
            patientDtos.add(new PatientDto(
                    item.getId(),
                    item.getName(),
                    item.getEmail(),
                    item.getDrugs().size(),
                    item.getCondition(),
                    item.getPassword()
            ));

        }
        return patientDtos;
    }

    public boolean checkPassword(String password, String confirmPassword) {
        if (password.equals(confirmPassword)) {
            return true;
        }
        return false;
    }

    public Patient convertToPatient(CreatePatientDto patientDto) {
        String patientName = patientDto.getFirstName() + " " + patientDto.getLastName();
        List<Drug> drugs = drugService.getListForPatient(patientDto.getId());
        Patient patient = new Patient(
                patientDto.getId(),
                patientName,
                patientDto.getEmail(),
                patientDto.getPassword(),
                patientDto.getCondition(),
                drugs
        );
        return patient;
    }

    public boolean verifyLogin(PatientLoginDto patientLoginDto) {
        String passwordConfirm = getPassword(patientLoginDto.getEmail());
        if (passwordConfirm.isEmpty()) {
            return false;
        }
        if (!passwordConfirm.equals(patientLoginDto.getPassword())) {
            return false;
        }
        return true;
    }

    private String getPassword(String email) {
        Optional<Patient> patient = patientRepository.findByEmail(email);
        if (!patient.isEmpty()) {
            return patient.get().getPassword();
        }
        return "";
    }

    public Patient getPatientByEmail(String email) {
        return patientRepository.findByEmail(email).get();
    }
}
