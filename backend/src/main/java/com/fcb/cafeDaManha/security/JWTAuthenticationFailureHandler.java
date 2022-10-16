package com.fcb.cafeDaManha.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler,AuthenticationEntryPoint {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
										HttpServletResponse response,
										AuthenticationException exception) throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json");
		response.getWriter().append(json(401, "CPF ou senha inválidos", request.getRequestURI()));

	}
	
	@Override
	public void commence(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException authException) throws IOException, ServletException {
		response.setContentType("application/json");
		response.setStatus(403);
		response.getWriter().append(json(403,"É preciso efetuar login", request.getRequestURI()));
	}
	
	 private String json(Integer status,String message,String path) {
         long date = new Date().getTime();
         return "{\"timestamp\": " + date + ", "
             + "\"status\": " + status + ","
             + "\"error\": \"Não autorizado\", "
             + "\"message\": " + "\"" + message + "\"" + ", "
             + "\"path\": " +  "\"" + path + "\"" + "}";
     }
}
