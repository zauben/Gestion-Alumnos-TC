package data;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entity.Alumno;
import entity.Carrera;
import entity.Persona;
import entity.InscripcionCurso;

public class DataInscripcion {

	public boolean yaInscripto(InscripcionCurso r) {
		Alumno p = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn()
					.prepareStatement("select * from persona p inner join alumno a on a.idpersona=p.identificador "
							+ "inner join inscripciones_curso ic ON ic.idalumno = a.identificador "
							+ "where a.legajo=? and ic.idcurso=? and EXTRACT(YEAR FROM ic.fechainscripcion) = 2018");
			stmt.setInt(1, r.getAlumno().getLegajo());
			stmt.setInt(2, r.getCurso().getIdentificador());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				return true;
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		try {
			if (rs != null)
				rs.close();
			if (stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return false;

	}

	public void add(InscripcionCurso r) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into inscripciones_curso(idalumno, idcurso, fechainscripcion) values (?,?,NOW())",
					PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, r.getAlumno().getIdentificador());
			stmt.setInt(2, r.getCurso().getIdentificador());
			stmt.executeUpdate();
			keyResultSet = stmt.getGeneratedKeys();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if (keyResultSet != null)
				keyResultSet.close();
			if (stmt != null)
				stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
