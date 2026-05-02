package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.DrugDtoDao.CreateDrugDTO;
import com.example.MedicalWebInput.Data.DrugDtoDao.DrugDTO;
import com.example.MedicalWebInput.Models.Drug;
import com.example.MedicalWebInput.Repository.DrugRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class DrugService {
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private ModelMapper modelMapper;

    public List<DrugDTO> getAllDrugs() {
        return drugRepository.findAll().stream().map(d -> modelMapper.map(d, DrugDTO.class))
                .collect(Collectors.toList());
    }

    public DrugDTO addNewDrug(CreateDrugDTO dto) {
        Drug drug = modelMapper.map(dto, Drug.class);
        Drug saved = drugRepository.save(drug);
        return modelMapper.map(saved, DrugDTO.class);
    }
}