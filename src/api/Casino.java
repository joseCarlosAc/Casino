package api;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Casino implements Operable {
	
	protected boolean status;
	private static final int TOTAL_CAPACITY=56;
	private static final int TOTAL_CAPACITY_TABLE=8;
	private static final int TOTAL_CAPACITY_SLOT=16;
	private static final int TOTAL_CAPACITY_USRT=5;
	
	private HashMap<Integer,User> users= new HashMap<>();
	
	private ArrayList<Table> tables= new ArrayList<>();
	private HashMap<Integer, Slot> slots= new HashMap<>();
	
	public void open() {
		System.out.println("casino abierto");
		System.out.println();
		status=true;
	}
	
	public void close() throws SQLException {
		Conexion cn= Conexion.getConecxion();
		for(Integer i: users.keySet()) {
			User u= users.get(i);
			Statement stm=cn.getCn().createStatement();
			String s= String.format("UPDATE user set DINERO= %f where IDUSER=%d", u.consulMoney(),u.getId());
			stm.executeUpdate(s);
			
		}
		users.clear();
		tables.clear();
		slots.clear();
		
		System.out.println("casino cerrado");
		System.out.println();
		status=false;
	}
	
	public void set() {
		SetStatus.set(this);
		while(SetStatus.getB()) System.out.printf("");
		SetStatus.setB(true);
	}
	
	public void userInCasino(User u) {
		if(status==false) {
			System.out.println("casino cerrado");
			return;
		}
		if(users.size()==TOTAL_CAPACITY) {
			JOptionPane.showMessageDialog(null,"Full casino","INFO",JOptionPane.INFORMATION_MESSAGE);
			return;
		}
		if(u.getId()==-1 || u.getId()==0) return;
		users.put(u.getId(), u);
	}
		
	public void userOutCasino(Integer id) throws SQLException {
		if(status==false) {
			System.out.println("casino cerrado");
			return;
		}
		Conexion cn= Conexion.getConecxion();
		User u=users.remove(id);
		if(u==null) return;
		Statement stm=cn.getCn().createStatement();
		String s= String.format("UPDATE user set DINERO= %f where IDUSER=%d", u.consulMoney(),u.getId());
		stm.executeUpdate(s);
		//cambiar
		System.out.println("usuario retirado");
		System.out.println();
	}
	
	public User searchUser(int id) {
		if(status==false) {
			System.out.println("casino cerrado");
			return null;
		}
		return users.get(id);
	}
	
	public Table searchTeable(int id) {
		if(status==false) {
			System.out.println("casino cerrado");
			return null;
		}
		for(Table i: tables) {
			for(Integer j: i.users_t.keySet()) {
				if(j==id) return i;
			}
		}
		return null;
	}
	
	private void tableClean(Table t) {
		if(t.users_t.size()==0) {
			for(int i=0;i<tables.size();i++) {
				if(t==tables.get(i)) {
					tables.remove(i);
					break;
				}
			}
		}
	}
	
	public void userOutTable(int id) throws SQLException {
		if(status==false) {
			System.out.println("casino cerrado");
			return;
		}
		Table t= searchTeable(id);
		if(t==null) {
			//cambiar
			System.out.println("usuario en ninguna mesa");
			return;
		}
		t.userOutTable(id);
		tableClean(t);
		//cambiar
		System.out.println("usuario "+ id+ " salio de la mesa");
		
	}
	
	public ArrayList<Table> listTable() {
		return tables;
	}
	
	public boolean userInTablePoker(int id) {
		if(status==false) {
			System.out.println("casino cerrado");
			return false;
		}
		if(searchTeable(id)!= null) {
			//cambiar
			System.out.println("usuario ya en una mesa");
			return false;
		}
		if(slots.get(id)!=null) {
			//cambiar
			System.out.println("usuario en un slot");
			return false;
		}
		if(tables.size()==0) {
			Poker p=new Poker();
			p.userInTable(id, this);
			tables.add(p);
			//cambiar
			System.out.println("usuario "+id+" entro a la mesa");
			return false;
		}
		boolean t=true;
		for(Table i: tables) {
			if(i instanceof Poker) {
				t=false;
				break;
			}
		}
		if(t) {
			if(tables.size()==8) {
				System.out.println("mesas llenas");
				return true;
			}
			Poker p=new Poker();
			p.userInTable(id, this);
			tables.add(p);
			//cambiar
			System.out.println("usuario "+id+" entro a la mesa");
			return false;
		}
		
		for(int i=0;i<tables.size();i++) {
			Table tmp=tables.get(i);
			if(tmp instanceof Poker) {
				if(i==TOTAL_CAPACITY_TABLE-1 && tmp.users_t.size()==TOTAL_CAPACITY_USRT) {
					//cambiar
					System.out.println("mesas llenas");
					return true;
				}
				
				if(tmp.users_t.size()==TOTAL_CAPACITY_USRT) continue;
				tmp.userInTable(id, this);
				//cambiar
				System.out.println("usuario "+id+" entro a la mesa");
				return false;
			}
			
		}
		if(tables.size()==8) {
			System.out.println("mesas llenas");
			return true;
		}
		Poker p=new Poker();
		p.userInTable(id, this);
		tables.add(p);
		//cambiar
		System.out.println("usuario "+id+" entro a la mesa");
		return false;
	}
	
	public boolean barajearPoker(int id) {
		if(status==false) {
			System.out.println("casino cerrado");
			return true;
		}
		if(users.get(id)==null) {
			//cambiar
			System.out.println("ususario no esta en el casino");
			return true;
		}
		Table t= searchTeable(id);
		if(t==null) {
			//cambiar
			System.out.println("usuario en ninguna mesa de Poker");
			return true;
		}
		if(t.users_t.size()<2) {
			//cambiar
			System.out.println("jugadores insuficiente");
			return true;
		}
		
		t.barajear();
		return false;
	}
	
	public ArrayList<Carta> repartirPoker(int id){
		if(status==false) {
			System.out.println("casino cerrado");
			return null;
		}
		if(users.get(id)==null) {
			//cambiar
			System.out.println("ususario no esta en el casino");
			return null;
		}
		Table t= searchTeable(id);
		if(t==null) {
			//cambiar
			System.out.println("usuario en ninguna mesa de Poker");
			return null;
		}
		return t.repartir();
	}
	
	public ArrayList<Carta> flop(int id){
		if(status==false) {
			//cambiar
			System.out.println("casino cerrado");
			return null;
		}
		if(users.get(id)==null) {
			//cambiar
			System.out.println("ususario no esta en el casino");
			return null;
		}
		Poker t=(Poker) searchTeable(id);
		if(t==null) {
			//cambiar
			System.out.println("usuario en ninguna mesa de Poker");
			return null;
		}
		return t.flop();
	}
		
	public ArrayList<Carta> turn(int id){
		if(status==false) {
			System.out.println("casino cerrado");
			return null;
		}
		if(users.get(id)==null) {
			//cambiar
			System.out.println("ususario no esta en el casino");
			return null;
		}
		Poker t=(Poker) searchTeable(id);
		if(t==null) {
			//cambiar
			System.out.println("usuario en ninguna mesa de Poker");
			return null;
		}
		return t.turn();
	}
	
	public ArrayList<Carta> river(int id){
		if(status==false) {
			System.out.println("casino cerrado");
			return null;
		}
		if(users.get(id)==null) {
			//cambiar
			System.out.println("ususario no esta en el casino");
			return null;
		}
		Poker t=(Poker) searchTeable(id);
		if(t==null) {
			//cambiar
			System.out.println("usuario en ninguna mesa de Poker");
			return null;
		}
		return t.river();
	}
	
	public ArrayList<Carta> getBestHand(int id,ArrayList<Carta> hand){
		if(status==false) {
			System.out.println("casino cerrado");
			return null;
		}
		if(users.get(id)==null) {
			//cambiar
			System.out.println("ususario no esta en el casino");
			return null;
		}
		Poker t=(Poker) searchTeable(id);
		if(t==null) {
			//cambiar
			System.out.println("usuario en ninguna mesa de Poker");
			return null;
		}
		return t.getBestHand(hand);
	}
	
	public Manos rank(int id,ArrayList<Carta> hand){
		if(status==false) {
			System.out.println("casino cerrado");
			return null;
		}
		if(users.get(id)==null) {
			//cambiar
			System.out.println("ususario no esta en el casino");
			return null;
		}
		Poker t=(Poker) searchTeable(id);
		if(t==null) {
			//cambiar
			System.out.println("usuario en ninguna mesa de Poker");
			return null;
		}
		return t.rank(hand);
	}
	
	public void ComprobarPoker(HashMap<Integer, ArrayList<Carta>> id_hand, HashMap<Integer, Double> bet) throws SQLException { 
		if(status==false) {
			System.out.println("casino cerrado");
			return;
		}
		int id=0;
		for(Integer i: id_hand.keySet()) {
			id=i;
			break;
		}
		if(users.get(id)==null) {
			//cambiar
			System.out.println("ususario no esta en el casino");
			return;
		}
		Table t= searchTeable(id);
		if(t==null) {
			//cambiar
			System.out.println("usuario en ninguna mesa de Poker");
			return;
		}
		t.comprobar(id_hand, bet);
	}
	
	public boolean userInTableBlackjack(int id) {
	if(status==false) {
		System.out.println("casino cerrado");
		return false;
	}	
	if(searchTeable(id)!= null) {
			//cambiar
			System.out.println("usuario ya en una mesa");
			return false;
		}
		if(slots.get(id)!=null) {
			//cambiar
			System.out.println("usuario en un slot");
			return false;
		}
		if(tables.size()==0) {
			Blackjack b=new Blackjack();
			b.userInTable(id, this);
			tables.add(b);
			//cambiar
			System.out.println("usuario "+id+" entro a la mesa");
			return false;
		}
		
		boolean t=true;
		for(Table i: tables) {
			if(i instanceof Blackjack) {
				t=false;
				break;
			}
		}
		if(t) {
			if(tables.size()==8) {
				System.out.println("mesas llenas");
				return true;
			}
			Blackjack p=new Blackjack();
			p.userInTable(id, this);
			tables.add(p);
			System.out.println("usuario "+id+" entro a la mesa");
			return false;
		}
		
		for(int i=0;i<tables.size();i++) {
			Table tmp=tables.get(i);
			if(tmp instanceof Blackjack) {
				if(i==TOTAL_CAPACITY_TABLE-1 && tmp.users_t.size()==TOTAL_CAPACITY_USRT) {
					//cambiar
					System.out.println("mesas llenas");
					return true;
				}
				
				if(tmp.users_t.size()==TOTAL_CAPACITY_USRT) continue;
				tmp.userInTable(id, this);
				//cambiar
				System.out.println("usuario "+id+" entro a la mesa");
				return false;
			}
			
		}
		if(tables.size()==8) {
			System.out.println("mesas llenas");
			return true;
		}
		Blackjack b=new Blackjack();
		b.userInTable(id, this);
		tables.add(b);
		//cambiar
		System.out.println("usuario "+id+" entro a la mesa");
		return false;
	}
	
	public void barajearBlackjack(int id) {
		if(status==false) {
			System.out.println("casino cerrado");
			return;
		}
		if(users.get(id)==null) {
			//cambiar
			System.out.println("ususario no esta en el casino");
			return;
		}
		Table t= searchTeable(id);
		if(t==null) {
			//cambiar
			System.out.println("usuario en ninguna mesa de Backjack");
			return;
		}
		
		t.barajear();
	}
	
	public ArrayList<Carta> repartirBlackjack(int id){
		if(status==false) {
			System.out.println("casino cerrado");
			return null;
		}
		if(users.get(id)==null) {
			//cambiar
			System.out.println("ususario no esta en el casino");
			return null;
		}
		Table t= searchTeable(id);
		if(t==null) {
			//cambiar
			System.out.println("usuario en ninguna mesa de Backjack");
			return null;
		}
		
		return t.repartir();
	}
	
	public Carta repartirCartaBlackjack(int id){
		if(status==false) {
			System.out.println("casino cerrado");
			return null;
		}
		if(users.get(id)==null) {
			//cambiar
			System.out.println("ususario no esta en el casino");
			return null;
		}
		Blackjack t=(Blackjack) searchTeable(id);
		if(t==null) {
			//cambiar
			System.out.println("usuario en ninguna mesa de Blackjack");
			return null;
		}
		
		return t.RepartirCarta();
	}
	
	public int SumaCartas(ArrayList<Carta> Mano,int id) {
		if(status==false) {
			System.out.println("casino cerrado");
			return -1;
		}
		if(users.get(id)==null) {
			//cambiar
			System.out.println("ususario no esta en el casino");
			return -1;
		}
		Blackjack t=(Blackjack) searchTeable(id);
		if(t==null) {
			//cambiar
			System.out.println("usuario en ninguna mesa de Blackjack");
			return -1;
		}
		
		return t.SumaCartas(Mano);
	}
	
	public ArrayList<Carta> getDealer(int id){
		if(status==false) {
			System.out.println("casino cerrado");
			return null;
		}
		if(users.get(id)==null) {
			//cambiar
			System.out.println("ususario no esta en el casino");
			return null;
		}
		Blackjack t=(Blackjack) searchTeable(id);
		if(t==null) {
			//cambiar
			System.out.println("usuario en ninguna mesa de Blackjack");
			return null;
		}
		
		return t.getDealer();
	 }
	
	public void RepartirDealer(int id) {
		if(status==false) {
			System.out.println("casino cerrado");
			return;
		}
		if(users.get(id)==null) {
			//cambiar
			System.out.println("ususario no esta en el casino");
			return;
		}
		Blackjack t=(Blackjack) searchTeable(id);
		if(t==null) {
			//cambiar
			System.out.println("usuario en ninguna mesa de Blackjack");
			return;
		}
		
		t.RepartirDealer();
	}
	
	public void ComprobarBlackjack(HashMap<Integer, ArrayList<Carta>> id_hand, HashMap<Integer, Double> bet) throws SQLException {
		if(status==false) {
			System.out.println("casino cerrado");
			return;
		}
		int id=0;
		for(Integer i: id_hand.keySet()) {
			id=i;
			break;
		}
		if(users.get(id)==null) {
			//cambiar
			System.out.println("ususario no esta en el casino");
			return;
		}
		Blackjack t=(Blackjack) searchTeable(id);
		if(t==null) {
			//cambiar
			System.out.println("usuario en ninguna mesa de Blackjack");
			return;
		}
		t.comprobar(id_hand, bet);
	}
	
	public boolean inSlot(int id) {
		if(status==false) {
			System.out.println("casino cerrado");
			return false;
		}
		if(slots.size()==TOTAL_CAPACITY_SLOT) {
			//cambiar
			System.out.println("slots llenos");
			return true;
		}
		if(searchTeable(id)!=null) {
			//cambiar
			System.out.println("ususario en un mesa");
			return false;
		}
		slots.put(id, new Slot());
		//cambiar
		System.out.println("usuario "+id +" entro en un Slot");
		return false;
	}
	
	public void outSlot(int id) {
		if(status==false) {
			System.out.println("casino cerrado");
			return;
		}
		slots.remove(id);
		//cambiar
		System.out.println("usuario "+id +" salio en un Slot");
	}
	
	public Slot playSlot(int id, double bet) throws SQLException {
		if(status==false) {
			System.out.println("casino cerrado");
			return null;
		}
		Slot s=slots.get(id);
		if(s==null) {
			//cambiar
			System.out.println("usuario no esta en un slot");
			return null;
		}
		return s.play(id, bet, this);
	}
	
	public ArrayList<Integer> listSlot(){
		ArrayList<Integer> list=new ArrayList<>();
		for(Integer i:slots.keySet()) {
			list.add(i);
		}
		return list;
	}
	
	public boolean checkStatus() {
		return status;
	}
	
	public HashMap<Integer, User> getUsers() {
		return users;
	}
	
}
