package com.coderhouse.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.dtos.VentaDto;
import com.coderhouse.models.Cliente;
import com.coderhouse.models.Producto;
import com.coderhouse.models.Venta;
import com.coderhouse.models.VentaDetalle;
import com.coderhouse.repository.VentaRepository;

import jakarta.transaction.Transactional;

@Service
public class VentaService implements IVentaService{
	
	@Autowired
	private VentaRepository venRepo;
	
	@Autowired
	private ProductoService proServ;
	
	@Autowired
	private ClienteService clieServ;
	
	@Autowired
	private TimeRestService timeServ;
	

	public List<Venta> getAllVentas() {
		return venRepo.findAll();
	}

	public Venta findByID(Long id) {
		return venRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Venta no encontrada"));
	}

	@Transactional
	public Venta saveVenta(Venta venta) {
		return venRepo.save(venta);	
	}
	
	@Transactional
	public Venta updateVenta(Long id, VentaDto ventaDto) {
		Venta venta = findByID(id);
		
		Cliente cliente = clieServ.findByID(ventaDto.getClieId());
		
		venta.setFecha(timeServ.obtenerFechaActual());
        venta.setClie(cliente);
        venta.setMetodoPago(ventaDto.getMetodoPago());

        // Crear los detalles de la venta
        List<VentaDetalle> detalles = new ArrayList<>();
        double montoTotal = 0;
        int cantidadProducto = 0;
        if (ventaDto.getProductoIds().size() != ventaDto.getCantidades().size()) {
        	throw new IllegalArgumentException(
        			"El número de cantidades no coincide con el número de "
        			+ "productos proporcionados. Verifique los datos enviados.");
        	
        }

        for (int i = 0; i < ventaDto.getProductoIds().size(); i++) {
            Long productoId = ventaDto.getProductoIds().get(i);
            int cantidad = ventaDto.getCantidades().get(i);

            Producto producto = proServ.findByID(productoId);
                    
            if (producto.getCantidad() < cantidad) {
                throw new IllegalArgumentException(
                        "Stock insuficiente para el producto: " + producto.getNombre());
            }
            
            producto.setCantidad(producto.getCantidad() - cantidad);
            proServ.saveProducto(producto);
            

            VentaDetalle detalle = new VentaDetalle();
            detalle.setProducto(producto);
            detalle.setCantidad(cantidad);
            detalle.setPrecio(producto.getPrecio());
            detalle.setVen(venta);


            detalles.add(detalle);
            montoTotal += producto.getPrecio() * cantidad;
            cantidadProducto += cantidad;
        }

        venta.setVentasDetalles(detalles);
        venta.setMontoTotal(montoTotal);
        venta.setCantidadProductos(cantidadProducto);
        // Guardar la venta
        return venRepo.save(venta);
    }

	public void deleteVentaById(Long id) {
		if (!venRepo.existsById(id)) {
			throw new IllegalArgumentException("Venta no encontrada");
		}
		venRepo.deleteById(id);
		
	}

	@Transactional
	public Venta crearVenta(VentaDto ventaDto) {
        // Buscar el cliente
        Cliente cliente = clieServ.findByID(ventaDto.getClieId());

        // Crear la venta
        Venta venta = new Venta();
        
        venta.setFecha(timeServ.obtenerFechaActual());
        venta.setClie(cliente);
        venta.setMetodoPago(ventaDto.getMetodoPago());

        // Crear los detalles de la venta
        List<VentaDetalle> detalles = new ArrayList<>();
        double montoTotal = 0;
        int cantidadProducto = 0;
        if (ventaDto.getProductoIds().size() != ventaDto.getCantidades().size()) {
        	throw new IllegalArgumentException(
        			"El número de cantidades no coincide con el número de "
        			+ "productos proporcionados. Verifique los datos enviados.");
        	
        }

        for (int i = 0; i < ventaDto.getProductoIds().size(); i++) {
            Long productoId = ventaDto.getProductoIds().get(i);
            int cantidad = ventaDto.getCantidades().get(i);

            Producto producto = proServ.findByID(productoId);
                    
            if (producto.getCantidad() < cantidad) {
                throw new IllegalArgumentException(
                        "Stock insuficiente para el producto: " + producto.getNombre());
            }
            
            producto.setCantidad(producto.getCantidad() - cantidad);
            proServ.saveProducto(producto);
            

            VentaDetalle detalle = new VentaDetalle();
            detalle.setProducto(producto);
            detalle.setCantidad(cantidad);
            detalle.setPrecio(producto.getPrecio());
            detalle.setVen(venta);


            detalles.add(detalle);
            montoTotal += producto.getPrecio() * cantidad;
            cantidadProducto += cantidad;
        }

        venta.setVentasDetalles(detalles);
        venta.setMontoTotal(montoTotal);
        venta.setCantidadProductos(cantidadProducto);
        // Guardar la venta
        return venRepo.save(venta);
    }


}
