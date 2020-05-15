package com.ftn.sbnz_2020.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ftn.sbnz_2020.facts.Disease;
import com.ftn.sbnz_2020.facts.DiseaseCategory;

@Repository
public interface DiseaseRepository extends JpaRepository<Disease, Long> {
	Disease findByName(String name);
	List<Disease> findAllByDiseaseCategory(DiseaseCategory diseaseCategory);
	Page<Disease> findAllByDiseaseCategory(DiseaseCategory diseaseCategory, 
			Pageable pageable);
}
