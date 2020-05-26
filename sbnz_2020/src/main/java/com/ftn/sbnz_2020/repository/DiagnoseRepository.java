package com.ftn.sbnz_2020.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.ftn.sbnz_2020.facts.Diagnose;

public interface DiagnoseRepository extends JpaRepository<Diagnose, Long>{
	List<Diagnose> findAllByDiseaseId(Long diseaseId);
	Page<Diagnose> findAllByDiseaseId(Long diseaseId, Pageable pageable);
	List<Diagnose> findByPatientId(Long patientId);
	List<Diagnose> findByDiseaseId(Long diseaseId);
	List<Diagnose> findByVetId(Long vetId);
}
