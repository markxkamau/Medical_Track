package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.CreatePatientDto;
import com.example.MedicalWebInput.Data.PatientDto;
import com.example.MedicalWebInput.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

//    *************************************************************************
//    GetMappings
//    =========================================================================

    @GetMapping
    @ResponseBody
    public String getHomepage() {
        return "Patient/home_page";
    }

//    Must have admin rights
    @GetMapping("/all")
    public String getAllPatients(@NotNull Model model) {
        model.addAttribute("patient_data", patientService.convertToPatientDto(patientService.getAllPatients()));
        return "Patient/patient_listing";
    }

    @GetMapping("/new_patient")
    public String addNewPatient(@NotNull Model model) {
        model.addAttribute("patient_data", new CreatePatientDto());
        return "Patient/patient_registration";
    }

//    *************************************************************************
//    PostMappings
//    =========================================================================

    @PostMapping("/new_patient")
    public String addPatientInfo(@ModelAttribute CreatePatientDto patientDto, @NotNull Model model) {
//        model.addAttribute("patient_data", patientDto);
        PatientDto patientDto1 = new PatientDto();
        if(patientDto.getPassword().equals(patientDto.getConfirmPassword())){
            patientDto1.setPassword(patientDto.getPassword());
        }
        else {
            model.addAttribute("patient_data", patientDto);
            model.addAttribute("patient_alert", "Passwords do not match, Check and try again");
            return "Patient/patient_registration";
        }
        return "";
    }
//    public void addPatientData(@RequestBody Patient patient) {
//        if (!patientService.checkForPatient(patient)) {
//            patientService.addNewPatient(patient);
//        }
//    }
}
