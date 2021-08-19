package com.ksy.syserver.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksy.syserver.domain.Backlog;
import com.ksy.syserver.domain.Project;
import com.ksy.syserver.exception.ProjectIdException;
import com.ksy.syserver.repository.BacklogRepository;
import com.ksy.syserver.repository.ProjectRepository;
//서비스를 만드는 이유는 컨트롤러에 너무 많은 로직이 들어가서, 비즈니스 로직을 서비스에 작성하자
@Service
public class ProjectService {

	@Autowired
	private ProjectRepository projectRepository;
	@Autowired
	private BacklogRepository  backlogRepository;
	public Project saveOrUpdateProject(Project project) {
		//logic 
		
		try {
			//project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			//create하는 부분
			if(project.getId() == null) {
				Backlog backlog = new Backlog();
				project.setBacklog(backlog);
				backlog.setProject(project);
				backlog.setProjectIdentifier(project.getProjectIdentifier());
			}
			//수정하는 부분 아마도 안쓸거
			/*
			 * if(project.getId() != null) {
			 * project.setBacklog(backlogRepository.findByProjectIdentifier(project.
			 * getProjectIdentifier())); }
			 */
			return projectRepository.save(project);
		}catch(Exception e) {
			throw new ProjectIdException("id: "+project.getProjectIdentifier().toUpperCase()+" 중복");
		}
	
	}
	public Project findProjectByIdentifier(String projectId) {
		
		Project project = projectRepository.findByProjectIdentifier(projectId);
		if(project == null) {
			throw new ProjectIdException("id: "+projectId+"가 존재하지 않습니다.");
		}else {
		return project;
		}
	}
	//iterable : findall 이나 list를 찾을대 그 데이터를 추출
	public Iterable<Project> findAllProjects(){
		return projectRepository.findAll();
	}
	
	public void deleteProjectById(String projectId) {
		Project project = projectRepository.findByProjectIdentifier(projectId);
		
		if(project == null) {
			throw new ProjectIdException("id: "+projectId+" 로는 삭제가 불가능 합니다.");
		}
		
		projectRepository.delete(project);
	}
	
	public Project updateById(String projectId,Project project) {
		Project updateProject = projectRepository.findByProjectIdentifier(projectId);
		updateProject.setDescription(project.getDescription());
		updateProject.setProjectName(project.getProjectName());
		updateProject.setStart_date(project.getStart_date());
		updateProject.setEnd_date(project.getEnd_date());
		return projectRepository.save(updateProject);
	}
}
