package com.mary.avjava.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;

import com.mary.avjava.domain.Cliente;
import com.mary.avjava.domain.Endereco;
import com.mary.avjava.dto.EnderecoDTO;
import com.mary.avjava.repositories.ClienteRepository;
import com.mary.avjava.repositories.EnderecoRepository;
import com.mary.avjava.services.exceptions.DataIntegrityException;
import com.mary.avjava.services.exceptions.ObjectNotFoundException;

@Service
public class EnderecoService {
	
	@Autowired
	private EnderecoRepository repo;
	public Endereco find(Integer id) {
		Optional<Endereco> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
				} 

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	public Endereco insert(Endereco obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Endereco update (Endereco obj) {
		find(obj.getId());
		return repo.save(obj);
	}
	
	

	public void delete(Integer id) {
		find(id);
		try {
				repo.deleteById(id);		
		}catch(DataIntegrityViolationException e) {
				throw new DataIntegrityException("Não é possível excluir um cliente que possui endereço");
			}

	}
	
	public List<Endereco> findAll(){
		return repo.findAll();
	}
	
	
	public Page<Endereco> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Endereco fromDTO(EnderecoDTO objDto) {
		return new Endereco(objDto.getId(), objDto.getLogradouro(), objDto.getNumero(), objDto.getCep(), objDto.getCidade(), null, null);
		
	}

}
