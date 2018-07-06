package entity;

import java.util.Date;

public class Persona {
	private int id_persona;
	private String nombre;
	private String apellido;
	private String dni;
	private String tipodoc;
	private Date fechaNac;
	private String usuario;
	private String pass;

	public Persona(String dni, String nombre, String apellido, boolean habilitado) {
		this.setDni(dni);
		this.setNombre(nombre);
		this.setApellido(apellido);

	}

	public Persona() {
	}

	@Override
	public boolean equals(Object p) {
		return (p instanceof Persona) && (((Persona) p).getDni().equals(this.getDni()));
	}

	public int getId_persona() {
		return id_persona;
	}

	public void setId_persona(int id_persona) {
		this.id_persona = id_persona;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public String getTipodoc() {
		return tipodoc;
	}

	public void setTipodoc(String tipodoc) {
		this.tipodoc = tipodoc;
	}

	public Date getFechaNac() {
		return fechaNac;
	}

	public void setFechaNac(Date fechaNac) {
		this.fechaNac = fechaNac;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

}
