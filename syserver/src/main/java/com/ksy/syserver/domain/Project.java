package com.ksy.syserver.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

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
	@NotBlank(message = "이름을 입력하세요")
	private String projectName;
	
	@NotBlank(message = "식별자 필요")
	@Size(min=4, max=5,message = "4 에서 5글자")
	@Column(updatable = false, unique = true)
	private String projectIdentifier;
	
	@NotBlank(message = "빈 칸 은 노노")
	private String description;
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date start_date;
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date end_date;
	@JsonFormat(pattern="yyyy-mm-dd")
	@Column(updatable = false)
	private Date create_At;
	@JsonFormat(pattern="yyyy-mm-dd")
	private Date update_At;     //date tracking
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project") //한방에 쿼리를 다 조회해온다. 연관 관꼐에 있는 모든 엔티티를 가져온다.
	@JsonIgnore
	//cascade all : 삭제하면 모든게 삭제
	private Backlog backlog;  // 1project 1backlog
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
