-- =================================================================================
-- Script de Datos para poner a prueba el Postman
-- =================================================================================

INSERT INTO departamentos (id_departamento, nombre, codigo, telefono) VALUES 
(1, 'Informática y Comunicaciones', 'IFC', '984100101'),
(2, 'Electricidad y Electrónica', 'ELE', '984100102'),
(3, 'Fabricación Mecánica', 'FME', '984100103'),
(4, 'Instalación y Mantenimiento', 'IMA', '984100104'),
(5, 'Química', 'QUI', '984100105'),
(6, 'Administración y Gestión', 'ADG', '984100109'),
(7, 'Comercio y Marketing', 'COM', '984100110'),
(8, 'Edificación y Obra Civil', 'EOC', '984100113');


INSERT INTO roles (id_rol, nombre, orden) VALUES 
(1, 'Direccion', 1),
(2, 'Docente', 2);

INSERT INTO docentes (nombre, apellidos, email, siglas, tipo, antiguedad, id_departamento, id_rol) VALUES 
('Sergio', 'Martínez López', 'sergioml@educastur.org', 'MLSe', 'Funcionario', DATE_SUB(CURDATE(), INTERVAL 5 YEAR), 1, 2);

INSERT INTO docentes (nombre, apellidos, email, siglas, tipo, antiguedad, id_departamento, id_rol) VALUES 
('Laura', 'González Pérez', 'lauragp@educastur.org', 'GPLa', 'Interino', DATE_SUB(CURDATE(), INTERVAL 15 YEAR), 1, 2);

INSERT INTO docentes (nombre, apellidos, email, siglas, tipo, antiguedad, id_departamento, id_rol) VALUES 
('David', 'Álvarez Rubio', 'davidar@educastur.org', 'ARDa', 'Funcionario', CURDATE(), 1, 1); -- Rol Direccion para probar

INSERT INTO docentes (nombre, apellidos, email, siglas, tipo, antiguedad, id_departamento, id_rol) VALUES 
('Diego', 'Fernández Álvarez', 'diegofa@educastur.org', 'FADi', 'Prácticas', CURDATE(), 2, 2);

INSERT INTO docentes (nombre, apellidos, email, siglas, tipo, antiguedad, id_departamento, id_rol) VALUES 
('María', 'Suárez Prieto', 'mariasu@educastur.org', 'SPMa', 'Interino', DATE_SUB(CURDATE(), INTERVAL 5 YEAR), 2, 2);

INSERT INTO docentes (nombre, apellidos, email, siglas, tipo, antiguedad, id_departamento, id_rol) VALUES 
('Javier', 'Campos Rubio', 'javiercr@educastur.org', 'CRJa', 'Funcionario', DATE_SUB(CURDATE(), INTERVAL 1 YEAR), 3, 2);

INSERT INTO docentes (nombre, apellidos, email, siglas, tipo, antiguedad, id_departamento, id_rol) VALUES 
('Hugo', 'García Prado', 'hugogp@educastur.org', 'GPHu', 'Funcionario', DATE_SUB(CURDATE(), INTERVAL 6 YEAR), 4, 2);

INSERT INTO docentes (nombre, apellidos, email, siglas, tipo, antiguedad, id_departamento, id_rol) VALUES 
('Elena', 'López Castro', 'elenalc@educastur.org', 'LCEl', 'Interino', DATE_SUB(CURDATE(), INTERVAL 10 YEAR), 4, 2);

INSERT INTO docentes (nombre, apellidos, email, siglas, tipo, antiguedad, id_departamento, id_rol) VALUES 
('Daniel', 'Santos Vega', 'danielsv@educastur.org', 'SVDa', 'Funcionario', DATE_SUB(CURDATE(), INTERVAL 2 YEAR), 5, 2);

INSERT INTO docentes (nombre, apellidos, email, siglas, tipo, antiguedad, id_departamento, id_rol) VALUES 
('Lucía', 'Ortega Rivas', 'luciaor@educastur.org', 'ORLu', 'Funcionario', DATE_SUB(CURDATE(), INTERVAL 10 YEAR), 5, 2);

INSERT INTO docentes (nombre, apellidos, email, siglas, tipo, antiguedad, id_departamento, id_rol) VALUES 
('Noelia', 'Lago Souto', 'noelials@educastur.org', 'LSNo', 'Interino', DATE_SUB(CURDATE(), INTERVAL 5 YEAR), 5, 2);

INSERT INTO asuntos_propios (id_docente, dia_solicitado, descripcion, fecha_tramitacion, aprobado) VALUES 
(3, '2099-12-31', 'Dia Pendiente Futuro', NOW(), 1);

INSERT INTO asuntos_propios (id_docente, dia_solicitado, descripcion, fecha_tramitacion, aprobado) VALUES 
(10, '2023-01-15', 'Historico 1', NOW(), 1),
(10, '2023-02-20', 'Historico 2', NOW(), 1),
(10, '2023-05-10', 'Historico 3', NOW(), 1);

INSERT INTO asuntos_propios (id_docente, dia_solicitado, descripcion, fecha_tramitacion, aprobado) VALUES 
(1, '2025-11-15', 'Solicitud sin validar', NOW(), NULL);

INSERT INTO asuntos_propios (id_docente, dia_solicitado, descripcion, fecha_tramitacion, aprobado) VALUES 
(2, CURDATE(), 'Dia de hoy', NOW(), 1);
