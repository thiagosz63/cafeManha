package com.fcb.cafeDaManha.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotBlank;

import com.fcb.cafeDaManha.entities.Colaborador;
import com.fcb.cafeDaManha.entities.Itens;
import com.fcb.cafeDaManha.entities.enums.ItemStatus;

public class ItensDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long  id;
	
	@NotBlank(message = "Preenchimento Obligatorio")
	private String nome;
	
	@NotBlank(message = "Preenchimento Obligatorio")
	private ItemStatus status;
	
	@NotBlank(message = "Preenchimento Obligatorio")
	private Colaborador colaborador;

	public ItensDTO() {
	}

	public ItensDTO(Long id, String nome, ItemStatus status,Colaborador colaborador) {
		super();
		this.id = id;
		this.nome = nome;
		this.status = status;
		this.colaborador = colaborador;
	}

	public ItensDTO(Itens obj) {
		id = obj.getId();
		nome = obj.getNome();
		status = obj.getStatus();
		colaborador = obj.getColaborador();
	}

	public ItemStatus getStatus() {
		return status;
	}

	public void setStatus(ItemStatus status) {
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}
	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}


	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ItensDTO other = (ItensDTO) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}
}
