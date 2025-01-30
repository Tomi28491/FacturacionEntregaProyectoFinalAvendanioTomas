package com.coderhouse.models;

import com.fasterxml.jackson.annotation.JsonBackReference;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
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
public class VentaDetalle {
	
	@Schema(description = "ID del Detalle de la Venta", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long idVentaDet;
	
	@Schema(description = "ID del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@ManyToOne
	@JoinColumn(name = "id_Producto")
	private Producto producto;
	
	@Schema(description = "Cantidad de Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "8")
	@Column(nullable=false)
	private int cantidad;
	
	@Schema(description = "Precio del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1500000.0")
	private double precio;
	
	
	@ManyToOne
	@JsonBackReference
	@JoinColumn(name = "id_venta")
	private Venta ven;
	

}
