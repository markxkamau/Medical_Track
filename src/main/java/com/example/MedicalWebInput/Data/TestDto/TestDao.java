package com.example.MedicalWebInput.Data.TestDto;

import java.util.Date;

public class TestDao {
    private String email;
    private int bloodPressure = 0;
    private int weight = 0;
    private int oxygen = 0;
    private int bloodSugar = 0;
    private Date testDate = new Date();

    public TestDao(String email, int bloodPressure, int weight, int oxygen, int bloodSugar, Date testDate) {
        this.email = email;
        this.bloodPressure = bloodPressure;
        this.weight = weight;
        this.oxygen = oxygen;
        this.bloodSugar = bloodSugar;
        this.testDate = testDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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
}
