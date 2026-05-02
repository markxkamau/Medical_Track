package com.example.MedicalWebInput.Data.StockDto;

import lombok.Data;

@Data
public class CreateDrugStockDTO {
    private Long patientId;
    private String medicationId;
    private String medicationName;
    private int currentStock;
    private int reorderLevel;
    private String unit;
    private String expiration;
}