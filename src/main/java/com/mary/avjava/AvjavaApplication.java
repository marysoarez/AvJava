package com.mary.avjava;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.mary.avjava.domain.Cliente;
import com.mary.avjava.domain.Endereco;
import com.mary.avjava.repositories.ClienteRepository;
import com.mary.avjava.repositories.EnderecoRepository;

@SpringBootApplication
public class AvjavaApplication implements CommandLineRunner {
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private EnderecoRepository enderecoRepository;
	

	public static void main(String[] args) {
		SpringApplication.run(AvjavaApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		// TODO Auto-generated method stub
		
		Cliente clie = new Cliente(null, "nome de teste", "inserir a data");
		
		Endereco end1 = new Endereco(null, "rua j", "230", "21660470", "Rio", clie);
		Endereco end2 = new Endereco(null, "rua a", "347", "21660430", "Rio", clie);
		
		clie.getEnderecos().addAll(Arrays.asList(end1, end2));
		
		clienteRepository.saveAll(Arrays.asList(clie));
		enderecoRepository.saveAll(Arrays.asList(end1, end2));
		
	}

}
