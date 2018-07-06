package controlers;

import java.util.ArrayList;

import data.DataAlumno;
import entity.Alumno;
import entity.Persona;
import ui.PantallaGestionAlumno;
import ui.PrincipalDestkop;

public class CtrlABMPersona {

	private DataAlumno dataPer = new DataAlumno();
	private PantallaGestionAlumno iuper;
	private ArrayList<Persona> pers= new ArrayList<Persona>();
	private PrincipalDestkop pd = new PrincipalDestkop();
		
	
	public void add(Alumno p) {
		
				dataPer.add(p);
	}
	
	public void delete(Alumno p){
		dataPer.borrar(p);
	}
	
	public void update(Alumno oldp){
		
		dataPer.actualizar(oldp);
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
	
	public Alumno getByLegajo(Alumno pe) {
		return this.dataPer.getByLegajo(pe);
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
	
	public ArrayList<Alumno> getAll(){
		dataPer= new DataAlumno();
		//return this.pers;
		return dataPer.getAll();
	}

	public void dialogoAlumnos() {
		iuper = new PantallaGestionAlumno();
		iuper.start();
		// TODO Auto-generated method stub
		
	}
	

	public Boolean validarUSR(String user, String pass) {
		return dataPer.validarUsuario(user, pass);
	}

	public void principalFrame() {
		pd.start();		
	}

}
