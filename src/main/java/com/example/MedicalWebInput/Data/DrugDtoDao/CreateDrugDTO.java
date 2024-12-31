package com.example.MedicalWebInput.Data.DrugDtoDao;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateDrugDTO {
    private String medNumber;
    private String purpose;
    private String drugName;
    private float drugDose;

}
