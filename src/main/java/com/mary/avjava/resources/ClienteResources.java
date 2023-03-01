package com.mary.avjava.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mary.avjava.domain.Cliente;
import com.mary.avjava.services.ClienteService;

@RestController
@RequestMapping(value="/clientes")

public class ClienteResources {

		@Autowired
		private ClienteService service;
		
		
		@RequestMapping(value="/{id}",method=RequestMethod.GET)
		public ResponseEntity<?> buscar(@PathVariable Integer id) {
			
			Cliente obj = service.buscar(id);
			return ResponseEntity.ok().body(obj);
			
			
		}
}
