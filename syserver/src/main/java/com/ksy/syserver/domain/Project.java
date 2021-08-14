package com.ksy.syserver.domain;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@AllArgsConstructor
@Data
@NoArgsConstructor
@Entity
public class Project {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String projectName;
	private String projectIdentifier;
	private String description;
	private Date start_date;
	private Date end_date;
	
	private Date create_At;
	private Date update_At;     //date tracking
	
	//리스너
	@PrePersist //처음 호출 될때 실행
	protected void onCreate() {
		this.create_At = new Date();
	}
	//sql update 이전에 실행
	@PreUpdate
	protected void onUpdate() {
		this.update_At = new Date();
	}
	
}
