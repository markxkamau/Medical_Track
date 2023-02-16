package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.TestDto.CreateTestDto;
import com.example.MedicalWebInput.Models.Test;
import com.example.MedicalWebInput.Repository.PatientRepository;
import com.example.MedicalWebInput.Repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private PatientRepository patientRepository;

    public ResponseEntity<List<Test>> getAllTests() {
        return ResponseEntity.ok(testRepository.findAll());
    }

    public boolean chcekBloodPressure(int bloodPressure) {
        if (bloodPressure < 0 || bloodPressure > 200) {
            return false;
        }
        return true;
    }

    public boolean checkOxygen(int oxygen) {
        if (oxygen < 0 || oxygen > 100) {
            return false;
        }
        return true;
    }

    public boolean checkBloodSugar(int bloodSugar) {
        if (bloodSugar < 0 || bloodSugar > 300) {
            return false;
        }
        return true;
    }

    public void addNewTest(CreateTestDto createTest) {
        Test test = new Test(
                createTest.getId(),
                createTest.getBloodPressure(),
                createTest.getWeight(),
                createTest.getOxygen(),
                createTest.getBloodSugar(),
                new Date(),
                patientRepository.findById(createTest.getPatientId()).get()
        );
        testRepository.save(test);
    }
}
