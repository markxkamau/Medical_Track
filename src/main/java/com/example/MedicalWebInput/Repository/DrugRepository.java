package com.example.MedicalWebInput.Repository;

import com.example.MedicalWebInput.Models.Drug;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DrugRepository extends JpaRepository<Drug,Long> {
}
