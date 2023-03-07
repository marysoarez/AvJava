package com.mary.avjava.resources;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.mary.avjava.domain.Endereco;

import com.mary.avjava.dto.EnderecoDTO;

import com.mary.avjava.services.EnderecoService;

@RestController
@RequestMapping(value="/enderecos")

public class EnderecoResources {

		@Autowired
		private EnderecoService service;
		
		
		@RequestMapping(value="/{id}",method=RequestMethod.GET)
		public ResponseEntity<?> buscar(@PathVariable Integer id) {
			
			Endereco obj = service.find(id);
			return ResponseEntity.ok().body(obj);
			}
		
		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void> insert( @RequestBody EnderecoDTO objDto) throws MalformedURLException{
			Endereco obj = service.fromDTO(objDto);
			
			obj = service.insert(obj);
			URL uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").build(obj.getId()).toURL();
			return ResponseEntity.created(null).build();
			
		}
	

		@RequestMapping(value="/{id}", method=RequestMethod.PUT)
		public ResponseEntity<Void> update (@RequestBody EnderecoDTO objDto, @PathVariable Integer id){
			Endereco obj = service.fromDTO(objDto);
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
		}
		
		
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<EnderecoDTO>> findAll() {
			
			List<Endereco> list = service.findAll();
			
			List<EnderecoDTO> listDto = list.stream().map(obj -> new EnderecoDTO (obj)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listDto);
		}
		
		
		
		@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
		public ResponseEntity<Void> delete(@PathVariable Integer id){
			service.delete(id);
			return ResponseEntity.noContent().build();
		}
		
		
		@RequestMapping(value="/page",method=RequestMethod.GET)
		public ResponseEntity<Page<EnderecoDTO>> findPage(
				@RequestParam (value="page", defaultValue="0") Integer page, 
				@RequestParam (value="linesPerPage", defaultValue="12")Integer linesPerPage, 
				@RequestParam (value="orderBy", defaultValue="id")String orderBy, 
				@RequestParam (value="direction", defaultValue="ASC")String direction) {
			
			Page<Endereco> list = service.findPage(page, linesPerPage, orderBy, direction);
			
			Page<EnderecoDTO> listDto = list.map(obj -> new EnderecoDTO (obj));
			return ResponseEntity.ok().body(listDto);
		}
}
