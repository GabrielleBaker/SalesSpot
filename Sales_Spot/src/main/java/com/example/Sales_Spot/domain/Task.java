package com.example.Sales_Spot.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
//tasks for the homepage todo list
@Entity
public class Task {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;

	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "priorityId")
	private Priority priority;

	public Task() {

	}

	public Task(String name, Priority priority) {
		super();
		this.name=name;
		this.priority = priority;
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

	public Priority getPriority() {
		return priority;
	}

	public void setPriority(Priority priority) {
		this.priority = priority;
	}

	@Override
	public String toString() {
		if (this.priority != null)
			return "Task [id=" + id + ", details= " + name + ", priority= " + this.getPriority() + "]";
		else
			return "Task [id=" + id + ", details =" + name + "]";
	}

}
