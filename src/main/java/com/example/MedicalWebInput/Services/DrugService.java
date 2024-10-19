package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.DrugDtoDao.DrugDao;
import com.example.MedicalWebInput.Data.DrugDtoDao.DrugDto;
import com.example.MedicalWebInput.Models.Drug;
import com.example.MedicalWebInput.Models.Patient;
import com.example.MedicalWebInput.Models.Schedule;
import com.example.MedicalWebInput.Repository.DrugRepository;
import com.example.MedicalWebInput.Repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DrugService {
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private PatientRepository patientRepository;

    public List<Drug> getListForPatient(String patientEmail) {
        return drugRepository.findByPatientEmail(patientEmail);
    }

    public List<Drug> getAllDrugs() {
        return drugRepository.findAll();
    }
    public List<DrugDao> getAllDrugDao(){
        List<DrugDao> drugDaos = new ArrayList<>();
        for (Drug drug : getAllDrugs()){
            drugDaos.add(convertToDrugDao(drug));
        }
        return drugDaos;
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

    public DrugDao convertToDrugDao(Drug d) {
        return new DrugDao(
                d.getDrugName(),
                d.getDrugScientificName(),
                d.getDrugSize(),
                d.getDrugPackaging(),
                d.getDrugPurpose(),
                d.getScheduleButton(),
                d.getStockButton()
        );
    }

    public void updateDrugData(DrugDto drugDto) {
        Drug drug = drugRepository.findByDrugScientificName(drugDto.getDrugScientificName());
        drug.setDrugName(drugDto.getDrugName());
        drug.setDrugPackaging(drugDto.getDrugPackaging());
        drug.setDrugPurpose(drugDto.getDrugPurpose());
        drug.setDrugSize(drugDto.getDrugSize());
        drug.setDrugScientificName(drugDto.getDrugScientificName());
        drug.setPatient(patientRepository.findById(drugDto.getPatientId()).get());
        drugRepository.save(drug);
    }

    public void updateDrugData(Schedule schedule) {
        Drug drug = drugRepository.findById(schedule.getDrug().getId()).get();
        drug.setDrugName(schedule.getDrug().getDrugName());
        drug.setDrugPackaging(schedule.getDrug().getDrugPackaging());
        drug.setDrugPurpose(schedule.getDrug().getDrugPurpose());
        drug.setDrugSize(schedule.getDrug().getDrugSize());
        drug.setDrugScientificName(schedule.getDrug().getDrugScientificName());
        drugRepository.save(drug);
    }

    public DrugDao getDrugById(Long id) {
        DrugDao drugDao = new DrugDao(
                drugRepository.findById(id).get().getDrugName(),
                drugRepository.findById(id).get().getDrugScientificName(),
                drugRepository.findById(id).get().getDrugSize(),
                drugRepository.findById(id).get().getDrugPackaging(),
                drugRepository.findById(id).get().getDrugPurpose(),
                drugRepository.findById(id).get().getScheduleButton(),
                drugRepository.findById(id).get().getStockButton()
        );
        return drugDao;
    }

    public List<DrugDao> convertToDrugListDao(List<Drug> drugsForPatient) {
        List<DrugDao> drugDaos = new ArrayList<>();
        for (Drug drug : drugsForPatient){
            drugDaos.add(convertToDrugDao(drug));
        }
        return  drugDaos;
    }


    public DrugDao convertDtoToDao(DrugDto drugDto) {
        DrugDao drugDao =  new DrugDao(
                drugDto.getDrugName(),
                drugDto.getDrugScientificName(),
                drugDto.getDrugSize(),
                drugDto.getDrugPackaging(),
                drugDto.getDrugPurpose(),
                drugDto.getStockButton(),
                drugDto.getStockButton()
        );
        return drugDao;
    }

    public boolean checkIfDrugExists(DrugDto drugDto) {
        List<Drug> drugList = drugRepository.findByPatientId(drugDto.getPatientId());
        for (Drug drug : drugList){
            if (drug.getDrugScientificName() == drugDto.getDrugScientificName()){
                return true;
            }
        }
        return false;
    }
}
