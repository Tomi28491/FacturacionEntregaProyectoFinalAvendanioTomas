package com.coderhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Venta;
import com.coderhouse.repository.VentaRepository;


@Service
public class VentaService {
	
	@Autowired
	private VentaRepository venRepo;

	public List<Venta> getAllVentas() {
		return venRepo.findAll();
	}

	public Venta findByID(Long id) {
		return venRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));
	}

	public Venta saveVenta(Venta venta) {
		return venRepo.save(venta);	
	}

	public Venta updateVenta(Long id, Venta ventaModificada) {
		Venta venta = findByID(id);
		venta.setMontoTotal(ventaModificada.getMontoTotal());
		venta.setMetodoPago(ventaModificada.getMetodoPago());
		venta.setFecha(ventaModificada.getFecha());
		
		return saveVenta(venta);
	}

	public void deleteVentaById(Long id) {
		if (!venRepo.existsById(id)) {
			throw new IllegalArgumentException("Venta no encontrada");
		}
		venRepo.deleteById(id);
		
	}

}
