package com.thread.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.thread.model.TeamLead;

@Repository
public interface TeamLeadRespository extends JpaRepository<TeamLead, Integer> {

	public TeamLead getTeamLeadByLeadName(String leadName);

}
