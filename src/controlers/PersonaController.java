package controlers;

import data.DataAlumno;
import entity.Alumno;
import ui.GestionAlumnoView;
import ui.PrincipalView;

public class PersonaController {

	private DataAlumno dataPer;
	private GestionAlumnoView iuper;
	private PrincipalView pd;

	public PersonaController() {
		dataPer = new DataAlumno();
		pd = new PrincipalView();
	}

	public Boolean add(Alumno p) {
		if (!dataPer.exists(p)) {
			dataPer.add(p);
			return true;
		} else {
			return false;
		}

	}

	public boolean delete(Alumno p) {
		if (dataPer.exists(p)) {
			dataPer.borrar(p);
			return true;
		} else {
			return false;
		}
	}

	public boolean update(Alumno p) {
		if (dataPer.exists(p)) {
			dataPer.actualizar(p);
			return true;
		} else {
			return false;
		}
	}

	public Alumno getByLegajo(Alumno pe) {
		return this.dataPer.getByLegajo(pe);
	}

	public void dialogoAlumnos() {
		iuper = new GestionAlumnoView();
		iuper.start();

	}

	public Boolean validarUSR(String user, String pass) {
		return dataPer.validarUsuario(user, pass);
	}

	public void principalFrame() {
		pd.start();
	}

}
