-- Eliminación de tablas si existen
DROP TABLE IF EXISTS Usuario;
DROP TABLE IF EXISTS Socio_Entrenamiento;
DROP TABLE IF EXISTS Entrenamiento;
DROP TABLE IF EXISTS Asistencia;
DROP TABLE IF EXISTS AbonoSocio;
DROP TABLE IF EXISTS Monitor;
DROP TABLE IF EXISTS Abono;
DROP TABLE IF EXISTS Socio;


-- Creación de la tabla Socio
CREATE TABLE IF NOT EXISTS Socio (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    apellidos VARCHAR(100) NOT NULL,
    dni CHAR(8) NOT NULL,
    telefono VARCHAR(20),
    correo VARCHAR(100),
    foto VARCHAR(500),
    es_activo BOOLEAN DEFAULT TRUE
);

-- Creación de la tabla Abono
CREATE TABLE IF NOT EXISTS Abono (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    descripcion VARCHAR(100) NOT NULL,
    tipo_abono INT not null,
    duracion INT,
    precio DECIMAL(10, 2) NOT NULL
);

-- Creación de la tabla AbonoSocio (relación entre Socio y Abono)
CREATE TABLE IF NOT EXISTS AbonoSocio (
    id SERIAL PRIMARY KEY,
    id_socio INT NOT NULL,
    id_abono INT NOT NULL,
    fecha_inicio TIMESTAMP(6) NOT NULL,
    fecha_fin TIMESTAMP(6) NOT NULL,
    precio DECIMAL(10, 2) NOT NULL,
    CONSTRAINT fk_abono_socio FOREIGN KEY (id_socio) REFERENCES Socio(id),
    CONSTRAINT fk_abono FOREIGN KEY (id_abono) REFERENCES Abono(id)
);

-- Creación de la tabla Monitor
CREATE TABLE IF NOT EXISTS Monitor (
    id SERIAL PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    dni VARCHAR(20) NOT NULL,
    foto VARCHAR(500),
    salario DECIMAL(10, 2) NOT NULL
);

-- Creación de la tabla Asistencia (relación con Socio)
CREATE TABLE IF NOT EXISTS Asistencia (
    id SERIAL PRIMARY KEY,
    id_socio INT NOT NULL,
    fecha TIMESTAMP(6) NOT NULL,
    hora_entrada TIME NOT NULL,
    hora_salida TIME,
    CONSTRAINT fk_asistencia_socio FOREIGN KEY (id_socio) REFERENCES Socio(id)
);

-- Creación de la tabla Entrenamiento (relación con Monitor)
CREATE TABLE IF NOT EXISTS Entrenamiento (
    id SERIAL PRIMARY KEY,
    nombre_entrenamiento VARCHAR(100) NOT NULL,
    frecuencia INT, -- frecuencia de entrenamiento por semana
    duracion INT, -- duración en minutos
    nivel_dificultad VARCHAR(50),
    fecha_inicio TIMESTAMP(6) NOT NULL,
    fecha_fin TIMESTAMP(6) NOT NULL,
    id_monitor INT NOT NULL,
    CONSTRAINT fk_entrenamiento_monitor FOREIGN KEY (id_monitor) REFERENCES Monitor(id)
);
CREATE TABLE IF NOT EXISTS Socio_Entrenamiento (
    id SERIAL PRIMARY KEY,
    id_socio INT NOT NULL,
    id_entrenamiento INT NOT NULL,
    CONSTRAINT fk_socio_entrenamiento_socio FOREIGN KEY (id_socio) REFERENCES Socio(id),
    CONSTRAINT fk_socio_entrenamiento_entrenamiento FOREIGN KEY (id_entrenamiento) REFERENCES Entrenamiento(id)
);

CREATE TABLE if not exists usuario (
                         id SERIAL PRIMARY KEY,
                         username VARCHAR(500) NOT NULL UNIQUE,
                         password VARCHAR(500) NOT NULL,
                         rol INT NOT NULL
);


ALTER TABLE socio
    ADD COLUMN id_usuario INT NULL,
    ADD CONSTRAINT fk_socio_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id);

ALTER TABLE monitor
    ADD COLUMN id_usuario INT NULL,
    ADD CONSTRAINT fk_monitor_usuario FOREIGN KEY (id_usuario) REFERENCES usuario(id);




INSERT INTO Socio (id, nombre, apellidos, dni, telefono, correo, es_activo) VALUES
(1,'Juan', 'Pérez García', '12345678', '612345678', 'juan.perez@example.com', TRUE),
(2,'María', 'López Fernández', '23456789', '623456789', 'maria.lopez@example.com', TRUE),
(3,'Carlos', 'Gómez Martínez', '34567890', '634567890', 'carlos.gomez@example.com', TRUE),
(4,'Ana', 'Hernández Ruiz', '45678901', '645678901', 'ana.hernandez@example.com', TRUE),
(5,'Pedro', 'Jiménez Sánchez', '56789012', '656789012', 'pedro.jimenez@example.com', TRUE),
(6,'Laura', 'Moreno López', '67890123', '667890123', 'laura.moreno@example.com', TRUE),
(7,'Luis', 'Romero Torres', '78901234', '678901234', 'luis.romero@example.com', TRUE),
(8,'Isabel', 'Castro Pérez', '89012345', '689012345', 'isabel.castro@example.com', TRUE),
(9,'Javier', 'Díaz Ruiz', '90123456', '690123456', 'javier.diaz@example.com', TRUE),
(10,'Sofía', 'Ríos Gómez', '01234567', '601234567', 'sofia.rios@example.com', TRUE);

