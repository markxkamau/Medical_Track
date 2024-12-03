package com.example.MedicalWebInput.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class Test {
    @Id
    @SequenceGenerator(
            sequenceName = "test_sequence",
            allocationSize = 1,
            name = "test_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "test_sequence"
    )
    private Long id;
    private int bloodPressure = 0;
    private int weight = 0;
    private int oxygen = 0;
    private int bloodSugar = 0;
    private Date testDate = new Date();
    @OneToOne
    private Patient patient = new Patient();

    public Test() {
    }

    public Test(int bloodPressure, int weight, int oxygen, int bloodSugar, Date testDate, Patient patient) {
        this.bloodPressure = bloodPressure;
        this.weight = weight;
        this.oxygen = oxygen;
        this.bloodSugar = bloodSugar;
        this.testDate = testDate;
        this.patient = patient;
    }

    public Test(long id, int bloodPressure, int weight, int oxygen, int bloodSugar, Patient patient) {
        this.id = id;
        this.bloodPressure = bloodPressure;
        this.weight = weight;
        this.oxygen = oxygen;
        this.bloodSugar = bloodSugar;
        this.patient = patient;
    }

    public Test(Long id, int bloodPressure, int weight, int oxygen, int bloodSugar, Date testDate, Patient patient) {
        this.id = id;
        this.bloodPressure = bloodPressure;
        this.weight = weight;
        this.oxygen = oxygen;
        this.bloodSugar = bloodSugar;
        this.testDate = testDate;
        this.patient = patient;
    }

}
