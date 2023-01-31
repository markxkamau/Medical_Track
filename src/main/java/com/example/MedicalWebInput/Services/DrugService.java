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
            if (drug.getDrugScientificName().equals(drugList.get(x).getDrugScientificName()) &&
                    drug.getDrugSize() == drugList.get(x).getDrugSize() &&
                    drug.getDrugName().equals(drugList.get(x).getDrugName())
            ) {
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

    public List<Drug> getDrugsForPatient(Long patientId) {
        return drugRepository.findByPatientId(patientId);
    }

    public DrugDto convertToDrugDto(Drug d) {
        return new DrugDto(
                d.getId(),
                d.getDrugName(),
                d.getDrugScientificName(),
                d.getDrugSize(),
                d.getDrugPackaging(),
                d.getDrugPurpose(),
                d.getPatient().getId(),
                d.getScheduleButton(),
                d.getStockButton()
        );
    }

    public void updateDrugData(DrugDto drugDto) {
        Drug drug = drugRepository.findById(drugDto.getId()).get();
        drug.setDrugName(drugDto.getDrugName());
        drug.setDrugPackaging(drugDto.getDrugPackaging());
        drug.setDrugPurpose(drugDto.getDrugPurpose());
        drug.setDrugSize(drugDto.getDrugSize());
        drug.setDrugScientificName(drugDto.getDrugScientificName());
        drug.setPatient(patientRepository.findById(drugDto.getPatientId()).get());
        drugRepository.save(drug);
    }
}
