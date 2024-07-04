package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Models.Patient;
import com.example.MedicalWebInput.Models.Token;
import com.example.MedicalWebInput.Repository.PatientRepository;
import com.example.MedicalWebInput.Repository.TokenRepository;
import com.google.firebase.messaging.FirebaseMessaging;
import com.google.firebase.messaging.FirebaseMessagingException;
import com.google.firebase.messaging.Message;
import com.google.firebase.messaging.Notification;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    private TokenRepository tokenRepository;
    @Autowired
    private PatientRepository patientRepository;

    public void sendNotification(String registrationToken, String title, String body) throws FirebaseMessagingException {
        Notification notification = Notification.builder()
                .setTitle(title)
                .setBody(body)
                .build();

        Message message = Message.builder()
                .setNotification(notification)
                .setToken(registrationToken)
                .build();

        String response = FirebaseMessaging.getInstance().send(message);
        System.out.println("Successfully sent message: " + response);
    }


    public void saveToken(String userEmail, String userToken) {
        Patient patient = patientRepository.findByEmail(userEmail).get();
        if (patient == null) {
            // Handle user not found, e.g., throw an exception or create a new user
            throw new IllegalArgumentException("User not found");
        }

        Token existingToken = tokenRepository.findByRegistrationToken(userToken);
        if (existingToken == null) {
            Token fcmToken = new Token();
            fcmToken.setRegistrationToken(userToken);
            fcmToken.setPatient(patient);
            tokenRepository.save(fcmToken);
        } else {
            System.out.println("Token already exists");
        }
    }

    public String getToken(Patient patient){
        Token token = tokenRepository.findByPatientId(patient.getId());
        return token.getRegistrationToken();
    }


}
