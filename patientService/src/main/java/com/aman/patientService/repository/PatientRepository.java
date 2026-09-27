package com.aman.patientService.repository;


import com.aman.patientService.model.Patient;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PatientRepository extends JpaRepository<Patient, UUID> {

    boolean existsByEmail(String email);
    boolean existsByEmailAndPatientIdIsNot(String email, UUID id);

    List<Patient> findByIsActiveTrue();

    @EntityGraph(attributePaths = {"appointments"})
    default List<Patient> getStatus() {
        return findAll();
    }

}
