package com.coderhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Cliente;
import com.coderhouse.repository.ClienteRepository;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	@Autowired
	private ClienteRepository clieRepo;
	
	@GetMapping
	public List<Cliente> getAllClientes() {
		return clieRepo.findAll();
	}
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
		if (clieRepo.existsById(id)) {
			Cliente cliente = clieRepo.findById(id).get();
			return ResponseEntity.ok(cliente);
		} else {
			return ResponseEntity.notFound().build();
		}
	}
	@PostMapping
	public Cliente createCliente(@RequestBody Cliente cliente) {
		return clieRepo.save(cliente);
	}

	
}
