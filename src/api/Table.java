package api;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

public abstract class Table {
	
	private static final int TOTAL_CAPACITY_t=5;
	protected HashMap<Integer,User> users_t= new HashMap<>();
	
	public void userInTable(int id, Casino c) {
		if(c.checkStatus()==false) {
			System.out.println("casino cerrado");
			return;
		}
		User tmp=c.getUsers().get(id);
		if(tmp==null) {
			System.out.println("usuario no presente en el casino");
			return;
		}
		if(users_t.size()==TOTAL_CAPACITY_t) {
			System.out.println("mesa llena");
			return;
		}
		users_t.put(tmp.getId(), tmp);
	}
	
	public void userOutTable(int id) throws SQLException {
		Conexion cn= Conexion.getConecxion();
		User tmp=users_t.remove(id);
		if(tmp==null) return;
		try {
			Statement stm=cn.getCn().createStatement();
			String s= String.format("UPDATE user set DINERO= %f where IDUSER=%d", tmp.consulMoney(),tmp.getId());
			stm.executeUpdate(s);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public HashMap<Integer, User> getUsers_t() {
		return users_t;
	}
	
	protected void finalize() {
		System.out.println("se destruye una mesa");
	}
	
	public abstract void comprobar(HashMap<Integer, ArrayList<Carta>> id_hand, HashMap<Integer, Double> bet) throws SQLException;
	public abstract void barajear();
	public abstract ArrayList<Carta> repartir();
}
