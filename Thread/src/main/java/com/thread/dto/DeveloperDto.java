package com.thread.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.thread.model.DeveloperTask;

@JsonInclude(Include.NON_NULL)
public class DeveloperDto {

	private String devName;
	private String email;
	private String number;
	private Double salary;
	private String leadName;
	private List<TaskDto> devTasks;
	
	public List<TaskDto> getDevTasks() {
		return devTasks;
	}
	public void setDevTasks(List<TaskDto> devTasks) {
		this.devTasks = devTasks;
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
	public String getLeadName() {
		return leadName;
	}
	public void setLeadName(String leadName) {
		this.leadName = leadName;
	}
}
