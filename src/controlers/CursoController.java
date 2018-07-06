package controlers;

import java.util.ArrayList;
import data.DataCurso;
import entity.Curso;

public class CursoController {

	private DataCurso datC;

	public CursoController() {
		datC = new DataCurso();
	}

	public ArrayList<Curso> getCursos(int idcarrera) {
		return datC.getAll(idcarrera);
	}
}
