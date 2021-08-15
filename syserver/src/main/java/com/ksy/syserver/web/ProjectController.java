package com.ksy.syserver.web;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ksy.syserver.domain.Project;
import com.ksy.syserver.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	//bindingresult = 에러가 있는지 없는지 
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project,BindingResult result){
		if(result.hasErrors()) {
			//field와 message 꺼내기
			Map<String,String> errorMap = new HashMap<>();
			for(FieldError error : result.getFieldErrors()) {
				errorMap.put(error.getField(), error.getDefaultMessage());
			}
			return new ResponseEntity<Map<String,String>>(errorMap,HttpStatus.BAD_REQUEST);
		}
		
		
		
		Project project1 = projectService.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(project1,HttpStatus.CREATED);
	}
	
}
