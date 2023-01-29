package gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import api.Carta;
import api.Casino;
import api.Table;

public class BlackjackMenu extends JFrame {

	private JPanel contentPane;
	private JLabel lbCard[]=new JLabel [5];
	private JLabel lbMoney[]= new JLabel [5];
	private JLabel lbUserSpace[]= new JLabel[10];
	private JLabel lbBet[]= new JLabel[5];
	private JTextField textField;
	private HashMap<Integer, ArrayList<Carta>> id_hand= new HashMap<>();
	private HashMap<Integer, Double> bets= new HashMap<>();
	private ArrayList<Integer> ids= new ArrayList<>();
	private int posUser;
	private int posCard;

	/**
	 * Create the frame.
	 */
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
	
	public BlackjackMenu(Casino c,int idB) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 730, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Image imgIcon = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		setIconImage(imgIcon);
		
		JLabel lbAmount = new JLabel("Amount");
		lbAmount.setBounds(147, 235, 49, 14);
		contentPane.add(lbAmount);
		lbAmount.setVisible(false);
		
		textField = new JTextField();
		textField.setBounds(193, 232, 96, 20);
		contentPane.add(textField);
		textField.setColumns(10);
		textField.setVisible(false);
		
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
		
		JButton btnHit = new JButton("Hit");
		btnHit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Carta tmp=c.repartirCartaBlackjack(ids.get(posUser));
				id_hand.get(ids.get(posUser)).add(tmp);
				lbCard[posCard].setIcon(new ImageIcon(tmp.getImage()));
				posCard++;
				if(posCard==5) {
					btnHit.setVisible(false);
				}
			}
		});
		btnHit.setBounds(10, 192, 89, 23);
		contentPane.add(btnHit);
		btnHit.setVisible(false);
		
		JButton btnStand = new JButton("Stand");
		btnStand.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lbUserSpace[posUser].setForeground(Color.BLACK);
				lbUserSpace[posUser+5].setForeground(Color.BLACK);
				lbMoney[posUser].setForeground(Color.BLACK);
				lbBet[posUser].setForeground(Color.BLACK);
				
				posUser++;
				
				if(posUser==ids.size()) {
					for(int i=0; i<ids.size(); i++) {
						int id=ids.get(i);
						bets.put(id, Double.parseDouble(lbBet[i].getText()));
					}
					dispose();
					BlackjacjEnd be= new BlackjacjEnd(c, id_hand, idB);
					be.setVisible(true);
					
					try {
						c.ComprobarBlackjack(id_hand, bets);
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null,"Failed to cnnect to DB","ERROR", JOptionPane.ERROR_MESSAGE);
					}
					
					return;
				}
				
				lbCard[0].setIcon(new ImageIcon(id_hand.get(ids.get(posUser)).get(0).getImage()));
				lbCard[1].setIcon(new ImageIcon(id_hand.get(ids.get(posUser)).get(1).getImage()));
				lbCard[2].setIcon(null);
				lbCard[3].setIcon(null);
				lbCard[4].setIcon(null);
				posCard=2;
				
				btnHit.setVisible(true);
				
				JOptionPane.showMessageDialog(null,"Turn of user "+ids.get(posUser),"INFO", JOptionPane.INFORMATION_MESSAGE);
				
				lbUserSpace[posUser].setForeground(Color.RED);
				lbUserSpace[posUser+5].setForeground(Color.RED);
				lbMoney[posUser].setForeground(Color.RED);
				lbBet[posUser].setForeground(Color.RED);
			}
		});
		btnStand.setBounds(149, 192, 89, 23);
		contentPane.add(btnStand);
		btnStand.setVisible(false);
		
		JButton btnAction = new JButton("Shuffle");
		btnAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnAction.getText().equals("Shuffle")) {
					lbUsersBet.setVisible(true);
					c.barajearBlackjack(idB);
					
					for(int i=0; i<ids.size();i++) {
						id_hand.put(ids.get(i), c.repartirBlackjack(idB));
						lbBet[i].setVisible(true);
						lbUserSpace[i+5].setVisible(true);
					}
					
					posUser=0;
					lbAmount.setVisible(true);
					textField.setVisible(true);
					btnAction.setText("Bet");
					
					lbUserSpace[posUser].setForeground(Color.RED);
					lbUserSpace[posUser+5].setForeground(Color.RED);
					lbMoney[posUser].setForeground(Color.RED);
					lbBet[posUser].setForeground(Color.RED);
				}
				else if(btnAction.getText().equals("Bet")) {
					try {
						double bet= Double.parseDouble(textField.getText());
						if(bet<10) {
							JOptionPane.showMessageDialog(null,"The minimum bet is 10","INFO", JOptionPane.INFORMATION_MESSAGE);
							return;
						}
						
						lbMoney[posUser].setText(""+(Double.parseDouble(lbMoney[posUser].getText())-bet));
						lbBet[posUser].setText(""+(Double.parseDouble(lbBet[posUser].getText())+bet));
						
						lbUserSpace[posUser].setForeground(Color.BLACK);
						lbUserSpace[posUser+5].setForeground(Color.BLACK);
						lbMoney[posUser].setForeground(Color.BLACK);
						lbBet[posUser].setForeground(Color.BLACK);
						
						posUser++;
						if(posUser>=ids.size()) {
							posUser=0;
							btnAction.setVisible(false);
							lbAmount.setVisible(false);
							textField.setVisible(false);
							btnBack.setVisible(false);
							btnHit.setVisible(true);
							btnStand.setVisible(true);
							
							lbCard[0].setIcon(new ImageIcon(id_hand.get(ids.get(posUser)).get(0).getImage()));
							lbCard[1].setIcon(new ImageIcon(id_hand.get(ids.get(posUser)).get(1).getImage()));
							posCard=2;
							
						}
						
						JOptionPane.showMessageDialog(null,"Turn of user "+ids.get(posUser),"INFO", JOptionPane.INFORMATION_MESSAGE);
						
						lbUserSpace[posUser].setForeground(Color.RED);
						lbUserSpace[posUser+5].setForeground(Color.RED);
						lbMoney[posUser].setForeground(Color.RED);
						lbBet[posUser].setForeground(Color.RED);
						
						textField.setText("");
						
						
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,"A caracter isn't a number","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		btnAction.setBounds(10, 231, 89, 23);
		contentPane.add(btnAction);
		
		setUsers(c.searchTeable(idB));
	}
}
