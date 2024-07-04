package com.example.MedicalWebInput.Models;

import javax.persistence.*;

@Entity
@Table
public class Token {
    @Id
    @SequenceGenerator(
            sequenceName = "token_sequence",
            name = "token_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "token_sequence"
    )
    private Long id;
    private String registrationToken;
    @ManyToOne
    private Patient patient;

    public Token() {
    }

    public Token(Long id, String registrationToken, Patient patient) {
        this.id = id;
        this.registrationToken = registrationToken;
        this.patient = patient;
    }

    public Token(String registrationToken, Patient patient) {
        this.registrationToken = registrationToken;
        this.patient = patient;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRegistrationToken() {
        return registrationToken;
    }

    public void setRegistrationToken(String registrationToken) {
        this.registrationToken = registrationToken;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
