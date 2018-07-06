package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Carrera;
import entity.Curso;

public class DataCurso {
	
	
	public ArrayList<Curso> getAll(int idcarrera){
		Curso el = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<Curso> elem= new ArrayList<>();
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
			"select * from curso "
			+ "where idcarrera = ?");
			stmt.setInt(1, idcarrera);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
				while(rs.next()){
					el=new Curso();
		 			el.setIdentificador(rs.getInt("identificador"));
		 			el.setCarrera(new Carrera());
		 			el.getCarrera().setIdentificador(idcarrera);
		 			el.setNombre(rs.getString("nombre"));
		 			el.setDescripcion(rs.getString("descripcion"));
		 			el.setCupomaximo(rs.getInt("cupomaximo"));
		 			el.setAnio(rs.getInt("anio"));
		 			
		 			elem.add(el);}
				}
			
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		

		try {
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return elem;
		
	}
	
	
	public Carrera getByCarrera(int idcarrera){
		Carrera e=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_elemento, e.nombre, e.id_tipo, t.nombre from carrera e "
					+ "inner join tipoelemento t on e.id_tipo=t.id_tipo "
					+ "where e.nombre=?");
			stmt.setString(1, elem.getNombre());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
					e=new Carrera();
					e.setTipoElemento(new Curso());
					e.setId_elemento(rs.getInt("id_elemento"));
					e.setNombre(rs.getString("e.nombre"));
					e.getTipoElemento().setId_tipo(rs.getInt("id_tipo"));
 					e.getTipoElemento().setNombre(rs.getString("t.nombre"));					
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		try {
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return e;
	}
	
	public Carrera getByNombre(String elem){
		Carrera e=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_elemento, e.nombre, e.id_tipo, t.nombre from elemento e "
					+ "inner join tipoelemento t on e.id_tipo=t.id_tipo "
					+ "where e.nombre=?");
			stmt.setString(1, elem);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
					e=new Carrera();
					e.setTipoElemento(new Curso());
					e.setId_elemento(rs.getInt("id_elemento"));
					e.setNombre(rs.getString("e.nombre"));
					e.getTipoElemento().setId_tipo(rs.getInt("id_tipo"));
 					e.getTipoElemento().setNombre(rs.getString("t.nombre"));					
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		try {
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return e;
	}
	
	public Carrera getByTipo(String tipo){
		Carrera e= new Carrera();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_elemento, e.nombre, e.id_tipo, t.nombre from elemento e "
					+ "inner join tipoelemento t on e.id_tipo=t.id_tipo "
					+ "where t.nombre=?");
			stmt.setString(1, tipo);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
					e=new Carrera();
					e.setTipoElemento(new Curso());
					e.setId_elemento(rs.getInt("id_elemento"));
					e.setNombre(rs.getString("e.nombre"));
					e.getTipoElemento().setId_tipo(rs.getInt("id_tipo"));
 					e.getTipoElemento().setNombre(rs.getString("t.nombre"));					
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		try {
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		return e;
	}
	
	
	
	
	public void add(Carrera e){
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"insert into elemento(nombre,id_tipo) values (?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			
			stmt.setString(1, e.getNombre());
			stmt.setInt(2, e.getTipoElemento().getId_tipo());			
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				e.setId_elemento(keyResultSet.getInt(1));
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
	
	public void delete(Carrera e){
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"DELETE FROM elemento where nombre=?",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(1, e.getNombre());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				e.setId_elemento(keyResultSet.getInt(1));
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
	
	public void update(Carrera e){
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"UPDATE  elemento SET id_tipo =? where nombre=? ",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			
			stmt.setInt(1, e.getTipoElemento().getId_tipo());
			stmt.setString(2, e.getNombre());
			
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				e.setId_elemento(keyResultSet.getInt(1));
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

	
}
