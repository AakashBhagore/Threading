package com.thread.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.thread.dao.DeveloperRepository;
import com.thread.dao.TeamLeadRespository;
import com.thread.dto.DeveloperDto;
import com.thread.dto.Response;
import com.thread.dto.TaskDto;
import com.thread.dto.TeamLeadDto;
import com.thread.model.Developer;
import com.thread.model.DeveloperTask;
import com.thread.model.TeamLead;

@Service
public class CommonService {

	@Autowired 
	private TeamLeadRespository teamLeadRespository;
	
	@Autowired 
	private DeveloperRepository developerRepository;
	
	@Autowired 
	private TaskService taskService;
	
    @Value("${server.tomcat.threads.max}")
	private int threadPoolSize;
    
    int threadCount = 0;
	/**
	 * @author System - 35
	 * @Objective save the teamlead object 
	 * @param teamLead
	 * @return teamLead Object 
	 */
	public TeamLead saveTeamLead(TeamLead teamLead) {
		TeamLead tl = teamLeadRespository.save(teamLead);
		return tl;
	}
	
	/**
	 * @author System - 35
	 * @param developer
	 * @return save the developer with respective teamlead
	 */
	public Developer saveDeveloper(Developer developer) {
		return developerRepository.save(developer);
	}

	/**
	 * @author System - 35
	 * @param leadName
	 * @return get teamlead obj
	 */
	public TeamLead getTeamLead(String leadName) {
		TeamLead tl = teamLeadRespository.getTeamLeadByLeadName(leadName);
		return tl;
	}

	/**
	 * @author System - 35
	 * @return all the details of TeamLead along with Developers
	 */
	public Response getAllData() {
	    Response  response = new Response();
	    List<TeamLeadDto> teamLeadDto = new ArrayList<TeamLeadDto>();
	    List<TeamLead> teamLeadDetails =  teamLeadRespository.findAll();	     
	     try {
	    	 if(teamLeadDetails!=null && !teamLeadDetails.isEmpty()) {
		    	 List<Callable<Object>> task = new ArrayList<Callable<Object>>();
		    	 ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
		    	 List<Future<Object>> future = new ArrayList<Future<Object>>();
		    	 for(TeamLead teamLead: teamLeadDetails) {
		    		 threadCount++;
		    		 if(threadCount <= threadPoolSize) {
		    			 task.add(new Callable<Object>() {
			    			@Override
			    			public TeamLeadDto call() throws Exception {
			    				return getAllDeveloperUpdatedData(teamLead);
			    			}
						});			 
		    		 } 
		    	 }
				 future =  executor.invokeAll(task);
		    	 executor.shutdown();
		    	 for(int i=0;i<future.size();i++) {
		    		 Future<Object> futureResult = future.get(i);
		    		 try {
						TeamLeadDto object = (TeamLeadDto) futureResult.get();
						teamLeadDto.add(object);
					  } catch (InterruptedException e) {
						e.printStackTrace();
					  } catch (ExecutionException e) {
						e.printStackTrace();
					}
		    	 }
		       response.setTeamLeadDto(teamLeadDto);
	    	 }
	     } catch (Exception e) {
	    	 e.printStackTrace();
	     }
	    threadCount = 0;
		return response;
	}

	/**
	 * @author System - 35
	 * @param teamLead
	 * @return TeamLeadDto
	 */
	protected TeamLeadDto getAllDeveloperUpdatedData(TeamLead teamLead) {
		TeamLeadDto teamLeadDto = null;
		List<DeveloperDto> devDetails = new ArrayList<DeveloperDto>();
		try {
			 teamLeadDto = new TeamLeadDto(teamLead.getLeadName(),teamLead.getEmail(),teamLead.getSalary());
			 List<Developer> developers = developerRepository.findByTeamLead(teamLead);
			 if(developers!=null && !developers.isEmpty()) {     
				 ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
			     List<Future<Object>> futureList = new ArrayList<Future<Object>>();
			 	 List<Callable<Object>> task = new ArrayList<Callable<Object>>();
                 for(Developer dev:developers) {
                	 threadCount++;
                	 if(threadCount <= threadPoolSize) {
                		 task.add(new Callable<Object>() {
    						@Override
    						public DeveloperDto call() throws Exception {
    							return updateDeveloperSalary(dev);
    						}
    					});  
                	 }
				 }
                 futureList = executor.invokeAll(task); 
                 executor.shutdown();
                 for(int i=0; i<futureList.size(); i++) {
                	 Future<Object> futureResult = futureList.get(i);
                	 DeveloperDto developer = (DeveloperDto) futureResult.get();
                	 devDetails.add(developer);
                 }
                 teamLeadDto.setDevelopers(devDetails);
			 }
		} catch (Exception e){
			e.printStackTrace();
		}
		return teamLeadDto;
	}

	/**
	 * @author System - 35
	 * @param dev
	 * @return DeveloperDto
	 */
	protected DeveloperDto updateDeveloperSalary(Developer dev) {
//		double salary = 20000;
		DeveloperDto developerDetail = new DeveloperDto();
		try {
//			developerRepository.updateDeveloperSalaryByDevName(dev.getDevName(),salary);
			List<TaskDto> developerTasks = new ArrayList<TaskDto>();
			Developer developer = developerRepository.findDeveloperById(dev.getId());	
			if(dev != null) {
			 List<DeveloperTask> devTaks = taskService.getDeveloperTasks(developer);
			 
			 List<Callable<Object>> taskList = new ArrayList<>();
             ExecutorService executor = Executors.newFixedThreadPool(threadPoolSize);
             List<Future<Object>> futureList = new ArrayList<>();
			 for(DeveloperTask developerTask : devTaks) {
				 if(developerTask.getStatus().equalsIgnoreCase("Completed")) {
					 taskList.add(new Callable<Object>() { 
					    @Override
						public TaskDto call() throws Exception {
							return taskService.getOpenTask(developerTask);
						}
					 });				 
   				 }  else if(developerTask.getStatus().equalsIgnoreCase("Open")) {
   					 taskList.add(new Callable<Object>() {
					    @Override
						public TaskDto call() throws Exception {
							return taskService.getOpenTask(developerTask);
						}
					 });
   				 } else if(developerTask.getStatus().equalsIgnoreCase("Closed")) {
   					 taskList.add(new Callable<Object>() {
					    @Override
						public TaskDto call() throws Exception {
							return taskService.getOpenTask(developerTask);
						}
					 });
   				 } else {
   					 taskList.add(new Callable<Object>() {
					    @Override
						public TaskDto call() throws Exception {
							return taskService.getOpenTask(developerTask);
						}
					 });
   				 }
			 }
			 futureList = executor.invokeAll(taskList);	 
			 executor.shutdown();
			 for(int i = 0; i < futureList.size(); i++) {
				 Future<Object> futureResult = futureList.get(i); 
				 TaskDto devTask =(TaskDto) futureResult.get();
				 developerTasks.add(devTask);
			 }
			 developerDetail.setDevName(developer.getDevName());
			 developerDetail.setEmail(developer.getEmail());
			 developerDetail.setNumber(developer.getNumber());
			 developerDetail.setSalary(developer.getSalary());
			 developerDetail.setDevTasks(developerTasks);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return developerDetail;
	}	
}
