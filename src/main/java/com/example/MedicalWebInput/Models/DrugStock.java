package com.example.MedicalWebInput.Models;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
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

    public DrugStock() {
    }

    public DrugStock(Long id, int drugCount, Date refillDate, Drug drug) {
        this.id = id;
        this.drugCount = drugCount;
        this.refillDate = refillDate;
        this.drug = drug;
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

    public Date getRefillDate() {
        return refillDate;
    }

    public void setRefillDate(Date refillDate) {
        this.refillDate = refillDate;
    }

    public Drug getDrug() {
        return drug;
    }

    public void setDrug(Drug drug) {
        this.drug = drug;
    }
}
