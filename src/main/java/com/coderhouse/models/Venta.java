package com.coderhouse.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name="ventas")
public class Venta {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long idVenta;
	
	@Column(nullable=false)
	private LocalDateTime fecha;
	
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente clie;
	
	@ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinTable(
        name = "Venta_Producto",
        joinColumns = @JoinColumn(name = "id_venta"),
        inverseJoinColumns = @JoinColumn(name = "id_producto")
    )
	private List<Producto> productos = new ArrayList<>();
	
	private double montoTotal;
	
	private String metodoPago;

	public Venta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Venta(long idVenta, LocalDateTime fecha, Cliente clie, List<Producto> productos, double montoTotal,
			String metodoPago) {
		this();
		this.idVenta = idVenta;
		this.fecha = fecha;
		this.clie = clie;
		this.productos = productos;
		this.montoTotal = montoTotal;
		this.metodoPago = metodoPago;
	}

	public long getIdVenta() {
		return idVenta;
	}

	public void setIdVenta(long idVenta) {
		this.idVenta = idVenta;
	}

	public LocalDateTime getFecha() {
		return fecha;
	}

	public void setFecha(LocalDateTime fecha) {
		this.fecha = fecha;
	}

	public Cliente getClie() {
		return clie;
	}

	public void setClie(Cliente clie) {
		this.clie = clie;
	}

	public List<Producto> getProductos() {
		return productos;
	}

	public void setProductos(List<Producto> productos) {
		this.productos = productos;
	}

	public double getMontoTotal() {
		return montoTotal;
	}

	public void setMontoTotal(double montoTotal) {
		this.montoTotal = montoTotal;
	}

	public String getMetodoPago() {
		return metodoPago;
	}

	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}

	@Override
	public String toString() {
		return "Venta [idVenta=" + idVenta + ", fecha=" + fecha + ", clie=" + clie + ", productos=" + productos
				+ ", montoTotal=" + montoTotal + ", metodoPago=" + metodoPago + "]";
	}
	
	 
	
}
