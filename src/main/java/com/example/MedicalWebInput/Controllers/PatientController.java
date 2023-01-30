package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.PatientDto.CreatePatientDto;
import com.example.MedicalWebInput.Data.PatientDto.PatientLoginDto;
import com.example.MedicalWebInput.Models.Patient;
import com.example.MedicalWebInput.Repository.DrugRepository;
import com.example.MedicalWebInput.Services.PatientService;
import com.example.MedicalWebInput.Services.ScheduleService;
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
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private DrugRepository drugRepository;

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

    @GetMapping("/edit_drug/{drugId}")
    public String editDrugInfo(@PathVariable Long drugId, @NotNull Model model) {
        model.addAttribute("drug_info", patientService.getDrugInfo(drugId));
        return "Drug/drug_input";
    }

    @GetMapping("/delete_drug/{drugId}")
    public String deleteDrugById(@PathVariable Long drugId) {
        Long patientId = patientService.getDrugInfo(drugId).getPatientId();
        patientService.deleteDrugById(drugId);
        return "redirect:/patient/patient_info/" + patientId;
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
        model.addAttribute("patient_data", patientService.getPatientById(patientId));
        model.addAttribute("drug_info", patientService.getDrugByPatientId(patientId));
        model.addAttribute("schedule_info", scheduleService.getScheduleByPatientId(patientId));
        model.addAttribute("schedule_present", scheduleService.checkIfNull(patientId));
        model.addAttribute("test_data", patientService.getAllPatientTests(patientId));
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
        return "redirect:/patient/patient_info/" + patient.getId();

    }

    @PostMapping("/forgot_password")
    public String resetPassword(@ModelAttribute PatientLoginDto patientLoginDto, @NotNull Model model) {
        if (!patientService.checkForPatient(patientLoginDto.getEmail())) {
            model.addAttribute("patient_email", patientLoginDto);
            model.addAttribute("reset_error", "Email doesn't exist");
            return "Patient/patient_reset";
        }
        String newPassword = patientService.setNewPassword(patientLoginDto.getEmail());
        return newPassword;
    }


}
