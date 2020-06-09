package com.ftn.sbnz_2020.dto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BreedDiseasesDTO {

	private String breed;
	
	public class DiseaseCountDTO{
		public DiseaseDTO disease;
		public int count;
		public DiseaseCountDTO(DiseaseDTO disease, int count){
			this.disease = disease;
			this.count = count;
		}
	}
	
	private List<DiseaseCountDTO> data;
	private int totalDiagnoses;
	
	public BreedDiseasesDTO(){
		this.data = new ArrayList<>();
		this.totalDiagnoses = 0;
	}
	
	public void addToData(DiseaseDTO disease, int count){
		this.data.add(new DiseaseCountDTO(disease, count));
	}
	
	public void calculateDiagnoses(){
		for (DiseaseCountDTO dc : this.data)
			this.totalDiagnoses += dc.count;
	}
	
	public void sortData(){
		List<Long> usedIds = new ArrayList<>();
		List<Integer> counts = new ArrayList<>();
		List<DiseaseCountDTO> dcNew = new ArrayList<>();
		
		for (DiseaseCountDTO dc : this.data){
			counts.add(dc.count);
		}
		
		Collections.sort(counts);
		Collections.reverse(counts);
		
		for (int c : counts){
			for (DiseaseCountDTO dc : this.data){
				if (usedIds.contains(dc.disease.getId()))
					continue;
				
				if (dc.count == c){
					dcNew.add(dc);
					usedIds.add(dc.disease.getId());
					break;
				}
			}
		}
		
		this.data = dcNew;
	}

	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public List<DiseaseCountDTO> getData() {
		return data;
	}

	public void setData(List<DiseaseCountDTO> data) {
		this.data = data;
	}

	public int getTotalDiagnoses() {
		return totalDiagnoses;
	}

	public void setTotalDiagnoses(int totalDiagnoses) {
		this.totalDiagnoses = totalDiagnoses;
	}
	
	
}
