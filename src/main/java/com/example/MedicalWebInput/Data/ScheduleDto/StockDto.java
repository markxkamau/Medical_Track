package com.example.MedicalWebInput.Data.ScheduleDto;

import java.util.Date;

public class StockDto {
    private Long id;
    private int drugCount = 0;
    private String refillDate = null;

    public StockDto() {
    }

    public StockDto(Long id, int drugCount, String refillDate) {
        this.id = id;
        this.drugCount = drugCount;
        this.refillDate = refillDate;
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

    public String getRefillDate() {
        return refillDate;
    }

    public void setRefillDate(String refillDate) {
        this.refillDate = refillDate;
    }
}
