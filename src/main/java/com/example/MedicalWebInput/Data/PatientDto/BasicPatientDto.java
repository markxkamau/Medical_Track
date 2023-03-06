package com.example.MedicalWebInput.Data.PatientDto;

import lombok.Data;

@Data
public class BasicPatientDto {
    private Long id;
    private String name = "";
    private String email = "";
    private int drugCount;
    private String condition = "";
    private boolean photoAvailable = false;


    public BasicPatientDto() {
    }

    public BasicPatientDto(Long id, String name, String email, int drugCount, String condition) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.drugCount = drugCount;
        this.condition = condition;
    }

    public BasicPatientDto(Long id, String name, String email, int drugCount, String condition, boolean photoAvailable) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.drugCount = drugCount;
        this.condition = condition;
        this.photoAvailable = photoAvailable;
    }
}
