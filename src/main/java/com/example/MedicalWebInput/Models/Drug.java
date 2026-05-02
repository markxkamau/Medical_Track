package com.example.MedicalWebInput.Models;

import lombok.*;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Drug {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String medId;
    private String name;
    private String purpose;
    private String dosage;
    private String frequency;
    private String prescribedBy;
    private String startDate;
    private String status;
    @ElementCollection
    private List<String> sideEffects = new ArrayList<>();
    private String conditionId;
}
