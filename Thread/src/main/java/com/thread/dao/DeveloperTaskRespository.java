package com.thread.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.thread.model.Developer;
import com.thread.model.DeveloperTask;


@Repository
public interface DeveloperTaskRespository extends JpaRepository<DeveloperTask, Integer>{
	
	@Transactional
	@Modifying
	@Query("update DeveloperTask set status='Completed' where id=:id")
	void updateTaskStatusById(int id);

	List<DeveloperTask> findDeveloperTasksByDeveloper(Developer developer);

	DeveloperTask findDeveloperTaskById(int id);

}
