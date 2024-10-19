package com.example.MedicalWebInput.Data.ScheduleDto;

public class ScheduleDto {
    private Long intakes;
    private String[] time;
    private String patientEmail;
    private String drugScientificName;

    public ScheduleDto() {
    }

    public ScheduleDto(Long intakes, String[] time, String patientEmail, String drugScientificName) {
        this.intakes = intakes;
        this.time = time;
        this.patientEmail = patientEmail;
        this.drugScientificName = drugScientificName;
    }

    public Long getIntakes() {
        return intakes;
    }

    public void setIntakes(Long intakes) {
        this.intakes = intakes;
    }

    public String[] getTime() {
        return time;
    }

    public void setTime(String[] time) {
        this.time = time;
    }

    public String getPatientEmail() {
        return patientEmail;
    }

    public void setPatientEmail(String patientEmail) {
        this.patientEmail = patientEmail;
    }

    public String getDrugScientificName() {
        return drugScientificName;
    }

    public void setDrugScientificName(String drugScientificName) {
        this.drugScientificName = drugScientificName;
    }
}
