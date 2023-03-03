package com.example.MedicalWebInput.Data.PatientDto;

import lombok.Data;

import javax.persistence.Lob;

@Data
public class AddPhotoDto {
    private Long id ;
    private Long patientId;
//    @Lob
    private byte[] profilePhoto;

    public AddPhotoDto() {
    }

    public AddPhotoDto(Long id, Long patientId, byte[] profilePhoto) {
        this.id = id;
        this.patientId = patientId;
        this.profilePhoto = profilePhoto;
    }
}
