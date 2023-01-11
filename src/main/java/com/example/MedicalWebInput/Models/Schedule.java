package com.example.MedicalWebInput.Models;

import javax.persistence.*;
import java.util.Date;
@Entity
@Table
public class Schedule {
    @Id
    @SequenceGenerator(
            sequenceName = "schedule_sequence",
            allocationSize = 1,
            name = "schedule_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "schedule_sequence"
    )
    private Long id;
    private int dayCount = 0;
    private Date time = new Date();
    private boolean[] confirm = {};
    @OneToOne
    private Patient patient = new Patient();
    @OneToOne
    private Drug drug = new Drug();

    public Schedule() {
    }

    public Schedule(long id, int dayCount, Date time, boolean[] confirm, Patient patient, Drug drug) {
        this.id = id;
        this.dayCount = dayCount;
        this.time = time;
        this.confirm = confirm;
        this.patient = patient;
        this.drug = drug;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDayCount() {
        return dayCount;
    }

    public void setDayCount(int dayCount) {
        this.dayCount = dayCount;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public boolean[] getConfirm() {
        return confirm;
    }

    public void setConfirm(boolean[] confirm) {
        this.confirm = confirm;
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
