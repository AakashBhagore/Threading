package com.thread.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thread.dto.DeveloperDto;
import com.thread.model.Developer;
import com.thread.model.TeamLead;

@Repository
public interface DeveloperRepository extends JpaRepository<Developer, Integer>{
	
//	@Query("SELECT d FROM Developer d WHERE teamLead=:leadName")
	public List<Developer> findByTeamLead(TeamLead teamLead);

	
	@Transactional
	@Modifying
	@Query("update Developer set salary=:salary WHERE devName=:devName")
	void updateDeveloperSalaryByDevName(String devName,double salary);


	public Developer findDeveloperById(Integer id);
	public Developer findDeveloperTaskById(Integer id);
	
}
