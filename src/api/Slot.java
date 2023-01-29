package api;

import java.awt.Image;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class Slot{
	
	private SlotFigures f1;
	private SlotFigures f2;
	private SlotFigures f3;
	private int id;
	private double bet;
	private Casino c;

    public Slot play(int id, double apuesta, Casino c){
        
    	this.id=id;
    	bet=apuesta;
    	this.c=c;
    	
        int i = (int)(Math.random()*5);
        int j = (int)(Math.random()*5);
        int k = (int)(Math.random()*5);
        
        for(SlotFigures Slot1 : SlotFigures.values()){
            if(Slot1.ordinal() == i){
                f1=Slot1;
                break;
                }
        }
        for(SlotFigures Slot2 : SlotFigures.values()){
            if(Slot2.ordinal() == j){
                f2=Slot2;
                break;
            }
        }
        for(SlotFigures Slot3 : SlotFigures.values()){
            if(Slot3.ordinal() == k){
                f3=Slot3;
                break;
            }
        }
        return this;
    }
    
    public void comprobar()throws SQLException{
    	if(f1 == f2 && f1 ==f3){
        	JOptionPane.showMessageDialog(null,"\nGanaste!!\nGanancias: $"+ 124*bet,"INFO", JOptionPane.INFORMATION_MESSAGE);
            c.getUsers().get(id).addMoney(124*bet);
        }
        else {
        	JOptionPane.showMessageDialog(null,"\nVuelva a intentar\nPerdida: $"+ bet,"INFO", JOptionPane.INFORMATION_MESSAGE);
            c.getUsers().get(id).addMoney(-bet);
        }   
    }
        
    public Image getF1() {
		return f1.getImg();
	}

	public Image getF2() {
		return f2.getImg();
	}

	public Image getF3() {
		return f3.getImg();
	}

	protected void finalize() {
		System.out.println("se destruye un Slot");
	}
}
