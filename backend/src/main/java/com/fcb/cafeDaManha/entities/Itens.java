package com.fcb.cafeDaManha.entities;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fcb.cafeDaManha.entities.enums.ItemStatus;

@Entity
@Table(name = "`itens`")
public class Itens implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long  id;
	private String nome;
	private Integer status;
	
	@ManyToOne
	@JoinColumn(name = "colaborador_id")
	private Colaborador colaborador;
	
	public Itens() {
	}

	public Itens(Long  id, String nome, ItemStatus status,Colaborador colaborador) {
		this.id = id;
		this.nome = nome;
		this.status = (status == null) ? null : status.getCod();
		this.colaborador =  colaborador;
	}

	public ItemStatus getStatus() {
		return ItemStatus.toEnum(status);
	}

	public void setStatus(ItemStatus status) {
		this.status = status.getCod();
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
	}

	@Override
	public int hashCode() {
		return Objects.hash(id, nome);
	}
	
	public Colaborador getColaborador() {
		return colaborador;
	}

	public void setColaborador(Colaborador colaborador) {
		this.colaborador = colaborador;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Itens other = (Itens) obj;
		return Objects.equals(id, other.id) && Objects.equals(nome, other.nome);
	}

}
