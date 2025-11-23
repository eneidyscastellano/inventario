package com.inventario.mapper;

import com.inventario.dto.InventarioDTO;
import com.inventario.model.Inventario;
import org.springframework.stereotype.Component;

@Component
public class InventarioMapper {

    /**
     * Convierte una entidad Inventario a DTO
     */
    public InventarioDTO toDTO(Inventario inventario) {
        if (inventario == null) {
            return null;
        }

        return new InventarioDTO(
            inventario.getId(),
            inventario.getNombre(),
            inventario.getDescripcion(),
            inventario.getCantidad(),
            inventario.getPrecio(),
            inventario.getCategoria()
        );
    }

    /**
     * Convierte un DTO a entidad Inventario
     */
    public Inventario toEntity(InventarioDTO dto) {
        if (dto == null) {
            return null;
        }

        Inventario inventario = new Inventario();
        inventario.setId(dto.getId());
        inventario.setNombre(dto.getNombre());
        inventario.setDescripcion(dto.getDescripcion());
        inventario.setCantidad(dto.getCantidad());
        inventario.setPrecio(dto.getPrecio());
        inventario.setCategoria(dto.getCategoria());

        return inventario;
    }
}

