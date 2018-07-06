package controlers;

import data.DataInscripcion;
import entity.InscripcionCurso;
import ui.IncripcionCursoView;

public class InscripcionCotroller {

	private DataInscripcion dataRes;

	public InscripcionCotroller() {
		dataRes = new DataInscripcion();

	}

	public Boolean add(InscripcionCurso r) {
		if (!dataRes.yaInscripto(r)) {
			dataRes.add(r);
			return true;
		}
		return false;

	}

	public void DialogoReserva() {
		IncripcionCursoView iuRe = new IncripcionCursoView();
		iuRe.start();
	}

}