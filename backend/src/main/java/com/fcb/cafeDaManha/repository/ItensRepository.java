package com.fcb.cafeDaManha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.fcb.cafeDaManha.entities.Itens;

public interface ItensRepository extends JpaRepository<Itens, Long> {
	
}
