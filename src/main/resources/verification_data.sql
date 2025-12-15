-- =================================================================================
-- Script de Datos de Verificación para GuardianTech
-- Objetivo: Poblar la base de datos con datos que permitan probar todos los casos de la rúbrica
-- =================================================================================


-- 2. Insertar Departamentos
-- ID 1: Informática
INSERT INTO departamentos (id_departamento, codigo, nombre, telefono) VALUES 
(1, 'INF', 'Informatica', '985123456');
-- ID 2: Matemáticas
INSERT INTO departamentos (id_departamento, codigo, nombre, telefono) VALUES 
(2, 'MAT', 'Matematicas', '985654321');

-- 3. Insertar Roles
-- ID 1: Dirección, ID 2: Docente
INSERT INTO roles (id_rol, nombre, orden) VALUES 
(1, 'Direccion', 1),
(2, 'Docente', 2);


-- 5. Insertar Asuntos Propios
-- IMPORTANTE: Las fechas se calculan relativas para que el script sea válido en el futuro cercana
-- Usaremos fechas fijas que asuman un contexto de prueba general, o fechas muy lejanas/cercanas.
-- Para asegurar consistencia lógica con "trimestres", asumiremos que estamos probando en un entorno donde las fechas cuadran.

-- Caso 1: Alice tiene 1 día pendiente de disfrutar (Aprobado, Fecha Futura)
-- Fecha: 2025-12-25 (Asumiendo que hoy es antes de Navidad 2025, o ajustar según necesidad)
-- Ajuste: Usamos una fecha lejana en el futuro (2099) para garantizar que salga en "pendientes"
INSERT INTO asuntos_propios (id_docente, dia_solicitado, descripcion, fecha_tramitacion, aprobado) VALUES 
(1, '2099-01-01', 'Boda familiar', NOW(), 1); 

-- Caso 2: Bob ha disfrutado muchos días en el pasado (histórico)
-- Fechas pasadas (2023)
INSERT INTO asuntos_propios (id_docente, dia_solicitado, descripcion, fecha_tramitacion, aprobado) VALUES 
(2, '2023-01-10', 'Asunto 1', NOW(), 1),
(2, '2023-02-15', 'Asunto 2', NOW(), 1),
(2, '2023-03-20', 'Asunto 3', NOW(), 1);

-- Caso 3: Charlie tiene una solicitud pendiente de validar (aprobado = NULL)
INSERT INTO asuntos_propios (id_docente, dia_solicitado, descripcion, fecha_tramitacion, aprobado) VALUES 
(3, '2099-05-05', 'Examen conducir', NOW(), NULL);

-- Caso 4: Docente 1 (Alice) intenta pedir otro en el trimestre actual (Para probar validación UC2)
-- Insertamos uno YA disfrutado en el trimestre "actual" (Supongamos hoy es Dic 2025 -> Trimestre 1)
-- Si hoy es Diciembre 2025, ponemos uno en Noviembre 2025.
-- NOTA: Este dato es para probar que NO le deje pedir otro.
INSERT INTO asuntos_propios (id_docente, dia_solicitado, descripcion, fecha_tramitacion, aprobado) VALUES 
(1, CURDATE() - INTERVAL 5 DAY, 'Dia ya disfrutado este trimestre', NOW(), 1);

-- =================================================================================
-- Fin del Script
-- =================================================================================
