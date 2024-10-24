package com.example.MedicalWebInput.Services;

import com.example.MedicalWebInput.Data.PatientDto.AddPhotoDto;
import com.example.MedicalWebInput.Models.Patient;
import com.example.MedicalWebInput.Models.Photo;
import com.example.MedicalWebInput.Repository.PatientRepository;
import com.example.MedicalWebInput.Repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Base64;

@Service
public class PhotoService {
    @Autowired
    private PhotoRepository photoRepository;
    @Autowired
    private PatientRepository patientRepository;

    public byte[] getProfileImage(Long id) {
        return photoRepository.findById(id).get().getProfilePhoto();
    }

    public Photo addNewPhoto(AddPhotoDto addPhotoDto) throws IOException {
        Patient patient = patientRepository.findById(addPhotoDto.getPatientId()).get();
        Photo photo = new Photo(
                addPhotoDto.getId(),
                generateMimeType(addPhotoDto.getProfilePhoto()),
                convertMultipartFileToByteArray(addPhotoDto.getProfilePhoto()),
                patient
        );
        Photo savedPhoto = photoRepository.save(photo);
        patient.setPhotoAvailable(true);
        patientRepository.save(patient);
        return savedPhoto;
    }

    private String generateMimeType(MultipartFile profilePhoto) {
        return profilePhoto.getContentType();
    }

    public Photo getPhotoInfoByPatientId(Long id) {
        return photoRepository.findByPatientId(id);
    }

    public byte[] convertMultipartFileToByteArray(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        return bytes;
    }

    public String getImage(Long id) throws IOException {
        byte[] imageData = getProfileImage(id);
        BufferedImage bufferedImage = ImageIO.read(new ByteArrayInputStream(imageData));
        if (bufferedImage != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, "jpg", baos);
            byte[] imageBytes = baos.toByteArray();
            String encodedImage = Base64.getEncoder().encodeToString(imageBytes);
            return encodedImage;
        }
        return null;
    }

    public void checkForCurrentPhoto(Long patientId) {
        Patient patient = patientRepository.findById(patientId).get();
        Photo photo = photoRepository.findByPatientId(patientId);
        if (photo != null) {
            photoRepository.delete(photo);
            patient.setPhotoAvailable(false);
        }
    }
}
