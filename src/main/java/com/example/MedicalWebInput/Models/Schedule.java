package com.example.MedicalWebInput.Models;

import javax.persistence.*;

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
    private Long intakes;
    private String[] time = {};
    @OneToOne
    private Patient patient = new Patient();
    @OneToOne
    private Drug drug = new Drug();

    public Schedule() {
    }

    public Schedule(Long id, Long intakes, String[] time, Patient patient, Drug drug) {
        this.id = id;
        this.intakes = intakes;
        this.time = time;
        this.patient = patient;
        this.drug = drug;
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
