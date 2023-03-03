package com.example.MedicalWebInput.Data.PatientDto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;

@Data
public class AddPhotoDto {
    private Long id;
    private Long patientId;
    private MultipartFile profilePhoto;

    public AddPhotoDto() {
    }

    public AddPhotoDto(Long id, Long patientId, MultipartFile profilePhoto) {
        this.id = id;
        this.patientId = patientId;
        this.profilePhoto = profilePhoto;
    }
}
