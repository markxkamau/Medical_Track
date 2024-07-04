package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.PatientDto.*;
import com.example.MedicalWebInput.Models.Patient;
import com.example.MedicalWebInput.Models.Photo;
import com.example.MedicalWebInput.Repository.DrugRepository;
import com.example.MedicalWebInput.Services.*;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.NotNull;
import java.io.IOException;

@Controller
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;
    @Autowired
    private ScheduleService scheduleService;
    @Autowired
    private ReminderService reminderService;
    @Autowired
    private EmailService emailService;
    @Autowired
    private PhotoService photoService;

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

    @GetMapping("/profile_photo")
    public String addProfilePhoto(@NotNull Model model, @NotNull HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        if (session == null || session.getAttribute("patient_info") == null) {
            // If the user is not logged in, redirect them to the login page
            return "redirect:/patient/login";
        }
        BasicPatientDto patient = (BasicPatientDto) session.getAttribute("patient_info");
        AddPhotoDto addPhotoDto = new AddPhotoDto();
        addPhotoDto.setPatientId(patient.getId());
        model.addAttribute("photo_data", addPhotoDto);
        return "Patient/profile_photo";
    }

    @GetMapping("/new_patient")
    public String addNewPatient(@NotNull Model model) {
        model.addAttribute("patient_data", new CreatePatientDto());
        return "Patient/patient_registration";
    }

    @GetMapping("/edit_drug/{drugId}")
    public String editDrugInfo(@PathVariable Long drugId, @NotNull Model model, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        if (session == null || session.getAttribute("patient_info") == null) {
            // If the user is not logged in, redirect them to the login page
            return "redirect:/patient/login";
        }
        if (patientService.getDrugAndScheduleInfo(drugId) != null) {
            model.addAttribute("schedule_info", patientService.getDrugAndScheduleInfo(drugId));
            return "Drug/drug_edit";
        }
        model.addAttribute("drug_info", patientService.getDrugInfo(drugId));
        return "Drug/drug_input";
    }

    @GetMapping("/delete_drug/{drugId}")
    public String deleteDrugById(@PathVariable Long drugId, HttpServletRequest httpServletRequest) {
        HttpSession session = httpServletRequest.getSession();
        if (session == null || session.getAttribute("patient_info") == null) {
            // If the user is not logged in, redirect them to the login page
            return "redirect:/patient/login";
        }
        Long patientId = patientService.getDrugInfo(drugId).getPatientId();
        patientService.deleteDrugById(drugId);
        return "redirect:/patient/patient_info";
    }

    @GetMapping("/login")
    public String loginPatient(@NotNull Model model) {
        model.addAttribute("login_detail", new PatientLoginDto());
        return "Patient/patient_login";
    }

    @GetMapping("/logout")
    public String logoutPatient(@NotNull HttpServletResponse response, HttpSession session) {
        session.invalidate();
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        Cookie sessionCookie = new Cookie("JSESSIONID", "");
        sessionCookie.setMaxAge(0);
        sessionCookie.setHttpOnly(true);
        response.addCookie(sessionCookie);
        return "redirect:/patient/login";
    }


    @GetMapping("/forgot_password")
    public String forgotPassword(@NotNull Model model) {

        model.addAttribute("patient_email", new PatientLoginDto());
        return "Patient/patient_reset";
    }

    @GetMapping("/patient_info")
    public String getPatientInfo(@NotNull Model model, HttpServletRequest httpServletRequest) throws FirebaseMessagingException, IOException {
        HttpSession session = httpServletRequest.getSession();
        if (session == null || session.getAttribute("patient_info") == null) {
            // If the user is not logged in, redirect them to the login page
            return "redirect:/patient/login";
        }
        BasicPatientDto patient = (BasicPatientDto) session.getAttribute("patient_info");
        model.addAttribute("patient_data", patientService.getPatientById(patient.getId()));
        model.addAttribute("drug_info", patientService.getDrugByPatientId(patient.getId()));
        model.addAttribute("drug_present", patientService.checkDrug(patient.getId()));
        model.addAttribute("schedule_info", scheduleService.getScheduleByPatientId(patient.getId()));
        model.addAttribute("schedule_present", scheduleService.checkIfNull(patient.getId()));
        model.addAttribute("stock_info", scheduleService.getStockInfo(patient.getId()));
        model.addAttribute("stock_present", scheduleService.checkStock(patient.getId()));
        model.addAttribute("test_data", patientService.getAllPatientTests(patient.getId()));
        Photo patientPhoto = photoService.getPhotoInfoByPatientId(patient.getId());

        if (patientPhoto != null) {
            String image = photoService.getImage(patientPhoto.getId());
            model.addAttribute("image", image);
        }

        reminderService.setPatientId(patient.getId());
        if (!patientService.getDrugByPatientId(patient.getId()).isEmpty()) {
            reminderService.sendDoseReminders();
        }

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
    public String verifyLogin(@ModelAttribute PatientLoginDto patientLoginDto, @NotNull RedirectAttributes redirectAttributes, HttpServletRequest request) {
        if (!patientService.verifyLogin(patientLoginDto)) {
            redirectAttributes.addFlashAttribute("login_error", "Password or email not correct, check and try again");
            return "redirect:/patient/login";
        }
        Patient patient = patientService.getPatientByEmail(patientLoginDto.getEmail());
        HttpSession session = request.getSession();
        session.setAttribute("patient_info", patientService.getPatientById(patient.getId()));
        session.setMaxInactiveInterval(900);

        return "redirect:/patient/patient_info";

    }

    @PostMapping("/forgot_password")
    public String resetPassword(@ModelAttribute PatientLoginDto patientLoginDto, @NotNull RedirectAttributes redirectAttributes) {
        if (!patientService.checkForPatient(patientLoginDto.getEmail())) {
            redirectAttributes.addFlashAttribute("reset_error", "Email doesn't exist");
            return "redirect:/patient/forgot_password";
        }
        String password = patientService.setNewPassword();
        emailService.sendSimpleMessage(patientLoginDto.getEmail(), "Password Reset", "New Password: " + password);
        patientService.changePassword(patientLoginDto.getEmail(), password);
        redirectAttributes.addFlashAttribute("change_success", true);
        return "redirect:/patient/login";
    }

    @PostMapping("/upload")
    public String handleFileUpload(@ModelAttribute AddPhotoDto addPhotoDto, @NotNull RedirectAttributes redirectAttributes) throws IOException {
        photoService.checkForCurrentPhoto(addPhotoDto.getPatientId());
        Photo photo = photoService.addNewPhoto(addPhotoDto);
        String image = photoService.getImage(photo.getId());
        if (image.equals(null)) {
            redirectAttributes.addFlashAttribute("upload_error", "Check your uploaded photo");
            return "redirect:/patient/profile_photo/" + photo.getId();
        }
        return "redirect:/patient/patient_info";
    }


}
