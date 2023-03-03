package com.example.MedicalWebInput.Models;

import lombok.Data;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
@Data
public class Patient {
    @Id
    @SequenceGenerator(
            sequenceName = "patient_sequence",
            allocationSize = 1,
            name = "patient_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "patient_sequence"
    )
    private Long id;
    private String name = "";
    private String email = "";
    private String password = "";
    private String condition = "";
    private boolean photoAvailable = false;

    @OneToMany
    private List<Drug> drugs = new ArrayList<>();

    @OneToOne
    private Photo photo;

    public Patient() {
    }

    public Patient(Long id, String name, String email, String password, String condition, List<Drug> drugs) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.condition = condition;
        this.drugs = drugs;
    }

    public Patient(Long id, String name, String email, String password, String condition, boolean photoAvailable, List<Drug> drugs) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.condition = condition;
        this.photoAvailable = photoAvailable;
        this.drugs = drugs;
    }

    public Patient(String email, String password) {
        this.email = email;
        this.password = password;
    }



}
