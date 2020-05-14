package com.ftn.sbnz_2020.dto;

import java.util.ArrayList;
import java.util.List;

import com.ftn.sbnz_2020.facts.Medicine;
import com.ftn.sbnz_2020.facts.Therapy;

public class TherapyDTO {
	
	private Long id;
	private List<MedicineDTO> medicineDTOs;
	private String description;
	
	public TherapyDTO(Long id, String description, List<MedicineDTO> medicineDTOs) {
		this.id = id;
		this.medicineDTOs = medicineDTOs;
		this.description = description;
	}
	
	public TherapyDTO(Long id, String description) {
		this.id = id;
		this.medicineDTOs = new ArrayList<MedicineDTO>();
		this.description = description;
	}
	
	public TherapyDTO(Therapy therapy){
		this.id = therapy.getId();
		this.description = therapy.getDescription();
		this.medicineDTOs = new ArrayList<MedicineDTO>();
		for (Medicine medicine : therapy.getMedicines())
			this.medicineDTOs.add(new MedicineDTO(medicine));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<MedicineDTO> getMedicineDTOs() {
		return medicineDTOs;
	}

	public void setMedicineDTOs(List<MedicineDTO> medicineDTOs) {
		this.medicineDTOs = medicineDTOs;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	

}
