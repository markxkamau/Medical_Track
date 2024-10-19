package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.PatientDto.BasicPatientDto;
import com.example.MedicalWebInput.Data.PatientDto.PatientDao;
import com.example.MedicalWebInput.Data.TestDto.CreateTestDto;
import com.example.MedicalWebInput.Data.TestDto.TestDao;
import com.example.MedicalWebInput.Models.Patient;
import com.example.MedicalWebInput.Services.PatientService;
import com.example.MedicalWebInput.Services.ScheduleService;
import com.example.MedicalWebInput.Services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.util.List;

@Controller
@RequestMapping("/medical")
public class TestController {
    @Autowired
    private TestService testService;

    //    *************************************************************************
//    GetMappings
//    =========================================================================
    @GetMapping("/all_tests")
    public ResponseEntity<List<TestDao>> getAllTests() {
        return ResponseEntity.ok(testService.convertTestToDao(testService.getAllTests()));
    }

    @GetMapping("/test/{patientId}")
    public ResponseEntity<List<TestDao>> addNewTest(@PathVariable Long patientId) {
        return ResponseEntity.ok(testService.getTestByPatientId(patientId));
    }

    //    *************************************************************************
//    PostMappings
//    =========================================================================

    @PostMapping("/new_test")
    public ResponseEntity<TestDao> addNewTestInfo(@RequestBody CreateTestDto createTestDto) {
        if (!testService.checkBloodPressure(createTestDto.getBloodPressure())) {
            return null;
        }
        if (!testService.checkOxygen(createTestDto.getOxygen())) {
            return null;
        }
        if (!testService.checkBloodSugar(createTestDto.getBloodSugar())) {
            return null;
        }
        testService.addNewTest(createTestDto);
        return ResponseEntity.ok(testService.convertDtotoDao(createTestDto));
    }

    //    *************************************************************************
//    PutMappings
//    =========================================================================

    @PutMapping("/new_test")
    public ResponseEntity<TestDao> updatePatientTestInfo(@RequestBody CreateTestDto createTestDto) {
        if (testService.getTestByPatientId(createTestDto.getPatientId()) != null) {
            testService.updateTestByPatient(createTestDto);
            return ResponseEntity.ok(testService.convertDtotoDao(createTestDto));
        }
        return ResponseEntity.ok(testService.getTestById(createTestDto.getId()));
    }

    //    *************************************************************************
//    DeleteMappings
//    =========================================================================
    @DeleteMapping("/test/{id}")
    public ResponseEntity<TestDao> deleteTestById(@PathVariable Long id){
        return ResponseEntity.ok(testService.deleteTestById(id));
    }
    @DeleteMapping("/test/{patientId}")
    public ResponseEntity<List<TestDao>> deleteTestByPatientId(@PathVariable Long patientId){

        return ResponseEntity.ok(testService.deleteTestsByPatientId(patientId));

    }
}

