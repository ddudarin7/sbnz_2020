package com.ftn.sbnz_2020.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.sbnz_2020.facts.Patient;

@Repository
public interface PatientRepository extends JpaRepository<Patient, Long> {
    Patient findByRecordNumber(String recordNumber);
    List<Patient> findAllByOwnerId(Long ownerId);
}
