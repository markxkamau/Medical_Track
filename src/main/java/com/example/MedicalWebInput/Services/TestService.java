package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.TestDto.CreateTestDto;
import com.example.MedicalWebInput.Models.Test;
import com.example.MedicalWebInput.Repository.PatientRepository;
import com.example.MedicalWebInput.Repository.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TestService {
    @Autowired
    private TestRepository testRepository;
    @Autowired
    private PatientRepository patientRepository;

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public boolean checkBloodPressure(int bloodPressure) {
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


    public Test getTestById(Long id) {
        Test test = testRepository.findById(id).get();

        return test;
    }



    public void updateTestByPatient(CreateTestDto createTestDto) {

    }

    public void deleteTestById(Long id) {
        testRepository.deleteById(id);
    }

    public void deleteTestsByPatientId(Long patientId) {
        List<Test> tests = getTestsByPatientId(patientId);
        for (Test test: tests) {
            testRepository.deleteById(test.getId());
        }
    }

    private List<Test> getTestsByPatientId(Long patientId) {
        return testRepository.findTestByPatientId(patientId);
    }
}
