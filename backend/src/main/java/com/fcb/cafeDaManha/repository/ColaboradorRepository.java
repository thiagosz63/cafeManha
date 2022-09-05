package com.fcb.cafeDaManha.repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.fcb.cafeDaManha.entities.Colaborador;

public interface ColaboradorRepository extends JpaRepository<Colaborador, Long> {

	@Query(value = "SELECT * FROM colaborador",
			countQuery = "SELECT count(*) FROM colaborador", nativeQuery = true)
	Page<Colaborador> buscarColaborador(Pageable pageable);
	
	@Modifying
	@Query(value = "insert into colaborador (cpf,nome,senha)"
			+ "values (?1,?2,?3)",
			nativeQuery = true)
	void iserir(String cpf, String nome, String senha);
	
	@Query(value = "select * from colaborador where cpf=?1",
			nativeQuery = true)
	Colaborador buscarPorCpf(String cpf);
	
	@Query(value = "SELECT * FROM colaborador where cpf=?1",
			countQuery = "SELECT count(*) FROM colaborador", nativeQuery = true)
	Page<Colaborador> buscarColaboradorPorCpf(String cpf,Pageable pageable);
}