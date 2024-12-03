package com.example.MedicalWebInput.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

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


}
