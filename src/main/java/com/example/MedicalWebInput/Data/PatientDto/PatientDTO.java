package com.example.MedicalWebInput.Data.PatientDto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Getter
@Setter
public class PatientDTO {
    private Long id;
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private MultipartFile profilePhoto;

    public PatientDTO() {
    }

    public PatientDTO(String name, String lastName, String email) {
        this.firstName = name;
        this.lastName = lastName;
        this.email = email;
    }

    public PatientDTO(String name, String lastName, String email, MultipartFile photo) {
        this.firstName = name;
        this.lastName = lastName;
        this.email = email;
        this.profilePhoto = photo;
    }
}
