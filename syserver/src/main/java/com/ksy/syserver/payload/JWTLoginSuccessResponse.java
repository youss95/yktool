package com.ksy.syserver.payload;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class JWTLoginSuccessResponse {

	private boolean success;
	private String token;
	
	
	
}
