package api;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;



public class User {
	
	private int id;
	private double money;
	
	
	public User(int id, String con) throws SQLException {
		Conexion cn=Conexion.getConecxion();
		Statement stm= cn.getCn().createStatement();
		ResultSet rs= stm.executeQuery("Select IDUSER FROM USER WHERE IDUSER="+id);
		if(rs.next()==false) {
			System.out.println("no existe el usuario");
			System.out.println();
			this.id=0;
			return;
		}
			
		rs=stm.executeQuery("SELECT CONTRASEÑA FROM USER WHERE IDUSER="+id);
		String tmp="";
		while(rs.next())tmp=rs.getString(1);

		if(tmp.equals(con)==false) {
			System.out.println("contraseña incorecta");
			System.out.println();
			this.id=-1;
			return;
		}
		this.id=id;
			
		rs= stm.executeQuery("SELECT DINERO FROM USER WHERE IDUSER="+id);
		while(rs.next()) money=rs.getDouble(1);
		System.out.println("usuario conectado");	
		System.out.println();
	}
	
	public static int addUser() {
		AddUser.Add();
		while(AddUser.getB())System.out.printf("");
		AddUser.setB(true);
		return AddUser.getId();
		
	}
	
	public void addMoney(double can) throws SQLException {
		Conexion cn=Conexion.getConecxion();
		Statement stm= cn.getCn().createStatement();
		money+=can;
		if(money<0) money=0;
		
		String s=String.format("UPDATE user set DINERO= %f where IDUSER= %d", money,id);
			
		stm.executeUpdate(s);
		System.out.println("se actualizo el dinero del usuario "+id);
		System.out.println();
		
	}
	
	public double retirar() throws SQLException {
		Conexion cn= Conexion.getConecxion();
		
		Statement stm=cn.getCn().createStatement();
			
		double can=money;
		stm.executeUpdate("UPDATE user set DINERO= 0 where IDUSER="+id);
		System.out.println("dinero retirado corectamente");
		System.out.println();
			
		money=0;
		return can;
			
		
	}
	
	public double consulMoney() {
		return money;
	}

	public int getId() {
		return id;
	}
	
	protected void finalize() {
		System.out.println("se destruye un usuario");
	}
}
