package com.coderhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
