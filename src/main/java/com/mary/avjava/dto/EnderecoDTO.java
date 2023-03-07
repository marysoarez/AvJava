package com.mary.avjava.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.mary.avjava.domain.Cliente;
import com.mary.avjava.domain.Endereco;

import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

public class EnderecoDTO implements Serializable{


	private static final long serialVersionUID = 1L;
	
	private Integer id;
	private String logradouro;
	private String numero;
	private String cep;
	private String cidade;
	private String cliente;

	
	public EnderecoDTO() {
		
	}
	
	public EnderecoDTO(Endereco obj) {
		id = obj.getId();
		logradouro = obj.getLogradouro();
		numero = obj.getNumero();
		cep = obj.getCep();
		cidade = obj.getCidade();
		cliente =obj.getCliente();
	
		


	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getCliente() {
		return cliente;
	}

	public void setCliente(String cliente) {
		this.cliente = cliente;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}



}
