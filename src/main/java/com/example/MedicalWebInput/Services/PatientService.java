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

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DrugService drugService;
    @Autowired
    private DrugRepository drugRepository;
    @Autowired
    private DrugStockRepository drugStockRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;
    @Autowired
    private ModelMapper modelMapper;


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
        Patient patient = patientRepository.findById(id).get();
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

        patientDTO.setProfilePhoto(null);
        return patientDTO;
    }

    public List<PatientDTO> convertToPatientDto(List<Patient> allPatients) {
        // Methode 1:
        return modelMapper.map(allPatients, new TypeToken<List<PatientDTO>>() {}.getType());

        //Method 2:
//        return allPatients.stream()
//                .map(patient -> modelMapper.map(patient, PatientDTO.class))
//                .collect(Collectors.toList());

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
        return modelMapper.map(patientRepository.findByEmail(email).get(), PatientDTO.class);
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
        Patient patient = patientRepository.findByEmail(email).get();
        patient.setPassword(String.join("", password));

        patientRepository.save(patient);
    }

    public PatientDTO getPatientById(Long id) {
        return modelMapper.map(patientRepository.findById(id).get(), PatientDTO.class);
    }

    public PatientDTO updatePatientDetails(CreatePatientDTO patientDto) {
        Patient patient = patientRepository.findByEmail(patientDto.getEmail()).get();
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
        Patient patient = patientRepository.findByEmail(email).get();
        String password = setNewPassword();
        changePassword(email, password);
        return patient;
    }
}
