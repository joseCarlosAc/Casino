package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.border.EmptyBorder;

import api.Casino;

public class SetStatus extends JFrame {

	private JPanel contentPane;

	/**
	 * Create the frame.
	 */
	public SetStatus(Casino c) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 164, 172);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Image imgIcon = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		setIconImage(imgIcon);
		
		JLabel status = new JLabel("");
		status.setBounds(16, 0, 140, 14);
		contentPane.add(status);
		
		if(c.checkStatus()) {
			status.setText("Status: Open");
		}
		else {
			status.setText("Status: Close");
		}
		
		JRadioButton open = new JRadioButton("Open");
		open.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				c.open();
				dispose();
				CasinoMenu cm= new CasinoMenu(c);
				cm.setVisible(true);
			}
		});
		open.setBounds(16, 21, 111, 23);
		
		
		JRadioButton close = new JRadioButton("Close");
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					c.close();
				} catch (SQLException e1) {
					JOptionPane.showMessageDialog(null,"Failed to connect to DB","ERROR", JOptionPane.ERROR_MESSAGE);
				}
				dispose();
				CasinoMenu cm= new CasinoMenu(c);
				cm.setVisible(true);
			}
		});
		close.setBounds(16, 47, 111, 23);
		
		ButtonGroup g1= new ButtonGroup();
		g1.add(open);
		g1.add(close);
		
		contentPane.add(open);
		contentPane.add(close);
		
		JButton cancelar = new JButton("Back");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				CasinoMenu cm= new CasinoMenu(c);
				cm.setVisible(true);
			}
		});
		cancelar.setBounds(10, 77, 117, 23);
		contentPane.add(cancelar);
	}

}
