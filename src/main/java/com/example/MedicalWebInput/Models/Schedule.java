package com.example.MedicalWebInput.Models;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
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
    private Long intakes = 1L;
    private String[] time;
    private Date startDate = new Date();
    @OneToOne
    private Patient patient = new Patient();
    @OneToOne
    private Drug drug = new Drug();

    public Schedule() {
    }

    public Schedule(Long intakes, String[] time, Date startDate, Patient patient, Drug drug) {
        this.intakes = intakes;
        this.time = time;
        this.startDate = startDate;
        this.patient = patient;
        this.drug = drug;
    }
    public Schedule(Long intakes, String[] time, Patient patient, Drug drug) {
        this.intakes = intakes;
        this.time = time;
        this.patient = patient;
        this.drug = drug;
    }

    public Schedule(Long id, Long intakes, String[] time, Patient patient, Drug drug) {
        this.id = id;
        this.intakes = intakes;
        this.time = time;
        this.patient = patient;
        this.drug = drug;
    }

    public Schedule(Long id, Long intakes, String[] time, Date startDate, Patient patient, Drug drug) {
        this.id = id;
        this.intakes = intakes;
        this.time = time;
        this.startDate = startDate;
        this.patient = patient;
        this.drug = drug;
    }
}
