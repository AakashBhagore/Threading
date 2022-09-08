package com.thread.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.thread.dto.DeveloperDto;
import com.thread.dto.Response;
import com.thread.dto.TaskDto;
import com.thread.model.Developer;
import com.thread.model.DeveloperTask;
import com.thread.model.TeamLead;
import com.thread.service.CommonService;
import com.thread.service.TaskService;
@RestController
public class ThreadController {
	
	@Autowired
	private CommonService commonService;

    @Autowired
    private TaskService taskService;
	
	@GetMapping(value="/teamlead")
	public Response saveTeamLead(@RequestBody TeamLead teamLead) {
		Response response = new Response();
		if(teamLead!=null) {
			TeamLead tl = commonService.saveTeamLead(teamLead);
			response.setObject(tl);
			response.setStatus("SUCCESS");
		} else {
			response.setStatus("FAILURE");
		}
		return response;
	}
	
	@GetMapping(value="/developer")
	public Response saveDeveloper(@RequestBody DeveloperDto developer) {
		Response response = new Response();
		if(developer!=null) {
			TeamLead teamLeadDetails = commonService.getTeamLead(developer.getLeadName());
			Developer dev = new Developer(developer.getDevName(),developer.getEmail(),developer.getNumber(),developer.getSalary(),teamLeadDetails);
//			dev.setTeamLead(teamLeadDetails);
//			dev.setDevName(developer.getDevName());
//			dev.setEmail(developer.getEmail());
//			dev.setNumber(developer.getNumber());
//			dev.setSalary(developer.getSalary());
		    dev = commonService.saveDeveloper(dev);
			response.setObject(dev);
			response.setStatus("SUCCESS");
		} else {
			response.setStatus("FAILURE");
		}
		return response;
	}
	
	@GetMapping(value="/saveTask")
	public Response saveTask(@RequestBody TaskDto taskDto) {
		Response response = new Response();
		DeveloperTask devTask = new DeveloperTask();
		if(taskDto.getId()!=0 && taskDto.getTaskDescription()!=null) {
			Developer developer = taskService.getDeveloper(taskDto.getId());
//			DeveloperTask devTask = new DeveloperTask(taskDto.getTaskDescription(),taskDto.getStatus(),developer);
			devTask.setTaskDescription(taskDto.getTaskDescription());
			devTask.setStatus(taskDto.getStatus());
			devTask.setDeveloper(developer);
			DeveloperTask task =  taskService.saveDeveloperTask(devTask);
			response.setObject(task);
			response.setStatus("SUCCESS");
		} else {
			response.setStatus("FAILURE");
		}
		return response;
	}
	
	@GetMapping(value = "/getall")
	public Response getAll() {
		Response response = commonService.getAllData();
		if(response!=null) {
			response.setStatus("SUCCESS");
		}
		return response;
	}
}
