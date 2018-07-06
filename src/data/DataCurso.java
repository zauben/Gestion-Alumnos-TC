package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Carrera;
import entity.Curso;

public class DataCurso {

	public ArrayList<Curso> getAll(int idcarrera) {
		Curso el = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		ArrayList<Curso> elem = new ArrayList<>();
		try {
			stmt = FactoryConexion.getInstancia().getConn()
					.prepareStatement("select * from curso " + "where idcarrera = ?");
			stmt.setInt(1, idcarrera);
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				while (rs.next()) {
					el = new Curso();
					el.setIdentificador(rs.getInt("identificador"));
					el.setCarrera(new Carrera());
					el.getCarrera().setIdentificador(idcarrera);
					el.setNombre(rs.getString("nombre"));
					el.setDescripcion(rs.getString("descripcion"));
					el.setCupomaximo(rs.getInt("cupomaximo"));
					el.setAnio(rs.getInt("anio"));

					elem.add(el);
				}
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
		} catch (SQLException ex) {
			ex.printStackTrace();
		}

		return elem;

	}

}
