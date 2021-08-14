package com.ksy.syserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksy.syserver.domain.Project;
import com.ksy.syserver.repository.ProjectRepository;
//서비스를 만드는 이유는 컨트롤러에 너무 많은 로직이 들어가서, 비즈니스 로직을 서비스에 작성하자
@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	
	public Project saveOrUpdateProject(Project project) {
		//logic 
		
		return projectRepository.save(project);
	}
	
}
