package com.example.MedicalWebInput.Repository;

import com.example.MedicalWebInput.Models.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DrugRepository extends JpaRepository<Drug,Long> {
    List<Drug> findByPatientId(Long patientId);
}
