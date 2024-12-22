package com.example.MedicalWebInput.Data.PatientDto;

import java.time.LocalDateTime;

public class CreatePatientDto {
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String condition = "";
    private String password = "";
    private LocalDateTime dateTime;

    public CreatePatientDto() {
    }

    public CreatePatientDto(String firstName, String lastName, String email, String condition, String password, LocalDateTime dateTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.condition = condition;
        this.password = password;
        this.dateTime = dateTime;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }
}
