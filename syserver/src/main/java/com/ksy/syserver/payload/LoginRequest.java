package com.ksy.syserver.payload;

import javax.validation.constraints.NotBlank;

import lombok.Data;
@Data
public class LoginRequest {

	@NotBlank(message = "빈 칸은 안되")
	private String username;
	@NotBlank(message = "비밀번호 빈칸 ㄴㄴ")
	private String password;
	
	
	
}
