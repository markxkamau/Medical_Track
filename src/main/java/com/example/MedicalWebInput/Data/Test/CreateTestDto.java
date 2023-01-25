package com.example.MedicalWebInput.Data.Test;

import java.util.Date;

public class CreateTestDto {
    private int bloodPressure = 0;
    private int weight = 0;
    private int oxygen = 0;
    private int bloodSugar = 0;
    private Long patientId = 0L;
    private Date testDate;

    public CreateTestDto() {
    }

    public CreateTestDto(int bloodPressure, int weight, int oxygen, int bloodSugar, Long patientId, Date testDate) {
        this.bloodPressure = bloodPressure;
        this.weight = weight;
        this.oxygen = oxygen;
        this.bloodSugar = bloodSugar;
        this.patientId = patientId;
        this.testDate = testDate;
    }


    public CreateTestDto(int bloodPressure, int weight, int oxygen, int bloodSugar, Long patientId) {
        this.bloodPressure = bloodPressure;
        this.weight = weight;
        this.oxygen = oxygen;
        this.bloodSugar = bloodSugar;
        this.patientId = patientId;
    }

    public int getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(int bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getOxygen() {
        return oxygen;
    }

    public void setOxygen(int oxygen) {
        this.oxygen = oxygen;
    }

    public int getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(int bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public Date getTestDate() {
        return testDate;
    }

    public void setTestDate(Date testDate) {
        this.testDate = testDate;
    }

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }
}
