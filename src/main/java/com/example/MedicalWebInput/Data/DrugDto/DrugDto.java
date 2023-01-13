package com.example.MedicalWebInput.Data.DrugDto;

import com.example.MedicalWebInput.Models.Patient;

import javax.persistence.ManyToOne;

public class DrugDto {
    private Long id;
    private String drugName = "";
    private String drugScientificName = "";
    private float drugSize;
    private String drugPackaging = "";
    private String drugPurpose = "";
    private Long patientId;

    public DrugDto() {
    }

    public DrugDto(Long id, String drugName, String drugScientificName, float drugSize, String drugPackaging, String drugPurpose) {
        this.id = id;
        this.drugName = drugName;
        this.drugScientificName = drugScientificName;
        this.drugSize = drugSize;
        this.drugPackaging = drugPackaging;
        this.drugPurpose = drugPurpose;
    }

    public DrugDto(Long id, String drugName, String drugScientificName, float drugSize, String drugPackaging, String drugPurpose, Long patientId) {
        this.id = id;
        this.drugName = drugName;
        this.drugScientificName = drugScientificName;
        this.drugSize = drugSize;
        this.drugPackaging = drugPackaging;
        this.drugPurpose = drugPurpose;
        this.patientId = patientId;
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

    public String getDrugScientificName() {
        return drugScientificName;
    }

    public void setDrugScientificName(String drugScientificName) {
        this.drugScientificName = drugScientificName;
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

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
