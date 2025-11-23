package com.inventario.controller;

import com.inventario.model.Inventario;
import com.inventario.service.InventarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/inventario")
@CrossOrigin(origins = "*")
public class InventarioController {

    @Autowired
    private InventarioService inventarioService;

    /**
     * Crear un nuevo producto
     * POST /api/inventario
     */
    @PostMapping
    public ResponseEntity<Inventario> crear(@RequestBody Inventario inventario) {
        try {
            Inventario nuevoInventario = inventarioService.guardar(inventario);
            return new ResponseEntity<>(nuevoInventario, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtener todos los productos
     * GET /api/inventario
     */
    @GetMapping
    public ResponseEntity<List<Inventario>> obtenerTodos() {
        try {
            List<Inventario> inventarios = inventarioService.obtenerTodos();
            if (inventarios.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inventarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Obtener un producto por ID
     * GET /api/inventario/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Inventario> obtenerPorId(@PathVariable("id") Long id) {
        Optional<Inventario> inventario = inventarioService.obtenerPorId(id);
        return inventario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Actualizar un producto
     * PUT /api/inventario/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<Inventario> actualizar(@PathVariable("id") Long id, @RequestBody Inventario inventario) {
        try {
            Inventario inventarioActualizado = inventarioService.actualizar(id, inventario);
            return new ResponseEntity<>(inventarioActualizado, HttpStatus.OK);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Eliminar un producto
     * DELETE /api/inventario/{id}
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> eliminar(@PathVariable("id") Long id) {
        try {
            inventarioService.eliminar(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Buscar por nombre
     * GET /api/inventario/buscar/nombre/{nombre}
     */
    @GetMapping("/buscar/nombre/{nombre}")
    public ResponseEntity<Inventario> buscarPorNombre(@PathVariable("nombre") String nombre) {
        Optional<Inventario> inventario = inventarioService.buscarPorNombre(nombre);
        return inventario.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    /**
     * Buscar por categoría
     * GET /api/inventario/buscar/categoria/{categoria}
     */
    @GetMapping("/buscar/categoria/{categoria}")
    public ResponseEntity<List<Inventario>> buscarPorCategoria(@PathVariable("categoria") String categoria) {
        try {
            List<Inventario> inventarios = inventarioService.buscarPorCategoria(categoria);
            if (inventarios.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inventarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Buscar productos con stock bajo
     * GET /api/inventario/stock-bajo?cantidad={cantidad}
     */
    @GetMapping("/stock-bajo")
    public ResponseEntity<List<Inventario>> buscarStockBajo(@RequestParam("cantidad") Integer cantidad) {
        try {
            List<Inventario> inventarios = inventarioService.buscarStockBajo(cantidad);
            if (inventarios.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inventarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    /**
     * Buscar por nombre parcial
     * GET /api/inventario/buscar?nombre={nombre}
     */
    @GetMapping("/buscar")
    public ResponseEntity<List<Inventario>> buscarPorNombreParcial(@RequestParam("nombre") String nombre) {
        try {
            List<Inventario> inventarios = inventarioService.buscarPorNombreParcial(nombre);
            if (inventarios.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(inventarios, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}

