package com.thread.dto;

public class TaskDto {

	public String taskDescription;
	private String status;
	public int id;
	public TaskDto(String taskDescription, String status, int id) {
		super();
		this.taskDescription = taskDescription;
		this.status = status;
		this.id = id;
	}
	public TaskDto() {
		// TODO Auto-generated constructor stub
	}
	public String getTaskDescription() {
		return taskDescription;
	}
	public void setTaskDescription(String taskDescription) {
		this.taskDescription = taskDescription;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
	
}
