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

	public void add(Alumno p) {
		dataPer.add(p);
	}

	public void delete(Alumno p) {
		dataPer.borrar(p);
	}

	public void update(Alumno oldp) {

		dataPer.actualizar(oldp);
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
