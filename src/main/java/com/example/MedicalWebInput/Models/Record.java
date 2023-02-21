package com.example.MedicalWebInput.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Record {
    @Id
    @SequenceGenerator(
            sequenceName = "record_sequence",
            allocationSize = 1,
            name = "record_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "record_sequence"
    )
    private Long id;
    private int day;
    private boolean[] timetable;
//    private Date currentDate;
    @OneToOne
    private Schedule schedule;

    public Record() {
    }

    public Record(Long id, int day, boolean[] timetable, Schedule schedule) {
        this.id = id;
        this.day = day;
        this.timetable = timetable;
        this.schedule = schedule;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public void setDay(int day) {
        this.day = day;
    }

    public boolean[] getTimetable() {
        return timetable;
    }

    public void setTimetable(boolean[] timetable) {
        this.timetable = timetable;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }

}
