package com.example.Sales_Spot.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
//customer entity
@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long customerId;
	private String name;
	private String address;
	private String shippingAddress;
	private String contactNumber;
	private String contactPerson;
	
	//linked in a one to many relationship with the order/bestel entity
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "customer")
	private List<Bestel> bestels;
	
	public Customer() {
		
	}
	
	public Customer(String name, String address, String shippingAddress, String contactNumber, String contactPerson) {
		super();
		this.name=name;
		this.address=address;
		this.shippingAddress=shippingAddress;
		this.contactNumber=contactNumber;
		this.contactPerson=contactPerson;
	}

	
//getters and setters for fields in the customer entity
	public Long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(Long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getShippingAddress() {
		return shippingAddress;
	}

	public void setShippingAddress(String shippingAddress) {
		this.shippingAddress = shippingAddress;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getContactPerson() {
		return contactPerson;
	}

	public void setContactPerson(String contactPerson) {
		this.contactPerson = contactPerson;
	}

	public List<Bestel> getBestels() {
		return bestels;
	}

	public void setBestels(List<Bestel> bestels) {
		this.bestels = bestels;
	}

	@Override
	public String toString() {
		return "Customer [id=" + customerId + ", name=" + name + ", address=" + address + ", shippingAddress=" + shippingAddress
				+ ", contactNumber=" + contactNumber + ", contactPerson=" + contactPerson + "]";
	}
	

	}
