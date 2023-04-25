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
	private String status;
	private Long quantity;
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "customerId")
	private Customer customer;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "productId")
	private Product product;
	
	public Bestel() {
	}

	public Bestel(String name, Customer customer,Product product, Long quantity, String duedate, String status) {
		super();
		this.name = name;
		this.customer = customer;
		this.duedate = duedate;
		this.status=status;
		this.product=product;
		this.quantity=quantity;
	
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
	
	
	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	

	public Long getQuantity() {
		return quantity;
	}

	public void setQuantity(Long quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		if (this.customer != null && this.product!=null)
			return "Order [id=" + id + ", name=" + name + ", duedate=" + duedate + ", customer=" + this.getCustomer()
					+" product= " +this.getProduct()+" status=" + status +"quantity"+quantity+" ]";

		else if(this.customer!= null && this.product== null)
			return "Order [id=" + id + ", name=" + name + ", duedate=" + duedate + ", customer=" + this.getCustomer()
			+" status=" + status +" ]";
		else if( this.customer== null && this.product!= null)
			return "Order [id=" + id + ", name=" + name + ", duedate=" + duedate 
			+" product= " +this.getProduct()+" status=" + status +"quantity"+quantity+" ]";

		else
			return "Order [id=" + id + ", name=" + name + ", duedate=" + duedate+", status=" + status +"quantity"+quantity;

	}
}
