package com.fcb.cafeDaManha.controller;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fcb.cafeDaManha.dto.ColaboradorDTO;
import com.fcb.cafeDaManha.entities.Colaborador;
import com.fcb.cafeDaManha.service.ColaboradorService;

@RestController
@RequestMapping(value = "/colaborador")
public class ColaboradorController {

	@Autowired
	private ColaboradorService colaboradorService;

	@GetMapping("/page")
	public ResponseEntity<Page<ColaboradorDTO>> buscarColaborador(
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPage", defaultValue = "24") Integer linesPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {

		Page<ColaboradorDTO> list = colaboradorService.buscarColaborador(page, linesPage, direction, orderBy);
		return ResponseEntity.ok().body(list);
	}
	
	@PostMapping
	public ResponseEntity<String> insert(@RequestBody ColaboradorDTO objDTO) {
		Colaborador obj = colaboradorService.fromDTO(objDTO);
		colaboradorService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body("Cliente adicionado com sucesso, URI = " + uri);
	}

	@GetMapping
	public ResponseEntity<ColaboradorDTO> buscarPorParametro(
			@RequestParam(value = "valor", defaultValue = "") String valor) {
		ColaboradorDTO list = colaboradorService.buscarbuscarPorParametro(valor);
		return ResponseEntity.ok().body(list);
	}
	@GetMapping("/pageCpf")
	public ResponseEntity<Page<ColaboradorDTO>> buscarColaboradorPorCpf(
			@RequestParam(value = "cpf", defaultValue = "") String cpf,
			@RequestParam(value = "page", defaultValue = "0") Integer page,
			@RequestParam(value = "linesPage", defaultValue = "24") Integer linesPage,
			@RequestParam(value = "direction", defaultValue = "ASC") String direction,
			@RequestParam(value = "orderBy", defaultValue = "nome") String orderBy) {

		Page<ColaboradorDTO> list = colaboradorService.buscarColaboradorPorCpf(cpf,page, linesPage, direction, orderBy);
		return ResponseEntity.ok().body(list);
	}

}
