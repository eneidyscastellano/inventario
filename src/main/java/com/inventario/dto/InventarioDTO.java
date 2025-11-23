package com.inventario.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioDTO {
    private Long id;
    private String nombre;
    private String descripcion;
    private Integer cantidad;
    private Double precio;
    private String categoria;
}

