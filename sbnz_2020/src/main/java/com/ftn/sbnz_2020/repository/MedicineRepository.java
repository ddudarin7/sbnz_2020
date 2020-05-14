package com.ftn.sbnz_2020.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.sbnz_2020.facts.Medicine;

@Repository
public interface MedicineRepository extends JpaRepository<Medicine, Long>{
	Medicine findByName(String name);
}
