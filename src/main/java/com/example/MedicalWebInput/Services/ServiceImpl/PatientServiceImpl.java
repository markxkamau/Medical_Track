package com.example.MedicalWebInput.Services.ServiceImpl;

import com.example.MedicalWebInput.Data.PatientDto.CreatePatientDTO;
import com.example.MedicalWebInput.Data.PatientDto.PatientDTO;
import com.example.MedicalWebInput.Models.Patient;
import com.example.MedicalWebInput.Repository.PatientRepository;
import com.example.MedicalWebInput.Services.PatientService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PatientServiceImpl implements PatientService {
    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public List<PatientDTO> getAllPatients() {
        return patientRepository.findAll().stream()
                .map(patient -> modelMapper.map(patient, PatientDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public PatientDTO getPatientById(Long id) {
        return modelMapper.map(patientRepository.findById(id).get(), PatientDTO.class);
    }

    @Override
    public PatientDTO addNewPatient(CreatePatientDTO createPatientDTO) {
        Patient patient = modelMapper.map(createPatientDTO, Patient.class);
        return modelMapper.map(patientRepository.save(patient), PatientDTO.class);
    }

    @Override
    public PatientDTO updatePatientDetails(Long id, CreatePatientDTO createPatientDTO) {
        Patient patient = patientRepository.findById(id).get();
        patient.setFirstName(createPatientDTO.getFirstName());
        patient.setLastName(createPatientDTO.getLastName());
        patient.setCondition(createPatientDTO.getCondition());
        return modelMapper.map(patientRepository.save(patient), PatientDTO.class);
    }

    @Override
    public void deletePatientById(Long id) {
        patientRepository.deleteById(id);
    }
}
