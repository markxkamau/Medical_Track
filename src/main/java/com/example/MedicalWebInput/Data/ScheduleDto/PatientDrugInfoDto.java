package com.example.MedicalWebInput.Data.ScheduleDto;

public class PatientDrugInfoDto {
    private String patientName;
    private String drugName;

    public PatientDrugInfoDto() {
    }

    public PatientDrugInfoDto(
             String patientName, String drugName) {
        this.patientName = patientName;
        this.drugName = drugName;
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public String getDrugName() {
        return drugName;
    }

    public void setDrugName(String drugName) {
        this.drugName = drugName;
    }
}
