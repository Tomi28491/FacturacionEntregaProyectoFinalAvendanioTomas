package com.coderhouse.models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Schema(description = "Modelo de Producto", title = "Modelo de Producto")
@Entity
@Table(name="productos")
public class Producto {

	@Schema(description = "ID del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long idProducto;
	@Schema(description = "Nombre del Producto", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "Iphone 12")
	private String nombre;
	
	
	@Schema(description = "Cantidad de Stock del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "8")
	private int cantidad;
	
	@JsonIgnore
	@Schema(description = "Lista de Detalle de Venta del Producto", requiredMode = Schema.RequiredMode.REQUIRED)
	@OneToMany(mappedBy = "producto", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<VentaDetalle> ventas = new ArrayList<>();
	
	@Column(nullable=false)
	@Schema(description = "Precio del Producto", requiredMode = Schema.RequiredMode.REQUIRED, example = "650000.0")
	private double precio;
	
	
}
