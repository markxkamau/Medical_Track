package com.example.MedicalWebInput.Repository;

import com.example.MedicalWebInput.Models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
}
