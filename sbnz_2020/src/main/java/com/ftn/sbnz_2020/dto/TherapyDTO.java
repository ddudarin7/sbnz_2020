package com.ftn.sbnz_2020.dto;

import java.util.ArrayList;
import java.util.List;

import com.ftn.sbnz_2020.facts.Medicine;
import com.ftn.sbnz_2020.facts.Therapy;

public class TherapyDTO {
	
	private Long id;
	private List<MedicineDTO> medicines;
	private String description;
	
	public TherapyDTO(){
		
	}
	
	public TherapyDTO(Long id, String description, List<MedicineDTO> medicines) {
		this.id = id;
		this.medicines = medicines;
		this.description = description;
	}
	
	public TherapyDTO(Long id, String description) {
		this.id = id;
		this.medicines = new ArrayList<MedicineDTO>();
		this.description = description;
	}
	
	public TherapyDTO(Therapy therapy){
		if (therapy.getId() != null)
			this.id = therapy.getId();
		if (therapy.getDescription() != null)
			this.description = therapy.getDescription();
		this.medicines = new ArrayList<MedicineDTO>();
		if (therapy.getMedicines() != null)
			for (Medicine medicine : therapy.getMedicines())
				this.medicines.add(new MedicineDTO(medicine));
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<MedicineDTO> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<MedicineDTO> medicines) {
		this.medicines = medicines;
	}
	
}
