package entity;

public class Curso {

	private int identificador;
	private Carrera carrera;
	private String nombre;
	private String descripcion;
	private int cupomaximo;
	private int anio;

	public Curso() {
	}

	@Override
	public String toString() {
		return this.getNombre();
	}

	@Override
	public boolean equals(Object o) {
		return (o instanceof Curso && ((Curso) o).getIdentificador() == this.getIdentificador());
	}

	@Override
	public int hashCode() {
		return ((Integer) this.getIdentificador()).hashCode();
	}

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

	public int getCupomaximo() {
		return cupomaximo;
	}

	public void setCupomaximo(int cupomaximo) {
		this.cupomaximo = cupomaximo;
	}

	public int getAnio() {
		return anio;
	}

	public void setAnio(int anio) {
		this.anio = anio;
	}

}
