package controlers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultComboBoxModel;

import data.DataCurso;
import data.DataCarrera;
import entity.Carrera;
import entity.Curso;
import ui.PantallaInscripcion;
public class CtrlABMElemento {
	

	private DataCurso datC;
	private DataCarrera dataTipo;
	private PantallaInscripcion iuEl;
	
	public void add(Carrera e){
		datC= new DataCurso();
		datC.add(e);
		
		}
	public void delete(Carrera e){
		datC= new DataCurso();
		datC.delete(e);
		
		}

	public void update(Carrera e){
		datC= new DataCurso();
		datC.update(e);
		
		}

	public Carrera getByNombre(Carrera e){
		datC= new DataCurso();
		return this.datC.getByCarrera(e);
	
	}
	
	public Carrera getByCarrera(int idcarrera){
		datC= new DataCurso();
		return this.datC.getByCarrera(idcarrera);
	
	}	
	
	public Carrera getByNombre(String nombre){
		datC= new DataCurso();
		Carrera e=new Carrera();
		e = datC.getByNombre(nombre);
		return e;
		
	}
	


	public ArrayList<Curso> getTipo(){
		datC= new DataCurso();
		return dataTipo.getAll();
	}
		
	
	public void DialogoElementos() {
		iuEl = new PantallaInscripcion();
		iuEl.start();// TODO Auto-generated method stub	

}
	public List<Carrera> loadElementos(Curso tipoel){
		
		java.util.List<Carrera> elementos = new java.util.ArrayList<Carrera>();
		elementos.addAll(this.getCursos(tipoel));
			return elementos;}
	

public ArrayList<Curso> getCursos(int idcarrera){ 
	datC= new DataCurso();
	return datC.getAll(idcarrera);
}

public DefaultComboBoxModel loadele(Curso tipoItemActual) {
	DefaultComboBoxModel elem = new DefaultComboBoxModel();
	for (int i = 0; i < this.loadElementos(tipoItemActual).size(); i++) {
		elem.addElement(this.loadElementos(tipoItemActual).get(i));}
	return elem;}

}
