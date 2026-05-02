package com.example.MedicalWebInput.Models;

import lombok.*;
import javax.persistence.*;

@Entity
@Table
@Getter @Setter @NoArgsConstructor @AllArgsConstructor
public class HealthRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long patientId;
    private String date;
    private String type;
    private String value;
}