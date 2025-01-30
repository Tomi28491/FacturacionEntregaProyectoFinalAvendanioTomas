package com.coderhouse.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.ErrorResponse;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coderhouse.models.Cliente;
import com.coderhouse.service.IClienteService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;

@RestController
@RequestMapping("/api/clientes")
@Tag(name = "Gestion de Clientes", description = "Endpoints para gestionar Clientes")
public class ClienteController {
	@Autowired
	private IClienteService clieServ;
	
	@Operation(summary = "Obtener Lista de Clientes")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Lista de Clientes obtenida Correctamente", 
					content = {
					@Content(mediaType = "application/json", 
							schema = @Schema(implementation = Cliente.class)) }),
			@ApiResponse(responseCode = "404", description = "Error al intentar Obtener a los Clientes", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class))) 
			})
	@GetMapping
	public ResponseEntity<List<Cliente>> getAllClientes() {
		try {
			List<Cliente> clientes = clieServ.getAllClientes();
			return ResponseEntity.ok(clientes); // 200 OK
		}catch (IllegalArgumentException e){
			return ResponseEntity.notFound().build(); // 404
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
		}
	}
	
	@Operation(summary = "Obtener un Cliente por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente obtenido Correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)) }),
			@ApiResponse(responseCode = "404", description = "Error al intentar Obtener el Cliente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
	
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
	
	@Operation(summary = "Crear un Cliente nuevo")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "201", description = "Cliente creado Correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)) }),
			@ApiResponse(responseCode = "404", description = "Error al intentar crear el Cliente", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })	
	
	@PostMapping
	public ResponseEntity<Cliente> saveCliente(@RequestBody Cliente cliente) {
		try {
			Cliente clienteCreado = clieServ.saveCliente(cliente);
			return ResponseEntity.status(HttpStatus.CREATED).body(clienteCreado); // 201 Created
		}catch (IllegalArgumentException e){
			return ResponseEntity.notFound().build(); // 404
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 
		}
		
	}
	
	@Operation(summary = "Editar un Cliente por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "200", description = "Cliente editado Correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)) }),
			@ApiResponse(responseCode = "404", description = "Error al intentar editar el Cliente", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class))) })	
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
	
	@Operation(summary = "Eliminar un Cliente por su ID")
	@ApiResponses(value = {
			@ApiResponse(responseCode = "204", description = "Cliente eliminado Correctamente", content = {
					@Content(mediaType = "application/json", schema = @Schema(implementation = Cliente.class)) }),
			@ApiResponse(responseCode = "404", description = "Error al intentar eliminar el Cliente, El Cliente no existe", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class))),
			@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
			content = @Content(mediaType = "application/json", 
			schema = @Schema(implementation = ErrorResponse.class))) })	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteClienteById(@PathVariable Long id){
		try {
			clieServ.deleteClienteById(id);
			return ResponseEntity.noContent().build(); // 204 
		}catch (IllegalArgumentException e){
			return ResponseEntity.notFound().build(); // 404
			
		}catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 
		}
		
	}
	
}