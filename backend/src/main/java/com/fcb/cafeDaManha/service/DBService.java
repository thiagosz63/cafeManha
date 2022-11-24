package com.fcb.cafeDaManha.service;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.fcb.cafeDaManha.entities.Colaborador;
import com.fcb.cafeDaManha.entities.Itens;
import com.fcb.cafeDaManha.entities.enums.ItemStatus;
import com.fcb.cafeDaManha.repository.ColaboradorRepository;
import com.fcb.cafeDaManha.repository.ItensRepository;

@Service
public class DBService {

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private ColaboradorRepository colaboradorRepositorys;

	@Autowired
	private ItensRepository itensRepository;
	
	public void instantiateTestDataBase() throws ParseException {
		Colaborador col1 = new Colaborador(
				null, "ADM", "16886639080", bCryptPasswordEncoder.encode("123456"));
		
		Colaborador col2 = new Colaborador(
				null, "Marya", "80229534074", bCryptPasswordEncoder.encode("123456"));
		
		Colaborador col3 = new Colaborador(
				null, "jose", "93052299050", bCryptPasswordEncoder.encode("123456"));
		
		Colaborador col4 = new Colaborador(
				null, "jaqueline", "97666631065", bCryptPasswordEncoder.encode("123456"));

		Itens it1 = new Itens(null, "bolo doce", ItemStatus.Disponivel, col1);
		Itens it2 = new Itens(null, "pão com queijo", ItemStatus.NaoDisponivel, col2);
		Itens it3 = new Itens(null, "tapioca", ItemStatus.NaoDisponivel, col2);
		Itens it4 = new Itens(null, "suco de laranja", ItemStatus.NaoDisponivel, col3);
		Itens it5 = new Itens(null, "suco de limão", ItemStatus.NaoDisponivel, col4);
		Itens it6 = new Itens(null, "bolo de fuba", ItemStatus.Disponivel, col1);

		col1.getItens().addAll(Arrays.asList(it1,it6));
		col2.getItens().addAll(Arrays.asList(it2, it3));
		col3.getItens().addAll(Arrays.asList(it4));
		col4.getItens().addAll(Arrays.asList(it5));

		colaboradorRepositorys.saveAll(Arrays.asList(col1, col2, col3, col4));
		itensRepository.saveAll(Arrays.asList(it1, it2, it3, it4, it5,it6));

	}
}
