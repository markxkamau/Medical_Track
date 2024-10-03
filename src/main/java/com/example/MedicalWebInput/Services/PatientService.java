package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.DrugDtoDao.DrugDao;
import com.example.MedicalWebInput.Data.DrugDtoDao.DrugDto;
import com.example.MedicalWebInput.Data.PatientDto.*;
import com.example.MedicalWebInput.Models.*;
import com.example.MedicalWebInput.Repository.*;
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
    @Autowired
    private DrugStockRepository drugStockRepository;
    @Autowired
    private ScheduleRepository scheduleRepository;

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

    public String setNewPassword() {
        String alphabet = "a b c d e f g h i j k l m n o p q r s t u v w x y z 1 2 3 4 5 6 7 8 9 0 - = [ ] ; ' \\ ; / , < >";
        String alpha[] = alphabet.split(" ");
        String password[] = new String[10];
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

    public BasicPatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id).get();
        BasicPatientDto basicPatientDto = convertToBasicPatientDto(patient);
        return basicPatientDto;
    }

    public PatientDao getPatientInfoById(Long id) {
        Patient patient = patientRepository.findById(id).get();
        PatientDao patientDao = convertToPatientDao(patient);
        return patientDao;
    }
    private PatientDao convertToPatientDao(Patient patient) {
        return new PatientDao(
                patient.getName(),
                patient.getEmail(),
                patient.getDrugs().size(),
                patient.getCondition(),
                patient.isPhotoAvailable()
        );
    }
    private BasicPatientDto convertToBasicPatientDto(Patient patient) {
        return new BasicPatientDto(
                patient.getId(),
                patient.getName(),
                patient.getEmail(),
                patient.getDrugs().size(),
                patient.getCondition(),
                patient.isPhotoAvailable()
        );
    }

    public List<DrugDao> getDrugByPatientId(Long patientId) {
        List<Drug> drugs;
        List<DrugDao> drugDaos = new ArrayList<>();
        drugs = drugService.getDrugsForPatient(patientId);
        for (Drug d : drugs) {
            DrugDao drugDao = drugService.convertToDrugDao(d);
            drugDaos.add(drugDao);
        }
        return drugDaos;
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

    public void deleteDrugById(Long drugId) {
        DrugStock drugStock = drugStockRepository.findByDrugId(drugId);
        if (drugStock != null) {
            drugStockRepository.deleteById(drugStock.getId());

        }
        Long patientId = drugRepository.findById(drugId).get().getPatient().getId();
        Schedule schedule = scheduleRepository.findByPatientIdAndDrugId(patientId, drugId);
        if (schedule != null) {
            scheduleRepository.deleteById(schedule.getId());
        }

        drugRepository.deleteById(drugId);
    }

    public Schedule getDrugAndScheduleInfo(Long drugId) {
        DrugDto drugDto = getDrugInfo(drugId);
        Long patientId = drugDto.getPatientId();
        return scheduleRepository.findByPatientIdAndDrugId(patientId, drugId);
    }

    public boolean checkDrug(Long id) {
        List<Drug> drug = drugRepository.findByPatientId(id);
        if (drug.isEmpty()) {
            return false;
        }
        return true;
    }
}
