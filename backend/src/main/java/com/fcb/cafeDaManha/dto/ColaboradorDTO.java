package com.fcb.cafeDaManha.dto;

import java.io.Serializable;
import java.util.Objects;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fcb.cafeDaManha.entities.Colaborador;
import com.fcb.cafeDaManha.service.validation.ColaboradorInsert;

@ColaboradorInsert
public class ColaboradorDTO implements Serializable {
	private static final long serialVersionUID = 1L;

	private Long id;
	
	@NotBlank(message = "Preenchimento Obligatorio")
	private String nome;
	
	@NotBlank(message = "Preenchimento Obligatorio")
	private String cpf;
	
	@JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
	@NotBlank(message = "Preenchimento Obligatorio")
	@Size(min = 6, max = 12, message = "O campo deve conter entre 6 e 12 caracteres")
	private String senha;

	public ColaboradorDTO() {
	}

	public ColaboradorDTO(Long id, String nome, String cpf, String senha) {
		super();
		this.id = id;
		this.nome = nome;
		this.cpf = cpf;
		this.senha = senha;
	}

	public ColaboradorDTO(Colaborador obj) {
		id = obj.getId();
		nome = obj.getNome();
		cpf = obj.getCpf();
		senha = obj.getSenha();
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

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	@Override
	public int hashCode() {
		return Objects.hash(cpf, id, nome, senha);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColaboradorDTO other = (ColaboradorDTO) obj;
		return Objects.equals(cpf, other.cpf) && Objects.equals(id, other.id) && Objects.equals(nome, other.nome)
				&& Objects.equals(senha, other.senha);
	}
}
