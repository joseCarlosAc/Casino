package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import api.Casino;
import api.Slot;
import api.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class SlotsMenu extends JFrame {

	private JPanel contentPane;
	private JTextField tfBet;

	/**
	 * Create the frame.
	 */
	public SlotsMenu(Casino c, int id) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		User u=c.searchUser(id);
		
		Image imgIcon = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		setIconImage(imgIcon);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CasinoMenu cm = new CasinoMenu(c);
				cm.setVisible(true);
			}
		});
		btnBack.setBounds(337, 229, 89, 23);
		contentPane.add(btnBack);
		
		JLabel lbFigureSlot1 = new JLabel("");
		lbFigureSlot1.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbFigureSlot1.setBounds(22, 47, 100, 120);
		contentPane.add(lbFigureSlot1);
		
		JLabel lbFigureSlot2 = new JLabel("");
		lbFigureSlot2.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbFigureSlot2.setBounds(169, 47, 100, 120);
		contentPane.add(lbFigureSlot2);
		
		JLabel lbFigureSlot3 = new JLabel("");
		lbFigureSlot3.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbFigureSlot3.setBounds(304, 47, 100, 120);
		contentPane.add(lbFigureSlot3);
		
		JLabel lbUser = new JLabel("User: "+id);
		lbUser.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbUser.setBounds(10, 11, 89, 25);
		contentPane.add(lbUser);
		
		JLabel lbMoney = new JLabel("Money: "+u.consulMoney());
		lbMoney.setHorizontalAlignment(SwingConstants.RIGHT);
		lbMoney.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lbMoney.setBounds(215, 11, 211, 25);
		contentPane.add(lbMoney);
		
		JButton btnPlay = new JButton("Play");
		btnPlay.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					double bet= Double.parseDouble(tfBet.getText());
					if(bet>u.consulMoney()) {
						JOptionPane.showMessageDialog(null,"Bet bigger than the money you have","ERROR", JOptionPane.ERROR_MESSAGE);
					}
					Slot tmp=c.playSlot(id, bet);
					
					lbFigureSlot1.setIcon(new ImageIcon(tmp.getF1()));
					lbFigureSlot2.setIcon(new ImageIcon(tmp.getF2()));
					lbFigureSlot3.setIcon(new ImageIcon(tmp.getF3()));
					
						tmp.comprobar();
					
					lbMoney.setText("Money: "+u.consulMoney());
					
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"Failed to cnnect to DB","ERROR", JOptionPane.ERROR_MESSAGE);
				}catch (NumberFormatException e1) {
					JOptionPane.showMessageDialog(null,"A caracter isn't a number","ERROR", JOptionPane.ERROR_MESSAGE);
				}  
			}
		});
		btnPlay.setBounds(10, 229, 89, 23);
		contentPane.add(btnPlay);
		
		JLabel lbBet = new JLabel("Bet");
		lbBet.setBounds(10, 204, 34, 14);
		contentPane.add(lbBet);
		
		tfBet = new JTextField();
		tfBet.setBounds(47, 201, 96, 20);
		contentPane.add(tfBet);
		tfBet.setColumns(10);
	}
}
