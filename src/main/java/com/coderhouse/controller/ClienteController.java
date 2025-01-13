package com.coderhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Cliente;
import com.coderhouse.service.ClienteService;

@RestController
@RequestMapping("/api/clientes")
public class ClienteController {
	@Autowired
	private ClienteService clieServ;
	
	@GetMapping
	public ResponseEntity<List<Cliente>> getAllClientes() {
		try {
			List<Cliente> clientes = clieServ.getAllCliente();
			return ResponseEntity.ok(clientes); // 200 OK
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		}
	}
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> getClienteById(@PathVariable Long id) {
		try {
			Cliente cliente = clieServ.findByID(id);
			return ResponseEntity.ok(cliente);
		}catch (IllegalArgumentException e){
			return ResponseEntity.notFound().build(); // 404
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Server Error
		}
	}
	
	@PostMapping
	public ResponseEntity<Cliente> createCliente(@RequestBody Cliente cliente) {
		try {
			Cliente clienteCreado = clieServ.saveCliente(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado); // 201 Created
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 
		}
		
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> updateClienteById(@PathVariable Long id, @RequestBody Cliente clienteModificado){
		try {
			Cliente updateClie = clieServ.updateCliente(id, clienteModificado);
			return ResponseEntity.ok(updateClie); // 200 OK
			
		}catch (IllegalArgumentException e){
			return ResponseEntity.notFound().build(); // 404
			
		}catch (Exception e){		
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 
		}
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClienteById(@PathVariable Long id){
		try {
			clieServ.deleteClienteById(id);
			return ResponseEntity.noContent().build(); // 400 
		}catch (IllegalArgumentException e){
			return ResponseEntity.notFound().build(); // 404
			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 
		}
		
	}
	
}