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
	//autogenerate id field as PK in table
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	private String duedate;
	private Long quantity;
	
	// status entity
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "statusId")
	private Status status;
	
	//customer entity
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "customerId")
	private Customer customer;

	//product entity
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "productId")
	private Product product;
	
	public Bestel() {
	}

	public Bestel(String name, Customer customer,Product product, Long quantity, String duedate, Status status) {
		super();
		this.name = name;
		this.customer = customer;
		this.duedate = duedate;
		this.status=status;
		this.product=product;
		this.quantity=quantity;
	
	}
	//getters and setters
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
	
	
	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
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
		//if status, product and customer are not null 
		if (this.customer != null && this.product!=null && this.status!=null)
			return "Order [id=" + id + ", name "+ name + ", duedate=" + duedate + ", customer=" + this.getCustomer()
					+" product= " +this.getProduct()+" status=" + this.getStatus() +"quantity"+quantity+" ]";
		
		//if customer and status are not null, product null--> dont return product
			else if(this.customer!= null && this.product== null && this.status!=null)
				return "Order [id=" + id + ", name=" + name + ", duedate=" + duedate + ", customer=" + this.getCustomer()
					+" status=" + this.getStatus() +"quantity"+quantity+" ]";
		
		//if customer and product not null, status null --> dont return status
			else if (this.customer != null && this.product!=null && this.status==null)
				return "Order [id=" + id + ", name "+ name + ", duedate=" + duedate + ", customer=" + this.getCustomer()
					+" product= " +this.getProduct() +"quantity"+quantity+" ]";
		
		//if product and status not null
			else if (this.customer == null && this.product!=null && this.status!=null)
					return "Order [id=" + id + ", name "+ name + ", duedate=" + duedate 
							+" product= " +this.getProduct() +" status=" + this.getStatus() +"quantity"+quantity+" ]";
		
		//if customer not null
			else if(this.customer!= null && this.product== null && this.status==null)
					return "Order [id=" + id + ", name=" + name + ", duedate=" + duedate + ", customer=" + this.getCustomer()
							+"quantity"+quantity+" ]";
		
		//if product not null
			else if(this.customer== null && this.product!= null && this.status==null)
					return "Order [id=" + id + ", name=" + name + ", duedate=" + duedate +" product= " +this.getProduct()
							+"quantity"+quantity+" ]";
				
		//if status not null
			else if(this.customer== null && this.product!= null && this.status==null)
					return "Order [id=" + id + ", name=" + name + ", duedate=" + duedate +" status=" + this.getStatus()
							+"quantity"+quantity+" ]";
				
		else
			//if all are null
			return "Order [id=" + id + ", name=" + name + ", duedate=" + duedate+"quantity"+quantity;

	}
}
