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

import com.mary.avjava.domain.Cliente;
import com.mary.avjava.dto.ClienteDTO;
import com.mary.avjava.services.ClienteService;




@RestController
@RequestMapping(value="/clientes")

public class ClienteResources {

		@Autowired
		private ClienteService service;
		
		
		
		@RequestMapping(value="/{id}", method=RequestMethod.GET)
		public ResponseEntity<Cliente> find(@PathVariable Integer id) {
			
			Cliente obj = service.find(id);
			return ResponseEntity.ok().body(obj);
		}
		
		@RequestMapping(method=RequestMethod.POST)
		public ResponseEntity<Void> insert( @RequestBody ClienteDTO objDto) throws MalformedURLException{
			Cliente obj = service.fromDTO(objDto);
			
			obj = service.insert(obj);
			URL uri = ServletUriComponentsBuilder.fromCurrentRequest()
					.path("/{id}").build(obj.getId()).toURL();
			return ResponseEntity.created(null).build();
			
		}
	

		@RequestMapping(value="/{id}", method=RequestMethod.PUT)
		public ResponseEntity<Void> update (@RequestBody ClienteDTO objDto, @PathVariable Integer id){
			Cliente obj = service.fromDTO(objDto);
			obj.setId(id);
			obj = service.update(obj);
			return ResponseEntity.noContent().build();
		}
		
		
		@RequestMapping(method=RequestMethod.GET)
		public ResponseEntity<List<ClienteDTO>> findAll() {
			
			List<Cliente> list = service.findAll();
			
			List<ClienteDTO> listDto = list.stream().map(obj -> new ClienteDTO (obj)).collect(Collectors.toList());
			return ResponseEntity.ok().body(listDto);
		}
		
		
		
		@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
		public ResponseEntity<Void> delete(@PathVariable Integer id){
			service.delete(id);
			return ResponseEntity.noContent().build();
		}
		
		
		@RequestMapping(value="/page",method=RequestMethod.GET)
		public ResponseEntity<Page<ClienteDTO>> findPage(
				@RequestParam (value="page", defaultValue="0") Integer page, 
				@RequestParam (value="linesPerPage", defaultValue="12")Integer linesPerPage, 
				@RequestParam (value="orderBy", defaultValue="id")String orderBy, 
				@RequestParam (value="direction", defaultValue="ASC")String direction) {
			
			Page<Cliente> list = service.findPage(page, linesPerPage, orderBy, direction);
			
			Page<ClienteDTO> listDto = list.map(obj -> new ClienteDTO (obj));
			return ResponseEntity.ok().body(listDto);
		}
		
}
