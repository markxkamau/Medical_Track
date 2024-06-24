package com.example.MedicalWebInput.Data.PatientDto;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public MultipartFile getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(MultipartFile profilePhoto) {
        this.profilePhoto = profilePhoto;
    }
}
