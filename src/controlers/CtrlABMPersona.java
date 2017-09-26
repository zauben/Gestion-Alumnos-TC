package controlers;

import java.util.ArrayList;

import data.DataCategoria;
import data.DataPersona;
import entity.Categoria;
import entity.Persona;
import ui.AMBCPersona;
import ui.PrincipalDestkop;

public class CtrlABMPersona {

	private DataPersona dataPer = new DataPersona();
	private AMBCPersona iuper;
	private ArrayList<Persona> pers= new ArrayList<Persona>();
	private PrincipalDestkop pd = new PrincipalDestkop();
	private DataCategoria dataCat= new DataCategoria();
		
	
	public void add(Persona p) {
		
				dataPer.add(p);
	}
	
	public void delete(Persona p){
		dataPer.borrar(p);
	}
	
	public void update(Persona newp, Persona oldp){
		
		dataPer.actualizar(newp, oldp);
	}
		
	/*public Persona getByDni(Persona p){
		dataPer= new DataPersona();
		return this.dataPer.getByDni(p);
	}
	
public Persona getByDni(String dni){
		Persona p=new Persona();
		p.setDni(dni);
		return getByDni(p);
	}
	*/
	
	public Persona getByDni(Persona pe) {
		return this.dataPer.getByDni(pe);
	}
	
	public Persona getByNombreApellido(Persona p){
		
		for (int i=0; i < this.pers.size(); i++){
			if(pers.get(i).getNombre().equalsIgnoreCase(p.getNombre())
				&& pers.get(i).getApellido().equalsIgnoreCase(p.getApellido())) {
				return pers.get(i);		
			}
		}
		return null; 
	}
	
	public ArrayList<Persona> getAll(){
		dataPer= new DataPersona();
		//return this.pers;
		return dataPer.getAll();
	}

	public void DialogoPersonas() {
		iuper = new AMBCPersona();
		iuper.start();
		// TODO Auto-generated method stub
		
	}
	

	public Boolean validarUSR(Persona p) {
		dataPer.cleanLoggedUser();
		return dataPer.validarUsuario(p);
	}

	public ArrayList<Categoria> getCategorias(){
		return dataCat.getAll();
	}

	public void principalFrame() {
		pd.start();		
	}

	public Persona getLogged() {
		dataPer= new DataPersona();
		return dataPer.getLogged();
		
	}


}
