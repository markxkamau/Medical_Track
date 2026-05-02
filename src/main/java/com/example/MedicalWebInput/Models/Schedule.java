package com.example.MedicalWebInput.Models;

import lombok.*;
import javax.persistence.*;
import java.util.List;
import java.util.ArrayList;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private String medicationId;
    private String medicationName;
    private String dosage;
    private String frequency;
    @ElementCollection
    private List<String> times = new ArrayList<>();
    private String startDate;
    private String endDate;
    private String instructions;
}