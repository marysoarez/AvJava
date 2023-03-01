package com.mary.avjava.resources;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mary.avjava.domain.Cliente;

@RestController
@RequestMapping(value="/clientes")

public class ClienteResources {

		@RequestMapping(method=RequestMethod.GET)
		public List<Cliente> listar() {
			
			
			Cliente cli = new Cliente(1, "nome", "data");


			List<Cliente> lista = new ArrayList<>();
			lista.add(cli);
			
			return lista;
			
		}
}
