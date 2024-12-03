package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.DrugDtoDao.DrugDao;
import com.example.MedicalWebInput.Data.DrugDtoDao.DrugDto;
import com.example.MedicalWebInput.Data.PatientDto.*;
import com.example.MedicalWebInput.Models.*;
import com.example.MedicalWebInput.Repository.*;
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

    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    public boolean checkForPatient(String email) {
        return patientRepository.findByEmail(email).isPresent();
    }

    public Patient addNewPatient(Patient patient) {
        return patientRepository.save(patient);
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
        return password.equals(confirmPassword);
    }

    public Patient convertToPatient(CreatePatientDto patientDto) {
        String patientName = patientDto.getFirstName() + " " + patientDto.getLastName();
        List<Drug> drugs = drugService.getListForPatient(patientDto.getEmail());
        return new Patient(
                patientName,
                patientDto.getEmail(),
                patientDto.getPassword(),
                patientDto.getCondition(),
                drugs
        );
    }

    public boolean verifyLogin(PatientLoginDto patientLoginDto) {
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

    public Patient getPatientByEmail(String email) {
        return patientRepository.findByEmail(email).get();
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

    public BasicPatientDto getPatientById(Long id) {
        Patient patient = patientRepository.findById(id).get();
        return convertToBasicPatientDto(patient);
    }

    public PatientDao getPatientInfoById(Long id) {
        Patient patient = patientRepository.findById(id).get();
        return convertToPatientDao(patient);
    }

    public PatientDao convertToPatientDao(Patient patient) {
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
                patient.getName(),
                patient.getEmail(),
                patient.getDrugs().size(),
                patient.getCondition(),
                patient.isPhotoAvailable()
        );
    }

    public List<DrugDao> getDrugByPatientId(Long patientId) {
        List<Drug> drugs;
        List<DrugDao> drugDaos = new LinkedList<>();
        drugs = drugService.getDrugsForPatient(patientId);
        for (Drug d : drugs) {
            DrugDao drugDao = drugService.convertToDrugDao(d);
            drugDaos.add(drugDao);
        }
        return drugDaos;
    }

    public DrugDto getDrugInfo(Long drugId) {
        Drug drug = drugRepository.findById(drugId).get();
        return new DrugDto(
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


    public Patient updatePatientDetails(CreatePatientDto patientDto) {
        Patient patient = getPatientByEmail(patientDto.getEmail());
        patient.setCondition(patientDto.getCondition());
        patient.setPassword(patientDto.getPassword());
        patient.setName(patientDto.getFirstName() + " " + patientDto.getLastName());

        patientRepository.save(patient);
        return patient;
    }

    public PatientDao deletePatientById(Long id) {
        PatientDao patientDao = getPatientInfoById(id);
        patientRepository.deleteById(id);
        return patientDao;
    }

    public void deleteDrugsByPatientId(Long patientId) {
        List<DrugDao> drugDaos = getDrugByPatientId(patientId);
        for (DrugDao drugDao: drugDaos){
            drugRepository.deleteById(drugRepository.findByDrugScientificName(drugDao.getDrugScientificName()).getId());
        }
    }
}
