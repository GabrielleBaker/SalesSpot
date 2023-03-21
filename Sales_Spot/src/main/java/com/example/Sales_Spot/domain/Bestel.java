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
//issues with the many to one relationship keep getting msg about cannot fetch lazily also cant use order as table name
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "statusId")
	private Status status;

	public Bestel() {
	}

	public Bestel(String name, Customer customer, String duedate, Status status) {
		super();
		this.name = name;
		this.customer = customer;
		this.duedate = duedate;
		this.status = status;
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

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	@Override
	public String toString() {
		if (this.customer != null && this.status != null)
			return "Order [id=" + id + ", name=" + name + ", duedate=" + duedate + ", customer=" + this.getCustomer()
					+ ", status=" + this.getStatus() + "]";

		else if (this.customer != null && this.status == null)
			return "Order [id=" + id + ", name=" + name + ", duedate=" + duedate + ", customer=" + this.getCustomer();

		else if (this.customer == null && this.status != null)
			return "Order [id=" + id + ", name=" + name + ", duedate=" + duedate + ", status=" + this.getStatus() + "]";

		else
			return "Order [id=" + id + ", name=" + name + ", duedate=" + duedate;

	}
}
