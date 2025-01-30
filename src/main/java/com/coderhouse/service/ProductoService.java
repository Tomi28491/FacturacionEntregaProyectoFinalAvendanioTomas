package com.coderhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Producto;
import com.coderhouse.repository.ProductoRepository;

import jakarta.transaction.Transactional;

@Service
public class ProductoService implements IProductoService{
	
	@Autowired
	private ProductoRepository proRepo;

	public List<Producto> getAllProductos() {
		return proRepo.findAll();
	}

	public Producto findByID(Long id) {
		return proRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Producto no encontrado"));
	}

	@Transactional
	public Producto saveProducto(Producto producto) {
		return proRepo.save(producto);	
	}

	@Transactional
	public Producto updateProducto(Long id, Producto productoModificado) {
		Producto producto = findByID(id);
		producto.setNombre(productoModificado.getNombre());
		producto.setCantidad(productoModificado.getCantidad());
		
		if (productoModificado.getPrecio() != 0) {
			producto.setPrecio(productoModificado.getPrecio());
		}
		
		return saveProducto(producto);
	}

	public void deleteProductoById(Long id) {
		if (!proRepo.existsById(id)) {
			throw new IllegalArgumentException("Producto no encontrado");
		}
		proRepo.deleteById(id);
		
	}



}
