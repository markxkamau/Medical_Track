package com.example.MedicalWebInput.Controllers;

import com.example.MedicalWebInput.Models.Patient;
import com.example.MedicalWebInput.Models.Photo;
import com.example.MedicalWebInput.Repository.PatientRepository;
import com.example.MedicalWebInput.Repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Base64;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/medical/api")
public class PhotoController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PhotoRepository photoRepository;

    @GetMapping("/patient/{id}/photo")
    public ResponseEntity<?> getPatientProfilePhoto(@PathVariable Long id) {
        Photo photo = photoRepository.findByPatientId(id);
        if (photo == null || photo.getProfilePhoto() == null) {
            return ResponseEntity.ok(Map.of("image", ""));
        }
        
        String base64Image = Base64.getEncoder().encodeToString(photo.getProfilePhoto());
        String mimeType = photo.getMimeType() != null ? photo.getMimeType() : "image/jpeg";
        String dataUri = "data:" + mimeType + ";base64," + base64Image;
        
        return ResponseEntity.ok(Map.of("image", dataUri));
    }

    @PostMapping("/patient/{id}/photo")
    public ResponseEntity<?> uploadPhoto(@PathVariable Long id, @RequestParam("file") MultipartFile file) {
        try {
            Optional<Patient> patientOpt = patientRepository.findById(id);
            if (!patientOpt.isPresent()) {
                return ResponseEntity.badRequest().body("Patient not found");
            }
            
            Patient patient = patientOpt.get();
            Photo photo = photoRepository.findByPatientId(id);
            
            if (photo == null) {
                photo = new Photo();
                photo.setPatientId(id);
            }
            
            photo.setMimeType(file.getContentType());
            photo.setProfilePhoto(file.getBytes());
            photo.setName(file.getOriginalFilename());
            
            Photo savedPhoto = photoRepository.save(photo);
            patient.setPhoto(savedPhoto);
            patientRepository.save(patient);
            
            String base64Image = Base64.getEncoder().encodeToString(savedPhoto.getProfilePhoto());
            String dataUri = "data:" + savedPhoto.getMimeType() + ";base64," + base64Image;
            
            return ResponseEntity.ok(Map.of("message", "Upload successful", "image", dataUri));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().body("Failed to upload image: " + e.getMessage());
        }
    }
}
