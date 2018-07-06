package entity;

import java.sql.Date;
import java.text.DateFormat;

public class InscripcionCurso {

	private Alumno alumno;
	private Curso curso;
	private java.util.Date fechainscripcion;

	public InscripcionCurso() {
	}

	public Alumno getAlumno() {
		return alumno;
	}

	public void setAlumno(Alumno alumno) {
		this.alumno = alumno;
	}

	public Curso getCurso() {
		return curso;
	}

	public void setCurso(Curso curso) {
		this.curso = curso;
	}

	public java.util.Date getFechainscripcion() {
		return fechainscripcion;
	}

	public void setFechainscripcion(java.util.Date fechainscripcion) {
		this.fechainscripcion = fechainscripcion;
	}

}
