package com.thread.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class DeveloperTask {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String taskDescription;
    
    @OneToOne
    private Developer developer;
    private String status;
	public DeveloperTask() {
		super();
		// TODO Auto-generated constructor stub
	}
	public DeveloperTask(int id, String taskDescription, Developer developer, String status) {
		super();
		this.id = id;
		this.taskDescription = taskDescription;
		this.developer = developer;
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public Developer getDeveloper() {
		return developer;
	}
	public void setDeveloper(Developer developer) {
		this.developer = developer;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "DeveloperTask [id=" + id + ", taskDescription=" + taskDescription + ", developer=" + developer
				+ ", status=" + status + "]";
	}

	

}
