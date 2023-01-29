package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import api.Carta;
import api.Casino;
import api.Table;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;
import javax.swing.JTextField;

public class PokerMenu extends JFrame {

	private JPanel contentPane;
	private JLabel lbCard[]=new JLabel [5];
	private JLabel lbMoney[]= new JLabel [5];
	private JLabel lbUserSpace[]= new JLabel[10];
	private JLabel lbBet[]= new JLabel[5];
	private JButton btnAction;
	private JTextField textField;
	private HashMap<Integer, ArrayList<Carta>> id_hand= new HashMap<>();
	private HashMap<Integer, Double> bets= new HashMap<>();
	private ArrayList<Integer> ids= new ArrayList<>();
	private ArrayList<Integer> usersFold= new ArrayList<>();
	private int con=0;
	private int inipos;
	private int pos;
	private int turn=0;
	private double bet=10;
	private double potF=0;
	
	
	private void setUsers(Table t) {
		Object[] tmp= t.getUsers_t().keySet().toArray();
		for(int i=0; i<tmp.length; i++) {
			if(tmp[i] instanceof Integer) {
				ids.add((Integer) tmp[i]);
				lbUserSpace[i].setText(""+tmp[i]);
				lbUserSpace[i].setVisible(true);
				lbMoney[i].setText(""+t.getUsers_t().get(tmp[i]).consulMoney());
				lbMoney[i].setVisible(true);
				lbUserSpace[i+5].setText(""+tmp[i]);
			}
		}
	}
	private void changeTurn(Casino c, int ID) {
		ArrayList<Carta> tmp=null;
		switch (turn) {
		case 0:
			JOptionPane.showMessageDialog(null,"Comes the flop","INFO", JOptionPane.INFORMATION_MESSAGE);
			tmp= c.flop(ID);
		break;
		case 1:
			JOptionPane.showMessageDialog(null,"Comes the turn","INFO", JOptionPane.INFORMATION_MESSAGE);
			tmp= c.turn(ID);
		break;
		case 2:
			JOptionPane.showMessageDialog(null,"Comes the river","INFO", JOptionPane.INFORMATION_MESSAGE);
			tmp= c.river(ID);
		break;
		case 3:
			ArrayList<Integer> tmp2= new ArrayList<>();
			for(Integer i: usersFold) {
				tmp2.add(ids.get(i));
			}
			int fo=0;
			for(int i=0; i<ids.size(); i++) {
				if(tmp2.contains(ids.get(i))) continue;
				
				if(fo==0) {
					bets.put(ids.get(i),Double.parseDouble(lbBet[i].getText())+potF);
					fo++;
				}
				else{
					bets.put(ids.get(i),Double.parseDouble(lbBet[i].getText()));
				}
			}
			
			for(Integer i: ids) {
				if(tmp2.contains(i)) {
					id_hand.remove(i);
				}
			}
			
			dispose();
			PokerEnd pe= new PokerEnd(c,id_hand,ID,inipos);
			pe.setVisible(true);
			try {
				c.ComprobarPoker(id_hand, bets);
			} catch (SQLException e) {
				JOptionPane.showMessageDialog(null,"Failed to cnnect to DB","ERROR", JOptionPane.ERROR_MESSAGE);
			}
		return;
		}
		int pos=0;
		for (Carta i: tmp) {
			lbCard[pos].setIcon(new ImageIcon(i.getImage()));
			pos++;
		}
		
	}
	
	public PokerMenu(Casino c, int idB, int inipos) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Image imgIcon = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		setIconImage(imgIcon);
		
		this.inipos=inipos;
		
		lbCard[0] = new JLabel("");
		lbCard[0].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[0].setBounds(123, 13, 80, 125);
		contentPane.add(lbCard[0]);
		
		lbCard[1] = new JLabel("");
		lbCard[1].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[1].setBounds(223, 13, 80, 125);
		contentPane.add(lbCard[1]);
		
		lbCard[2] = new JLabel("");
		lbCard[2].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[2].setBounds(323, 13, 80, 125);
		contentPane.add(lbCard[2]);
		
		lbCard[3] = new JLabel("");
		lbCard[3].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[3].setBounds(423, 13, 80, 125);
		contentPane.add(lbCard[3]);
		
