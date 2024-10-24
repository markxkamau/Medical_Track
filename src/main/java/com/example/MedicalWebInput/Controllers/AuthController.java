package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.PatientDto.PatientLoginDto;
import com.example.MedicalWebInput.Data.PatientDto.PatientLoginDao;
import com.example.MedicalWebInput.Services.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/medical/api/auth")
public class AuthController {
    @Autowired
    private PatientService patientService;

    @PostMapping("/login")
    public ResponseEntity<String> loginPatient(@RequestBody PatientLoginDto patientLoginDto) {
        // Login logic
        if (!patientService.verifyLogin(patientLoginDto)) {
            return null;
        }
        return ResponseEntity.ok("String token = jwtService.generateToken(authRequest.getUsername());");
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutPatient(@RequestParam("email") String email) {
        // Logout
        return ResponseEntity.ok("Logout Successful");
    }

    @PostMapping("/forgot_password")
    public ResponseEntity<PatientLoginDao> resetPassword(@RequestBody PatientLoginDto patientLoginDto) {
        // Password reset logic
        return ResponseEntity.ok(new PatientLoginDao());
    }


}