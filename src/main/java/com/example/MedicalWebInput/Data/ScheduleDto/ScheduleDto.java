package com.example.MedicalWebInput.Data.ScheduleDto;

import com.example.MedicalWebInput.Models.Drug;
import com.example.MedicalWebInput.Models.Patient;

import javax.persistence.OneToOne;
import java.util.Date;

public class ScheduleDto {
    private Long id;
    private int dayCount = 0;
    private Date time = new Date();
    private boolean[] confirm = {};
    private Long patientId;
    private Long drugId;

    public ScheduleDto() {
    }

    public ScheduleDto(Long id, int dayCount, Date time, boolean[] confirm, Long patientId, Long drugId) {
        this.id = id;
        this.dayCount = dayCount;
        this.time = time;
        this.confirm = confirm;
        this.patientId = patientId;
        this.drugId = drugId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
