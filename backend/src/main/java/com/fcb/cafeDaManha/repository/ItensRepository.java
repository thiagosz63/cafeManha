package com.fcb.cafeDaManha.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.fcb.cafeDaManha.entities.Itens;

public interface ItensRepository extends JpaRepository<Itens, Long> {

	@Query(value = "select * from itens where colaborador_id=?1", nativeQuery = true)
	Page<Itens> findByColaborador(Long id, Pageable pageable);

}
