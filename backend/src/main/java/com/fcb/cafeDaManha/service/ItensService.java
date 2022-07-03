package com.fcb.cafeDaManha.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import com.fcb.cafeDaManha.entities.Itens;
import com.fcb.cafeDaManha.entitiesDTO.ItensDTO;
import com.fcb.cafeDaManha.repository.ItensRepository;

public class ItensService {

	@Autowired
	private ItensRepository itensRepository;

	@Transactional(readOnly = true)
	public List<ItensDTO> findAll() {
		List<Itens> list = itensRepository.findItens();
		return list.stream().map(x -> new ItensDTO(x)).collect(Collectors.toList());
	}
	
	@Transactional
	public ItensDTO insert(ItensDTO dto) {
		Itens itens = new Itens(null, dto.getNome(), dto.getStatus());
		itens = itensRepository.save(itens);
		return new ItensDTO(itens);
	}
	
	@Transactional
	public void delete(Long id) {
		itensRepository.deleteById(id);
	}

	@Transactional
	public Itens update(Itens obj) {
		Optional<Itens> optional = itensRepository.findById(obj.getId());
		Itens newItens = optional.get();
		updateData(newItens, obj);
		return itensRepository.save(newItens);
	}

	private void updateData(Itens newItens, Itens obj) {
		newItens.setNome(obj.getNome());
		newItens.setStatus(obj.getStatus());
	}
	
	public Itens fromDTO(ItensDTO objDTO) {
		return new Itens(objDTO.getId(), objDTO.getNome(), objDTO.getStatus());
	}
}
