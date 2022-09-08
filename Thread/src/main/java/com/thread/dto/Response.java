package com.thread.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.thread.model.TeamLead;

public class Response {


	private Object object;
	private String status;
	private List<TeamLeadDto> teamLeadDto;
	
	public  List<TeamLeadDto> getTeamLeadDto() {
		return teamLeadDto;
	}
	public void setTeamLeadDto( List<TeamLeadDto> teamLeadDto) {
		this.teamLeadDto = teamLeadDto;
	}
	public Object getObject() {
		return object;
	}
	public void setObject(Object object) {
		this.object = object;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}

}
