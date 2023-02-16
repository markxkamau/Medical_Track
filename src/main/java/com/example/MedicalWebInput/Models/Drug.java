package com.example.MedicalWebInput.Models;

import javax.persistence.*;

@Entity
@Table
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

    public Drug() {
    }

    public Drug(Long id, String drugName, String drugScientificName, float drugSize, String drugPackaging, String drugPurpose) {
        this.id = id;
        this.drugName = drugName;
        this.drugScientificName = drugScientificName;
        this.drugSize = drugSize;
        this.drugPackaging = drugPackaging;
        this.drugPurpose = drugPurpose;
    }

    public Drug(Long id, String drugName, String drugScientificName, float drugSize, String drugPackaging, String drugPurpose, boolean scheduleButton, Patient patient) {
        this.id = id;
        this.drugName = drugName;
        this.drugScientificName = drugScientificName;
        this.drugSize = drugSize;
        this.drugPackaging = drugPackaging;
        this.drugPurpose = drugPurpose;
        this.scheduleButton = scheduleButton;
        this.patient = patient;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Drug(Long id, String drugName, String drugScientificName, float drugSize, String drugPackaging, String drugPurpose, Patient patient) {
        this.id = id;
        this.drugName = drugName;
        this.drugScientificName = drugScientificName;
        this.drugSize = drugSize;
        this.drugPackaging = drugPackaging;
        this.drugPurpose = drugPurpose;
        this.patient = patient;
    }

    public Drug(String drugName, String drugPurpose) {
        this.drugName = drugName;
        this.drugPurpose = drugPurpose;
    }

    public String getDrugScientificName() {
        return drugScientificName;
    }

    public void setDrugScientificName(String drugScientificName) {
        this.drugScientificName = drugScientificName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }

    public float getDrugSize() {
        return drugSize;
    }

    public void setDrugSize(float drugSize) {
        this.drugSize = drugSize;
    }

    public String getDrugPackaging() {
        return drugPackaging;
    }

    public void setDrugPackaging(String drugPackaging) {
        this.drugPackaging = drugPackaging;
    }

    public String getDrugPurpose() {
        return drugPurpose;
    }

    public void setDrugPurpose(String drugPurpose) {
        this.drugPurpose = drugPurpose;
    }

    public boolean getScheduleButton() {
        return scheduleButton;
    }

    public void setScheduleButton(boolean scheduleButton) {
        this.scheduleButton = scheduleButton;
    }

    public boolean getStockButton() {
        return stockButton;
    }

    public void setStockButton(boolean stockButton) {
        this.stockButton = stockButton;
    }
}
