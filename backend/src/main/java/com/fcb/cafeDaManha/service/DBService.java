package com.fcb.cafeDaManha.service;

import java.text.ParseException;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fcb.cafeDaManha.entities.Colaborador;
import com.fcb.cafeDaManha.entities.Itens;
import com.fcb.cafeDaManha.entities.enums.ItemStatus;
import com.fcb.cafeDaManha.repository.ColaboradorRepository;
import com.fcb.cafeDaManha.repository.ItensRepository;

@Service
public class DBService {

	@Autowired
	private ColaboradorRepository colaboradorRepositorys;

	@Autowired
	private ItensRepository itensRepository;

	public void instantiateTestDataBase() throws ParseException {
		Colaborador col1 = new Colaborador(null, "thiago", "168.866.390-80", "123456");
		Colaborador col2 = new Colaborador(null, "Marya", "802.295.340-74", "123456");
		Colaborador col3 = new Colaborador(null, "jose", "930.522.990-50", "123456");
		Colaborador col4 = new Colaborador(null, "jaqueline", "976.666.310-65", "123456");

		Itens it1 = new Itens(null, "bolo doce", ItemStatus.NaoDisponivel, col1);
		Itens it2 = new Itens(null, "pão com queijo", ItemStatus.NaoDisponivel, col2);
		Itens it3 = new Itens(null, "tapioca", ItemStatus.NaoDisponivel, col2);
		Itens it4 = new Itens(null, "suco de laranja", ItemStatus.NaoDisponivel, col3);
		Itens it5 = new Itens(null, "suco de limão", ItemStatus.NaoDisponivel, col4);

		col1.getItens().addAll(Arrays.asList(it1));
		col2.getItens().addAll(Arrays.asList(it2, it3));
		col3.getItens().addAll(Arrays.asList(it4));
		col4.getItens().addAll(Arrays.asList(it5));

		colaboradorRepositorys.saveAll(Arrays.asList(col1, col2, col3, col4));
		itensRepository.saveAll(Arrays.asList(it1, it2, it3, it4, it5));

	}
}
