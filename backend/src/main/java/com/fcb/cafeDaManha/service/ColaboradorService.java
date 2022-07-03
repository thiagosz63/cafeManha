package com.fcb.cafeDaManha.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fcb.cafeDaManha.entities.Colaborador;
import com.fcb.cafeDaManha.entitiesDTO.ColaboradorDTO;
import com.fcb.cafeDaManha.repository.ColaboradorRepository;

public class ColaboradorService {

	@Autowired
	private ColaboradorRepository colaboradorRepository;

	@Transactional(readOnly = true)
	public List<ColaboradorDTO> findItensService() {
		List<Colaborador> list = colaboradorRepository.findAllColaborador();
		return list.stream().map(x -> new ColaboradorDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional(readOnly = true)
	public Colaborador findById(Long id) {
		Optional<Colaborador> obj = colaboradorRepository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado " + id + ", tipo " + ColaboradorService.class.getName(), " " + id));
	}
	
	@Transactional
	public ColaboradorDTO insert (ColaboradorDTO dto) {
		Colaborador colaborador = new Colaborador(null, dto.getNome(), dto.getCpf(), dto.getSenha());
		colaborador = colaboradorRepository.save(colaborador);
		return new ColaboradorDTO(colaborador);
	}
	
	@Transactional
	public void delete(Long id) {
		colaboradorRepository.deleteById(id);
	}
	
	@Transactional
	public Colaborador update(Colaborador obj) {
		Optional<Colaborador> optional = colaboradorRepository.findById(obj.getId());
		Colaborador newColaborador = optional.get();
		updateData(newColaborador, obj);
		return colaboradorRepository.save(newColaborador);
	}
	
	private void updateData(Colaborador newColaborador, Colaborador obj) {
		newColaborador.setNome(obj.getNome());
		newColaborador.setCpf(obj.getCpf());
		newColaborador.setSenha(obj.getSenha());
	}
	
	public Colaborador fromDTO(ColaboradorDTO objDTO ) {
		return new Colaborador(objDTO.getId(), objDTO.getNome(), objDTO.getCpf(), objDTO.getSenha());
	}

}
