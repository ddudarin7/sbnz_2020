package com.ftn.sbnz_2020.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.sbnz_2020.facts.Medicine;
import com.ftn.sbnz_2020.facts.Therapy;

@Repository
public interface TherapyRepository extends JpaRepository<Therapy, Long>{
	Therapy findByDescription(String description);
	List<Therapy> findAllByMedicine(Medicine medicine);
	List<Therapy> findAllByMedicineId(Long id);
	Page<Therapy> findAllByMedicine(Medicine medicine, Pageable pageable);
	Page<Therapy> findAllByMedicineId(Long id, Pageable pageable);
}
