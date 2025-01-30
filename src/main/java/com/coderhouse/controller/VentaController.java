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


import com.coderhouse.dtos.VentaDto;
import com.coderhouse.models.Venta;
import com.coderhouse.service.IVentaService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/ventas")
public class VentaController {

	@Autowired
	private IVentaService venServ;
		
		@Operation(summary = "Obtener Lista de Ventas")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Lista de Ventas obtenida Correctamente", 
						content = {
						@Content(mediaType = "application/json", 
								schema = @Schema(implementation = Venta.class)) }),
				@ApiResponse(responseCode = "404", description = "Error al intentar Obtener a las Ventas", 
				content = @Content(mediaType = "application/json", 
				schema = @Schema(implementation = ErrorResponse.class))),
				@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
				content = @Content(mediaType = "application/json", 
				schema = @Schema(implementation = ErrorResponse.class))) 
				})
		@GetMapping
		public ResponseEntity<List<Venta>> getAllVentas(){
			try {
				List<Venta> ventas = venServ.getAllVentas();
				return ResponseEntity.ok(ventas); // 200 OK
			}catch (IllegalArgumentException e){
				return ResponseEntity.notFound().build(); // 404
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
			}
			}
		
		@Operation(summary = "Obtener una Venta por su ID")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Venta obtenida Correctamente", content = {
						@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class)) }),
				@ApiResponse(responseCode = "404", description = "Error al intentar Obtener el Venta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
				@ApiResponse(responseCode = "500", description = "Error interno del Servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
		
		@GetMapping("/{id}")
		public ResponseEntity<Venta> getVentaById(@PathVariable Long id) {
			try {
				Venta venta = venServ.findByID(id);
				return ResponseEntity.ok(venta);
			}catch (IllegalArgumentException e){
				return ResponseEntity.notFound().build(); // 404
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Server Error
			}
		}
		
		@Operation(summary = "Crear una Venta nuevo")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "201", description = "Venta creada Correctamente", content = {
						@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class)) }),
				@ApiResponse(responseCode = "404", description = "Error al intentar crear la Venta", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
				@ApiResponse(responseCode = "500", description = "Error interno del Servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })	
		
		@PostMapping("/vender")
		public ResponseEntity<Venta> generarVenta(@RequestBody VentaDto dto){
			try {
				Venta venta = venServ.crearVenta(dto);
				return ResponseEntity.status(HttpStatus.CREATED).body(venta); // 201 CREATED
			}catch (IllegalArgumentException e){
				return ResponseEntity.badRequest().build(); // 400
				
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 
			}
		}
		
		@Operation(summary = "Editar una Venta por su ID")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Venta editado Correctamente", content = {
						@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class)) }),
				@ApiResponse(responseCode = "404", description = "Error al intentar editar la Venta", 
				content = @Content(mediaType = "application/json", 
				schema = @Schema(implementation = ErrorResponse.class))),
				@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
				content = @Content(mediaType = "application/json", 
				schema = @Schema(implementation = ErrorResponse.class))) })	
		@PutMapping("/{id}")
		public ResponseEntity<Venta> updateVentaById(@PathVariable Long id, @RequestBody VentaDto ventaModificada){
			try {
				Venta updateVent = venServ.updateVenta(id, ventaModificada);
				return ResponseEntity.ok(updateVent); // 200 OK
				
			}catch (IllegalArgumentException e){
				return ResponseEntity.notFound().build(); // 404
				
			}catch (Exception e){		
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 
			}
		}
		
		@Operation(summary = "Eliminar una Venta por su ID")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "204", description = "Venta eliminada Correctamente", content = {
						@Content(mediaType = "application/json", schema = @Schema(implementation = Venta.class)) }),
				@ApiResponse(responseCode = "404", description = "Error al intentar eliminar la Venta, La Venta no existe", 
				content = @Content(mediaType = "application/json", 
				schema = @Schema(implementation = ErrorResponse.class))),
				@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
				content = @Content(mediaType = "application/json", 
				schema = @Schema(implementation = ErrorResponse.class))) })
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> deleteVentaById(@PathVariable Long id){
			try {
				venServ.deleteVentaById(id);
				return ResponseEntity.noContent().build(); // 204 
			}catch (IllegalArgumentException e){
				return ResponseEntity.notFound().build(); // 404
				
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 
			}
			
		}
	
}
