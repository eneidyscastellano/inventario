package com.inventario.service;

import com.inventario.model.Inventario;
import com.inventario.repository.InventarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventarioService {

    @Autowired
    private InventarioRepository inventarioRepository;

    /**
     * Guardar un nuevo producto en el inventario
     */
    public Inventario guardar(Inventario inventario) {
        return inventarioRepository.save(inventario);
    }

    /**
     * Actualizar un producto existente
     */
    public Inventario actualizar(Long id, Inventario inventarioActualizado) {
        Optional<Inventario> inventarioExistente = inventarioRepository.findById(id);

        if (inventarioExistente.isPresent()) {
            Inventario inventario = inventarioExistente.get();
            inventario.setNombre(inventarioActualizado.getNombre());
            inventario.setDescripcion(inventarioActualizado.getDescripcion());
            inventario.setCantidad(inventarioActualizado.getCantidad());
            inventario.setPrecio(inventarioActualizado.getPrecio());
            inventario.setCategoria(inventarioActualizado.getCategoria());
            return inventarioRepository.save(inventario);
        } else {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
    }

    /**
     * Eliminar un producto por ID
     */
    public void eliminar(Long id) {
        if (inventarioRepository.existsById(id)) {
            inventarioRepository.deleteById(id);
        } else {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
    }

    /**
     * Obtener todos los productos
     */
    @Transactional(readOnly = true)
    public List<Inventario> obtenerTodos() {
        return inventarioRepository.findAll();
    }

    /**
     * Obtener un producto por ID
     */
    @Transactional(readOnly = true)
    public Optional<Inventario> obtenerPorId(Long id) {
        return inventarioRepository.findById(id);
    }

    /**
     * Buscar por nombre
     */
    @Transactional(readOnly = true)
    public Optional<Inventario> buscarPorNombre(String nombre) {
        return inventarioRepository.findByNombre(nombre);
    }

    /**
     * Buscar por categoría
     */
    @Transactional(readOnly = true)
    public List<Inventario> buscarPorCategoria(String categoria) {
        return inventarioRepository.findByCategoria(categoria);
    }

    /**
     * Buscar productos con stock bajo
     */
    @Transactional(readOnly = true)
    public List<Inventario> buscarStockBajo(Integer cantidadMinima) {
        return inventarioRepository.findByCantidadLessThan(cantidadMinima);
    }

    /**
     * Buscar por nombre parcial
     */
    @Transactional(readOnly = true)
    public List<Inventario> buscarPorNombreParcial(String nombre) {
        return inventarioRepository.findByNombreContainingIgnoreCase(nombre);
    }
}

