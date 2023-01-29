package api;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JOptionPane;

public class Blackjack extends Table {

	protected ArrayList<Carta> MasoActual= new ArrayList<>();
	protected ArrayList<Carta> ManoDealer= new ArrayList<>();
    
    public void barajear(){
        Baraja Temp = new Baraja();
        Temp.barajear();
        int n=(users_t.size() + 1) * (5);
        Carta[] Temp2 = Temp.repartir(n);
        for (int i = 0; i < n; i++){
            MasoActual.add(Temp2[i]);
        }
    }

    public ArrayList<Carta> repartir(){
        if (ManoDealer.size() == 0){
            for (int i = 0; i < 2; i++){
                ManoDealer.add(MasoActual.get(MasoActual.size()- 1));
                MasoActual.remove(MasoActual.size()-1);      
            }
        }

        ArrayList<Carta> Temp = new ArrayList<>();

        for (int i = 0; i < 2; i++){
            Temp.add(MasoActual.get(MasoActual.size()- 1));
            MasoActual.remove(MasoActual.size()-1);    
        }
        return Temp;
    }

    public Carta RepartirCarta(){
        Carta Temp = MasoActual.get(MasoActual.size() - 1);
        MasoActual.remove(MasoActual.size() - 1);

        return Temp;
    }

    public int SumaCartas(ArrayList<Carta> Mano){
        int sum1 = 0;
        int sum2 = 0;

        for (Carta C : Mano){
        	sum1 += C.intNumBlack();
            }
        
        for (Carta C : Mano){
            if (C.getNumero() == NumCarta.AS){
                sum2 += 11;
                continue;    
            }

            sum2 += C.intNumBlack();
        }
        
        if(sum1>21) return sum1;
        else if(sum2>21) return sum1;
        else if(sum2==21) return sum2;
        else return sum1;
        

        

    }

    public void RepartirDealer(){
    	if(ManoDealer.size()==5) return;
        if (SumaCartas(ManoDealer) >= 16){
            return;    
        }

        ManoDealer.add(MasoActual.get(MasoActual.size()-1));
        MasoActual.remove(MasoActual.size()-1);
        RepartirDealer();
    }

    public void comprobar(HashMap<Integer, ArrayList<Carta>> id_hand, HashMap<Integer, Double> bet) throws SQLException {
        int Result;
        for (Integer I : id_hand.keySet()) {
    		
            int SumPlayer = SumaCartas(id_hand.get(I));
            int SumDealer = SumaCartas(ManoDealer);
            
            if(SumPlayer<21 && id_hand.get(I).size()==5) Result=1;
            else if(SumDealer>21) Result=1;
            else if(SumPlayer> 21) Result = -1;
            else if(SumPlayer==21) Result=1;
            else if(SumPlayer> SumDealer) Result = 1;
            else if(SumPlayer==SumDealer) Result =0;
            else Result = -1;
            
            if(Result==-1){
            	System.out.println("Perdiste\nPerdida: "+ bet.get(I)+"\nMano: "+id_hand.get(I)+"\nMano dealer: "+ManoDealer);
            	users_t.get(I).addMoney(-(bet.get(I)));
            }
            
            else if(Result==1){
            	JOptionPane.showMessageDialog(null,"User "+ I+ " win " + bet.get(I),"INFO", JOptionPane.INFORMATION_MESSAGE);
            	System.out.println("Ganaste\nGanancia: "+ bet.get(I)+"\nMano: "+id_hand.get(I)+"\nMano dealer: "+ManoDealer);
            	users_t.get(I).addMoney(bet.get(I));
            }
            else{
            	JOptionPane.showMessageDialog(null,"User "+ I+ " is stand-off" + bet.get(I),"INFO", JOptionPane.INFORMATION_MESSAGE);
            }
        }
        ManoDealer=new ArrayList<>();
    }
    
    public ArrayList<Carta> getDealer(){
    	return ManoDealer;
    }
    
    

}
