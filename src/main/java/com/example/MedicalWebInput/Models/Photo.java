package com.example.MedicalWebInput.Models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table
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

    public Photo() {
    }

    public Photo(Long id, String name, String mimeType, byte[] profilePhoto) {
        this.id = id;
        this.name = name;
        this.mimeType = mimeType;
        this.profilePhoto = profilePhoto;
    }

    public Photo(String mimeType, byte[] profilePhoto) {
        this.mimeType = mimeType;
        this.profilePhoto = profilePhoto;
    }


}
