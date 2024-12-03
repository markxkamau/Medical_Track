package com.example.MedicalWebInput.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
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

}
