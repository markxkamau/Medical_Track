package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.TokenDto.CollectTokenDto;
import com.example.MedicalWebInput.Services.NotificationService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;

    @PostMapping("/registerToken")
    public ResponseEntity<String> registerToken(@RequestBody CollectTokenDto collectTokenDto){
        try {
            notificationService.saveToken(collectTokenDto.getUsername(), collectTokenDto.getToken());
            return ResponseEntity.ok("Token received");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving token");
        }
    }

    @PostMapping("/sendNotification")
    public ResponseEntity<String> sendNotification(@RequestParam String token,
                                                   @RequestParam String title,
                                                   @RequestParam String body) {
        try {
            notificationService.sendNotification(token, title, body);
            return ResponseEntity.ok("Notification sent");
        } catch (FirebaseMessagingException e) {
            return ResponseEntity.status(500).body("Error sending notification");
        }
    }
}

