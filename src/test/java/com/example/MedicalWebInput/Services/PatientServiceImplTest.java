package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.PatientDto.CreatePatientDTO;
import com.example.MedicalWebInput.Data.PatientDto.PatientDTO;
import com.example.MedicalWebInput.Models.Patient;
import com.example.MedicalWebInput.Repository.PatientRepository;
import com.example.MedicalWebInput.Services.ServiceImpl.PatientServiceImpl;
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
class PatientServiceImplTest {

    @InjectMocks
    private PatientServiceImpl patientService;

    @Mock
    private PatientRepository patientRepository;

    @Mock
    private ModelMapper modelMapper;

    @Test
    void getAllPatients() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("John");
        patient.setLastName("Doe");

        when(patientRepository.findAll()).thenReturn(Collections.singletonList(patient));

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setFirstName("John");

        when(modelMapper.map(patient, PatientDTO.class)).thenReturn(patientDTO);

        List<PatientDTO> result = patientService.getAllPatients();

        assertEquals(1, result.size());
        assertEquals("John", result.get(0).getFirstName());
    }

    @Test
    void getPatientById() {
        Patient patient = new Patient();
        patient.setId(1L);
        patient.setFirstName("John");
        patient.setLastName("Doe");

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setFirstName("John");

        when(modelMapper.map(patient, PatientDTO.class)).thenReturn(patientDTO);

        PatientDTO result = patientService.getPatientById(1L);

        assertEquals("John", result.getFirstName());
    }

    @Test
    void addNewPatient() {
        CreatePatientDTO createPatientDTO = new CreatePatientDTO();
        createPatientDTO.setFirstName("John");

        Patient patient = new Patient();
        patient.setFirstName("John");

        when(modelMapper.map(createPatientDTO, Patient.class)).thenReturn(patient);
        when(patientRepository.save(patient)).thenReturn(patient);

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setFirstName("John");

        when(modelMapper.map(patient, PatientDTO.class)).thenReturn(patientDTO);

        PatientDTO result = patientService.addNewPatient(createPatientDTO);

        assertEquals("John", result.getFirstName());
    }

    @Test
    void updatePatientDetails() {
        CreatePatientDTO createPatientDTO = new CreatePatientDTO();
        createPatientDTO.setFirstName("John");

        Patient patient = new Patient();
        patient.setFirstName("Jane");

        when(patientRepository.findById(1L)).thenReturn(Optional.of(patient));
        when(patientRepository.save(patient)).thenReturn(patient);

        PatientDTO patientDTO = new PatientDTO();
        patientDTO.setFirstName("John");

        when(modelMapper.map(patient, PatientDTO.class)).thenReturn(patientDTO);

        PatientDTO result = patientService.updatePatientDetails(1L, createPatientDTO);

        assertEquals("John", result.getFirstName());
    }

    @Test
    void deletePatientById() {
        patientService.deletePatientById(1L);
        verify(patientRepository, times(1)).deleteById(1L);
    }
}
