package com.thread.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Developer {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String devName;
	private String email;
	private String number;
	private Double salary;
	
	
	@ManyToOne
	@JsonIgnore
	@JoinColumn(name = "leadName")
	private TeamLead teamLead;
	
	public Developer() {
	}

	public Developer(String devName, String email, String number, Double salary, TeamLead teamLead) {
		super();
		this.devName = devName;
		this.email = email;
		this.number = number;
		this.salary = salary;
		this.teamLead = teamLead;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getDevName() {
		return devName;
	}

	public void setDevName(String devName) {
		this.devName = devName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public TeamLead getTeamLead() {
		return teamLead;
	}

	public void setTeamLead(TeamLead teamLead) {
		this.teamLead = teamLead;
	}

	@Override
	public String toString() {
		return "Developer [id=" + id + ", devName=" + devName + ", email=" + email + ", number=" + number + ", salary="
				+ salary + ", teamLead=" + teamLead + "]";
	}

}
