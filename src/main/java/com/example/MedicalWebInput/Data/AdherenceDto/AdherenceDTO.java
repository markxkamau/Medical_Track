package com.example.MedicalWebInput.Data.AdherenceDto;

import lombok.Data;

@Data
public class AdherenceDTO {
    private Long id;
    private Long patientId;
    private String medicationId;
    private String medicationName;
    private int adherenceRate;
    private int dosesTaken;
    private int dosesMissed;
    private String lastTaken;
}