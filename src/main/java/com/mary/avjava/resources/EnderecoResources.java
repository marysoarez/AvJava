package com.mary.avjava.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mary.avjava.domain.Endereco;
import com.mary.avjava.services.EnderecoService;

@RestController
@RequestMapping(value="/enderecos")

public class EnderecoResources {

		@Autowired
		private EnderecoService service;
		
		
		@RequestMapping(value="/{id}",method=RequestMethod.GET)
		public ResponseEntity<?> buscar(@PathVariable Integer id) {
			
			Endereco obj = service.buscar(id);
			return ResponseEntity.ok().body(obj);
			
			
		}
}
