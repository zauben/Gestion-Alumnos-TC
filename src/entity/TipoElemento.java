package entity;

public class TipoElemento {
	
	private int id_tipo;
	private String nombre;
	private int cantMaxima;
	private int tiempoMax;
	private int diasAnticipacion;
	
	public int getId_tipo() {
		return id_tipo;
	}
	
	public void setId_tipo(int id_tipo) {
		this.id_tipo = id_tipo;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getCantMaxima() {
		return cantMaxima;
	}
	
	public void setCantMaxima(int cantMaxima) {
		this.cantMaxima = cantMaxima;
	}
	
	public int getTiempoMax() {
		return tiempoMax;
	}
	
	public void setTiempoMax(int tiempoMax) {
		this.tiempoMax = tiempoMax;
	}
	
	public int getDiasAnticipacion() {
		return diasAnticipacion;
	}
	
	public void setDiasAnticipacion(int diasAnticipacion) {
		this.diasAnticipacion = diasAnticipacion;
	}
	
	public TipoElemento( String nombre, int cantMaxima, int tiempoMax, int diasAnticipacion) {
		this.nombre = nombre;
		this.cantMaxima = cantMaxima;
		this.tiempoMax = tiempoMax;
		this.diasAnticipacion = diasAnticipacion;
	}
	
	public TipoElemento() {
	}

	@Override
	public String toString(){
		return Integer.toString(this.getId_tipo())+"- "+this.getNombre();
	}
	
	@Override
	public boolean equals(Object o){
		return (o instanceof TipoElemento && ((TipoElemento)o).getId_tipo()==this.getId_tipo());
	}
	
	@Override
	public int hashCode(){
		return ((Integer)this.getId_tipo()).hashCode();
	}
}
