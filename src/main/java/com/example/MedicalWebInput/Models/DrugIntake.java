package com.example.MedicalWebInput.Models;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
public class DrugIntake {
    @Id
    @SequenceGenerator(
            sequenceName = "drug_intake_sequence",
            name = "drug_intake_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "drug_intake_sequence"
    )
    private Long id;
    @ManyToOne
    private Drug drug;
    private LocalDateTime intakeTime;
    private boolean taken;

    public DrugIntake() {
    }

    public DrugIntake(LocalDateTime intakeTime, boolean taken) {
        this.intakeTime = intakeTime;
        this.taken = taken;
    }

    public DrugIntake(Drug drug, LocalDateTime intakeTime, boolean taken) {
        this.drug = drug;
        this.intakeTime = intakeTime;
        this.taken = taken;
    }

    public DrugIntake(Drug drug, LocalDateTime intakeTime, boolean taken, Long id) {
        this.drug = drug;
        this.intakeTime = intakeTime;
        this.taken = taken;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }

    public LocalDateTime getIntakeTime() {
        return intakeTime;
    }

    public void setIntakeTime(LocalDateTime intakeTime) {
        this.intakeTime = intakeTime;
    }

    public boolean isTaken() {
        return taken;
    }

    public void setTaken(boolean taken) {
        this.taken = taken;
    }
}
