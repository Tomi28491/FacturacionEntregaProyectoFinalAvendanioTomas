package com.coderhouse.service;

import java.util.List;

import com.coderhouse.models.Cliente;

public interface IClienteService {
	public List<Cliente> getAllClientes();
	public Cliente findByID(Long id);
	public Cliente saveCliente(Cliente clie);
	public Cliente updateCliente(Long id, Cliente updateClie);
	public void deleteClienteById(Long id);
	
}
