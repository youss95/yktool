package com.ksy.syserver.web;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
	
	@GetMapping("/{backlog_id}/{pt_id}") // /identifier/projectSequence
	public ResponseEntity<?> getProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id){
		ProjectTask projectTask = projectTaskService.findByProjectSequence(backlog_id,pt_id);
		return new ResponseEntity<ProjectTask>(projectTask,HttpStatus.OK);
	}
	//put vs patch put: 전체 업데이트 patch: 부분 업데이트
	@PatchMapping("/{backlog_id}/{pt_id}")
	public ResponseEntity<?> updateProjectTask(@Valid @RequestBody ProjectTask updatedProjectTask,@PathVariable String backlog_id, @PathVariable String pt_id ,BindingResult result){
		ResponseEntity<?>errorMap = MapValidationErrorService.MapValidationService(result);
		if(errorMap != null) return errorMap;
		
		ProjectTask updatedTask = projectTaskService.updateByProjectSequence(updatedProjectTask, backlog_id,pt_id);
		
		return new ResponseEntity<ProjectTask>(updatedTask,HttpStatus.OK);
	}
	
	@DeleteMapping("/{backlog_id}/{pt_id}")
	public ResponseEntity<?> deleteProjectTask(@PathVariable String backlog_id, @PathVariable String pt_id){
		projectTaskService.deletePTByPs(backlog_id, pt_id);
		return new ResponseEntity<String>("삭제 성공",HttpStatus.OK);
	}
	
	
}
