package entity;

import java.util.ArrayList;

public class Alumno extends Persona {
	private int identificador;
	private Integer legajo;
	private Carrera carrera;
	private ArrayList <Curso> cursosInscripto;
	
	
	
	
	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}
	
	

	public Carrera getCarrera() {
		return carrera;
	}

	public void setCarrera(Carrera carrera) {
		this.carrera = carrera;
	}

	public ArrayList<Curso> getCursosInscripto() {
		return cursosInscripto;
	}

	public void setCursosInscripto(ArrayList<Curso> cursosInscripto) {
		this.cursosInscripto = cursosInscripto;
	}

	public Integer getLegajo() {
		return legajo;
	}

	public void setLegajo(Integer legajo) {
		this.legajo = legajo;
	}

	public Alumno (String dni, String nombre, String apellido, boolean habilitado){
		this.setDni(dni);
		this.setNombre(nombre);
		this.setApellido(apellido);
	}
	
	public Alumno(){}
	
	@Override
	public boolean equals(Object p){
		return (p instanceof Alumno) &&
			 (((Alumno)p).getDni().equals(this.getDni()));
	}
}





