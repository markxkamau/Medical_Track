package com.example.MedicalWebInput.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Drug {
    @Id
    @SequenceGenerator(
            sequenceName = "drug_sequence",
            name = "drug_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "drug_sequence"
    )
    private Long id;
    private String drugName = "";
    private String drugScientificName = "";
    private float drugSize;
    private String drugPackaging = "";
    private String drugPurpose = "";
    private boolean scheduleButton = true;
    private boolean stockButton = true;
    @ManyToOne
    private Patient patient;


    public Drug(String drugName, String drugScientificName, float drugSize, String drugPackaging, String drugPurpose, Patient patient) {

    }
}
