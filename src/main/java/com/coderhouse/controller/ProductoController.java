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

import com.coderhouse.models.Producto;
import com.coderhouse.service.IProductoService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;


@RestController
@RequestMapping("/api/productos")
public class ProductoController {

		@Autowired
		private IProductoService proServ;
		
		@Operation(summary = "Obtener Lista de Productos")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Lista de Productos obtenida Correctamente", 
						content = {
						@Content(mediaType = "application/json", 
								schema = @Schema(implementation = Producto.class)) }),
				@ApiResponse(responseCode = "404", description = "Error al intentar Obtener a los Productos", 
				content = @Content(mediaType = "application/json", 
				schema = @Schema(implementation = ErrorResponse.class))),
				@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
				content = @Content(mediaType = "application/json", 
				schema = @Schema(implementation = ErrorResponse.class))) 
				})
		@GetMapping
		public ResponseEntity<List<Producto>> getAllProductos(){
			try {
				List<Producto> productos = proServ.getAllProductos();
				return ResponseEntity.ok(productos); // 200 OK
			}catch (IllegalArgumentException e){
				return ResponseEntity.notFound().build(); // 404
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
			}
		}
		@Operation(summary = "Obtener un Producto por su ID")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Producto obtenido Correctamente", content = {
						@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class)) }),
				@ApiResponse(responseCode = "404", description = "Error al intentar Obtener el Producto", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
				@ApiResponse(responseCode = "500", description = "Error interno del Servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })
		
		@GetMapping("/{id}")
		public ResponseEntity<Producto> getProductoById(@PathVariable Long id) {
			try {
				Producto producto = proServ.findByID(id);
				return ResponseEntity.ok(producto);
			}catch (IllegalArgumentException e){
				return ResponseEntity.notFound().build(); // 404
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 Server Error
			}
		}
		
		@Operation(summary = "Crear un Producto nuevo")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "201", description = "Producto creado Correctamente", content = {
						@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class)) }),
				@ApiResponse(responseCode = "404", description = "Error al intentar crear el Producto", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))),
				@ApiResponse(responseCode = "500", description = "Error interno del Servidor", content = @Content(mediaType = "application/json", schema = @Schema(implementation = ErrorResponse.class))) })	
		
		@PostMapping
		public ResponseEntity<Producto> saveProducto(@RequestBody Producto producto) {
			try {
				Producto productoCreado = proServ.saveProducto(producto);
				return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado); // 201 Created
			}catch (IllegalArgumentException e){
				return ResponseEntity.notFound().build(); // 404
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 
			}
		}
		
		@Operation(summary = "Editar un Producto por su ID")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "200", description = "Producto editado Correctamente", content = {
						@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class)) }),
				@ApiResponse(responseCode = "404", description = "Error al intentar editar el Producto", 
				content = @Content(mediaType = "application/json", 
				schema = @Schema(implementation = ErrorResponse.class))),
				@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
				content = @Content(mediaType = "application/json", 
				schema = @Schema(implementation = ErrorResponse.class))) })	
		@PutMapping("/{id}")
		public ResponseEntity<Producto> updateProductoById(@PathVariable Long id, @RequestBody Producto productoModificado){
			try {
				Producto updateProd = proServ.updateProducto(id, productoModificado);
				return ResponseEntity.ok(updateProd); // 200 OK
				
			}catch (IllegalArgumentException e){
				return ResponseEntity.notFound().build(); // 404
				
			}catch (Exception e){		
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 
			}
		}
		
		@Operation(summary = "Eliminar un Producto por su ID")
		@ApiResponses(value = {
				@ApiResponse(responseCode = "204", description = "Producto eliminado Correctamente", content = {
						@Content(mediaType = "application/json", schema = @Schema(implementation = Producto.class)) }),
				@ApiResponse(responseCode = "404", description = "Error al intentar eliminar el Producto, El Producto no existe", 
				content = @Content(mediaType = "application/json", 
				schema = @Schema(implementation = ErrorResponse.class))),
				@ApiResponse(responseCode = "500", description = "Error interno del Servidor", 
				content = @Content(mediaType = "application/json", 
				schema = @Schema(implementation = ErrorResponse.class))) })	
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> deleteProductoById(@PathVariable Long id){
			try {
				proServ.deleteProductoById(id);
				return ResponseEntity.noContent().build(); // 204 
			}catch (IllegalArgumentException e){
				return ResponseEntity.notFound().build(); // 404
				
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 
			}
			
		}
}
