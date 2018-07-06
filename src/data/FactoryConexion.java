package data;

import java.sql.*;
import java.util.Properties;

public class FactoryConexion {

	String url = "jdbc:postgresql://localhost:5434/postgres";
	Properties prop = new Properties();

	Connection conn;

	private static FactoryConexion instancia;

	private FactoryConexion() {
		prop.setProperty("user", "postgres");
		prop.setProperty("password", "admin");
		prop.setProperty("ssl", "false");

	}

	public static FactoryConexion getInstancia() {
		if (FactoryConexion.instancia == null) {
			FactoryConexion.instancia = new FactoryConexion();
		}
		return FactoryConexion.instancia;

	}

	private int cantConn = 0;

	public Connection getConn() {
		try {
			if (conn == null || conn.isClosed()) {

				conn = DriverManager.getConnection(url, prop);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cantConn++;
		return conn;
	}

	public void releaseConn() {
		try {
			cantConn--;
			if (cantConn == 0) {
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
