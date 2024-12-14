package com.example.MedicalWebInput.Repository;

import com.example.MedicalWebInput.Models.Drug;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DrugRepository extends JpaRepository<Drug, Long> {

    void deleteById(Long drugId);

    Drug findByDrugScientificName(String drugScientificName);
}
