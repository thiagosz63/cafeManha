package com.fcb.cafeDaManha.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcb.cafeDaManha.entities.Itens;
import com.fcb.cafeDaManha.entitiesDTO.ItensDTO;
import com.fcb.cafeDaManha.repository.ItensRepository;

@Service
public class ItensService {

	@Autowired
	private ItensRepository itensRepository;

	@Transactional(readOnly=true)
	public List<ItensDTO> findAll() {
		List<Itens> list = itensRepository.findAll();
		return list.stream().map(x -> new ItensDTO(x)).collect(Collectors.toList());
	}
}
