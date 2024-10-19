package com.example.MedicalWebInput.Data.PatientDto;

import org.springframework.web.multipart.MultipartFile;

import javax.persistence.Lob;

public class AddPhotoDto {
    private Long patientId;
    private MultipartFile profilePhoto;

    public AddPhotoDto() {
    }

    public AddPhotoDto(Long patientId, MultipartFile profilePhoto) {
        this.patientId = patientId;
        this.profilePhoto = profilePhoto;
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
