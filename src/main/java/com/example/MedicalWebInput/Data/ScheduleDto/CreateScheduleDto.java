package com.example.MedicalWebInput.Data.ScheduleDto;

import java.util.Date;

public class CreateScheduleDto {
    private int dayCount = 0;
    private Date time = new Date();
    private boolean[] confirm = {};

    public CreateScheduleDto() {
    }

    public CreateScheduleDto(int dayCount, Date time, boolean[] confirm) {
        this.dayCount = dayCount;
        this.time = time;
        this.confirm = confirm;
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
}
