package com.fcb.cafeDaManha.security;

import java.io.IOException;
import java.util.Date;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

public class JWTAuthenticationFailureHandler implements AuthenticationFailureHandler {

	@Override
	public void onAuthenticationFailure(HttpServletRequest request,
										HttpServletResponse response,
										AuthenticationException exception) throws IOException, ServletException {
		response.setStatus(HttpStatus.UNAUTHORIZED.value());
		response.setContentType("application/json");
		response.getWriter().append(json());

	}
	
	 private String json() {
         long date = new Date().getTime();
         return "{\"timestamp\": " + date + ", "
             + "\"status\": 401, "
             + "\"error\": \"Não autorizado\", "
             + "\"message\": \"CPF ou senha inválidos\", "
             + "\"path\": \"/login\"}";
     }
}
