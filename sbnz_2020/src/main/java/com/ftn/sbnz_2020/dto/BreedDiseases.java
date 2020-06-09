package com.ftn.sbnz_2020.dto;

import java.util.HashMap;

import com.ftn.sbnz_2020.facts.Breed;

public class BreedDiseases {
	
	private Breed breed;
	private HashMap<Long, Integer> diseases;

	public BreedDiseases(){
		this.diseases = new HashMap<Long, Integer>();
	}
	
	public void countOneMore(Long diseaseId){
		if (this.diseases.containsKey(diseaseId))
			this.diseases.put(diseaseId, this.diseases.get(diseaseId) + 1) ;
		else
			this.diseases.put(diseaseId, 1) ;
	}

	public Breed getBreed() {
		return breed;
	}

	public void setBreed(Breed breed) {
		this.breed = breed;
	}

	public HashMap<Long, Integer> getDiseases() {
		return diseases;
	}

	public void setDiseases(HashMap<Long, Integer> diseases) {
		this.diseases = diseases;
	}
	
	
}
