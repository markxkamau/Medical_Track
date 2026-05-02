package com.example.MedicalWebInput.Data.DrugDtoDao;

import lombok.Data;
import java.util.List;

@Data
public class CreateDrugDTO {
    private String medId;
    private String name;
    private String purpose;
    private String dosage;
    private String frequency;
    private String prescribedBy;
    private String startDate;
    private String status;
    private List<String> sideEffects;
    private String conditionId;
}