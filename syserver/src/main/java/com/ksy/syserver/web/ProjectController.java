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
import com.ksy.syserver.service.MapValidationErrorService;
import com.ksy.syserver.service.ProjectService;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	@Autowired
	private ProjectService projectService;
	@Autowired
	MapValidationErrorService mapValidationService;
	//bindingresult = 에러가 있는지 없는지 
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project,BindingResult result){
		//id 유니크 에러를 잡지 못하는 이유: mapValidationService는 데이터베이스 체크가 불가능 해서 바로 세이브된다 ->  커스텀 익셉션을 만들자
		ResponseEntity<?> errorMap = mapValidationService.MapValidationService(result);
		if(errorMap!=null) return errorMap;
		
		
		Project project1 = projectService.saveOrUpdateProject(project);
		return new ResponseEntity<Project>(project1,HttpStatus.CREATED);
	}
	
}
