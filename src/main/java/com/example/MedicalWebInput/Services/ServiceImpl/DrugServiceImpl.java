package com.example.MedicalWebInput.Services.ServiceImpl;

import com.example.MedicalWebInput.Data.DrugDtoDao.CreateDrugDTO;
import com.example.MedicalWebInput.Data.DrugDtoDao.DrugDTO;
import com.example.MedicalWebInput.Models.Drug;
import com.example.MedicalWebInput.Repository.DrugRepository;
import com.example.MedicalWebInput.Services.DrugService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrugServiceImpl implements DrugService {
    @Autowired
    private DrugRepository drugRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<DrugDTO> getAllDrugs() {
        return drugRepository.findAll().stream()
                .map(drug -> modelMapper.map(drug, DrugDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public DrugDTO getDrugById(Long id) {
        return modelMapper.map(drugRepository.findById(id).get(), DrugDTO.class);
    }

    @Override
    public DrugDTO addNewDrug(CreateDrugDTO createDrugDTO) {
        Drug drug = modelMapper.map(createDrugDTO, Drug.class);
        return modelMapper.map(drugRepository.save(drug), DrugDTO.class);
    }

    @Override
    public DrugDTO updateDrugDetails(Long id, CreateDrugDTO createDrugDTO) {
        Drug drug = drugRepository.findById(id).get();
        drug.setDrugName(createDrugDTO.getDrugName());
        drug.setDrugPurpose(createDrugDTO.getDrugPurpose());
        drug.setDrugSize(createDrugDTO.getDrugSize());
        drug.setMedNumber(createDrugDTO.getMedNumber());
        return modelMapper.map(drugRepository.save(drug), DrugDTO.class);
    }

    @Override
    public void deleteDrugById(Long id) {
        drugRepository.deleteById(id);
    }
}
