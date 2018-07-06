package data;
import java.sql.*;
import java.util.Properties;


public class FactoryConexion {
	
	/*private String driver="com.mysql.jdbc.Driver";
	private String host="localhost";
	private String port="3306";
	private String user="root";
	private String password="root";
	private String db="tpjava";
	*/
	
	//String url = "jdbc:postgresql://localhost/tpjava?user=postgres&password=santiago&ssl=true";
	String url = "jdbc:postgresql://localhost:5434/postgres";
	Properties prop = new Properties();
	
	Connection conn; 

	
	private static FactoryConexion instancia;
		
	private FactoryConexion(){
			prop.setProperty("user","postgres");
			prop.setProperty("password","admin");
			prop.setProperty("ssl","false");
			
			//Class.forName(driver);
		
	}
	
	public static FactoryConexion getInstancia(){
		if (FactoryConexion.instancia == null){		
			FactoryConexion.instancia=new FactoryConexion();
		}
		return FactoryConexion.instancia;
		
	}
	
//	private Connection conn;
	private int cantConn=0;
	public Connection getConn(){
		try {
			if(conn==null || conn.isClosed()){	
				//conn = DriverManager.getConnection(
			      //  "jdbc:mysql://"+host+":"+port+"/"+db+"?user="+user+"&password="+password);
			
				conn = DriverManager.getConnection(url, prop);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		cantConn++;
		return conn;
	}
	
	public void releaseConn(){
		try {
			cantConn--;
			if(cantConn==0){
				conn.close();
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	

}
