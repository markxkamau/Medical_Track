package com.example.MedicalWebInput.Data.PatientDto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class CreatePatientDTO {
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String password = "";
    private LocalDateTime dateTime;

    public CreatePatientDTO() {
    }

    public CreatePatientDTO(String firstName, String lastName, String email, String password, LocalDateTime dateTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.dateTime = dateTime;
    }
}
