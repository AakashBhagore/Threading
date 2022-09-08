package com.thread.dto;

import java.util.List;

import com.thread.model.Developer;

public class TeamLeadDto {

	private String leadName;
	private String email;
	private Double salary;
	private List<DeveloperDto> developers;
	
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
	public Double getSalary() {
		return salary;
	}
	public void setSalary(Double salary) {
		this.salary = salary;
	}
	public List<DeveloperDto> getDevelopers() {
		return developers;
	}
	public void setDevelopers(List<DeveloperDto> developers) {
		this.developers = developers;
	}
	public TeamLeadDto(String leadName, String email, Double salary) {
		super();
		this.leadName = leadName;
		this.email = email;
		this.salary = salary;
	}
	@Override
	public String toString() {
		return "TeamLeadDto [leadName=" + leadName + ", email=" + email + ", salary=" + salary + ", developers="
				+ developers + "]";
	}
}
