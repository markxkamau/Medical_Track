package com.example.MedicalWebInput.Data.ScheduleDto;

import lombok.Data;
import java.util.List;

@Data
public class ScheduleDTO {
    private Long id;
    private Long patientId;
    private String medicationId;
    private String medicationName;
    private String dosage;
    private String frequency;
    private List<String> times;
    private String startDate;
    private String endDate;
    private String instructions;
}