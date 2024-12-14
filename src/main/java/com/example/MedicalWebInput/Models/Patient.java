package com.example.MedicalWebInput.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    // Instead of List<Drug>, use a relationship that's more database-friendly
    @OneToMany(mappedBy = "patient", fetch = FetchType.LAZY)
    private List<Schedule> schedules = new ArrayList<>();

    public Patient(String name, String email, String password, String condition) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.condition = condition;
    }
}