package com.fcb.cafeDaManha.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.fcb.cafeDaManha.entitiesDTO.ItensDTO;
import com.fcb.cafeDaManha.service.ItensService;

public class ItensController {

	@RestController
	@RequestMapping(value = "/products")
	public class ProductController {

		@Autowired
		private ItensService itensService;

		@GetMapping
		public ResponseEntity<List<ItensDTO>> findAll() {
			List<ItensDTO> list = itensService.findAll();
			return ResponseEntity.ok().body(list);
		}
	}
}
