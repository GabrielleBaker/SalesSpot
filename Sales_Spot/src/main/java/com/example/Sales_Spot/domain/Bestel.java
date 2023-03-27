package com.example.Sales_Spot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class Bestel {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String duedate;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "customerId")
	private Customer customer;

	public Bestel() {
	}

	public Bestel(String name, Customer customer, String duedate) {
		super();
		this.name = name;
		this.customer = customer;
		this.duedate = duedate;
	
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDuedate() {
		return duedate;
	}

	public void setDuedate(String duedate) {
		this.duedate = duedate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}



	@Override
	public String toString() {
		if (this.customer != null)
			return "Order [id=" + id + ", name=" + name + ", duedate=" + duedate + ", customer=" + this.getCustomer()
					+"]";

		else
			return "Order [id=" + id + ", name=" + name + ", duedate=" + duedate;

	}
}
