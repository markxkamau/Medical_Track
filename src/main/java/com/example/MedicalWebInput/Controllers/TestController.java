package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.PatientDto.BasicPatientDto;
import com.example.MedicalWebInput.Data.PatientDto.PatientDao;
import com.example.MedicalWebInput.Data.TestDto.CreateTestDto;
import com.example.MedicalWebInput.Models.Patient;
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

@Controller
@RequestMapping("/medical")
public class TestController {
    @Autowired
    private TestService testService;
    @Autowired
    private ScheduleService scheduleService;

    //    *************************************************************************
//    GetMappings
//    =========================================================================
    @GetMapping("/all_tests")
    public ResponseEntity getAllTests() {
        return ResponseEntity.ok(testService.getAllTests());
    }

    @GetMapping("/new_test/{id}")
    public String addNewTest(@NotNull Model model, @NotNull HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        if (session == null || session.getAttribute("patient_info") == null) {
            // If the user is not logged in, redirect them to the login page
            return "redirect:/medical/login";
        }
        BasicPatientDto patient = (BasicPatientDto)session.getAttribute("patient_info");
        CreateTestDto testDto = new CreateTestDto();
        testDto.setPatientId(patient.getId());
        model.addAttribute("test_info", testDto);
        return "Test/test_input";
    }

    //    *************************************************************************
//    PostMappings
//    =========================================================================
    @PostMapping("new_test")
    public String addNewTestInfo(@NotNull Model model, @ModelAttribute CreateTestDto createTest) {
        model.addAttribute("test_info", createTest);
        if (!testService.chcekBloodPressure(createTest.getBloodPressure())) {
            model.addAttribute("pressure_error", "Blood Pressure input out of normal range");
            return "Test/test_input";
        }
        if (!testService.checkOxygen(createTest.getOxygen())) {
            model.addAttribute("oxygen_error", "O2 Levels out of range");
            return "Test/test_input";
        }
        if (!testService.checkBloodSugar(createTest.getBloodSugar())) {
            model.addAttribute("sugar_error", "Blood Sugar levels out of normal range");
            return "Test/test_input";
        }
        testService.addNewTest(createTest);
        return "redirect:/medical/patient_info";
    }
}
