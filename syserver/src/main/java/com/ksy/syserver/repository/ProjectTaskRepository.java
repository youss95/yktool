package com.ksy.syserver.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ksy.syserver.domain.ProjectTask;

public interface ProjectTaskRepository extends JpaRepository<ProjectTask, Long>{

	List<ProjectTask> findByProjectIdentifierOrderByPriority(String id);
}

