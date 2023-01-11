package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.PatientDto;
import com.example.MedicalWebInput.Models.Patient;
import com.example.MedicalWebInput.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;

    public List<Patient> getAllPatients(){
        return patientRepository.findAll();
    }
    public boolean checkForPatient(Patient patient){
        if(!patientRepository.findByEmail(patient.getEmail()).isEmpty()){
            return true;
        }
        return false;
    }

    public void addNewPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public List<PatientDto> convertToPatientDto(List<Patient> allPatients) {
        List<PatientDto> patientDtos = new ArrayList<>();
        for(Patient item: allPatients){
            patientDtos.add(new PatientDto(
                    item.getId(),
                    item.getName(),
                    item.getEmail(),
                    item.getDrugs().size(),
                    item.getCondition(),
                    item.getPassword()
            ));

        }
        return  patientDtos;
    }
}
