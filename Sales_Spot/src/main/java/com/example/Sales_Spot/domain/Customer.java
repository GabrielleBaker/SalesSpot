package com.example.Sales_Spot.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String address;
	private String shippingAddress;
	private String contactNumber;
	private String contactPerson;
	
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

	@Override
	public String toString() {
		return "Customer [id=" + id + ", name=" + name + ", address=" + address + ", shippingAddress=" + shippingAddress
				+ ", contactNumber=" + contactNumber + ", contactPerson=" + contactPerson + "]";
	}
	

	}
