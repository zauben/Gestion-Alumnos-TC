package entity;

public class Elemento {

	private int id_elemento;
	private String nombre;
	private TipoElemento tipoElemento;
	
	public Elemento() {
	}

	public Elemento(int id_elemento, String nombre) {
		this.id_elemento = id_elemento;
		this.nombre = nombre;
	}

	public int getId_elemento() {
		return id_elemento;
	}
	
	public void setId_elemento(int id_elemento) {
		this.id_elemento = id_elemento;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public TipoElemento getTipoElemento() {
		return tipoElemento;
	}
	
	public void setTipoElemento(TipoElemento tipoElemento) {
		this.tipoElemento = tipoElemento;
	}
	
	@Override
	public boolean equals(Object e){
		return (e instanceof Elemento) &&
				(((Elemento)e).getNombre().equals(this.getNombre()));
	}
}