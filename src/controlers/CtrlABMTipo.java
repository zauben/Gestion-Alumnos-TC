package controlers;


import java.util.ArrayList;
import java.util.List;

import data.DataCarrera;
import entity.Curso;
import ui.AMBCTipo;

public class CtrlABMTipo {
	
	private DataCarrera dataTipo;
	private AMBCTipo iuper;

	
	public CtrlABMTipo(){
	dataTipo= new DataCarrera();
	}
	

	public void add(Curso t) {
		dataTipo.add(t);;
	}
	
	public void delete(Curso t){
		dataTipo.delete(t);
	}
	
	public void update(Curso t){
		dataTipo.update(t);
	}
		
	public Curso getByNombre(Curso t){
		return this.dataTipo.getByNombre(t);
	}
	
	public Curso getByNombre(String nombre){
		Curso t=new Curso();
		t.setNombre(nombre);
		return getByNombre(t);
	}
	
	public ArrayList<Curso> getTipos(){
		return dataTipo.getAll();}


	public ArrayList<Curso>getAll() throws Exception{
		return dataTipo.getAll();
		}
	
	public void DialogoTipo() {
		iuper = new AMBCTipo();
		iuper.start();
	}
	
	public List<Curso> loadtipos() { 
		java.util.List<Curso> tipos = new java.util.ArrayList<Curso>();
		tipos.addAll(this.getTipos());
		return tipos;
	}
	
}
