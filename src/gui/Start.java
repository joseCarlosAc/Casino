package gui;

import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import api.Casino;
import api.User;

public class Start {

	protected JFrame frame;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Casino c=new Casino();
					
					c.open();
					c.userInCasino(new User(1, "Admin123"));
					c.userInCasino(new User(2, "123"));
					c.userInCasino(new User(3, "123"));
					
					for(int i=4;i<57;i++) {
						c.userInCasino(new User(i, "123"));
					}
					
					for(int i=1; i<21;i++) {
						c.userInTablePoker(i);
					}
					
					for(int i=21; i<41;i++) {
						c.userInTableBlackjack(i);
					}
					
					for(int i=41; i<57;i++) {
						c.inSlot(i);
					}
					
					System.out.println("\n\n\n\n\n\n");
					
					Start window = new Start(c);
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public Start(Casino c) {
		
		initialize(c);
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize(Casino c) {
		frame = new JFrame("CASINO");
		frame.setBounds(100, 100, 429, 282);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel inicio = new JLabel("");
		
		Image imgInicio= new ImageIcon(this.getClass().getResource("/inicio.jpg")).getImage();
		Image imgIcon = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		inicio.setIcon(new ImageIcon(imgInicio));
		frame.setIconImage(imgIcon);
		
		inicio.setBounds(10, 11, 234, 236);
		frame.getContentPane().add(inicio);
		
		JButton btnUser = new JButton("User");
		btnUser.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				UserMenu u = new UserMenu(c);
				u.setVisible(true);
			}
		});
		btnUser.setBounds(280, 92, 119, 23);
		frame.getContentPane().add(btnUser);
		
		JButton btnCasino = new JButton("Casino");
		btnCasino.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				CasinoMenu u = new CasinoMenu(c);
				u.setVisible(true);
			}
		});
		btnCasino.setBounds(280, 126, 119, 23);
		frame.getContentPane().add(btnCasino);
		
		String s= new String();
		s="Users in the casino: ";
		for(Integer i:c.getUsers().keySet()) {
			s+=i+", ";
		}
		if(s.length()>21)s=s.substring(0, s.length()-2);
	}
}
