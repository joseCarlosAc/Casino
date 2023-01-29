package api;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
	private static final String CONTROLADOR ="com.mysql.jdbc.Driver";
	private static final String URL= "jdbc:mysql://localhost:3306/CASINO";
	private static final String USUARIO= "root";
	private static final String CLAVE= "Admin123";
	
	private static Conexion instace=null;
	private Connection cn= null;
	
	private Conexion() throws SQLException {
		
		try {
			Class.forName(CONTROLADOR);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage()+"error");
		}
		cn=DriverManager.getConnection(URL,USUARIO,CLAVE);
		System.out.println("conectado");
			
		System.out.println();
	}
	
	public static Conexion getConecxion() throws SQLException {
		if(instace==null) {
			instace= new Conexion();
		}
		return instace;
	}
	
	public Connection getCn() {
		return cn;
	}
	
	
}

