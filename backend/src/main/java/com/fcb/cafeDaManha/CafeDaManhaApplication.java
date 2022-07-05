package com.fcb.cafeDaManha;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fcb.cafeDaManha.entities.Colaborador;
import com.fcb.cafeDaManha.repository.ColaboradorRepositorys;

@SpringBootApplication
public class CafeDaManhaApplication implements CommandLineRunner {
	
	@Autowired
	private ColaboradorRepositorys colaboradorRepositorys;

	public static void main(String[] args) {
		SpringApplication.run(CafeDaManhaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Colaborador col1 = new Colaborador(null, "thiago", "168.866.390-80", "123456");
		Colaborador col2 = new Colaborador(null, "Marya", "802.295.340-74", "123456");
		Colaborador col3 = new Colaborador(null, "Jaqueline", "214.497.880-84", "123456");
		Colaborador col4 = new Colaborador(null, "Jose", "682.857.550-45", "123456");
		colaboradorRepositorys.saveAll(Arrays.asList(col1,col2,col3,col4));
		
	}
}
