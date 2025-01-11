package com.coderhouse.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderhouse.models.Producto;

public interface ProductoRepository extends JpaRepository<Producto, Long> {

}
