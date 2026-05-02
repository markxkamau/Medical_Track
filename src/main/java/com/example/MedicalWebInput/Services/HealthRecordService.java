package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.HealthRecordDto.CreateHealthRecordDTO;
import com.example.MedicalWebInput.Data.HealthRecordDto.HealthRecordDTO;
import com.example.MedicalWebInput.Models.HealthRecord;
import com.example.MedicalWebInput.Repository.HealthRecordRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class HealthRecordService {
    @Autowired
    private HealthRecordRepository hrRepo;
    @Autowired
    private ModelMapper modelMapper;

    public List<HealthRecordDTO> getAllRecords() {
        return hrRepo.findAll().stream().map(h -> modelMapper.map(h, HealthRecordDTO.class))
                .collect(Collectors.toList());
    }

    public HealthRecordDTO createRecord(CreateHealthRecordDTO dto) {
        HealthRecord record = modelMapper.map(dto, HealthRecord.class);
        HealthRecord saved = hrRepo.save(record);
        return modelMapper.map(saved, HealthRecordDTO.class);
    }
}