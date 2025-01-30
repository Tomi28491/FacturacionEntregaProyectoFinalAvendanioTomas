package com.coderhouse.service;

import java.util.List;

import com.coderhouse.models.Producto;

public interface IProductoService {
	public List<Producto> getAllProductos();
	public Producto findByID(Long id);
	public Producto saveProducto(Producto producto);
	public Producto updateProducto(Long id, Producto productoModificado);
	public void deleteProductoById(Long id);
	
}
