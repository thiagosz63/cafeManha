package com.fcb.cafeDaManha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcb.cafeDaManha.entities.Colaborador;
import com.fcb.cafeDaManha.entitiesDTO.ColaboradorDTO;
import com.fcb.cafeDaManha.repository.ColaboradorRepositorys;

@Service
public class ColaboradorService {

	@Autowired
	private ColaboradorRepositorys colaboradorRepository;

	@Transactional(readOnly = true)
	public Page<ColaboradorDTO> buscarColaborador(Integer page, Integer linesPage, String direction, String orderBy) {

		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		Page<Colaborador> pages = colaboradorRepository.buscarColaborador(pageRequest);
		return pages.map(x -> new ColaboradorDTO(x));
	}

	@Transactional
	public void insert(Colaborador obj) {
		colaboradorRepository.iserir(obj.getCpf(), obj.getNome(), obj.getSenha());

	}

	@Transactional(readOnly = true)
	public ColaboradorDTO buscarbuscarPorParametro(String valor) {
		Colaborador list = colaboradorRepository.buscarPorParametro(valor);
		return new ColaboradorDTO(list);
	}

	public Colaborador fromDTO(ColaboradorDTO objDTO) {
		return new Colaborador(objDTO.getId(), objDTO.getNome(), objDTO.getCpf(), objDTO.getSenha());
	}
	
	@Transactional(readOnly = true)
	public Page<ColaboradorDTO> buscarColaboradorPorCpf(String cpf,Integer page, Integer linesPage, String direction, String orderBy) {

		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		Page<Colaborador> pages = colaboradorRepository.buscarColaboradorPorCpf(cpf,pageRequest);
		return pages.map(x -> new ColaboradorDTO(x));
	}
}
