package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.DrugDtoDao.DrugDTO;
import com.example.MedicalWebInput.Data.PatientDto.*;
import com.example.MedicalWebInput.Models.*;
import com.example.MedicalWebInput.Repository.*;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.time.LocalDateTime;

@Service
public class PatientService {
    private final PatientRepository patientRepository;
    private final ModelMapper modelMapper;

    public PatientService(PatientRepository patientRepository, ModelMapper modelMapper) {
        this.patientRepository = patientRepository;
        this.modelMapper = modelMapper;
    }

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public boolean checkForPatient(Long id) {
        return patientRepository.findById(id).isPresent();
    }

    public boolean checkEmailForPatient(String email) {
        return patientRepository.findByEmail(email).isPresent();
    }

    public PatientDTO deletePatientById(Long id) {
        Patient patient = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        patientRepository.deleteById(id);
        return new PatientDTO(patient.getFirstName(), patient.getLastName(), patient.getEmail());
    }

    public PatientDTO addNewPatient(CreatePatientDTO createPatientDTO) {
        // First mapping: CreatePatientDTO -> Patient
        Patient patient = modelMapper.map(createPatientDTO, Patient.class);

        // Save the patient
        Patient savedPatient = patientRepository.save(patient);

        // Second mapping: Patient -> PatientDTO
        PatientDTO patientDTO = modelMapper.map(savedPatient, PatientDTO.class);
        patientDTO.setId(savedPatient.getId());

        patientDTO.setProfilePhoto(null);
        return patientDTO;
    }

    public List<PatientDTO> convertToPatientDto(List<Patient> allPatients) {
        return modelMapper.map(allPatients, new TypeToken<List<PatientDTO>>() {
        }.getType());
    }

    public Patient convertToPatient(CreatePatientDTO patientDto) {
        return modelMapper.map(patientDto, Patient.class);
    }

    public boolean verifyLogin(PatientLoginDTO patientLoginDto) {
        String passwordConfirm = getPassword(patientLoginDto.getEmail());
        if (passwordConfirm.isEmpty()) {
            return false;
        }
        return passwordConfirm.equals(patientLoginDto.getPassword());
    }

    private String getPassword(String email) {
        Optional<Patient> patient = patientRepository.findByEmail(email);
        if (patient.isPresent()) {
            return patient.get().getPassword();
        }
        return "";
    }

    public PatientDTO getPatientByEmail(String email) {
        Patient p = patientRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Patient not found"));
        PatientDTO dto = modelMapper.map(p, PatientDTO.class);
        dto.setId(p.getId());
        return dto;
    }

    public String setNewPassword() {
        String alphabet = "a b c d e f g h i j k l m n o p q r s t u v w x y z 1 2 3 4 5 6 7 8 9 0 - = [ ] ; ' \\ ; / , < >";
        String[] alpha = alphabet.split(" ");
        String[] password = new String[10];
        Random random = new Random();

        for (int x = 0; x < password.length; x++) {
            password[x] = alpha[random.nextInt(alpha.length)];
        }
        return String.join("", password);
    }

    public void changePassword(String email, String password) {
        Patient patient = patientRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        patient.setPassword(password);
        patientRepository.save(patient);
    }

    public PatientDTO getPatientById(Long id) {
        Patient p = patientRepository.findById(id).orElseThrow(() -> new RuntimeException("Patient not found"));
        PatientDTO dto = modelMapper.map(p, PatientDTO.class);
        dto.setId(p.getId());
        return dto;
    }

    public PatientDTO updatePatientDetails(CreatePatientDTO patientDto) {
        Patient patient = patientRepository.findByEmail(patientDto.getEmail())
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        patient.setPassword(patientDto.getPassword());
        patient.setFirstName(patientDto.getFirstName());
        patient.setLastName(patientDto.getLastName());

        patientRepository.save(patient);
        return modelMapper.map(patient, PatientDTO.class);
    }

    public boolean isValidPatientData(CreatePatientDTO patientDto) {
        return patientDto.getEmail() != null && !patientDto.getEmail().isEmpty() &&
                patientDto.getPassword() != null && !patientDto.getPassword().isEmpty() &&
                patientDto.getFirstName() != null && !patientDto.getFirstName().isEmpty() &&
                patientDto.getLastName() != null && !patientDto.getLastName().isEmpty();
    }

    public Patient resetPassword(String email) {
        Patient patient = patientRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        String password = setNewPassword();
        changePassword(email, password);
        return patient;
    }

    public void recordLogoutTime(String email) {
        Patient patient = patientRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Patient not found"));
        patient.setLogoutTime(LocalDateTime.now());
        patientRepository.save(patient);
    }
}
