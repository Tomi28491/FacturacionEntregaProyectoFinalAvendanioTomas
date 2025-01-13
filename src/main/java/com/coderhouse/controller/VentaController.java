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

import com.coderhouse.models.Venta;
import com.coderhouse.service.VentaService;

@RestController
@RequestMapping("/api/ventas")
public class VentaController {

	@Autowired
	private VentaService venServ;
		
		@GetMapping
		public ResponseEntity<List<Venta>> getAllVentas(){
			try {
				List<Venta> ventas = venServ.getAllVentas();
				return ResponseEntity.ok(ventas); // 200 OK
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); //500
			}
			}
		
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
		@PostMapping
		public ResponseEntity<Venta> createVenta(@RequestBody Venta venta) {
			try {
				Venta ventaCreado = venServ.saveVenta(venta);
				return ResponseEntity.status(HttpStatus.CREATED).body(ventaCreado); // 201 Created
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 
			}
		}
		
		@PutMapping("/{id}")
		public ResponseEntity<Venta> updateVentaById(@PathVariable Long id, @RequestBody Venta ventaModificada){
			try {
				Venta updateVent = venServ.updateVenta(id, ventaModificada);
				return ResponseEntity.ok(updateVent); // 200 OK
				
			}catch (IllegalArgumentException e){
				return ResponseEntity.notFound().build(); // 404
				
			}catch (Exception e){		
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 
			}
		}
		
		@DeleteMapping("/{id}")
		public ResponseEntity<Void> deleteVentaById(@PathVariable Long id){
			try {
				venServ.deleteVentaById(id);
				return ResponseEntity.noContent().build(); // 400 
			}catch (IllegalArgumentException e){
				return ResponseEntity.notFound().build(); // 404
				
			}catch (Exception e) {
				return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build(); // 500 
			}
			
		}
}
