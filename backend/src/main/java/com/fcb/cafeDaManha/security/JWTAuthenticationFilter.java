package com.fcb.cafeDaManha.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fcb.cafeDaManha.dto.CredenciaisDTO;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{
	
	private	AuthenticationManager authenticationManager;
	private JWTUtil jwtUtil;
	
	public JWTAuthenticationFilter(AuthenticationManager authenticationManager, JWTUtil jwtUtil) {
		setAuthenticationFailureHandler(new JWTAuthenticationFailureHandler());
		this.authenticationManager = authenticationManager;
		this.jwtUtil = jwtUtil;
	}
	
	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			CredenciaisDTO credenciaisDTO = new ObjectMapper().readValue(
					request.getInputStream(), CredenciaisDTO.class);
			return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					credenciaisDTO.getCpf(), credenciaisDTO.getSenha(), new ArrayList<>()));
			
		} catch (IOException e) {
			throw new RuntimeException(e);
		} 
	}
	
	@Override
	protected void successfulAuthentication(HttpServletRequest request,
											HttpServletResponse response, 
											FilterChain chain,
											Authentication authResult) throws IOException, ServletException {
		Long userID = ((UserSS)authResult.getPrincipal()).getId();
		String userName = ((UserSS)authResult.getPrincipal()).getUsername();
		String token = jwtUtil.generatedToken(userName);
		response.addHeader("Authorization", "Bearer " + token);
		response.addHeader("access-control-expose-headers", "Authorization");
		//response.getWriter().write("Login efetuado com sucesso!");
		response.setContentType("application/json");
		response.getWriter().append(json(userID, userName, userName));
	}
	
	 private String json(Long id,String nome,String cpf) {
		return  "{\"message\": \"Login efetuado com sucesso!\", "
				+"\"id\": " + id + ", "
				+ "\"cpf\": " +  "\"" + cpf + "\"" + "}";
     }
}
