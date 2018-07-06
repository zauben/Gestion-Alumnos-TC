package data;

import java.sql.*;

import entity.*;

public class DataAlumno {

	public Alumno getByLegajo(Alumno per) {
		Alumno p = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"select p.identificador AS idpersona, a.identificador AS idalumno, p.nombre AS nombreper, p.apellido, p.documento, a.legajo, p.tipodoc, p.fechanac, "
							+ "ic.idcarrera, c.nombre AS nombrecar from persona p "
							+ "inner join alumno a on a.idpersona=p.identificador "
							+ "left join inscripciones_carrera ic ON ic.idalumno = a.identificador "
							+ "left join carrera c ON c.identificador = ic.idcarrera where a.legajo=?");
			stmt.setInt(1, per.getLegajo());
			rs = stmt.executeQuery();
			if (rs != null && rs.next()) {
				p = new Alumno();
				p.setNombre(rs.getString("nombreper"));
				p.setApellido(rs.getString("apellido"));
				p.setDni(rs.getString("documento"));
				p.setLegajo(rs.getInt("legajo"));
				p.setTipodoc(rs.getString("tipodoc"));
				p.setFechaNac(rs.getDate("fechanac"));
				p.setId_persona(rs.getInt("idpersona"));
				p.setIdentificador(rs.getInt("idalumno"));
				p.setCarrera(new Carrera());
				p.getCarrera().setIdentificador(rs.getInt("idcarrera"));
				p.getCarrera().setNombre(rs.getString("nombrecar"));

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

		return p;
	}

	public void add(Alumno p) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;

		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into persona(tipodoc, documento, nombre, apellido, fechanac) values (?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, p.getTipodoc());
			// stmt.setLong(2, p.getDni());
			stmt.setObject(2, p.getDni(), java.sql.Types.BIGINT);
			stmt.setString(3, p.getNombre());
			stmt.setString(4, p.getApellido());
			stmt.setDate(5, (Date) p.getFechaNac());
			stmt.executeUpdate();
			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				p.setId_persona(keyResultSet.getInt(1));
			}

			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"insert into alumno(idpersona, legajo) values (?,?)", PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, p.getId_persona());
			stmt.setInt(2, p.getLegajo());
			stmt.executeUpdate();
			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				p.setIdentificador(keyResultSet.getInt(1));
			}

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

	public void borrar(Alumno p) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;
		try {

			stmt = FactoryConexion.getInstancia().getConn().prepareStatement("DELETE FROM alumno where legajo=?",
					PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, p.getLegajo());
			stmt.executeUpdate();
			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				p.setIdentificador((keyResultSet.getInt(1)));
			}

			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"DELETE FROM persona where identificador=?", PreparedStatement.RETURN_GENERATED_KEYS);
			stmt.setInt(1, p.getId_persona());
			stmt.executeUpdate();
			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				p.setId_persona(keyResultSet.getInt(1));
			}

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

	public void actualizar(Alumno oldp) {
		PreparedStatement stmt = null;
		ResultSet keyResultSet = null;

		try {
			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"UPDATE persona SET tipodoc=?, documento=?, nombre=? ,apellido=?, fechanac=?  where identificador=? ",
					PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setString(1, oldp.getTipodoc());
			// stmt.setLong(2, Long.parseLong(newp.getDni()));
			stmt.setObject(2, oldp.getDni(), java.sql.Types.BIGINT);
			stmt.setString(3, oldp.getNombre());
			stmt.setString(4, oldp.getApellido());
			stmt.setDate(5, (Date) oldp.getFechaNac());
			stmt.setInt(6, oldp.getIdentificador());
			stmt.executeUpdate();
			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				oldp.setId_persona(keyResultSet.getInt(1));
			}

			stmt = FactoryConexion.getInstancia().getConn().prepareStatement(
					"UPDATE alumno SET idpersona=?, legajo=? where idpersona=? ",
					PreparedStatement.RETURN_GENERATED_KEYS);

			stmt.setInt(1, oldp.getId_persona());
			stmt.setInt(2, oldp.getLegajo());
			stmt.setInt(3, oldp.getIdentificador());
			stmt.executeUpdate();
			keyResultSet = stmt.getGeneratedKeys();
			if (keyResultSet != null && keyResultSet.next()) {
				oldp.setId_persona(keyResultSet.getInt(1));
			}

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

	public Boolean validarUsuario(String user, String pass) {
		if (user.equals("admin") && pass.equals("admin")) {
			return true;
		}
		return false;

	}

}
