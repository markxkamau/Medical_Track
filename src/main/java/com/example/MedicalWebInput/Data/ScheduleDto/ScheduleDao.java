package com.example.MedicalWebInput.Data.ScheduleDto;

import com.example.MedicalWebInput.Models.Drug;
import com.example.MedicalWebInput.Models.Patient;

import javax.persistence.OneToOne;
import java.util.Date;

public class ScheduleDao {
    private Long intakes = 1L;
    private String[] time;
    private Date startDate;
    private Patient patient;
    private Drug drug;

    public ScheduleDao() {
    }

    public ScheduleDao(Long intakes, String[] time) {
        this.intakes = intakes;
        this.time = time;
    }

    public ScheduleDao(Long intakes, String[] time, Date startDate, Patient patient, Drug drug) {
        this.intakes = intakes;
        this.time = time;
        this.startDate = startDate;
        this.patient = patient;
        this.drug = drug;
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

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }
}
