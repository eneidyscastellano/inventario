package com.inventario.repository;

import com.inventario.model.Inventario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventarioRepository extends JpaRepository<Inventario, Long> {

    // Buscar por nombre
    Optional<Inventario> findByNombre(String nombre);

    // Buscar por categoría
    List<Inventario> findByCategoria(String categoria);

    // Buscar productos con cantidad menor a un valor
    List<Inventario> findByCantidadLessThan(Integer cantidad);

    // Buscar por nombre que contenga un texto (búsqueda parcial)
    List<Inventario> findByNombreContainingIgnoreCase(String nombre);
}

