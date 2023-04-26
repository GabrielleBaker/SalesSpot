package com.example.Sales_Spot.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
//status on orders shipment
@Entity
public class Status {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long statusId;
	
	private String name;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "status")
	private List<Bestel> bestels;
	
	public Status() {
		
	}
	public Status(String name) {
		super();
		this.name=name;
	}

	public Long getStatusId() {
		return statusId;
	}
	public void setStatusId(Long statusId) {
		this.statusId = statusId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public List<Bestel> getBestels() {
		return bestels;
	}
	public void setBestels(List<Bestel> bestels) {
		this.bestels = bestels;
	}
	
}
