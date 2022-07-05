package com.fcb.cafeDaManha;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fcb.cafeDaManha.entities.Colaborador;
import com.fcb.cafeDaManha.entities.Itens;
import com.fcb.cafeDaManha.entities.enums.Status;
import com.fcb.cafeDaManha.repository.ColaboradorRepository;
import com.fcb.cafeDaManha.repository.ItensRepository;

@SpringBootApplication
public class CafeDaManhaApplication implements CommandLineRunner {
	
	@Autowired
	private ColaboradorRepository colaboradorRepositorys;
	
	@Autowired
	private ItensRepository itensRepository;

	public static void main(String[] args) {
		SpringApplication.run(CafeDaManhaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		
		Colaborador col1 = new Colaborador(null, "thiago", "168.866.390-80", "123456");
		Colaborador col2 = new Colaborador(null, "Marya", "802.295.340-74", "123456");
		Colaborador col3 = new Colaborador(null, "jose", "930.522.990-50", "123456");
		Colaborador col4 = new Colaborador(null, "jaqueline", "976.666.310-65", "123456");
				
		Itens it1 = new Itens(null, "bolo doce", Status.NAO_DISPONIVEL,col1);
		Itens it2 = new Itens(null, "pão com queijo", Status.NAO_DISPONIVEL,col2);
		Itens it3 = new Itens(null, "tapioca", Status.NAO_DISPONIVEL,col2);
		Itens it4 = new Itens(null, "suco de laranja", Status.NAO_DISPONIVEL,col3);
		Itens it5 = new Itens(null, "suco de limão", Status.NAO_DISPONIVEL,col4);
				
		col1.getItens().addAll(Arrays.asList(it1));
		col2.getItens().addAll(Arrays.asList(it2,it3));
		col3.getItens().addAll(Arrays.asList(it4));
		col4.getItens().addAll(Arrays.asList(it5));
		
		
		colaboradorRepositorys.saveAll(Arrays.asList(col1,col2,col3,col4));
		itensRepository.saveAll(Arrays.asList(it1,it2,it3,it4,it5));
		
	}
}
