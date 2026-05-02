package com.example.MedicalWebInput.Data.HealthRecordDto;

import lombok.Data;

@Data
public class HealthRecordDTO {
    private Long id;
    private Long patientId;
    private String date;
    private String type;
    private String value;
}