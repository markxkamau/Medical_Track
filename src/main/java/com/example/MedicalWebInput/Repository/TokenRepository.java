package com.example.MedicalWebInput.Repository;

import com.example.MedicalWebInput.Models.Patient;
import com.example.MedicalWebInput.Models.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TokenRepository extends JpaRepository<Token,Long> {
    Token findByPatientId(Long id);

    Token findByRegistrationToken(String token);
}
