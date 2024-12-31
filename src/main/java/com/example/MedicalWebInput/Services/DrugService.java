package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.DrugDtoDao.CreateDrugDTO;
import com.example.MedicalWebInput.Data.DrugDtoDao.DrugDTO;
import com.example.MedicalWebInput.Models.Drug;
import com.example.MedicalWebInput.Models.Schedule;
import com.example.MedicalWebInput.Repository.DrugRepository;
import com.example.MedicalWebInput.Repository.PatientRepository;
import com.example.MedicalWebInput.Repository.ScheduleRepository;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
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
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ModelMapper modelMapper;


    public List<Drug> getListForPatient(String patientEmail) {
        List<Drug> drugs = new ArrayList<>();
        for(Schedule e : scheduleRepository.findByPatientId(patientRepository.findByEmail(patientEmail).get().getId())){
            drugs.add(e.getDrug());
        }
        return drugs;
    }


    public List<DrugDTO> getAllDrugs() {
        return modelMapper.map(drugRepository.findAll(), new TypeToken<List<DrugDTO>>() {}.getType());
    }

    public DrugDTO getDrugById(Long drugId) {
        return modelMapper.map(drugRepository.findById(drugId).get(), DrugDTO.class);
    }

    public void checkDataViability(CreateDrugDTO createDrugDTO) {
    }

    public DrugDTO addNewDrug(CreateDrugDTO createDrugDTO) {

        Drug drug = modelMapper.map(createDrugDTO, Drug.class);

        Drug savedDrug = drugRepository.save(drug);

        return modelMapper.map(savedDrug, DrugDTO.class);
    }

    public DrugDTO deleteDrugById(Long id) {
        Drug drug = drugRepository.findById(id).get();
        drugRepository.deleteById(id);
        return modelMapper.map(drug, DrugDTO.class);
    }

    // Helper method to convert dosage from string to float
    private float parseDosageToFloat(String dosage) {
        // Parse dosage from a string like "100mcg" to float (e.g., 100.0)
        if (dosage.endsWith("mcg") || dosage.endsWith("mg")) {
            try {
                return Float.parseFloat(dosage.replaceAll("[^\\d.]", ""));
            } catch (NumberFormatException e) {
                // Handle parsing error
                return 0f;
            }
        }
        return 0f;
    }

//    public void deleteDrugById(Long drugId) {
//        DrugStock drugStock = drugStockRepository.findByDrugId(drugId);
//        if (drugStock != null) {
//            drugStockRepository.deleteById(drugStock.getId());
//
//        }
//
//        //TODO: Clear Schedule of drug deleted by Patient Id
////        Schedule schedule = scheduleRepository.findByPatientIdAndDrugId(patientId, drugId);
////        if (schedule != null) {
////            scheduleRepository.deleteById(schedule.getId());
////        }
//
//        drugRepository.deleteById(drugId);
//    }
}
