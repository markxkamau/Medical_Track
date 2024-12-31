package com.example.MedicalWebInput.Data.ScheduleDto;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class PatientDrugInfoDto {
    private String patientFirstName;
    private String patientLastName;
    private String drugName;

    public PatientDrugInfoDto() {
    }

    public PatientDrugInfoDto(String patientFirstName, String patientLastName, String drugName) {
        this.patientFirstName = patientFirstName;
        this.patientLastName = patientLastName;
        this.drugName = drugName;
    }
}
