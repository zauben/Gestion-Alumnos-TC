package controlers;


import java.util.ArrayList;

import data.DataTipoElemento;
import entity.TipoElemento;
import ui.AMBCTipo;

public class CtrlABMTipo {
	
	private DataTipoElemento dataTipo;
	private AMBCTipo iuper;

	
	public CtrlABMTipo(){
	dataTipo= new DataTipoElemento();
	}
	

	public void add(TipoElemento t) {
		dataTipo.add(t);;
	}
	
	public void delete(TipoElemento t){
		dataTipo.delete(t);
	}
	
	public void update(TipoElemento t){
		dataTipo.update(t);
	}
		
	public TipoElemento getByNombre(TipoElemento t){
		return this.dataTipo.getByNombre(t);
	}
	
	public TipoElemento getByNombre(String nombre){
		TipoElemento t=new TipoElemento();
		t.setNombre(nombre);
		return getByNombre(t);
	}
	
	public ArrayList<TipoElemento> getTipos(){
		return dataTipo.getAll();}


	public ArrayList<TipoElemento>getAll() throws Exception{
		return dataTipo.getAll();
		}
	
	public void DialogoTipo() {
		iuper = new AMBCTipo();
		iuper.start();
	}
	
}
