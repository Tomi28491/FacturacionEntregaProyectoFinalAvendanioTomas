package com.coderhouse.models;


import java.time.LocalDateTime;
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


@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Schema(description = "Modelo de Cliente", title = "Modelo de Cliente")
@Entity
@Table(name="clientes")
public class Cliente {
	@Schema(description = "ID del Cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "1")
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long idCliente;
	@Schema(description = "Nombre del Cliente", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "Tomas")
	private String nombre;
	@Schema(description = "Apellido del Cliente", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "Avendaño")
	private String apellido;
	@Schema(description = "Email del Cliente", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "tavendao@mail.com")
	private String mail;
	
	@Schema(description = "DNI del Cliente", requiredMode = Schema.RequiredMode.REQUIRED, example = "44828352")
	@Column(unique=true, nullable=false)
	private int dni;
	@Schema(description = "Direccion del Cliente", requiredMode = Schema.RequiredMode.NOT_REQUIRED, example = "Cordoba, Argentina")
	private String direccion;
	@Schema(description = "Fecha de alta del Cliente", example = "2025-01-29T14:30:00")
	private LocalDateTime fechaRegistro;
	
	@JsonIgnore
	@Schema(description = "Lista de Ventas del Cliente", requiredMode = Schema.RequiredMode.REQUIRED)
	@OneToMany(mappedBy = "clie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Venta> ventas = new ArrayList<> ();
	
	
	
}
