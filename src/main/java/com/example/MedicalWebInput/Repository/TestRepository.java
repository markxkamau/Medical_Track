package com.example.MedicalWebInput.Repository;

import com.example.MedicalWebInput.Models.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TestRepository extends JpaRepository<Test, Long> {
    List<Test> findTestByPatientId(Long patientId);

    Test findTestByPatientEmail(String email);
}
