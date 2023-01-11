package com.example.MedicalWebInput.Data.DrugDto;

import com.example.MedicalWebInput.Models.Patient;

import javax.persistence.ManyToOne;

public class DrugDto {
    private Long id;
    private String drugName = "";
    private String drugScientificName = "";
    private int drugSize;
    private String drugPackaging = "";
    private String drugPurpose = "";

    public DrugDto() {
    }

    public DrugDto(Long id, String drugName, String drugScientificName, int drugSize, String drugPackaging, String drugPurpose) {
        this.id = id;
        this.drugName = drugName;
        this.drugScientificName = drugScientificName;
        this.drugSize = drugSize;
        this.drugPackaging = drugPackaging;
        this.drugPurpose = drugPurpose;
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

    public int getDrugSize() {
        return drugSize;
    }

    public void setDrugSize(int drugSize) {
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
}
