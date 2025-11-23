-- Script SQL para crear la tabla inventario en PostgreSQL
-- Este script es opcional ya que Hibernate puede crear la tabla automáticamente

-- Crear la tabla inventario
CREATE TABLE IF NOT EXISTS inventario (
    id BIGSERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(500),
    cantidad INTEGER NOT NULL,
    precio DOUBLE PRECISION NOT NULL,
    categoria VARCHAR(50),
    fecha_creacion TIMESTAMP NOT NULL,
    fecha_actualizacion TIMESTAMP
);

-- Crear índices para mejorar el rendimiento de las búsquedas
CREATE INDEX IF NOT EXISTS idx_inventario_nombre ON inventario(nombre);
CREATE INDEX IF NOT EXISTS idx_inventario_categoria ON inventario(categoria);
CREATE INDEX IF NOT EXISTS idx_inventario_cantidad ON inventario(cantidad);

-- Insertar datos de ejemplo (opcional)
INSERT INTO inventario (nombre, descripcion, cantidad, precio, categoria, fecha_creacion, fecha_actualizacion)
VALUES
    ('Laptop Dell Inspiron 15', 'Laptop con procesador Intel i5, 8GB RAM, 256GB SSD', 10, 899.99, 'Electrónica', NOW(), NOW()),
    ('Mouse Logitech MX Master', 'Mouse inalámbrico ergonómico', 25, 79.99, 'Accesorios', NOW(), NOW()),
    ('Teclado Mecánico RGB', 'Teclado mecánico con retroiluminación RGB', 15, 129.99, 'Accesorios', NOW(), NOW()),
    ('Monitor Samsung 27"', 'Monitor Full HD de 27 pulgadas', 8, 299.99, 'Electrónica', NOW(), NOW()),
    ('Disco Duro Externo 1TB', 'Disco duro externo portátil', 30, 59.99, 'Almacenamiento', NOW(), NOW());

-- Consultas útiles para verificar los datos
-- SELECT * FROM inventario;
-- SELECT * FROM inventario WHERE cantidad < 10;
-- SELECT * FROM inventario WHERE categoria = 'Electrónica';

