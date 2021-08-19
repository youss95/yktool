package com.ksy.syserver.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ksy.syserver.domain.Backlog;
import com.ksy.syserver.domain.Project;
import com.ksy.syserver.domain.ProjectTask;
import com.ksy.syserver.exception.ProjectNotFoundException;
import com.ksy.syserver.repository.BacklogRepository;
import com.ksy.syserver.repository.ProjectRepository;
import com.ksy.syserver.repository.ProjectTaskRepository;

@Service
public class ProjectTaskService {

	@Autowired
	private BacklogRepository backlogRepository;
	@Autowired
	private ProjectTaskRepository projectTaskRepository;
	@Autowired
	private ProjectRepository projectRepository;
	public ProjectTask addProjectTask(String projectIdentifier, ProjectTask projectTask) {
		
		try {
			//PTs to be added to a specific project, != null
			Backlog backlog = backlogRepository.findByProjectIdentifier(projectIdentifier);
			//set the backlog to pt
			projectTask.setBacklog(backlog);
			//project seq = project identi  ex : IDPRO-1...
			Integer BacklogSequence = backlog.getPTSequence();
			BacklogSequence++;
			backlog.setPTSequence(BacklogSequence);
			//add seq to pt
			projectTask.setProjectSequence(projectIdentifier+"-"+BacklogSequence);
			projectTask.setProjectIdentifier(projectIdentifier);
			//고려할 사항 1. initial pritoriy when pri null
			
			  if( projectTask.getPriority() == null) {
			 projectTask.setPriority(3); }
			 
			//initial status is null
			if(projectTask.getStatus()=="" || projectTask.getStatus() ==null) {
				projectTask.setStatus("TO_DO");
			}
			return projectTaskRepository.save(projectTask);
		}catch(Exception e) {
			throw new ProjectNotFoundException("찾을 수가 없습니다.");
		}
		
		
	}

	public List<ProjectTask> findBacklogById(String id) {
		Project project = projectRepository.findByProjectIdentifier(id);
		if(project == null) {
			throw new ProjectNotFoundException("id: "+id+"인 프로젝트를 찾을 수 없습니다.");
		}
		return projectTaskRepository.findByProjectIdentifierOrderByPriority(id);
	}
	
}
