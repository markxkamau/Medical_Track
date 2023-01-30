package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.DrugDto.DrugDto;
import com.example.MedicalWebInput.Data.PatientDto.CreatePatientDto;
import com.example.MedicalWebInput.Data.PatientDto.PatientDto;
import com.example.MedicalWebInput.Data.PatientDto.PatientLoginDto;
import com.example.MedicalWebInput.Models.Drug;
import com.example.MedicalWebInput.Models.Patient;
import com.example.MedicalWebInput.Models.Test;
import com.example.MedicalWebInput.Repository.DrugRepository;
import com.example.MedicalWebInput.Repository.PatientRepository;
import com.example.MedicalWebInput.Repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
public class PatientService {
    @Autowired
    private PatientRepository patientRepository;
    @Autowired
    private DrugService drugService;
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private DrugRepository drugRepository;

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public boolean checkForPatient(String email) {
        if (!patientRepository.findByEmail(email).isEmpty()) {
            return true;
        }
        return false;
    }

    public void addNewPatient(Patient patient) {
        patientRepository.save(patient);
    }

    public List<PatientDto> convertToPatientDto(List<Patient> allPatients) {
        List<PatientDto> patientDtos = new ArrayList<>();
        for (Patient item : allPatients) {
            patientDtos.add(new PatientDto(
                    item.getId(),
                    item.getName(),
                    item.getEmail(),
                    item.getDrugs().size(),
                    item.getCondition(),
                    item.getPassword()
            ));

        }
        return patientDtos;
    }

    public boolean checkPassword(String password, String confirmPassword) {
        if (password.equals(confirmPassword)) {
            return true;
        }
        return false;
    }

    public Patient convertToPatient(CreatePatientDto patientDto) {
        String patientName = patientDto.getFirstName() + " " + patientDto.getLastName();
        List<Drug> drugs = drugService.getListForPatient(patientDto.getId());
        Patient patient = new Patient(
                patientDto.getId(),
                patientName,
                patientDto.getEmail(),
                patientDto.getPassword(),
                patientDto.getCondition(),
                drugs
        );
        return patient;
    }

    public boolean verifyLogin(PatientLoginDto patientLoginDto) {
        String passwordConfirm = getPassword(patientLoginDto.getEmail());
        if (passwordConfirm.isEmpty()) {
            return false;
        }
        if (!passwordConfirm.equals(patientLoginDto.getPassword())) {
            return false;
        }
        return true;
    }

    private String getPassword(String email) {
        Optional<Patient> patient = patientRepository.findByEmail(email);
        if (!patient.isEmpty()) {
            return patient.get().getPassword();
        }
        return "";
    }

    public Patient getPatientByEmail(String email) {
        return patientRepository.findByEmail(email).get();
    }

    public String setNewPassword(String email) {
        String alphabet = "a b c d e f g h i j k l m n o p q r s t u v w x y z 1 2 3 4 5 6 7 8 9 0 - = [ ] ; ' \\ ; / , < >";
        String alpha[] = alphabet.split(" ");
        String password[] = new String[10];
        Random random = new Random();

        for (int x = 0; x < password.length; x++) {
            password[x] = alpha[random.nextInt(alpha.length)];
        }
        Patient patient = patientRepository.findByEmail(email).get();
        patient.setPassword(String.join("", password));

        patientRepository.save(patient);

        return String.join("", password);
    }

    public PatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id).get();
        PatientDto patientDto = convertToPatientDto(patient);
        return patientDto;
    }

    private PatientDto convertToPatientDto(Patient patient) {
        return new PatientDto(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getDrugs().size(),
                patient.getCondition(),
                patient.getPassword()
        );
    }

    public List<DrugDto> getDrugByPatientId(Long patientId) {
        List<Drug> drugs;
        List<DrugDto> drugDtos = new ArrayList<>();
        drugs = drugService.getDrugsForPatient(patientId);
        for (Drug d : drugs) {
            DrugDto drugDto = drugService.convertToDrugDto(d);
            drugDtos.add(drugDto);
        }
        return drugDtos;
    }

    public List<Test> getAllPatientTests(Long patientId) {
        return testRepository.findTestByPatientId(patientId);
    }

    public DrugDto getDrugInfo(Long drugId) {
        Drug drug = drugRepository.findById(drugId).get();
        return new DrugDto(
                drug.getId(),
                drug.getDrugName(),
                drug.getDrugScientificName(),
                drug.getDrugSize(),
                drug.getDrugPackaging(),
                drug.getDrugPurpose(),
                drug.getPatient().getId()
        );
    }
}
