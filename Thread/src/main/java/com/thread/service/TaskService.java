package com.thread.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thread.dao.DeveloperRepository;
import com.thread.dao.DeveloperTaskRespository;
import com.thread.dto.TaskDto;
import com.thread.model.Developer;
import com.thread.model.DeveloperTask;

@Service
public class TaskService {

	@Autowired
	private DeveloperTaskRespository devTaskRepository;
	
	@Autowired
	private DeveloperRepository devRepository;
	
	
	public DeveloperTask saveDeveloperTask(DeveloperTask task) {
		DeveloperTask devTask = devTaskRepository.save(task);
		return devTask;
	}

	public Developer getDeveloper(int  id) {
		Developer developer = devRepository.findDeveloperTaskById(id);
		return developer;
	}

	public List<DeveloperTask> getDeveloperTasks(Developer developer) {
		List<DeveloperTask> devTasks = null;
		if(developer!=null) {
			devTasks = devTaskRepository.findDeveloperTasksByDeveloper(developer); 
		}
		return devTasks;
	}

	public TaskDto getOpenTask(DeveloperTask developerTask) {
		TaskDto devUpdatedTask = new TaskDto();
		if(developerTask.getStatus().equalsIgnoreCase("Completed")) {
			devUpdatedTask = updateTaskStatus(developerTask);    
		} else if(developerTask.getStatus().equalsIgnoreCase("Open")) {
			devUpdatedTask = updateTaskStatus(developerTask);
		} else if(developerTask.getStatus().equalsIgnoreCase("Closed")) {
			devUpdatedTask = updateTaskStatus(developerTask);
		} else {
			devUpdatedTask = updateTaskStatus(developerTask);
		}
		return devUpdatedTask;
	}

	public TaskDto updateTaskStatus(DeveloperTask developerTask) {
		DeveloperTask devTask = null;
		TaskDto devUpdatedTask = null;
		if(developerTask.getStatus().equalsIgnoreCase("Open")) {
			devTaskRepository.updateTaskStatusById(developerTask.getId());
			devTask = devTaskRepository.findDeveloperTaskById(developerTask.getId());
			devUpdatedTask = new TaskDto(devTask.getTaskDescription(),devTask.getStatus(),devTask.getId());
//			devUpdatedTask.setId(devTask.getId());
//		    devUpdatedTask.setStatus(devTask.getStatus());
//		    devUpdatedTask.setTaskDescription(devTask.getTaskDescription());
		} else {
			devTask = devTaskRepository.findDeveloperTaskById(developerTask.getId());
			devUpdatedTask = new TaskDto(devTask.getTaskDescription(),devTask.getStatus(),devTask.getId());
		}
		return devUpdatedTask;
	}
}
