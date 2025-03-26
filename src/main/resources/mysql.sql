DROP DATABASE IF EXISTS dbclientesdam01;
CREATE DATABASE IF NOT EXISTS dbclientesdam01;
USE dbclientesdam01;

CREATE TABLE cliente (
  id INT NOT NULL AUTO_INCREMENT,
  nombre VARCHAR(50) NOT NULL,
  apellido VARCHAR(50) NOT NULL,
  telefono VARCHAR(50) NOT NULL,
  correo VARCHAR(50) NOT NULL,
  direccion VARCHAR(50) NOT NULL,
  PRIMARY KEY (id)
);

INSERT INTO cliente (nombre, apellido, telefono, correo, direccion) VALUES
('Juan', 'Pérez', '555-1234', 'juan.perez@example.com', 'Calle Falsa 123'),
('María', 'López', '555-5678', 'maria.lopez@example.com', 'Avenida Siempre Viva 456'),
('Carlos', 'Martínez', '555-9101', 'carlos.martinez@example.com', 'Ruta 78 Km 5'),
('Ana', 'González', '555-1122', 'ana.gonzalez@example.com', 'Boulevard Los Olivos 789'),
('Luis', 'García', '555-3344', 'luis.garcia@example.com', 'Callejón Quito 101'),
('Lucía', 'Fernández', '555-5566', 'lucia.fernandez@example.com', 'Alameda Norte 234'),
('Roberto', 'Hernández', '555-7788', 'roberto.hernandez@example.com', 'Camino Largo 567'),
('Patricia', 'Sánchez', '555-9900', 'patricia.sanchez@example.com', 'Sendero de la Luna 890'),
('Jorge', 'Ramírez', '555-2233', 'jorge.ramirez@example.com', 'Plaza Central 345'),
('Sofía', 'Alvarez', '555-4466', 'sofia.alvarez@example.com', 'Avenida del Parque 678');

SELECT * FROM cliente;