INSERT INTO Abono (nombre, descripcion, tipo_abono, duracion, precio) VALUES
('Abono Mensual', 'Acceso ilimitado durante un mes', 0, 30, 50.00),
('Abono Trimestral', 'Acceso ilimitado durante tres meses', 1, 90, 135.00),
('Abono Semestral', 'Acceso ilimitado durante seis meses', 2, 180, 260.00),
('Abono Anual', 'Acceso ilimitado durante un año', 3, 365, 480.00);


INSERT INTO AbonoSocio (id_socio, id_abono, fecha_inicio, fecha_fin, precio) VALUES
(1, 1, '2023-09-01 08:00:00', '2023-09-30 23:59:59', 50.00),  -- Abono Mensual
(2, 2, '2023-07-01 08:00:00', '2023-09-30 23:59:59', 135.00), -- Abono Trimestral
(3, 4, '2023-01-01 08:00:00', '2023-12-31 23:59:59', 480.00), -- Abono Anual
(4, 3, '2023-06-01 08:00:00', '2023-11-30 23:59:59', 260.00), -- Abono Semestral
(5, 1, '2023-10-01 08:00:00', '2023-10-31 23:59:59', 50.00),  -- Abono Mensual
(6, 4, '2023-05-01 08:00:00', '2024-04-30 23:59:59', 480.00), -- Abono Anual
(7, 2, '2023-08-01 08:00:00', '2023-10-31 23:59:59', 135.00), -- Abono Trimestral
(8, 1, '2023-09-15 08:00:00', '2023-10-14 23:59:59', 50.00),  -- Abono Mensual
(9, 3, '2023-07-01 08:00:00', '2023-12-31 23:59:59', 260.00), -- Abono Semestral
(10, 2, '2023-07-01 08:00:00', '2023-09-30 23:59:59', 135.00); -- Abono Trimestral

INSERT INTO Monitor (nombre, dni, salario) VALUES
('Roberto Díaz', '32109876', 1800.00),
('Claudia Martínez', '43210987', 1750.00),
('José Fernández', '54321098', 1900.00),
('Carla Gutiérrez', '65432109', 1850.00),
('Alejandro Pérez', '76543210', 2000.00),
('Santiago Herrera', '87654321', 1950.00),
('Lucía Romero', '98765432', 1700.00),
('Carmen López', '09876543', 1850.00),
('Pablo García', '10987654', 1950.00),
('Marta Jiménez', '21098765', 1750.00);


INSERT INTO Asistencia (id_socio, fecha, hora_entrada, hora_salida) VALUES
(1, '2023-09-01 08:00:00', '08:00:00', '10:00:00'),
(2, '2023-09-01 09:00:00', '09:00:00', '11:00:00'),
(3, '2023-09-02 08:30:00', '08:30:00', '10:30:00'),
(4, '2023-09-02 09:30:00', '09:30:00', '11:30:00'),
(5, '2023-09-03 07:45:00', '07:45:00', '09:45:00'),
(6, '2023-09-03 08:15:00', '08:15:00', '10:15:00'),
(7, '2023-09-04 08:45:00', '08:45:00', '10:45:00'),
(8, '2023-09-04 09:15:00', '09:15:00', '11:15:00'),
(9, '2023-09-05 07:30:00', '07:30:00', '09:30:00'),
(10, '2023-09-05 08:00:00', '08:00:00', '10:00:00');

INSERT INTO Entrenamiento (nombre_entrenamiento, frecuencia, duracion, nivel_dificultad, fecha_inicio, fecha_fin, id_monitor) VALUES
('Entrenamiento Fuerza', 3, 60, 'Intermedio', '2023-09-01 08:00:00', '2023-12-01 08:00:00', 1),
('Entrenamiento Cardio', 4, 45, 'Principiante', '2023-09-01 09:00:00', '2023-12-01 09:00:00', 2),
('HIIT Avanzado', 5, 30, 'Avanzado', '2023-09-01 10:00:00', '2023-12-01 10:00:00', 3),
('Pilates Básico', 2, 60, 'Principiante', '2023-09-01 11:00:00', '2023-12-01 11:00:00', 4),
('Entrenamiento Funcional', 3, 50, 'Intermedio', '2023-09-01 12:00:00', '2023-12-01 12:00:00', 5),
('Yoga Avanzado', 3, 60, 'Avanzado', '2023-09-01 13:00:00', '2023-12-01 13:00:00', 6),
('Spinning', 4, 45, 'Intermedio', '2023-09-01 14:00:00', '2023-12-01 14:00:00', 7),
('CrossFit', 5, 60, 'Avanzado', '2023-09-01 15:00:00', '2023-12-01 15:00:00', 8),
('Zumba', 3, 60, 'Principiante', '2023-09-01 16:00:00', '2023-12-01 16:00:00', 9),
('Boxeo', 3, 60, 'Intermedio', '2023-09-01 17:00:00', '2023-12-01 17:00:00', 10);








