package controlers;

import java.util.ArrayList;
import java.util.List;

import javax.swing.JComboBox;

import org.jdesktop.beansbinding.AutoBinding.UpdateStrategy;
import org.jdesktop.swingbinding.JComboBoxBinding;
import org.jdesktop.swingbinding.SwingBindings;

import data.DataCurso;
import data.DataAlumno;
import data.DataInscripcion;
import data.DataCarrera;
import entity.Carrera;
import entity.Persona;
import entity.InscripcionCurso;
import entity.Curso;
import ui.AMBCIncripcionCurso;
import ui.AMBCReservasPendientes;

public class CtrlABMInscripcion {

	private DataInscripcion dataRes;
	private DataAlumno dataPer;
	private DataCurso dataElem;
	private DataCarrera dataTipo;
	private ArrayList<InscripcionCurso> res;
	private AMBCIncripcionCurso iuRe;
	private AMBCReservasPendientes rePer;
	

	public void initDataBindings(ArrayList<Carrera> elementos, JComboBox cboElementos_1) {
		JComboBoxBinding<Carrera,List<Carrera>,JComboBox> jComboBinding = SwingBindings.createJComboBoxBinding(UpdateStrategy.READ_WRITE, elementos, cboElementos_1, "elementosDeUnTipo");
		jComboBinding.bind();
	}	
	
public CtrlABMInscripcion() {
	dataRes = new DataInscripcion();
	dataElem= new DataCurso();
	dataPer= new DataAlumno();
	dataTipo= new DataCarrera();
}

public void add(InscripcionCurso r) {
	dataRes.add(r);;
}

public void delete(InscripcionCurso r){
	dataRes.delete(r);
}

public void update(InscripcionCurso r){
	dataRes.update(r);
}


public ArrayList<InscripcionCurso> getAll(){
return dataRes.getAll();}

public ArrayList<Persona> getPersonas(){
return dataPer.getAll();}

public void DialogoReserva() {
	iuRe = new AMBCIncripcionCurso();
	iuRe.start();
	// TODO Auto-generated method stub
	
}

public void reservasDePer() {
	rePer = new AMBCReservasPendientes();
	rePer.start();
}

public ArrayList<InscripcionCurso> reservasDePer(Persona logged) {
	return dataRes.getReservasdePer(logged);
	
}

public void cancelarReservas(Persona logged) {
	dataRes.CancelarReservasDePersona(logged);
}



}	