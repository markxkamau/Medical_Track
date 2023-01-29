package com.example.MedicalWebInput.Data.ScheduleDto;

import java.util.Date;

public class DrugTimetableDto {
    private Long id;
    private int drugCount = 0;
    private String startDate = null;
    private String refillDate = null;
    private Long scheduleId;

    public DrugTimetableDto() {
    }


    public DrugTimetableDto(Long id, int drugCount, String startDate, String refillDate, Long scheduleId) {
        this.id = id;
        this.drugCount = drugCount;
        this.startDate = startDate;
        this.refillDate = refillDate;
        this.scheduleId = scheduleId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDrugCount() {
        return drugCount;
    }

    public void setDrugCount(int drugCount) {
        this.drugCount = drugCount;
    }


    public Long getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(Long scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getRefillDate() {
        return refillDate;
    }

    public void setRefillDate(String refillDate) {
        this.refillDate = refillDate;
    }
}
