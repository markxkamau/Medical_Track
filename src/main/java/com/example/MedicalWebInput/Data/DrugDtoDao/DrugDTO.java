package com.example.MedicalWebInput.Data.DrugDtoDao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DrugDTO {
    private String medNumber = "";
    private String drugName = "";
    private float drugSize;
    private String drugPurpose = "";

    public DrugDTO() {
    }

    public DrugDTO(String medId, String drugName, float drugSize, String drugPurpose) {
        this.medNumber = medId;
        this.drugName = drugName;
        this.drugSize = drugSize;
        this.drugPurpose = drugPurpose;
    }

}
