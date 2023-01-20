package com.example.MedicalWebInput.Data.ScheduleDto;

import java.util.Date;

public class ScheduleDto {
    private Long id;
    private Long intakes;
    private String[] time = {};
    private Long patientId;
    private Long drugId;

    public ScheduleDto() {
    }

    public ScheduleDto(Long id, Long intakes, String[] time, Long patientId, Long drugId) {
        this.id = id;
        this.intakes = intakes;
        this.time = time;
        this.patientId = patientId;
        this.drugId = drugId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getPatientId() {
        return patientId;
    }

    public void setPatientId(Long patientId) {
        this.patientId = patientId;
    }

    public Long getDrugId() {
        return drugId;
    }

    public void setDrugId(Long drugId) {
        this.drugId = drugId;
    }
}
