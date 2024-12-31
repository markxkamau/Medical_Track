package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.TestDto.CreateTestDto;
import com.example.MedicalWebInput.Services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/medical/api")
public class TestController {
    @Autowired
    private TestService testService;

    //    *************************************************************************
    //    GetMappings
    //    =========================================================================
    @GetMapping("/all_tests")
    public ResponseEntity<List<?>> getAllTests() {
        return ResponseEntity.ok(null);
    }

    @GetMapping("/test/{patientId}")
    public ResponseEntity<List<?>> addNewTest(@PathVariable Long patientId) {
        return ResponseEntity.ok(null);
    }

    //    *************************************************************************
    //    PostMappings
    //    =========================================================================

    @PostMapping("/new_test")
    public ResponseEntity<?> addNewTestInfo(@RequestBody CreateTestDto createTestDto) {
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
        return ResponseEntity.ok(null);
    }

    //    *************************************************************************
    //    PutMappings
    //    =========================================================================

    @PutMapping("/new_test")
    public ResponseEntity<?> updatePatientTestInfo(@RequestBody CreateTestDto createTestDto) {
        return ResponseEntity.ok(testService.getTestById(createTestDto.getId()));
    }

    //    *************************************************************************
    //    DeleteMappings
    //    =========================================================================
    @DeleteMapping("/test/{id}")
    public ResponseEntity<?> deleteTestById(@PathVariable Long id){
        return ResponseEntity.ok("Successfully Deleted");
    }
    @DeleteMapping("/test/{patientId}")
    public ResponseEntity<List<?>> deleteTestByPatientId(@PathVariable Long patientId){

        return ResponseEntity.ok(null);

    }
}

