package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.PatientDto.PatientLoginDTO;
import com.example.MedicalWebInput.Services.PatientService;
import com.example.MedicalWebInput.Configuration.JwtUtils;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/medical/api/auth")
public class AuthController {
    private final PatientService patientService;
    private final JwtUtils jwtUtils;

    public AuthController(PatientService patientService, JwtUtils jwtUtils) {
        this.patientService = patientService;
        this.jwtUtils = jwtUtils;
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginPatient(@RequestBody PatientLoginDTO patientLoginDto) {
        // Login logic
        if (!patientService.verifyLogin(patientLoginDto)) {
            return ResponseEntity.badRequest().body("Invalid Login data. Check your email and password");
        }
        
        String token = jwtUtils.generateToken(patientLoginDto.getEmail());
        Map<String, Object> response = new HashMap<>();
        response.put("token", token);
        response.put("user", patientService.getPatientByEmail(patientLoginDto.getEmail()));
        
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logoutPatient(@RequestHeader("Authorization") String authHeader) {
        String token = null;
        if (authHeader != null && authHeader.startsWith("Bearer ")) {
            token = authHeader.substring(7);
        }
        if (token == null || !jwtUtils.validateToken(token)) {
            return ResponseEntity.badRequest().body("Invalid or missing token");
        }

        String email = jwtUtils.extractEmail(token);
        patientService.recordLogoutTime(email);

        return ResponseEntity.ok("Logout Successful");
    }

    @PostMapping("/forgot_password")
    public ResponseEntity<?> resetPassword(@RequestParam("email") String email) {
        // Password reset logic
        //Check if account exists
        if(!patientService.checkEmailForPatient(email)){
            return ResponseEntity.badRequest().body("No patient exists");
        }
        return ResponseEntity.ok(patientService.resetPassword(email));
    }


}