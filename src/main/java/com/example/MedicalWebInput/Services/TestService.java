package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.PatientDto.BasicPatientDto;
import com.example.MedicalWebInput.Data.TestDto.CreateTestDto;
import com.example.MedicalWebInput.Data.TestDto.TestDao;
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

    public void updateTestByPatientEmail(TestDao testDao) {
        Test test = testRepository.findTestByPatientEmail(testDao.getEmail());
        test.setBloodPressure(testDao.getBloodPressure());
        test.setWeight(testDao.getWeight());
        test.setOxygen(testDao.getOxygen());
        test.setBloodSugar(testDao.getBloodSugar());
        test.setTestDate(testDao.getTestDate());
        testRepository.save(test);
    }

    public TestDao getTestById(Long id) {
        Test test = testRepository.findById(id).get();
        TestDao testDao = new TestDao(
                test.getPatient().getEmail(),
                test.getBloodPressure(),
                test.getWeight(),
                test.getOxygen(),
                test.getBloodSugar(),
                test.getTestDate()
        );

        return testDao;
    }

    public TestDao getTestByPatientEmail(BasicPatientDto basicPatientDto) {
        Test test = testRepository.findTestByPatientEmail(basicPatientDto.getEmail());
        return getTestById(test.getId());
    }

    public List<TestDao> convertTestToDao(List<Test> allTests) {
        List<TestDao> testDaos = new ArrayList<>();

        for (Test test : allTests) {
            testDaos.add(new TestDao(
                    test.getPatient().getEmail(),
                    test.getBloodPressure(),
                    test.getWeight(),
                    test.getOxygen(),
                    test.getBloodSugar(),
                    test.getTestDate()
            ));
        }
        return testDaos;
    }

    public List<TestDao> getTestByPatientId(Long patientId) {
        return convertTestToDao(testRepository.findTestByPatientId(patientId));
    }

    public TestDao convertDtotoDao(CreateTestDto createTestDto) {
        Test test = testRepository.findTestByPatientEmail(patientRepository.findById(createTestDto.getPatientId()).get().getEmail());
        TestDao testDao = new TestDao(
                test.getPatient().getEmail(),
                test.getBloodPressure(),
                test.getWeight(),
                test.getOxygen(),
                test.getBloodSugar(),
                test.getTestDate()
        );
        return testDao;
    }

    public void updateTestByPatient(CreateTestDto createTestDto) {

    }

    public TestDao deleteTestById(Long id) {
        TestDao testDao = getTestById(id);
        testRepository.deleteById(id);
        return testDao;
    }

    public List<TestDao> deleteTestsByPatientId(Long patientId) {
        List<TestDao> testDaos = getTestByPatientId(patientId);
        List<Test> tests = getTestsByPatientId(patientId);
        for (Test test: tests) {
            testRepository.deleteById(test.getId());
        }
        return testDaos;
    }

    private List<Test> getTestsByPatientId(Long patientId) {
        return testRepository.findTestByPatientId(patientId);
    }
}
