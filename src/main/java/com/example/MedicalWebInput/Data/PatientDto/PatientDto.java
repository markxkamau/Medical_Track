package com.example.MedicalWebInput.Data.PatientDto;

import com.example.MedicalWebInput.Models.Photo;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

@Getter
@Setter
public class PatientDto {
    private Long id;
    private String name = "";
    private String email = "";
    private String condition = "";
    private String password = "";
    private Optional<Photo> photo;
    private boolean photoAvailable = false;


    public PatientDto() {
    }

    public PatientDto(Long id,String name, String email, String condition, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.condition = condition;
        this.password = password;
    }

    public PatientDto(Long id, String name, String email, String condition, String password, boolean photoAvailable) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.condition = condition;
        this.password = password;
        this.photoAvailable = photoAvailable;
    }

    public PatientDto(Long id, String name, String email, String condition, String password, Optional<Photo> photo, boolean photoAvailable) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.condition = condition;
        this.password = password;
        this.photo = photo;
        this.photoAvailable = photoAvailable;
    }
}
