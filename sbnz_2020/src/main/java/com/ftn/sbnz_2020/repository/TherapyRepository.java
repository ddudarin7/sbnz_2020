package com.ftn.sbnz_2020.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.sbnz_2020.facts.Therapy;

@Repository
public interface TherapyRepository extends JpaRepository<Therapy, Long>{
	Therapy findByDescription(String description);
}
