package com.ksy.syserver.domain;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Transient;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@NoArgsConstructor
@AllArgsConstructor
@Data
@Entity
public class User implements UserDetails{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	//@Email(message = "Username needs to be an email") //정규식 같은거
	@NotBlank(message = "username is required")
	@Column(unique = true)
	private String username;
	@NotBlank(message = "fullname required")
	private String fullName;
	@NotBlank(message = "비밀번호 필요")
	private String password;
	@Transient //직렬화 과정에서 제외, 데이터를 전송하고 싶지 않을때
	private String confirmPassword;
	private Date created_At;
	private Date updated_At;
	
	//ontomany project
	
	@PrePersist
	protected void onCreate() {
		this.created_At = new Date();
	}
	
	@PreUpdate
	protected void onUpdate() {
		this.updated_At = new Date();
	}
	
	//userdetail interface methods
	@Override
	@JsonIgnore
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	@JsonIgnore
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	@JsonIgnore
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	@JsonIgnore
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	@JsonIgnore
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}
}
