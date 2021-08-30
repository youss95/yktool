package com.ksy.syserver.security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import com.google.gson.Gson;
import com.ksy.syserver.exception.InvalidLoginResponse;

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint{

	@Override
	public void commence(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse,
			AuthenticationException e) throws IOException, ServletException {
		
	InvalidLoginResponse loginResponse = new InvalidLoginResponse();
	//json 으로
	String jsonLoginResponse = new Gson().toJson(loginResponse);
	//response : server가 말해주는거
	httpServletResponse.setContentType("application/json");
    httpServletResponse.setStatus(401);
    httpServletResponse.getWriter().print(jsonLoginResponse); //display
		
	}
	
}
