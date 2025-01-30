package com.coderhouse.service;

import java.util.List;

import com.coderhouse.dtos.VentaDto;
import com.coderhouse.models.Venta;

public interface IVentaService {
	public List<Venta> getAllVentas();
	public Venta findByID(Long id);
	public Venta saveVenta(Venta venta);
	public Venta updateVenta(Long id, VentaDto ventaDto);
	public void deleteVentaById(Long id);
	public Venta crearVenta(VentaDto ventaDto);
	
}
