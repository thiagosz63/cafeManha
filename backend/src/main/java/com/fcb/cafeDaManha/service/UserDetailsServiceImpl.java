package com.fcb.cafeDaManha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.fcb.cafeDaManha.entities.Colaborador;
import com.fcb.cafeDaManha.repository.ColaboradorRepository;
import com.fcb.cafeDaManha.security.UserSS;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private ColaboradorRepository colaboradorRepository;

	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		Colaborador colaborador = colaboradorRepository.buscarPorCpf(cpf);
		if(colaborador == null) {
			throw new UsernameNotFoundException(cpf);
		}
		return new UserSS(colaborador.getId(), colaborador.getCpf(), colaborador.getSenha());
	}
}
