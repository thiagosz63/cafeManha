package com.fcb.cafeDaManha.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fcb.cafeDaManha.dto.ItensDTO;
import com.fcb.cafeDaManha.entities.Itens;
import com.fcb.cafeDaManha.repository.ItensRepository;

@Service
public class ItensService {

	@Autowired
	private ItensRepository itensRepository;
	
	@Transactional(readOnly = true)
	public Page<ItensDTO> findPage(Integer status, Integer page, Integer linesPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
		Page<Itens> pages = itensRepository.myFindAll(status,pageRequest);
		return pages.map(x -> new ItensDTO(x));
	}
	
	@Transactional(readOnly = true)
	public Page<ItensDTO> findColaborador(Long idColaborador,Integer page, Integer linesPage, String direction, String orderBy) {
		PageRequest pageRequest = PageRequest.of(page, linesPage, Direction.valueOf(direction), orderBy);
	Page<Itens> obj = itensRepository.findByColaborador(idColaborador, pageRequest);
		return obj.map(x-> new ItensDTO(x));
	}

	@Transactional
	public Itens insert(Itens obj) {
		obj = itensRepository.save(obj);
		return obj;
	}

	public Itens fromDTO(ItensDTO objDTO) {
		Itens item = new Itens(null, objDTO.getNome(), objDTO.getStatus(), objDTO.getColaborador());
		return item;
	}

}
