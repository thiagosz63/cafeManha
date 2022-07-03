package com.fcb.cafeDaManha.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.fcb.cafeDaManha.entities.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {
			
	List<Colaborador> findAllColaborador();
}
