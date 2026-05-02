package com.example.MedicalWebInput.Models;

import lombok.*;
import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Adherence {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private String medicationId;
    private String medicationName;
    private int adherenceRate;
    private int dosesTaken;
    private int dosesMissed;
    private String lastTaken;
}