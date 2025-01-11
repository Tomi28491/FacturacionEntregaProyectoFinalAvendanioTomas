package com.coderhouse.models;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name="clientes")
public class Cliente {

	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private long idCliente;
	private String nombre;
	private String apellido;
	private String mail;
	
	@Column(unique=true, nullable=false)
	private int dni;
	private String direccion;
	private LocalDate fechaRegistro;
	
	@OneToMany(mappedBy = "clie", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Venta> ventas = new ArrayList<> ();

	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cliente(String nombre, String apellido, String mail, int dni, String direccion, LocalDate fechaRegistro,
			List<Venta> ventas) {
		this();
		this.nombre = nombre;
		this.apellido = apellido;
		this.mail = mail;
		this.dni = dni;
		this.direccion = direccion;
		this.fechaRegistro = fechaRegistro;
		this.ventas = ventas;
	}

	public long getIdCliente() {
		return idCliente;
	}

	public void setIdCliente(long idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public int getDni() {
		return dni;
	}

	public void setDni(int dni) {
		this.dni = dni;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public LocalDate getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDate fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public List<Venta> getVentas() {
		return ventas;
	}

	public void setVentas(List<Venta> ventas) {
		this.ventas = ventas;
	}

	@Override
	public String toString() {
		return "Cliente [idCliente=" + idCliente + ", nombre=" + nombre + ", apellido=" + apellido + ", mail=" + mail
				+ ", dni=" + dni + ", direccion=" + direccion + ", fechaRegistro=" + fechaRegistro + ", ventas="
				+ ventas + "]";
	}
	
	
	
}
