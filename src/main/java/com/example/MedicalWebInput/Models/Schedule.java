package com.example.MedicalWebInput.Models;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
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

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "patient_id")
    private Patient patient;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "drug_id")
    private Drug drug;

    public Schedule(Long intakes, String[] time,  Patient patient, Drug drug) {
        this.intakes = intakes;
        this.time = time;
        this.startDate = startDate;
        this.patient = patient;
        this.drug = drug;
    }
}