package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.Elemento;
import entity.TipoElemento;

public class DataElemento {
	
	
	public ArrayList<Elemento> getAll(TipoElemento tipoel){
		Elemento el = null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		ArrayList<Elemento> elem= new ArrayList<>();
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
			"select * from elemento e "
			+ "inner join tipoelemento t on e.id_tipo=t.id_tipo "
			+ " where t.nombre = ?");
			stmt.setString(1, tipoel.getNombre());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
					el=new Elemento();
		 			el.setTipoElemento(new TipoElemento());
		 			el.setId_elemento(rs.getInt("id_elemento"));
		 			el.setNombre(rs.getString("e.nombre"));
		 			el.getTipoElemento().setId_tipo(rs.getInt("id_tipo"));
		 			el.getTipoElemento().setNombre(rs.getString("t.nombre"));
		 			elem.add(el);
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
	
	
	public Elemento getByElemento(Elemento elem){
		Elemento e=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_elemento, e.nombre, e.id_tipo, t.nombre from elemento e "
					+ "inner join tipoelemento t on e.id_tipo=t.id_tipo "
					+ "where e.nombre=?");
			stmt.setString(1, elem.getNombre());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
					e=new Elemento();
					e.setTipoElemento(new TipoElemento());
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
	
	public Elemento getByNombre(String elem){
		Elemento e=null;
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
					e=new Elemento();
					e.setTipoElemento(new TipoElemento());
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
	
	public Elemento getByTipo(String tipo){
		Elemento e= new Elemento();
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
					e=new Elemento();
					e.setTipoElemento(new TipoElemento());
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
	
	
	
	
	public void add(Elemento e){
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
	
	public void delete(Elemento e){
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
	
	public void update(Elemento e){
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
