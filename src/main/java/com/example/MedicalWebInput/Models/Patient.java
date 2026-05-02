package com.example.MedicalWebInput.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Patient {
    @Id
    @SequenceGenerator(sequenceName = "patient_sequence", allocationSize = 1, name = "patient_sequence")
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "patient_sequence")
    private Long id;
    private String firstName = "";
    private String lastName = "";
    private String email = "";
    private String password = "";
    private String condition = "";
    private LocalDateTime localDateTime = LocalDateTime.now();
    private LocalDateTime logoutTime;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "photo_id")
    private Photo photo;

    public Patient(String firstName, String lastName, String email, String password, String condition,
            LocalDateTime localDateTime) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.condition = condition;
        this.localDateTime = localDateTime;
    }
}