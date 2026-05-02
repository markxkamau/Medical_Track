package com.example.MedicalWebInput.Data.HealthRecordDto;

import lombok.Data;

@Data
public class CreateHealthRecordDTO {
    private Long patientId;
    private String date;
    private String type;
    private String value;
}