package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.Test.CreateTestDto;
import com.example.MedicalWebInput.Services.ScheduleService;
import com.example.MedicalWebInput.Services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Controller
@RequestMapping("test")
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private ScheduleService scheduleService;

    //    *************************************************************************
//    GetMappings
//    =========================================================================
    @GetMapping("all")
    public ResponseEntity getAllTests() {
        return ResponseEntity.ok(testService.getAllTests());
    }

    @GetMapping("new_test")
    public String addNewTest(@NotNull Model model) {
        model.addAttribute("test_info", new CreateTestDto());
        return "Test/test_input";
    }

    //    *************************************************************************
//    PostMappings
//    =========================================================================
    @PostMapping("new_test")
    public String addNewTestInfo(@NotNull Model model, @ModelAttribute CreateTestDto createTest) {
        model.addAttribute("test_info", createTest);
        if (!testService.chcekBloodPressure(createTest.getBloodPressure())) {
            model.addAttribute("pressure_error","Blood Pressure input out of normal range");
            return "Test/test_input";
        }
        if (!testService.checkOxygen(createTest.getOxygen())) {
            model.addAttribute("oxygen_error","O2 Levels out of range");
            return "Test/test_input";
        }
        if (!testService.checkBloodSugar(createTest.getBloodSugar())) {
            model.addAttribute("sugar_error", "Blood Sugar levels out of normal range");
            return "Test/test_input";
        }
        testService.addNewTest(createTest);
        model.addAttribute("patient_data", scheduleService.getPatientById(createTest.getPatientId()));
        model.addAttribute("drug_info", scheduleService.getDrugByPatientId(createTest.getPatientId()));
        model.addAttribute("schedule_info", scheduleService.getScheduleByPatientId(createTest.getPatientId()));
        return "HomePage";
    }
}
