package com.ksy.syserver.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
public class InvalidLoginResponse {

	private String username;
	private String password;
	
	//하드코딩한 이유 80에서 정보 노출을 안하려고
	public InvalidLoginResponse() {
	
		this.username = "Invalid Username";
		this.password = "Invalid Password";
	}
	
	
	
}
