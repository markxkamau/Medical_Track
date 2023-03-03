package com.example.MedicalWebInput.Repository;

import com.example.MedicalWebInput.Models.Photo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<Photo, Long> {
    Photo findByPatientId(Long id);
}
