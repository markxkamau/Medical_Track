package com.example.MedicalWebInput.Repository;

import com.example.MedicalWebInput.Models.DrugStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DrugStockRepository extends JpaRepository<DrugStock, Long> {

    DrugStock findByDrugId(Long drugId);
}
