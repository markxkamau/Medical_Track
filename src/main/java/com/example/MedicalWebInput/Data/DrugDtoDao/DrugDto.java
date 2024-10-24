package com.example.MedicalWebInput.Data.DrugDtoDao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DrugDto {
    private Long drugId;
    private String drugName = "";
    private String drugScientificName = "";
    private float drugSize;
    private String drugPackaging = "";
    private String drugPurpose = "";
    private Long patientId;
    private boolean scheduleButton = true;
    private boolean stockButton = true;


    public DrugDto(String drugName, String drugScientificName, float drugSize, String drugPackaging, String drugPurpose, Long patientId) {

        this.drugName = drugName;
        this.drugScientificName = drugScientificName;
        this.drugSize = drugSize;
        this.drugPackaging = drugPackaging;
        this.drugPurpose = drugPurpose;
        this.patientId = patientId;
    }

}
