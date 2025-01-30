package com.coderhouse.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coderhouse.models.Cliente;
import com.coderhouse.repository.ClienteRepository;

import jakarta.transaction.Transactional;

@Service
public class ClienteService implements IClienteService{
	@Autowired
	private ClienteRepository clieRepo;
	
	@Autowired
	private TimeRestService timeServ;
	
	public List<Cliente> getAllClientes(){
		return clieRepo.findAll();
	}
	
	public Cliente findByID(Long id) {
		
		return clieRepo.findById(id).orElseThrow(() -> new IllegalArgumentException("Cliente no encontrado"));
	}
	
	@Transactional
	public Cliente saveCliente(Cliente clie) {
		clie.setFechaRegistro(timeServ.obtenerFechaActual());
		return clieRepo.save(clie);	
	}
	
	@Transactional
	public Cliente updateCliente(Long id, Cliente updateClie) {
		Cliente cliente = findByID(id);
		cliente.setNombre(updateClie.getNombre());
		cliente.setApellido(updateClie.getApellido());
		cliente.setDireccion(updateClie.getDireccion());
		cliente.setMail(updateClie.getMail());
		if (updateClie.getDni() != 0) {
			cliente.setDni(updateClie.getDni());
		}
		
		return saveCliente(cliente);
	}
	
	public void deleteClienteById(Long id) {
		if (!clieRepo.existsById(id)) {
			throw new IllegalArgumentException("Cliente no encontrado");
		}
		clieRepo.deleteById(id);
	}

}
