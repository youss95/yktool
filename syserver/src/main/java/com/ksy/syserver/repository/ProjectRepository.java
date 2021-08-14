package com.ksy.syserver.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.ksy.syserver.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{

}
