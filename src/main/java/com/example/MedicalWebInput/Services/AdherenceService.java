package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.AdherenceDto.CreateAdherenceDTO;
import com.example.MedicalWebInput.Data.AdherenceDto.AdherenceDTO;
import com.example.MedicalWebInput.Models.Adherence;
import com.example.MedicalWebInput.Repository.AdherenceRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AdherenceService {
    @Autowired
    private AdherenceRepository adherenceRepo;
    @Autowired
    private ModelMapper modelMapper;

    public List<AdherenceDTO> getAllAdherence() {
        return adherenceRepo.findAll().stream().map(a -> modelMapper.map(a, AdherenceDTO.class))
                .collect(Collectors.toList());
    }

    public AdherenceDTO createAdherence(CreateAdherenceDTO dto) {
        Adherence adherence = modelMapper.map(dto, Adherence.class);
        Adherence saved = adherenceRepo.save(adherence);
        return modelMapper.map(saved, AdherenceDTO.class);
    }
}