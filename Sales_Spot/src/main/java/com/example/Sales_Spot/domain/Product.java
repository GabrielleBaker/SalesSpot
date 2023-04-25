package com.example.Sales_Spot.domain;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

@Entity
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long productId;
	private String name;
	private String description;
	private Long price;
		
	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "product")
	private List<Bestel> bestels;
	
	public Product() {
		
	}
	
	public Product(String name, String description,Long price) {
		super();
		this.name=name;
		this.description=description;
		this.price=price;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Long getPrice() {
		return price;
	}

	public void setPrice(Long price) {
		this.price = price;
	}

	public List<Bestel> getBestels() {
		return bestels;
	}

	public void setBestels(List<Bestel> bestels) {
		this.bestels = bestels;
	}
	@Override
	public String toString() {
		return "Product [productId=" + productId + ", name=" + name + ", description=" + description + ", price="
				+ price + "]";
	}


	}
