package com.fcb.cafeDaManha.controller;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fcb.cafeDaManha.dto.ItensDTO;
import com.fcb.cafeDaManha.entities.Itens;
import com.fcb.cafeDaManha.service.ItensService;

@RestController
@RequestMapping(value = "/itens")
public class ItensController {

	@Autowired
	private ItensService itensService;

	@GetMapping("/page")
	public ResponseEntity<Page<ItensDTO>> findPage(
			@RequestParam(value = "status", defaultValue = "1") Integer status,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPage", defaultValue = "24") Integer linesPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy) {
		Page<ItensDTO> list = itensService.findPage(status,page, linesPage, direction, orderBy);
		return ResponseEntity.ok().body(list);
	}

	@GetMapping("/colaborador")
	public ResponseEntity<Page<ItensDTO>> findColaborador(
			@RequestParam(value = "id") Long idColaborador,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPage", defaultValue = "24") Integer linesPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "id") String orderBy){
		Page<ItensDTO> obj = itensService.findColaborador(idColaborador,page, linesPage, direction, orderBy);
		return ResponseEntity.ok().body(obj);
	}

	@PostMapping
	public ResponseEntity<String> insert(@Valid @RequestBody ItensDTO objDTO) {
		Itens obj = itensService.fromDTO(objDTO);
		obj = itensService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body("Item adicionado com sucesso, URI = " + uri);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Void> update(@RequestBody ItensDTO objDTO, @PathVariable Long id) {
		Itens obj = itensService.fromDTO(objDTO);
		obj.setId(id);
		obj = itensService.update(obj);
		return ResponseEntity.noContent().build();
	}
}
