package com.example.MedicalWebInput.Repository;

import com.example.MedicalWebInput.Models.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface RecordRepository extends JpaRepository<Record, Long> {
}
