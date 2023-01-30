package com.example.MedicalWebInput.Repository;

import com.example.MedicalWebInput.Models.Test;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findTestByPatientId(Long patientId);
}
