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

import com.coderhouse.models.Producto;
import com.coderhouse.service.ProductoService;

@RestController
@RequestMapping("/api/productos")
public class ProductoController {

		@Autowired
		private ProductoService proServ;
		
		@GetMapping
		public ResponseEntity<List<Producto>> getAllProductos(){
			try {
				List<Producto> productos = proServ.getAllProductos();
				return ResponseEntity.ok(productos); // 200 OK
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
			}
		}
		
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
		@PostMapping
		public ResponseEntity<Producto> saveProducto(@RequestBody Producto producto) {
			try {
				Producto productoCreado = proServ.saveProducto(producto);
				return ResponseEntity.status(HttpStatus.CREATED).body(productoCreado); // 201 Created
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 
			}
		}
		
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
		
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> deleteProductoById(@PathVariable Long id){
			try {
				proServ.deleteProductoById(id);
				return ResponseEntity.noContent().build(); // 400 
			}catch (IllegalArgumentException e){
				return ResponseEntity.notFound().build(); // 404
				
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 
			}
			
		}
}
