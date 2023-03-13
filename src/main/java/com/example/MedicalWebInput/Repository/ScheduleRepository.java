package com.example.MedicalWebInput.Repository;

import com.example.MedicalWebInput.Models.Schedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {
    List<Schedule> findByPatientId(Long patientId);

    Schedule findByPatientIdAndDrugId(Long patientId, Long drugId);
}
