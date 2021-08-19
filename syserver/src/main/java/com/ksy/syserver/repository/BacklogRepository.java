package com.ksy.syserver.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ksy.syserver.domain.Backlog;

@Repository
public interface BacklogRepository extends JpaRepository<Backlog, Long>{

	Backlog findByProjectIdentifier(String Identifier);
		
	
	
}
