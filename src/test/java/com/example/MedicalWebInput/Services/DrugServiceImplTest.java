package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.DrugDtoDao.CreateDrugDTO;
import com.example.MedicalWebInput.Data.DrugDtoDao.DrugDTO;
import com.example.MedicalWebInput.Models.Drug;
import com.example.MedicalWebInput.Repository.DrugRepository;
import com.example.MedicalWebInput.Services.ServiceImpl.DrugServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class DrugServiceImplTest {

    @InjectMocks
    private DrugServiceImpl drugService;

    @Mock
    private DrugRepository drugRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void getAllDrugs() {
        Drug drug = new Drug();
        drug.setId(1L);
        drug.setDrugName("Aspirin");

        when(drugRepository.findAll()).thenReturn(Collections.singletonList(drug));

        DrugDTO drugDTO = new DrugDTO();
        drugDTO.setDrugName("Aspirin");

        when(modelMapper.map(drug, DrugDTO.class)).thenReturn(drugDTO);

        List<DrugDTO> result = drugService.getAllDrugs();

        assertEquals(1, result.size());
        assertEquals("Aspirin", result.get(0).getDrugName());
    }

    @Test
    void getDrugById() {
        Drug drug = new Drug();
        drug.setId(1L);
        drug.setDrugName("Aspirin");

        when(drugRepository.findById(1L)).thenReturn(Optional.of(drug));

        DrugDTO drugDTO = new DrugDTO();
        drugDTO.setDrugName("Aspirin");

        when(modelMapper.map(drug, DrugDTO.class)).thenReturn(drugDTO);

        DrugDTO result = drugService.getDrugById(1L);

        assertEquals("Aspirin", result.getDrugName());
    }

    @Test
    void addNewDrug() {
        CreateDrugDTO createDrugDTO = new CreateDrugDTO();
        createDrugDTO.setDrugName("Aspirin");

        Drug drug = new Drug();
        drug.setDrugName("Aspirin");

        when(modelMapper.map(createDrugDTO, Drug.class)).thenReturn(drug);
        when(drugRepository.save(drug)).thenReturn(drug);

        DrugDTO drugDTO = new DrugDTO();
        drugDTO.setDrugName("Aspirin");

        when(modelMapper.map(drug, DrugDTO.class)).thenReturn(drugDTO);

        DrugDTO result = drugService.addNewDrug(createDrugDTO);

        assertEquals("Aspirin", result.getDrugName());
    }

    @Test
    void updateDrugDetails() {
        CreateDrugDTO createDrugDTO = new CreateDrugDTO();
        createDrugDTO.setDrugName("Aspirin");

        Drug drug = new Drug();
        drug.setDrugName("Ibuprofen");

        when(drugRepository.findById(1L)).thenReturn(Optional.of(drug));
        when(drugRepository.save(drug)).thenReturn(drug);

        DrugDTO drugDTO = new DrugDTO();
        drugDTO.setDrugName("Aspirin");

        when(modelMapper.map(drug, DrugDTO.class)).thenReturn(drugDTO);

        DrugDTO result = drugService.updateDrugDetails(1L, createDrugDTO);

        assertEquals("Aspirin", result.getDrugName());
    }

    @Test
    void deleteDrugById() {
        drugService.deleteDrugById(1L);
        verify(drugRepository, times(1)).deleteById(1L);
    }
}
