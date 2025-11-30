package com.example.MedicalWebInput.Repository;

import com.example.MedicalWebInput.Models.ERole;
import com.example.MedicalWebInput.Models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findByName(ERole name);
}
