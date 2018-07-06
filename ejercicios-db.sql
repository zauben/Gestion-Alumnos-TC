----EJERCICIOS
-- SOLO ES REQUERIDO REALIZAR LOS EJERCICIOS DE COMPLEJIDAD BAJA, realizar los demas ejercicios serán puntos extras para la evaluación del examen.

--- EJERCICIO 1 - COMPLEJIDAD BAJA: 
--Realizar una consulta para consultar todos los alumnos existentes, mostrar: TipoDoc, Documento, Nombre, Apellido, Legajo.

SELECT p.tipodoc, p.documento, p.nombre, p.apellido, a.legajo
FROM alumno a
INNER JOIN persona p
ON a.identificador = p.identificador; 

--- EJERCICIO 2 - COMPLEJIDAD BAJA: 
--Realizar una consulta para consultar todas las carreras a la que un alumno esta inscripto, mostrar: Legajo, nombre, apellido, nombre carrera, fechainscripcioncarrera
--ordenado por legajo descendiente

SELECT a.legajo, p.nombre, p.apellido, c.nombre ,ic.fechainscripcion 
FROM alumno a
INNER JOIN persona p
ON a.identificador = p.identificador
INNER JOIN inscripciones_carrera ic
ON ic.idalumno = a.identificador
INNER JOIN carrera c
ON c.identificador = ic.idcarrera
ORDER BY a.legajo DESC;

--- EJERCICIO 3 - COMPLEJIDAD MEDIA: 
--Realizar una consulta para consultar la cantidad de inscriptos por curso, mostrando: nombre carrera, nombre curso, cantidad inscriptos, cupo máximo por curso

DROP TABLE IF EXISTS cant_insc;

CREATE TEMP TABLE cant_insc AS
SELECT COUNT(*) AS cant, c.identificador AS id
FROM inscripciones_curso ic
INNER JOIN curso c
ON ic.idcurso = c.identificador
GROUP BY c.identificador;

SELECT ca.nombre AS "Nombre Carrera", c.nombre AS "Nombre Curso", ci.cant AS "Inscriptos", c.cupomaximo AS "Cupo"
FROM curso c
INNER JOIN cant_insc ci
ON ci.id = c.identificador
INNER JOIN carrera ca
ON c.idcarrera = ca.identificador

--- EJERCICIO 4 - COMPLEJIDAD ALTA: 
--Sobre la consulta anterior (copiar y pegarla y modificarla) modificar la sql para que la consulta retorne solo los cursos cuya cantidad de inscripciones 
--supera su cupo maximo

DROP TABLE IF EXISTS cant_insc_2;

CREATE TEMP TABLE cant_insc_2 AS
SELECT COUNT(*) AS cant, c.identificador AS id
FROM inscripciones_curso ic
INNER JOIN curso c
ON ic.idcurso = c.identificador
GROUP BY c.identificador
HAVING COUNT(*) > c.cupomaximo;

SELECT ca.nombre AS "Nombre Carrera", c.nombre AS "Nombre Curso", ci.cant AS "Inscriptos", c.cupomaximo AS "Cupo"
FROM curso c
INNER JOIN cant_insc_2 ci
ON ci.id = c.identificador
INNER JOIN carrera ca
ON c.idcarrera = ca.identificador



--- EJERCICIO 5 -  COMPLEJIDAD BAJA: 
-- actualizar todos las cursos que posean anio 2018 y cuyo cupo sea menor a 5, y actualizar el cupo de todos ellos a 10.

BEGIN TRANSACTION;

UPDATE curso SET cupomaximo = 10 WHERE anio = 2018 AND cupomaximo < 5;

COMMIT;

--- EJERCICIO 6 -  COMPLEJIDAD ALTA: 
-- actualizar todas las fechas de inscripcion a cursados que posean el siguiente error: la fecha de inscripcion al cursado de un 
-- alumno es menor a la fecha de inscripcion a la carrera. La nueva fecha que debe tener es la fecha del dia. Se puede hacer en dos pasos, primero
-- realizando la consulta y luego realizando el update manual

BEGIN TRANSACTION;

UPDATE inscripciones_curso ic
SET fechainscripcion = NOW()
WHERE ic.idalumno IN (

SELECT DISTINCT a.identificador
FROM alumno a
INNER JOIN inscripciones_curso ic
ON a.identificador = ic.idalumno
INNER JOIN inscripciones_carrera ica
ON ica.idalumno = a.identificador
WHERE ic.fechainscripcion < ica.fechainscripcion
)

AND ic.idcurso IN (

SELECT DISTINCT ic.idcurso 
FROM alumno a
INNER JOIN inscripciones_curso ic
ON a.identificador = ic.idalumno
INNER JOIN inscripciones_carrera ica
ON ica.idalumno = a.identificador
WHERE ic.fechainscripcion < ica.fechainscripcion);

COMMIT;


--- EJERCICIO 7 - COMPLEJIDAD BAJA:  
--INSERTAR un nuevo registro de alumno con tus datos personales, (hacer todos inserts que considera necesario)

BEGIN TRANSACTION;

INSERT INTO persona (tipodoc, documento, nombre, apellido, fechanac) VALUES ('DNI', 37450760, 'Julian', 'Antuna', '1993-10-25');

INSERT INTO alumno (idpersona, legajo) VALUES ((SELECT COUNT(*) FROM persona),40630);

COMMIT;

--- EJERCICIO 8 -  COMPLEJIDAD BAJA: 
-- si se desea comenzar a persistir de ahora en mas el dato de direccion de un alumno y considerando que este es un único cambio string de 200 caracteres.
-- Determine sobre que tabla seria mas conveniente persistir esta información y realizar la modificación de estructuras correspondientes.

BEGIN TRANSACTION;

ALTER TABLE persona
ADD direccion character varying(200);

COMMIT;