package com.example.MedicalWebInput.Models;

import javax.persistence.*;

@Entity
@Table
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
    @OneToOne
    private Patient patient = new Patient();

    public Test() {
    }

    public Test(long id, int bloodPressure, int weight, int oxygen, int bloodSugar, Patient patient) {
        this.id = id;
        this.bloodPressure = bloodPressure;
        this.weight = weight;
        this.oxygen = oxygen;
        this.bloodSugar = bloodSugar;
        this.patient = patient;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getBloodPressure() {
        return bloodPressure;
    }

    public void setBloodPressure(int bloodPressure) {
        this.bloodPressure = bloodPressure;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getOxygen() {
        return oxygen;
    }

    public void setOxygen(int oxygen) {
        this.oxygen = oxygen;
    }

    public int getBloodSugar() {
        return bloodSugar;
    }

    public void setBloodSugar(int bloodSugar) {
        this.bloodSugar = bloodSugar;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
}
