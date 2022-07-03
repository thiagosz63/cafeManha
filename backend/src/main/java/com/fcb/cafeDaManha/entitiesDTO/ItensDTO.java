package com.fcb.cafeDaManha.entitiesDTO;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fcb.cafeDaManha.entities.Itens;
import com.fcb.cafeDaManha.entities.enums.Status;

public class ItensDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  id;
	private String nome;
	private Status status;

	public ItensDTO() {
	}

	public ItensDTO(Long id, String nome, Status status) {
		super();
		this.id = id;
		this.nome = nome;
		this.status = status;
	}

	public ItensDTO(Itens obj) {
		id = obj.getId();
		nome = obj.getNome();
		status = obj.getStatus();
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
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
