package com.example.MedicalWebInput.Data.ScheduleDto;

import com.example.MedicalWebInput.Models.Drug;
import com.example.MedicalWebInput.Models.Patient;

import javax.persistence.OneToOne;
import java.util.Date;

public class ScheduleDao {
    private Long intakes = 1L;
    private String[] time;
    private Date startDate;
    private Long patientId;
    private Long drugId;

    public ScheduleDao() {
    }

    public ScheduleDao(Long intakes, String[] time) {
        this.intakes = intakes;
        this.time = time;
    }


    public ScheduleDao(Long intakes, String[] time, Date startDate, Long patientId, Long drugId) {
        this.intakes = intakes;
        this.time = time;
        this.startDate = startDate;
        this.patientId = patientId;
        this.drugId = drugId;
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
