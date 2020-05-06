package com.ftn.sbnz_2020.facts;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.JoinColumn;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;

@Entity
@Table(name = "therapy")
public class Therapy {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;
    
    @LazyCollection(LazyCollectionOption.FALSE)
    @ManyToMany(cascade={CascadeType.MERGE}) // unidirectional, because there is no use-case for the other direction
    @JoinTable(name = "therapy_medicine", joinColumns = {@JoinColumn(name = "therapy_id")},
                inverseJoinColumns = {@JoinColumn(name = "medicine_id")})
    private List<Medicine> medicines;
	
    private String description;

	public Therapy() {
		super();
	}

	public Therapy(Long id, List<Medicine> medicines, String description) {
		super();
		this.id = id;
		this.medicines = medicines;
		this.description = description;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public List<Medicine> getMedicines() {
		return medicines;
	}

	public void setMedicines(List<Medicine> medicines) {
		this.medicines = medicines;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
    
}
