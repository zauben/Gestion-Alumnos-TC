package data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import entity.TipoElemento;

public class DataTipoElemento {
public ArrayList<TipoElemento> getAll(){
		
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<TipoElemento> tipos= new ArrayList<TipoElemento>();
		try {
			stmt = FactoryConexion.getInstancia()
					.getConn().createStatement();
			rs = stmt.executeQuery("select * from tipoelemento");
			if(rs!=null){
				while(rs.next()){
					TipoElemento t=new TipoElemento();
					t.setId_tipo(rs.getInt("id_tipo"));
					t.setNombre(rs.getString("nombre"));
					t.setCantMaxima(rs.getInt("cantMaxima"));
					t.setTiempoMax(rs.getInt("tiempoMax"));
					t.setDiasAnticipacion(rs.getInt("diasAnticipacion"));
					tipos.add(t);
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
		
		return tipos;
		
	}
	
	
	public TipoElemento getByNombre(TipoElemento tipo){
		TipoElemento t=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from tipoelemento t where t.nombre=?");
			stmt.setString(1, tipo.getNombre());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
					t=new TipoElemento();
					t.setId_tipo(rs.getInt("id_tipo"));
					t.setNombre(rs.getString("nombre"));
					t.setCantMaxima(rs.getInt("cantMaxima"));
					t.setTiempoMax(rs.getInt("tiempoMax"));
					t.setDiasAnticipacion(rs.getInt("diasAnticipacion"));		
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
		
		return t;
	}
	
	
	
	
	public void add(TipoElemento t){
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"insert into tipoelemento( nombre,cantMaxima,tiempoMax,diasAnticipacion) values (?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			
			stmt.setString(1, t.getNombre());
			stmt.setInt(2, t.getCantMaxima());
			stmt.setInt(3, t.getTiempoMax());
			stmt.setInt(4, t.getDiasAnticipacion());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				t.setId_tipo(keyResultSet.getInt(1));
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
	public void delete(TipoElemento t){
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"DELETE FROM tipoelemento where nombre=?",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(1, t.getNombre());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				t.setId_tipo(keyResultSet.getInt(1));
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
	
	public void update(TipoElemento t){
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
 		try {
 			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
 					"update tipoelemento "
 					+ "set nombre=?, cantMaxima=?, tiempoMax=?, diasAnticipacion=? "
 					+ "where id_tipo=?",
 					PreparedStatement.RETURN_GENERATED_KEYS
 					);
 			stmt.setString(1, t.getNombre());
 			stmt.setInt(2, t.getCantMaxima());
 			stmt.setInt(3, t.getTiempoMax());
 			stmt.setInt(4, t.getDiasAnticipacion());
 			stmt.setInt(5, t.getId_tipo());
 			stmt.executeUpdate();
 			
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

