package com.ksy.syserver.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksy.syserver.domain.ProjectTask;
import com.ksy.syserver.service.MapValidationErrorService;
import com.ksy.syserver.service.ProjectTaskService;

@RestController
@RequestMapping("/api/backlog")
public class BacklogController {

	@Autowired
	private ProjectTaskService projectTaskService;
	
	@Autowired
	private MapValidationErrorService MapValidationErrorService;
	
	@PostMapping("/{backlog_id}")
	public ResponseEntity<?> addPTtoBacklog(@PathVariable String backlog_id, @Valid @RequestBody ProjectTask projectTask,BindingResult result){
		ResponseEntity<?>errorMap = MapValidationErrorService.MapValidationService(result);
		if(errorMap != null) return errorMap;
		
		ProjectTask projectTask1 = projectTaskService.addProjectTask(backlog_id, projectTask);
		return new ResponseEntity<ProjectTask>(projectTask1,HttpStatus.CREATED);
	}
	
	@GetMapping("/{backlog_id}")
	public ResponseEntity<List<ProjectTask>> getProjectBacklog(@PathVariable String backlog_id){
		return new ResponseEntity<List<ProjectTask>>(projectTaskService.findBacklogById(backlog_id),HttpStatus.OK);
	}
	
}
