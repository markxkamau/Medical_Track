package com.example.MedicalWebInput.Data.ScheduleDto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ScheduleDTO {
    private Long intakes;
    private String[] time;
    private String patientEmail;

    public ScheduleDTO() {
    }

    public ScheduleDTO(Long intakes, String[] time, String patientEmail) {
        this.intakes = intakes;
        this.time = time;
        this.patientEmail = patientEmail;
    }
}
