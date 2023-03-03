package com.mary.avjava.dto;

import java.io.Serializable;

import com.mary.avjava.domain.Cliente;

import jakarta.validation.constraints.NotEmpty;

public class ClienteDTO implements Serializable{

	private static final long serialVersionUID = 1L;
	

	private Integer id;
	
	@NotEmpty(message="Campo Obrigatório")
	private String nome;
	
	@NotEmpty
	private String nascimento;
	
	public ClienteDTO() {
		
	}

	public ClienteDTO(Cliente obj) {
		id = obj.getId();
		nome = obj.getNome();
		nascimento = obj.getNascimento();
		
	}
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getNascimento() {
		return nascimento;
	}

	public void setNascimento(String nascimento) {
		this.nascimento = nascimento;
	}
	
}
