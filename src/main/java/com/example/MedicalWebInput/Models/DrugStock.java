package com.example.MedicalWebInput.Models;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class DrugStock {
    @Id
    @SequenceGenerator(
            sequenceName = "stock_sequence",
            allocationSize = 1,
            name = "stock_sequence"
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "stock_sequence"
    )
    private Long id;
    private int drugCount = 0;
    private Date refillDate = new Date();
    @OneToOne
    private Drug drug;
    @OneToOne
    private Patient patient;

    public DrugStock() {
    }

    public DrugStock(Long id, int drugCount, Date refillDate, Drug drug) {
        this.id = id;
        this.drugCount = drugCount;
        this.refillDate = refillDate;
        this.drug = drug;
    }

}
