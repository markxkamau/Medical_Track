package com.example.MedicalWebInput.Models;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
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
    @OneToOne
    private Patient patient;

    public Photo() {
    }

    public Photo(Long id, byte[] profilePhoto, Patient patient) {
        this.id = id;
        this.profilePhoto = profilePhoto;
        this.patient = patient;
    }

    public Photo(Long id, String name, String mimeType, byte[] profilePhoto, Patient patient) {
        this.id = id;
        this.name = name;
        this.mimeType = mimeType;
        this.profilePhoto = profilePhoto;
        this.patient = patient;
    }
}
