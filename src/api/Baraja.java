package api;

public class Baraja {
	private Carta[] baraja;

	public Baraja() {
		baraja= new Carta[52];
		Figura[] arrF= Figura.values();
		NumCarta[] arrN= NumCarta.values();
		
		int cont=0;
		for (Figura i: arrF) {
			for(NumCarta j: arrN) {
				baraja[cont]=new Carta(j, i);
				cont++;
			}
		}
	}
	
	protected void finalize() {
		System.out.println("se destruye una baraja");
	}
	
	public void barajear() {
		int j;
		Carta []tmp=new Carta[baraja.length];
		for(int i=0; i<tmp.length;i++) {
			do {
				j= (int) (Math.random()*52);
				
			}while(baraja[j]==null);
			tmp[i]= baraja[j];
			baraja[j]=null;
		}
		baraja=tmp;
	}
	
	public Carta[] repartir(int num) {
		Carta tmp[]= new Carta[num];
		for(int i=0; i<num; i++) {
			tmp[i]=baraja[i];
		}
		return tmp;
	}
	
	public String toString() {
		String s1="";
		for(int i=0; i<baraja.length; i++) {
			s1+=String.format("%d.%s\n",i+1, baraja[i]);
		}
		return s1;
	}
	
}
