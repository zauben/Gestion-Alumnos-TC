package data;

import java.util.ArrayList;
import java.sql.*;

import entity.*;

public class DataPersona {

	public ArrayList<Persona> getAll(){
	
		Statement stmt=null;
		ResultSet rs=null;
		ArrayList<Persona> pers= new ArrayList<Persona>();
		try {
			stmt = FactoryConexion.getInstancia()
					.getConn().createStatement();
			rs = stmt.executeQuery("select * from persona p "
		 			+ "inner join categorias c on p.id_categoria=c.id_categoria");
			if(rs!=null){
				while(rs.next()){
					Persona p=new Persona();
					p.setCategoria(new Categoria());
					p.setId_persona(rs.getInt("id_persona"));
					p.setNombre(rs.getString("nombre"));
					p.setApellido(rs.getString("apellido"));
					p.setDni(rs.getString("dni"));
					p.setHabilitado(rs.getBoolean("habilitado"));
					p.getCategoria().setId_categoria(rs.getInt("id_categoria"));
		 			p.getCategoria().setNombreCat(rs.getString("c.nombre"));
		 			p.setLogged(rs.getBoolean("logged_user"));
					pers.add(p);
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
		
		return pers;
		
	}
	
	public Persona getByDni(Persona per){
		Persona p=null;
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select id_persona, p.nombre, apellido, dni, habilitado, p.id_categoria, c.nombre from persona p "
					+ "inner join categoria c on p.id_categoria=c.id_categoria where dni=?");
			stmt.setString(1, per.getDni());
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){
				p=new Persona();
				p.setCategoria(new Categoria());
				p.setId_persona(rs.getInt("id_persona"));
				p.setNombre(rs.getString("nombre"));
				p.setApellido(rs.getString("apellido"));
				p.setDni(rs.getString("dni"));
				p.setHabilitado(rs.getBoolean("habilitado"));
				p.getCategoria().setId_categoria(rs.getInt("id_categoria"));
				p.getCategoria().setNombreCat(rs.getString("nombre")); 			
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return p;
	}
	
	public void add(Persona p){
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"insert into persona(dni, nombre, apellido, habilitado, id_categoria) values (?,?,?,?,?)",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(1, p.getDni());
 			stmt.setString(2, p.getNombre());
 			stmt.setString(3, p.getApellido());
 			stmt.setBoolean(4, p.isHabilitado());
 			stmt.setInt(5, p.getCategoria().getId_categoria());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				p.setId_persona(keyResultSet.getInt(1));
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
	
	public void borrar(Persona p){
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"DELETE FROM persona where dni=?",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(1, p.getDni());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				p.setId_persona(keyResultSet.getInt(1));
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
	
	public void actualizar(Persona newp, Persona oldp){
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"UPDATE  persona SET dni=?, nombre=? , apellido=?, habilitado=?, id_categoria=? where dni=? ",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			
			stmt.setString(1, newp.getDni());
			stmt.setString(2, newp.getNombre());
			stmt.setString(3, newp.getApellido());
			stmt.setBoolean(4, newp.isHabilitado());
 			stmt.setInt(5, newp.getCategoria().getId_categoria());
			stmt.setString(6, oldp.getDni());
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			if(keyResultSet!=null && keyResultSet.next()){
				newp.setId_persona(keyResultSet.getInt(1));
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
	
	//pase el metodo tal cual estaba antes, me parece que estaria correcto asi
	public Boolean validarUsuario(Persona per) {
		int aux = 0;
		ResultSet rs=null;
		PreparedStatement stmt=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn()
					.prepareStatement(
					"select * from persona where usuario = ? and contraseña = ?",
					PreparedStatement.RETURN_GENERATED_KEYS
					);
			stmt.setString(1, per.getUsuario());
			stmt.setString(2, per.getContraseña());
			
			rs= stmt.executeQuery();
		if(rs!=null && rs.next() && rs.getInt("habilitado")==1){
			aux = 1;
			this.storeLoggedUser(rs.getInt("id_persona"));
			
		}else{
			aux = 2;
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
		if (aux == 1) {
			return true;
		} else 
			return false; 
	}

	private void storeLoggedUser(int idLogueada) {
		PreparedStatement stmt=null;
		ResultSet keyResultSet=null;
			try {
				stmt=FactoryConexion.getInstancia().getConn()
						.prepareStatement(
						"UPDATE  persona SET logged_user=? where id_persona=? ",
						PreparedStatement.RETURN_GENERATED_KEYS
						);
				
			stmt.setInt(1, 1);
			stmt.setInt(2, idLogueada);
			stmt.executeUpdate();
			keyResultSet=stmt.getGeneratedKeys();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}}
	

			public void cleanLoggedUser() {
				PreparedStatement stmt=null;
				ResultSet keyResultSet=null;
				ArrayList<TipoElemento> tipos= new ArrayList<TipoElemento>();
				try {
					stmt=FactoryConexion.getInstancia().getConn()
							.prepareStatement("UPDATE persona SET logged_user = 0",
									PreparedStatement.RETURN_GENERATED_KEYS);
					stmt.executeUpdate();
					keyResultSet=stmt.getGeneratedKeys();
					} catch (SQLException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	
}

	public Persona getLogged() {
		Persona perLogueada = new Persona();
		PreparedStatement stmt=null;
		ResultSet rs=null;
		try {
			stmt=FactoryConexion.getInstancia().getConn().prepareStatement(
					"select * from persona p where logged_user=?");
			stmt.setInt(1, 1);
			rs=stmt.executeQuery();
			if(rs!=null && rs.next()){		
		perLogueada.setCategoria(new Categoria());
		perLogueada.setId_persona(rs.getInt("id_persona"));
		perLogueada.setNombre(rs.getString("nombre"));
		perLogueada.setApellido(rs.getString("apellido"));
		perLogueada.setDni(rs.getString("dni"));
		perLogueada.setHabilitado(rs.getBoolean("habilitado"));
		perLogueada.getCategoria().setId_categoria(rs.getInt("id_categoria"));
		perLogueada.getCategoria().setNombreCat(rs.getString("nombre")); 
	
	
}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		try {
			if(rs!=null)rs.close();
			if(stmt!=null)stmt.close();
			FactoryConexion.getInstancia().releaseConn();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return perLogueada;
	}}


