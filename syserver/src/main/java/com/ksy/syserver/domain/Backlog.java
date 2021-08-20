package com.ksy.syserver.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Backlog {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	private Integer PTSequence =0 ;
	private String projectIdentifier;
	
	//OnetoOne 하나의 프로젝트는 1개의 backlog
	@OneToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "project_id",nullable = false)
	@JsonIgnore //child side에서 추가
	private Project project;
	//onetomany 1 project many tasks
	//orphanremove : 부모엔티티와 연관관계가 끊어진 엔티티를 자동 삭제 없으면 delete by id가 안됨
	//-> id(부모가 삭제 되면) 연결되 있는 sequence(자식)들이 관계가 다 끊김
	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.REFRESH, mappedBy = "backlog",orphanRemoval = true)
	private List<ProjectTask> projectTasks = new ArrayList<>();
	
	
	
}
