package com.thread.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class TeamLead {

	@Id 
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String leadName;
	private String email;
	private double salary;
	
	
	public TeamLead(Integer id, String leadName, String email, double salary) {
		super();
		this.id = id;
		this.leadName = leadName;
		this.email = email;
		this.salary = salary;
	}	
	
	public TeamLead() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLeadName() {
		return leadName;
	}

	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}	
}
