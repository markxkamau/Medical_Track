package com.example.MedicalWebInput.Repository;
import com.example.MedicalWebInput.Models.HealthRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HealthRecordRepository extends JpaRepository<HealthRecord, Long> {}
