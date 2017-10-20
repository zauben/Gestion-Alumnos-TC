package entity;

import java.sql.Date;
import java.text.DateFormat;

public class Reserva {
	
	private int id_reserva;
	private java.util.Date fecha_hora;
	private String descripcion;
	private Elemento elemento;
	private Persona persona;
	
	public Persona getPersona() {
		return persona;
	}

	public void setPersona(Persona persona) {
		this.persona = persona;
	}

	public int getId_reserva() {
		return id_reserva;
	}
	
	public void setId_reserva(int id_reserva) {
		this.id_reserva = id_reserva;
	}
	
	public java.util.Date getFecha_hora() {
		return fecha_hora;
	}
	
	public void setFecha_hora(java.util.Date fecha_hora) {
		this.fecha_hora = fecha_hora;
	}
	
	public String getDescripcion() {
		return descripcion;
	}
	
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	public Elemento getElemento() {
		return elemento;
	}
	
	public void setElemento(Elemento elemento) {
		this.elemento = elemento;
	}

	public Reserva() {
	}

	public Reserva(Date fecha_hora, String descripcion) {
		this.fecha_hora = fecha_hora;
		this.descripcion = descripcion;
	}
	
	
}
