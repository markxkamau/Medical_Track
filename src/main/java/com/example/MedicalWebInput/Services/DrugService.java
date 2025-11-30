package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.DrugDtoDao.CreateDrugDTO;
import com.example.MedicalWebInput.Data.DrugDtoDao.DrugDTO;

import java.util.List;

public interface DrugService {
    List<DrugDTO> getAllDrugs();

    DrugDTO getDrugById(Long id);

    DrugDTO addNewDrug(CreateDrugDTO createDrugDTO);

    DrugDTO updateDrugDetails(Long id, CreateDrugDTO createDrugDTO);

    void deleteDrugById(Long id);
}
