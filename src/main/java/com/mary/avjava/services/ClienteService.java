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
import com.mary.avjava.dto.ClienteDTO;
import com.mary.avjava.repositories.ClienteRepository;
import com.mary.avjava.services.exceptions.DataIntegrityException;
import com.mary.avjava.services.exceptions.ObjectNotFoundException;

@Service
public class ClienteService {
	
	@Autowired
	private ClienteRepository repo;
	public Cliente find(Integer id) {
		Optional<Cliente> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				 "Objeto não encontrado! Id: " + id + ", Tipo: " + Cliente.class.getName()));
				} 

	
	
	
	public Cliente insert(Cliente obj) {
		obj.setId(null);
		return repo.save(obj);
	}
	
	public Cliente update (Cliente obj) {
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
	
	public List<Cliente> findAll(){
		return repo.findAll();
	}
	
	
	public Page<Cliente> findPage(Integer page, Integer linesPerPage, String orderBy, String direction){
		PageRequest pageRequest = PageRequest.of(page, linesPerPage, Direction.valueOf(direction), orderBy);
		return repo.findAll(pageRequest);
	}
	
	public Cliente fromDTO(ClienteDTO objDto) {
		return new Cliente (objDto.getId(),objDto.getNome(), objDto.getNascimento() );
		
	}
	
}

