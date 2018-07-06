package data;

import java.util.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import entity.Carrera;
import entity.Persona;
import entity.InscripcionCurso;

public class DataInscripcion {

	public ArrayList<InscripcionCurso> getAll(){
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<InscripcionCurso> inscripcionCursos= new ArrayList<InscripcionCurso>();
		try {
			stmt = FactoryConexion.getInstancia()
					.getConn().createStatement();
			rs = stmt.executeQuery("select * from reserva r "
		 			+ "inner join elemento e on r.id_elemento=e.id_elemento");
			if(rs!=null){
				while(rs.next()){
					InscripcionCurso r=new InscripcionCurso();
					r.setElemento(new Carrera());
					r.setId_reserva(rs.getInt("id_reserva"));
					//r.setFecha_hora(rs.getString("fecha_hora")); ver
					r.setDescripcion(rs.getString("descripcion"));
					r.getElemento().setId_elemento(rs.getInt("id_elemento"));
		 			r.getElemento().setNombre(rs.getString("e.nombre"));
		 			inscripcionCursos.add(r);
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return inscripcionCursos;	
	}
	
public ArrayList<InscripcionCurso> getReservasdePer(Persona per){ //OBTENER RESERVAS POR PERSONA
	DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
	java.util.Date date = new Date();
	
	
	PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<InscripcionCurso> inscripcionCursos= new ArrayList<InscripcionCurso>();
		try {
			stmt = FactoryConexion.getInstancia().getConn()
					.prepareStatement("select * from reserva r "
		 			+ "inner join elemento e on r.id_elemento=e.id_elemento "
		 			+ "where id_persona = ? and fecha_hora > ?",
		 			PreparedStatement.RETURN_GENERATED_KEYS);
			
			stmt.setInt(1, per.getId_persona());
			stmt.setString(2, dateFormat.format(date));
			
			
			rs = stmt.executeQuery();
			if(rs!=null){
				while(rs.next()){
					InscripcionCurso r=new InscripcionCurso();
					r.setElemento(new Carrera());
					r.setId_reserva(rs.getInt("id_reserva"));
					r.setFecha_hora(rs.getTimestamp("fecha_hora")); 
					r.setDescripcion(rs.getString("descripcion"));
					r.getElemento().setId_elemento(rs.getInt("id_elemento"));
		 			r.getElemento().setNombre(rs.getString("e.nombre"));
		 			inscripcionCursos.add(r);
				}
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		try {
			if(rs!=null) rs.close();
			if(stmt!=null) stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {	
			e.printStackTrace();
		}
		return inscripcionCursos;	
	}
	
	public void add(InscripcionCurso r){
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"insert into inscripciones_curso(idalumno, idcurso, fechainscripcion) values (?,?,NOW())",
					PreparedStatement.RETURN_GENERATED_KEYS
					);

 			stmt.setInt(1, r.getAlumno().getIdentificador());
 			stmt.setInt(2, r.getCurso().getIdentificador());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void delete(InscripcionCurso r){
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"DELETE FROM reserva where id_reserva=?",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setInt(1, r.getId_reserva());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				r.setId_reserva(keyResultSet.getInt(1));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

	public void update(InscripcionCurso r){
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"UPDATE  reserva SET fecha_hora=?, descripcion=?, id_elemento =? where id_reserva=? ",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			
			stmt.setString(1, r.getFecha_hora().toString());
			stmt.setString(2, r.getDescripcion());
			stmt.setInt(3, r.getElemento().getId_elemento());
			stmt.setInt(4, r.getId_reserva());			
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				r.setId_reserva(keyResultSet.getInt(1));
			}
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
	}

	public void CancelarReservasDePersona(Persona logged) {
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"DELETE FROM reserva where id_persona=?",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setInt(1, logged.getId_persona());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		try {
			if(keyResultSet!=null)keyResultSet.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
}