		lbCard[4] = new JLabel("");
		lbCard[4].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[4].setBounds(523, 13, 80, 125);
		contentPane.add(lbCard[4]);
		
		JLabel lbUsersMoney = new JLabel("Money of users");
		lbUsersMoney.setBounds(613, 11, 95, 18);
		contentPane.add(lbUsersMoney);
		
		JLabel lbUsersBet = new JLabel("Bet of users");
		lbUsersBet.setBounds(10, 11, 95, 14);
		contentPane.add(lbUsersBet);
		lbUsersBet.setVisible(false);
		
		JLabel lbAmount = new JLabel("Amount");
		lbAmount.setBounds(147, 235, 49, 14);
		contentPane.add(lbAmount);
		lbAmount.setVisible(false);
		
		textField = new JTextField();
		textField.setBounds(193, 232, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setVisible(false);
		
		lbUserSpace[0] = new JLabel("");
		lbUserSpace[0].setBounds(613, 33, 14, 21);
		contentPane.add(lbUserSpace[0]);
		lbUserSpace[0].setVisible(false);
		
		lbUserSpace[1] = new JLabel("");
		lbUserSpace[1].setBounds(613, 54, 14, 21);
		contentPane.add(lbUserSpace[1]);
		lbUserSpace[1].setVisible(false);
		
		lbUserSpace[2] = new JLabel("");
		lbUserSpace[2].setBounds(613, 75, 14, 21);
		contentPane.add(lbUserSpace[2]);
		lbUserSpace[2].setVisible(false);
		
		lbUserSpace[3] = new JLabel("");
		lbUserSpace[3].setBounds(613, 96, 14, 21);
		contentPane.add(lbUserSpace[3]);
		lbUserSpace[3].setVisible(false);
		
		lbUserSpace[4] = new JLabel("");
		lbUserSpace[4].setBounds(613, 117, 14, 21);
		contentPane.add(lbUserSpace[4]);
		lbUserSpace[4].setVisible(false);
		
		lbUserSpace[5] = new JLabel("");
		lbUserSpace[5].setBounds(10, 33, 14, 21);
		contentPane.add(lbUserSpace[5]);
		lbUserSpace[5].setVisible(false);
		
		lbUserSpace[6] = new JLabel("");
		lbUserSpace[6].setBounds(10, 54, 14, 21);
		contentPane.add(lbUserSpace[6]);
		lbUserSpace[6].setVisible(false);
		
		lbUserSpace[7] = new JLabel("");
		lbUserSpace[7].setBounds(10, 75, 14, 21);
		contentPane.add(lbUserSpace[7]);
		lbUserSpace[7].setVisible(false);
		
		lbUserSpace[8] = new JLabel("");
		lbUserSpace[8].setBounds(10, 96, 14, 21);
		contentPane.add(lbUserSpace[8]);
		lbUserSpace[8].setVisible(false);
		
		lbUserSpace[9] = new JLabel("");
		lbUserSpace[9].setBounds(10, 117, 14, 21);
		contentPane.add(lbUserSpace[9]);
		lbUserSpace[9].setVisible(false);
		
		lbMoney[0] = new JLabel("");
		lbMoney[0].setBounds(636, 36, 72, 14);
		contentPane.add(lbMoney[0]);
		lbMoney[0].setVisible(false);
		
		lbMoney[1] = new JLabel("");
		lbMoney[1].setBounds(636, 57, 72, 14);
		contentPane.add(lbMoney[1]);
		lbMoney[1].setVisible(false);
		
		lbMoney[2] = new JLabel("");
		lbMoney[2].setBounds(636, 78, 72, 14);
		contentPane.add(lbMoney[2]);
		lbMoney[2].setVisible(false);
		
		lbMoney[3] = new JLabel("");
		lbMoney[3].setBounds(636, 99, 72, 14);
		contentPane.add(lbMoney[3]);
		lbMoney[3].setVisible(false);
		
		lbMoney[4] = new JLabel("");
		lbMoney[4].setBounds(637, 120, 72, 14);
		contentPane.add(lbMoney[4]);
		lbMoney[4].setVisible(false);
		
		lbBet[0] = new JLabel("0");
		lbBet[0].setBounds(33, 36, 72, 14);
		contentPane.add(lbBet[0]);
		lbBet[0].setVisible(false);
		
		lbBet[1] = new JLabel("0");
		lbBet[1].setBounds(33, 57, 72, 14);
		contentPane.add(lbBet[1]);
		lbBet[1].setVisible(false);
		
		lbBet[2] = new JLabel("0");
		lbBet[2].setBounds(33, 78, 72, 14);
		contentPane.add(lbBet[2]);
		lbBet[2].setVisible(false);
		
		lbBet[3] = new JLabel("0");
		lbBet[3].setBounds(33, 99, 72, 14);
		contentPane.add(lbBet[3]);
		lbBet[3].setVisible(false);
		
		lbBet[4] = new JLabel("0");
		lbBet[4].setBounds(34, 120, 72, 14);
		contentPane.add(lbBet[4]);
		lbBet[4].setVisible(false);
		
		JButton btnFold = new JButton("Fold");
		btnFold.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbAmount.setVisible(false);
				textField.setText("");
				textField.setVisible(false);
				btnAction.setVisible(false);
				
				usersFold.add(pos);
				potF+=Double.parseDouble(lbBet[pos].getText());
				lbUserSpace[pos].setForeground(Color.GREEN);
				lbUserSpace[pos+5].setForeground(Color.GREEN);
				lbMoney[pos].setForeground(Color.GREEN);
				lbBet[pos].setForeground(Color.GREEN);
				
				pos++;
				con++;
				if(pos>=ids.size()) pos=0;
				while(usersFold.contains(pos)) {
					pos++;
					con++;
					if(pos>=ids.size()) pos=0;
				}
				
				if(usersFold.size()==ids.size()-1){
					ArrayList<Integer> tmp= new ArrayList<>();
					for(Integer i: usersFold) {
						tmp.add(ids.get(i));
					}
					int idT=0;
					for(Integer i: ids) {
						if(!tmp.contains(i)) {
							idT=i;
							break;
						}
					}
					
					JOptionPane.showMessageDialog(null,"User "+idT +" win "+potF,"INFO", JOptionPane.INFORMATION_MESSAGE);
					try {
						for(int i: usersFold) {
							if(ids.get(i)==idT) continue;
							c.searchUser(ids.get(i)).addMoney(Double.parseDouble(lbBet[i].getText())*-1);
						}
						c.searchUser(idT).addMoney(potF);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null,"Failed to cnnect to DB","ERROR", JOptionPane.ERROR_MESSAGE);
					}
					dispose();
					PokerMenu pm= new PokerMenu(c, idB,inipos+1);
					pm.setVisible(true);
					return;
				
				}else if(con>=ids.size()) {
					changeTurn(c, idB);
					turn++;
					pos=inipos;
					con=0;
					while(usersFold.contains(pos)) {
						pos++;
						con++;
						if(pos>=ids.size()) pos=0;
					}
				
				}else {
					JOptionPane.showMessageDialog(null,"Turn of user "+ids.get(pos),"INFO", JOptionPane.INFORMATION_MESSAGE);
				}
				lbUserSpace[pos].setForeground(Color.RED);
				lbUserSpace[pos+5].setForeground(Color.RED);
				lbMoney[pos].setForeground(Color.RED);
				lbBet[pos].setForeground(Color.RED);
				
				
			}
		});
		btnFold.setBounds(10, 181, 89, 23);
		contentPane.add(btnFold);
		btnFold.setVisible(false);
		
		JButton btnCheck = new JButton("Check");
		btnCheck.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbAmount.setVisible(false);
				textField.setText("");
				textField.setVisible(false);
				btnAction.setVisible(false);
				
				double add=bet-Double.parseDouble(lbBet[pos].getText());
				
				lbMoney[pos].setText(""+(Double.parseDouble(lbMoney[pos].getText())-add));
				lbBet[pos].setText(""+(Double.parseDouble(lbBet[pos].getText())+add));
				
				lbUserSpace[pos].setForeground(Color.BLACK);
				lbUserSpace[pos+5].setForeground(Color.BLACK);
				lbMoney[pos].setForeground(Color.BLACK);
				lbBet[pos].setForeground(Color.BLACK);
				
				
				pos++;
				con++;
				if(pos>=ids.size()) pos=0;
				while(usersFold.contains(pos)) {
					pos++;
					con++;
					if(pos>=ids.size()) pos=0;
				}
				
				
				if(con>=ids.size()) {
					changeTurn(c, idB);
					turn++;
					pos=inipos;
					con=0;
					while(usersFold.contains(pos)) {
						pos++;
						con++;
						if(pos>=ids.size()) pos=0;
					}
				}
				
				else{
					JOptionPane.showMessageDialog(null,"Turn of user "+ids.get(pos),"INFO", JOptionPane.INFORMATION_MESSAGE);
				}
				lbUserSpace[pos].setForeground(Color.RED);
				lbUserSpace[pos+5].setForeground(Color.RED);
				lbMoney[pos].setForeground(Color.RED);
				lbBet[pos].setForeground(Color.RED);
			}
		});
		btnCheck.setBounds(116, 181, 89, 23);
		contentPane.add(btnCheck);
		btnCheck.setVisible(false);
		
		JButton btnRaise = new JButton("Raise");
		btnRaise.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbAmount.setVisible(true);
				textField.setVisible(true);
				btnAction.setText("Raise");
				btnAction.setVisible(true);
			}
		});
		btnRaise.setBounds(222, 181, 89, 23);
		contentPane.add(btnRaise);
		btnRaise.setVisible(false);
		
		JButton btnHand = new JButton("See hand");
		btnHand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				PokerHand ph= new PokerHand(id_hand.get(ids.get(pos)));
				ph.setVisible(true);
			}
		});
		btnHand.setBounds(328, 181, 89, 23);
		contentPane.add(btnHand);
		btnHand.setVisible(false);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CasinoMenu cm = new CasinoMenu(c);
				cm.setVisible(true);
			}
		});
		btnBack.setBounds(619, 231, 89, 23);
		contentPane.add(btnBack);
		
		btnAction = new JButton("Deal cards");
		btnAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnAction.getText().equals("Deal cards")) {
					lbUsersBet.setVisible(true);
					c.barajearPoker(idB);
					
					for(int i=0; i<ids.size();i++) {
						id_hand.put(ids.get(i), c.repartirPoker(idB));
						lbMoney[i].setText(""+(Double.parseDouble(lbMoney[i].getText())-10));
						lbBet[i].setText("10.0");
						lbBet[i].setVisible(true);
						lbUserSpace[i+5].setVisible(true);
					}
					btnFold.setVisible(true);
					btnCheck.setVisible(true);
					btnRaise.setVisible(true);
					btnHand.setVisible(true);
					btnAction.setVisible(false);
					btnBack.setVisible(false);
					
					pos=inipos;
					lbUserSpace[pos].setForeground(Color.RED);
					lbUserSpace[pos+5].setForeground(Color.RED);
					lbMoney[pos].setForeground(Color.RED);
					lbBet[pos].setForeground(Color.RED);
				}
				else if(btnAction.getText().equals("Raise")) {
					try {
						double tmpBet=Double.parseDouble(textField.getText());
						
						bet+=tmpBet;
						double add=bet-Double.parseDouble(lbBet[pos].getText());
						lbMoney[pos].setText(""+(Double.parseDouble(lbMoney[pos].getText())-add));
						lbBet[pos].setText(""+(Double.parseDouble(lbBet[pos].getText())+add));
						
						lbUserSpace[pos].setForeground(Color.BLACK);
						lbUserSpace[pos+5].setForeground(Color.BLACK);
						lbMoney[pos].setForeground(Color.BLACK);
						lbBet[pos].setForeground(Color.BLACK);
						
						con=1;
						pos++;
						if(pos>=ids.size()) pos=0;
						while(usersFold.contains(pos)) {
							pos++;
							con++;
							if(pos>=ids.size()) pos=0;
						}
						
						
						lbUserSpace[pos].setForeground(Color.RED);
						lbUserSpace[pos+5].setForeground(Color.RED);
						lbMoney[pos].setForeground(Color.RED);
						lbBet[pos].setForeground(Color.RED);
						
						lbAmount.setVisible(false);
						textField.setText("");
						textField.setVisible(false);
						btnAction.setVisible(false);
						
						JOptionPane.showMessageDialog(null,"Turn of user "+ids.get(pos),"INFO", JOptionPane.INFORMATION_MESSAGE);
						
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,"A caracter isn't a number","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				
			}
		});
		btnAction.setBounds(10, 231, 95, 23);
		contentPane.add(btnAction);
		
		setUsers(c.searchTeable(idB));
	}
}
