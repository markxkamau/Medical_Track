package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.DrugDto.DrugDto;
import com.example.MedicalWebInput.Models.Drug;
import com.example.MedicalWebInput.Models.Patient;
import com.example.MedicalWebInput.Repository.DrugRepository;
import com.example.MedicalWebInput.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DrugService {
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private PatientRepository patientRepository;

    public List<Drug> getListForPatient(Long patientId) {
        return drugRepository.findByPatientId(patientId);
    }

    public List<Drug> getAllDrugs() {
        return drugRepository.findAll();
    }

    public boolean checkDrugData(DrugDto drug) {
        List<Drug> drugList = getAllDrugs();
        int x = 0;
        while (x < drugList.size()) {
            if (drug.getDrugScientificName().equals(drugList.get(x).getDrugScientificName()) && drug.getDrugSize() == drugList.get(x).getDrugSize()) {
                return false;
            }
            x++;
        }
        return true;
    }

    public void addNewDrugData(DrugDto drugDto) {
        Patient patient = patientRepository.findById(drugDto.getPatientId()).get();
        Drug drug = new Drug(
                drugDto.getId(),
                drugDto.getDrugName(),
                drugDto.getDrugScientificName(),
                drugDto.getDrugSize(),
                drugDto.getDrugPackaging(),
                drugDto.getDrugPurpose(),
                patient
        );
        drugRepository.save(drug);
    }
}
