package com.example.MedicalWebInput.Models;

import javax.persistence.*;
import java.util.*;

@Entity
@Table
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
    @OneToMany
    private List<Drug> drugs = new ArrayList<>();

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

    public Patient(String email, String password) {
        this.email = email;
        this.password = password;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public List<Drug> getDrugs() {
        return drugs;
    }

    public void setDrugs(List<Drug> drugs) {
        this.drugs = drugs;
    }
}
