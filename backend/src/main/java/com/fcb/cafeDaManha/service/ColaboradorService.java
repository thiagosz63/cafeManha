package com.fcb.cafeDaManha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcb.cafeDaManha.dto.ColaboradorDTO;
import com.fcb.cafeDaManha.entities.Colaborador;
import com.fcb.cafeDaManha.repository.ColaboradorRepository;
import com.fcb.cafeDaManha.security.UserSS;
import com.fcb.cafeDaManha.service.exceptions.ObjectNotFoundException;

@Service
public class ColaboradorService {

	@Autowired
	private ColaboradorRepository colaboradorRepository;
	
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Transactional(readOnly = true)
	public Page<ColaboradorDTO> buscarColaborador(Integer page, Integer linesPage, String direction, String orderBy) {

		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		Page<Colaborador> pages = colaboradorRepository.buscarColaborador(pageRequest);
		return pages.map(x -> new ColaboradorDTO(x));
	}

	@Transactional
	public void insert(Colaborador obj) {
		colaboradorRepository.iserir(obj.getCpf(), obj.getNome(),
				bCryptPasswordEncoder.encode(obj.getSenha()));
	}

	@Transactional(readOnly = true)
	public ColaboradorDTO buscarbuscarPorParametro(String valor) {
		Colaborador obj = colaboradorRepository.buscarPorCpf(valor);
		UserSS userSS = UserService.authenticated();
		if (obj == null) {
			throw new ObjectNotFoundException(
					"CPF não encontrado!" + " Tipo: " + userSS.getUsername() + " " + Colaborador.class.getName());
		}
		return new ColaboradorDTO(obj);
	}

	public Colaborador fromDTO(ColaboradorDTO objDTO) {
		return new Colaborador(objDTO.getId(), objDTO.getNome(), objDTO.getCpf(), objDTO.getSenha());
	}

	@Transactional(readOnly = true)
	public Page<ColaboradorDTO> buscarColaboradorPorCpf(String cpf, Integer page, Integer linesPage, String direction,
			String orderBy) {

		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		Page<Colaborador> pages = colaboradorRepository.buscarColaboradorPorCpf(cpf, pageRequest);
		return pages.map(x -> new ColaboradorDTO(x));
	}
}
