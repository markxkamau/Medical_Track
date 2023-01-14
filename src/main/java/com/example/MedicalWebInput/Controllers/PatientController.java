package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.DrugDto.DrugDto;
import com.example.MedicalWebInput.Data.PatientDto.CreatePatientDto;
import com.example.MedicalWebInput.Data.PatientDto.PatientDto;
import com.example.MedicalWebInput.Data.PatientDto.PatientLoginDto;
import com.example.MedicalWebInput.Models.Drug;
import com.example.MedicalWebInput.Models.Patient;
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

    @GetMapping("/login")
    public String loginPatient(@NotNull Model model) {
        model.addAttribute("login_detail", new PatientLoginDto());
        return "Patient/patient_login";
    }

    @GetMapping("/forgot_password")
    public String forgotPassword(@NotNull Model model) {
        model.addAttribute("patient_email", new PatientLoginDto());
        return "Patient/patient_reset";
    }

    @GetMapping("/patient_info/{patientId}")
    public String getPatientInfo(@NotNull Model model, @PathVariable Long patientId) {
        Model model1 = model.addAttribute("patient_data", patientService.getPatientById(patientId));
        model1.addAttribute("drug_info", patientService.getDrugByPatientId(patientId));
        return "HomePage";
    }


//    *************************************************************************
//    PostMappings
//    =========================================================================

    @PostMapping("/new_patient")
    public String addPatientInfo(@ModelAttribute CreatePatientDto patientDto, @NotNull Model model) {
        Model patientData = model.addAttribute("patient_data", patientDto);
        if (patientService.checkForPatient(patientDto.getEmail())) {
//            Patient email already exists
            patientData.addAttribute("patient_error", "Email already exists");
            return "Patient/patient_registration";
        }
        if (!patientService.checkPassword(patientDto.getPassword(), patientDto.getConfirmPassword())) {
//            Passwords not similar
            patientData.addAttribute("patient_alert", "Passwords do not match, Check and try again");
            return "Patient/patient_registration";
        }
        if (patientDto.getDrugCount() < 1) {
//            Drug count less than one
            patientData.addAttribute("drug_error", "Minimum drug limit is 1");
            return "Patient/patient_registration";
        }
        Patient patient = patientService.convertToPatient(patientDto);
        patientService.addNewPatient(patient);
        model.addAttribute("login_detail", new PatientLoginDto());

        return "Patient/patient_login";
    }

    @PostMapping("/login")
    public String verifyLogin(@ModelAttribute PatientLoginDto patientLoginDto, @NotNull Model model) {
        if (!patientService.verifyLogin(patientLoginDto)) {
            model.addAttribute("login_detail", patientLoginDto);
            model.addAttribute("login_error", "Password or email not correct, check and try again");
            return "Patient/patient_login";
        }
        Patient patient = patientService.getPatientByEmail(patientLoginDto.getEmail());
        model.addAttribute("patient_data", patient);
        model.addAttribute("drug_info", patientService.getDrugByPatientId(patient.getId()));
        return "HomePage";
    }

    @PostMapping("/forgot_password")
    @ResponseBody
    public String resetPassword(@ModelAttribute PatientLoginDto patientLoginDto, @NotNull Model model) {
        if (!patientService.checkForPatient(patientLoginDto.getEmail())) {
            model.addAttribute("patient_email", patientLoginDto);
            model.addAttribute("reset_error", "Email doesn't exist");
            return "Patient/patient_reset";
        }
        String newPassword = patientService.setNewPassword(patientLoginDto.getEmail());
        return newPassword;
    }
//    public void addPatientData(@RequestBody Patient patient) {
//        if (!patientService.checkForPatient(patient)) {
//            patientService.addNewPatient(patient);
//        }
//    }
}
