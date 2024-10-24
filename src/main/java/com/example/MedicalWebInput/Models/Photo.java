package com.example.MedicalWebInput.Models;


import javax.persistence.*;

@Entity
public class Photo {
    @Id
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "profile_generator")
    @SequenceGenerator(
            sequenceName = "profile_generator",
            name = "profile_generator",
            allocationSize = 1)
    private Long id;
    private String name = "Profile Photo";
    private String mimeType;
    //    @Lob
    private byte[] profilePhoto;
    private Long patientId;

    public Photo() {
    }

    public Photo(Long id, String name, String mimeType, byte[] profilePhoto, Long patient_PatientId) {
        this.id = id;
        this.name = name;
        this.mimeType = mimeType;
        this.profilePhoto = profilePhoto;
        this.patientId = patientId;
    }

    public Photo(String mimeType, byte[] profilePhoto, Long patient_PatientId) {
        this.mimeType = mimeType;
        this.profilePhoto = profilePhoto;
        this.patientId = patientId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public byte[] getProfilePhoto() {
        return profilePhoto;
    }

    public void setProfilePhoto(byte[] profilePhoto) {
        this.profilePhoto = profilePhoto;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
