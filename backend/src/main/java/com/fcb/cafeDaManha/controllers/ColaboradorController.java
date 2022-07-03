package com.fcb.cafeDaManha.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fcb.cafeDaManha.entities.Colaborador;
import com.fcb.cafeDaManha.entitiesDTO.ColaboradorDTO;
import com.fcb.cafeDaManha.service.ColaboradorService;

@RestController
@RequestMapping(value = "/colaborador")
public class ColaboradorController {
	
	@Autowired
	private ColaboradorService colaboradorService;
	
	@GetMapping
	public ResponseEntity<List<ColaboradorDTO>>  findAll() {
		List<ColaboradorDTO> list = colaboradorService.findItensService();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Colaborador> buscar(@PathVariable long id) {
		Colaborador  obj = colaboradorService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<ColaboradorDTO> insert(@RequestBody ColaboradorDTO dto) {
		dto = colaboradorService.insert(dto);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(dto.getId()).toUri();
		return ResponseEntity.created(uri).body(dto);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void>delete(@PathVariable Long id) {
		colaboradorService.delete(id);
	    return ResponseEntity.noContent().build();
	}
	
	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> update(@RequestBody ColaboradorDTO objDto,@PathVariable Long id) {
		Colaborador obj = colaboradorService.fromDTO(objDto);
		obj.setId(id);
		obj = colaboradorService.update(obj);
		return ResponseEntity.noContent().build();
	}

}
