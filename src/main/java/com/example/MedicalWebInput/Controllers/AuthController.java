package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.PatientDto.PatientLoginDto;
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
    public ResponseEntity<?> loginPatient(@RequestBody PatientLoginDto patientLoginDto) {
        // Login logic
        if (!patientService.verifyLogin(patientLoginDto)) {
            return ResponseEntity.badRequest().body("Invalid Login data. Check your email and password");
        }
        //// TODO: START SESSION, CREATE JWT
        return ResponseEntity.ok(patientService.getPatientByEmail(patientLoginDto.getEmail()));
        // "String token = jwtService.generateToken(authRequest.getUsername());"
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutPatient(@RequestParam("email") String email) {
        // Logout
        /// /TODO : Kill JWT and RECORD TIME
        return ResponseEntity.ok("Logout Successful");
    }

    @PostMapping("/forgot_password")
    public ResponseEntity<?> resetPassword(@RequestBody PatientLoginDto patientLoginDto) {
        // Password reset logic
        //Check if account exists
        if(!patientService.checkEmailForPatient(patientLoginDto.getEmail())){
            return ResponseEntity.badRequest().body("No patient exists");
        }
        return ResponseEntity.ok(patientService.resetPassword(patientLoginDto.getEmail()));
    }


}