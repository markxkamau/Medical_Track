package com.example.MedicalWebInput.Repository;

import com.example.MedicalWebInput.Models.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DrugRepository extends JpaRepository<Drug,Long> {
    List<Drug> findByPatientId(Long patientId);

    void deleteById(Long drugId);
}
