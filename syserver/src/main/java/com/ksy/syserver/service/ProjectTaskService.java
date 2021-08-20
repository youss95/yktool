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
	
	public ProjectTask findByProjectSequence(String backlog_id,String pt_id) {
		
		//존재하는 backlog 여야 한다.
		Backlog backlog = backlogRepository.findByProjectIdentifier(backlog_id);
		if(backlog == null) {
			throw new ProjectNotFoundException(" id :"+backlog_id+" 존재하지 않습니다.");
		}
		//task도 존재해야 한다.
		ProjectTask projectTask = projectTaskRepository.findByProjectSequence(pt_id);
		if(projectTask == null) {
			throw new ProjectNotFoundException("id :"+pt_id+" 존재하지 않습니다.");
		}
		//매칭이 되어야한다.
		if(!projectTask.getProjectIdentifier().equals(backlog_id)) {
			throw new ProjectNotFoundException("프로젝트 아이디하고 url아이디가 매칭 되지 않는다."); //not related each other
		}
		
		return projectTask;
		
		
	}
	
	public ProjectTask updateByProjectSequence(ProjectTask updateTask,String backlog_id, String pt_id) {
		ProjectTask projectTask = findByProjectSequence(backlog_id, pt_id);
		projectTask = updateTask;
		return projectTaskRepository.save(projectTask);
	}
	
	public void deletePTByPs(String backlog_id,String pt_id) {
		ProjectTask projectTask = findByProjectSequence(backlog_id, pt_id);
		/*
		 * Backlog backlog = projectTask.getBacklog(); List<ProjectTask> pts =
		 * projectTask.getBacklog().getProjectTasks(); pts.remove(projectTask);
		 * backlogRepository.save(backlog);
		 */
		projectTaskRepository.delete(projectTask);
	}
}
