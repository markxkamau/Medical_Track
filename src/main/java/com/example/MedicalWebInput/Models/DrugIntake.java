package com.example.MedicalWebInput.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table
@Getter
@Setter
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
    @OneToOne
    private Patient patient;

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
}
