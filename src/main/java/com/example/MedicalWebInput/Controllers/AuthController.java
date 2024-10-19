package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.PatientDto.BasicPatientDto;
import com.example.MedicalWebInput.Data.PatientDto.PatientDao;
import com.example.MedicalWebInput.Data.PatientDto.PatientLoginDto;
import com.example.MedicalWebInput.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RestController
@RequestMapping("/medical/api/auth")
public class AuthController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/login")
    public ResponseEntity<PatientDao> loginPatient(@RequestBody PatientLoginDto patientLoginDto, HttpServletRequest request) {
        // Login logic
        return ResponseEntity.ok(new PatientDao());
    }

    @PostMapping("/logout")
    public ResponseEntity<Object> logoutPatient(HttpServletResponse response, HttpSession session) {
        session.invalidate();
        response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
        response.setHeader("Pragma", "no-cache");
        response.setDateHeader("Expires", 0);

        Cookie sessionCookie = new Cookie("JSESSIONID", "");
        sessionCookie.setMaxAge(0);
        sessionCookie.setHttpOnly(true);
        response.addCookie(sessionCookie);
        return ResponseEntity.ok(session.getAttribute("patient_info"));
        // Logout logic
    }

    @PostMapping("/forgot_password")
    public ResponseEntity<PatientDao> resetPassword(@RequestBody PatientLoginDto patientLoginDto) {
        // Password reset logic
        return ResponseEntity.ok(new PatientDao());
    }
    @GetMapping("/login")
    public ResponseEntity<BasicPatientDto> loginPatient(@RequestParam("id") Long id) {
        return ResponseEntity.ok(patientService.getPatientById(id));
    }



    @GetMapping("/forgot_password")
    public ResponseEntity<BasicPatientDto> getPatientNewPassword(@RequestParam("email") String email) {
        return ResponseEntity.ok(patientService.getPatientById(patientService.getPatientByEmail(email).getId()));
    }

}
