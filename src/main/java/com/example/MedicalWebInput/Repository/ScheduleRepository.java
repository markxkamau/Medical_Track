package com.example.MedicalWebInput.Repository;

import com.example.MedicalWebInput.Models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByPatientId(Long patientId);
}
