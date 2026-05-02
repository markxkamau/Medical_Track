package com.example.MedicalWebInput.Repository;
import com.example.MedicalWebInput.Models.Adherence;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdherenceRepository extends JpaRepository<Adherence, Long> {}
