package com.coderhouse.models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity
@Table(name="ventas")
public class Venta {

	@Schema(description = "ID de la Venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long idVenta;
	
	
	@Schema(description = "Fecha de alta del Cliente", example = "2025-01-29T14:30:00")
	@Column(nullable=true)
	private LocalDateTime fecha;
	
	
	@Schema(description = "ID del Cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@ManyToOne
	@JoinColumn(name = "id_cliente")
	private Cliente clie;
	
	
	@Schema(description = "Lista de Detalle de Venta de la Venta", requiredMode = Schema.RequiredMode.REQUIRED)
	@JsonManagedReference
	@OneToMany(mappedBy = "ven", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<VentaDetalle> ventasDetalles = new ArrayList<>();
	
	@Schema(description = "Cantidad de los Productos", requiredMode = Schema.RequiredMode.REQUIRED, example = "8")
	private int cantidadProductos;
	
	@Schema(description = "Monto Total", requiredMode = Schema.RequiredMode.REQUIRED, example = "1500000.0")
	private double montoTotal;
	
	@Schema(description = "Metodo de Pago", requiredMode = Schema.RequiredMode.REQUIRED, example = "Tarjeta")
	private String metodoPago;
	 
	
}
