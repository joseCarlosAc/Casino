package api;


import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Poker extends Table {
	protected ArrayList<Carta> cartas=new ArrayList<>();
	protected ArrayList<Carta> centro=new ArrayList<>();
	
	public void barajear() {
		int n=users_t.size();
		n*=2;
		n+=5;
		Carta[] deck=new Carta[n];
		Baraja b= new Baraja();
		b.barajear();
		b.barajear();
		deck=b.repartir(n);
		
		n=deck.length-5;
		for(int j=0; j<5;j++,n++) {
			centro.add(deck[n]);
		}
		
		for(Carta i: deck) {
			cartas.add(i);
		}
		
	}
	
	public ArrayList<Carta> repartir() {
		ArrayList<Carta> c= new ArrayList<>();
		
		
		for(int j=0;j<2;j++) {
			c.add(cartas.get(0));
			cartas.remove(0);
		}
		
		return c;
	}

	public ArrayList<Carta> flop() {
		ArrayList<Carta> c =new ArrayList<>();
		
		for(int j=0; j<3;j++) {
			c.add(cartas.get(j));
		}
		return c;
	}
	
	public ArrayList<Carta> turn(){
		ArrayList<Carta> c =new ArrayList<>();
		
		for(int j=0; j<4;j++) {
			c.add(cartas.get(j));
		}
		return c;
	}
	
	public ArrayList<Carta> river(){
		ArrayList<Carta> c =new ArrayList<>();
		
		for(int j=0; j<5;j++) {
			c.add(cartas.get(j));
		}
		return c;
	}
	
	private ArrayList<Carta> ordenar(ArrayList<Carta> mano) {
		ArrayList<Carta>tmp = new ArrayList<>();
		int n=mano.size();
		for(int i=0;i<n;i++) {
			Carta min=mano.get(0);
			int pmin=0;
			for(int j=0; j<mano.size();j++) {
				if(min.intNumPoker()>mano.get(j).intNumPoker()) {
					min=mano.get(j);
					pmin=j;
				}
			}
			tmp.add(min);
			mano.remove(pmin);
		}
		
		return tmp;
	}
	
	public Manos rank(ArrayList<Carta> h) {
		int n0=h.get(0).intNumPoker();
		int n1=h.get(1).intNumPoker();
		int n2=h.get(2).intNumPoker();
		int n3=h.get(3).intNumPoker();
		int n4=h.get(4).intNumPoker();
		
		Figura f0=h.get(0).getFig();
		Figura f1=h.get(1).getFig();
		Figura f2=h.get(2).getFig();
		Figura f3=h.get(3).getFig();
		Figura f4=h.get(4).getFig();
		
		if(		h.get(0).equals(new Carta(NumCarta.DIEZ, f0)) && 
				h.get(1).equals(new Carta(NumCarta.JOTO, f0)) && 
				h.get(2).equals(new Carta(NumCarta.REINA, f0)) && 
				h.get(3).equals(new Carta(NumCarta.REY, f0)) && 
				h.get(4).equals(new Carta(NumCarta.AS, f0)) 
				) {
			return Manos.FLOR_IMPERIAL;
		}
		else if(((n0+1==n1 && n1+1==n2 && n2+1==n3 && n3+1==n4) &&
						(f1==f0 && f2==f0 && f3==f0 && f4==f0)) ||
				((n0==1 && n1==2 && n2==3 && n4==13) &&
						(f1==f0 && f2==f0 && f3==f0 && f4==f0))) {
			return Manos.ESCALERA_COLOR;
		}
		else if((n1==n0 && n2==n0 && n3==n0) ||
				(n2==n1 && n3==n1 && n4==n1)) {
			return Manos.POKER;
		}
		else if((n1==n0 && n2==n0 && n3==n4) ||
				(n1==n0 && n3==n2 && n4==n2)) {
			return Manos.FULL;
		}
		else if(f1==f0 && f2==f0 && f3==f0 && f4==f0) {
			return Manos.COLOR;
		}
		else if((n0+1==n1 && n1+1==n2 && n2+1==n3 && n3+1==n4) ||
				(n0==1 && n1==2 && n2==3 && n4==13)) {
			return Manos.ESCALERA;
		}
		else if((n1==n0 && n2==n0) ||
				(n2==n1 && n3==n1) ||
				(n3==n2 && n4==n2)) {
			return Manos.TERCIA;
		}
		else if((n1==n0 && n2==n3) ||
				(n1==n0 && n4==n3) ||
				(n1==n2 && n4==n3)) {
			return Manos.DOS_PAR;
		}
		else if(n0==n1 || n1==n2 || n2==n3 || n3==n4) {
			return Manos.PAR;
		}
		
		return Manos.ALTA;
	}
	
	private int cartaMano(ArrayList<Carta> h) {
		int n0=h.get(0).intNumPoker();
		int n1=h.get(1).intNumPoker();
		int n2=h.get(2).intNumPoker();
		int n3=h.get(3).intNumPoker();
		int n4=h.get(4).intNumPoker();
		switch(rank(h)) {
		case POKER:
			if(n1==n0 && n2==n0 && n3==n0) return n0;
			else return n1;
		case FULL:
			if(n1==n0 && n2==n0) return n0;
			else return n2;
		case TERCIA:
			if(n1==n0 && n2==n0) return n0;
			else if(n2==n1 && n3==n1) return n1;
			else return n2;
		case DOS_PAR:
			if(n1==n0 && n2==n3) return n1>n2? n1:n2;
			else if(n1==n0 && n4==n3) return n0>n3? n0:n3;
			else return n2>n4? n2:n4;
		case PAR:
			if(n0==n1) return n0;
			else if(n1==n2) return n1;
			else if(n2==n3) return n2;
			else return n3;
		default: 
			return 0;
		}
	}
	
	private int comparar(ArrayList<Carta> h1, ArrayList<Carta> h2) {
		if(rank(h1).compareTo(rank(h2))==0) {
			if(cartaMano(h1)==cartaMano(h2)) {
				if(h1.get(4).intNumPoker() == h2.get(4).intNumPoker()) {
					if(h1.get(3).intNumPoker() == h2.get(3).intNumPoker()) {
						if(h1.get(2).intNumPoker() == h2.get(2).intNumPoker()) {
							if(h1.get(1).intNumPoker() == h1.get(1).intNumPoker()) {
								if(h1.get(0).intNumPoker() == h2.get(0).intNumPoker()) {
									return 0;
								}
								else if(h1.get(0).intNumPoker() < h2.get(0).intNumPoker()) {
									return 1;
								}
							}
							else if(h1.get(1).intNumPoker() < h2.get(1).intNumPoker()) {
								return 1;
							}
						}
						else if(h1.get(2).intNumPoker() < h2.get(2).intNumPoker()) {
							return 1;
						}
					}
					else if(h1.get(3).intNumPoker() < h2.get(3).intNumPoker()) {
						return 1;
					}
				}
				else if(h1.get(4).intNumPoker() < h2.get(4).intNumPoker()) {
					return 1;
				}
			}
			else if(cartaMano(h1)< cartaMano(h2)) {
				return 1;
			}	
		}
		else if(rank(h1).compareTo(rank(h2))>0) {
			return 1;
		}
		return -1;
	}
	
	public void comprobar(HashMap<Integer, ArrayList<Carta>> id_hand, HashMap<Integer, Double> bet) throws SQLException {
		centro=ordenar(centro);
		int pmax=0;
		int count=0;
		
		for(Integer i: id_hand.keySet()) {
			id_hand.replace(i, getBestHand(id_hand.get(i)));
		}
		
		count=0;
		for(Integer i: id_hand.keySet()) {
			ArrayList<Carta> tmp=new ArrayList<>();
			if(count==0) {
				pmax=i;
				count++;
			}
			tmp=ordenar(id_hand.get(i));
			id_hand.replace(i, tmp);
		}
		
		ArrayList<Integer> posmax= new ArrayList<>();
		posmax.add(pmax);
		
		double pot=0;
		
		for(Integer i: id_hand.keySet()) {
			if(i==pmax) continue;
			int com= comparar(id_hand.get(pmax),id_hand.get(i));
			if(com==0) {
				posmax.add(i);
				pot+=bet.get(i);
			}
			if(com==1) {
				pmax=i;
				posmax.clear();
				posmax.add(i);
			}
		}
		
		for( Integer i: id_hand.keySet()) {
			if(posmax.contains(i)) continue;
			
			if(users_t.get(i)==null) {
				System.out.println("el usuario "+i+ " no esta en la mesa");
				System.out.println();
        		continue;
			}
			
			pot+=bet.get(i);
			System.out.println("usuario "+ i+ " perdio " + bet.get(i)+ "\nmano:"+ rank(id_hand.get(i)) +"\n" +id_hand.get(i));
			System.out.println();
			users_t.get(i).addMoney(-bet.get(i));
		}
		
		if(posmax.size()>1) pot+=bet.get(pmax);
		for(Integer i: posmax) {
			JOptionPane.showMessageDialog(null,"User "+ i+ " win " + pot/posmax.size(),"INFO", JOptionPane.INFORMATION_MESSAGE);
			System.out.println("usuario "+ i+ " gano " + pot/posmax.size() + "\nmano:"+ rank(id_hand.get(i)) +"\n" +id_hand.get(i));
			System.out.println();
			users_t.get(i).addMoney(pot/posmax.size());
		}
		
		cartas=new ArrayList<>();
		centro= new ArrayList<>();
	}
	
	public ArrayList<Carta> getBestHand(ArrayList<Carta> hand){
		ArrayList<Carta> best=new ArrayList<>();
		ArrayList<Carta> tmp=new ArrayList<>();
		centro=ordenar(centro);
		
		tmp.add(hand.get(0));
		tmp.add(hand.get(1));
		tmp.add(centro.get(0));
		tmp.add(centro.get(1));
		tmp.add(centro.get(2));
		tmp=ordenar(tmp);
		
		best=tmp;
		
		tmp=new ArrayList<>();
		tmp.add(hand.get(0));
		tmp.add(hand.get(1));
		tmp.add(centro.get(0));
		tmp.add(centro.get(1));
		tmp.add(centro.get(3));
		tmp=ordenar(tmp);
		
		int b=comparar(best, tmp);
		if(b==1) {
			best=tmp;
		}
		
		tmp=new ArrayList<>();
		tmp.add(hand.get(0));
		tmp.add(hand.get(1));
		tmp.add(centro.get(0));
		tmp.add(centro.get(1));
		tmp.add(centro.get(4));
		tmp=ordenar(tmp);
		
		b=comparar(best, tmp);
		if(b==1) {
			best=tmp;
		}
		
		tmp=new ArrayList<>();
		tmp.add(hand.get(0));
		tmp.add(hand.get(1));
		tmp.add(centro.get(1));
		tmp.add(centro.get(2));
		tmp.add(centro.get(3));
		tmp=ordenar(tmp);
		
		b=comparar(best, tmp);
		if(b==1) {
			best=tmp;
		}
		
		tmp=new ArrayList<>();
		tmp.add(hand.get(0));
		tmp.add(hand.get(1));
		tmp.add(centro.get(1));
		tmp.add(centro.get(2));
		tmp.add(centro.get(4));
		tmp=ordenar(tmp);
		
		b=comparar(best, tmp);
		if(b==1) {
			best=tmp;
		}
		
		tmp=new ArrayList<>();
		tmp.add(hand.get(0));
		tmp.add(hand.get(1));
		tmp.add(centro.get(2));
		tmp.add(centro.get(3));
		tmp.add(centro.get(4));
		tmp=ordenar(tmp);
		
		b=comparar(best, tmp);
		if(b==1) {
			best=tmp;
		}
		
		return best;
	}
	
	

}
