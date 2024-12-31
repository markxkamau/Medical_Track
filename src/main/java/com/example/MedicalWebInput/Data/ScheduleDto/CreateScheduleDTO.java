package com.example.MedicalWebInput.Data.ScheduleDto;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
public class CreateScheduleDTO {
    private Long patientEmail;
    private Long drugId = null;
    private String drugName;  // Name of the drug (can be helpful for logging or processing)
    private float drugSize;  // The dosage of the drug
    private String drugPackaging;  // Packaging information
    private String drugPurpose;  // Purpose of the drug
    private Long intakes;  // The number of doses per day
    private List<String> time = new ArrayList<>();  // The specific times to take the medication
    private Date startDate;  // The date the schedule starts
}
