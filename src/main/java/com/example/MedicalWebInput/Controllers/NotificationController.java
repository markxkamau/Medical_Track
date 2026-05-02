package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Data.TokenDto.CollectTokenDto;
import com.example.MedicalWebInput.Data.TokenDto.NotificationDto;
import com.example.MedicalWebInput.Services.NotificationService;
import com.example.MedicalWebInput.Services.ReminderService;
import com.google.firebase.messaging.FirebaseMessagingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/medical")
public class NotificationController {
    @Autowired
    private NotificationService notificationService;
    @Autowired
    private ReminderService reminderService;

    @PostMapping("/api/registerToken")
    public ResponseEntity<String> registerToken(@RequestBody CollectTokenDto collectTokenDto){
        try {
            notificationService.saveToken(collectTokenDto.getUsername(), collectTokenDto.getToken());
            return ResponseEntity.ok("Token received");
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error saving token");
        }
    }

    @PostMapping("/api/sendNotification")
    public ResponseEntity<String> sendNotification(@RequestBody NotificationDto notificationDto) {
        try {
            notificationService.sendNotification(
                    notificationDto.getToken(),
                    notificationDto.getTitle(),
                    notificationDto.getBody()
            );
            return ResponseEntity.ok("Notification sent");
        } catch (FirebaseMessagingException e) {
            return ResponseEntity.status(500).body("Error sending notification");
        }
    }

    @GetMapping("/api/upcoming-reminders/{patientId}")
    public ResponseEntity<List<String>> getUpcomingReminders(@PathVariable Long patientId) {
        List<String> reminders = reminderService.getUpcomingReminders(patientId);
        return ResponseEntity.ok(reminders);
    }

}

