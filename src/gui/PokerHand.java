package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import api.Carta;

import javax.swing.JLabel;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.awt.event.ActionEvent;

public class PokerHand extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public PokerHand(ArrayList<Carta> hand) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(900, 100, 229, 233);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Image imgIcon = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		setIconImage(imgIcon);
		
		JLabel lbCard1 = new JLabel("");
		lbCard1.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard1.setBounds(10, 11, 80, 125);
		contentPane.add(lbCard1);
		lbCard1.setIcon(new ImageIcon(hand.get(0).getImage()));
		
		JLabel lbCard2 = new JLabel("");
		lbCard2.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard2.setBounds(119, 11, 80, 125);
		contentPane.add(lbCard2);
		lbCard2.setIcon(new ImageIcon(hand.get(1).getImage()));
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();;
			}
		});
		btnBack.setBounds(119, 154, 89, 23);
		contentPane.add(btnBack);
	}
}
