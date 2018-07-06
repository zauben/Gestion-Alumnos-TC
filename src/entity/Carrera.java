package entity;

import java.sql.Date;

public class Carrera {

	private int identificador;
	private String nombre;
	private String descripcion;
	private Date fechadesde;
	private Date fechahasta;

	public Carrera() {
	}

	public int getIdentificador() {
		return identificador;
	}

	public void setIdentificador(int identificador) {
		this.identificador = identificador;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechadesde() {
		return fechadesde;
	}

	public void setFechadesde(Date fechadesde) {
		this.fechadesde = fechadesde;
	}

	public Date getFechahasta() {
		return fechahasta;
	}

	public void setFechahasta(Date fechahasta) {
		this.fechahasta = fechahasta;
	}

	@Override
	public boolean equals(Object e) {
		return (e instanceof Carrera) && (((Carrera) e).getNombre().equals(this.getNombre()));
	}
}