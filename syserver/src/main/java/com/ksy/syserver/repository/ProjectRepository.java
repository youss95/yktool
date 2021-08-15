package com.ksy.syserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ksy.syserver.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{

	
	Project findByProjectIdentifier(String projectIdentifier);
	//iterator : 컬렉션 데이터를 하나씩 읽을떄
	
	Iterable<Project> findAll();
	
	
	
}
