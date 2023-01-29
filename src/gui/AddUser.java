package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import api.Casino;
import api.Conexion;

public class AddUser extends JFrame {

	private JPanel contentPane;
	private JTextField T_nom;
	private JTextField T_AP;
	private JTextField T_AM;
	private JPasswordField P_con;

	/**
	 * Create the frame.
	 */
	public AddUser(Casino c) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 328);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Image imgIcon = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		setIconImage(imgIcon);
		
		T_nom = new JTextField();
		T_nom.setBounds(155, 24, 96, 20);
		contentPane.add(T_nom);
		T_nom.setColumns(10);
		
		JLabel L_nom = new JLabel("NOMBRE");
		L_nom.setBounds(21, 27, 96, 14);
		contentPane.add(L_nom);
		
		JLabel L_AP = new JLabel("APPATERNO");
		L_AP.setBounds(21, 65, 96, 17);
		contentPane.add(L_AP);
		
		T_AP = new JTextField();
		T_AP.setText("");
		T_AP.setBounds(155, 63, 96, 20);
		contentPane.add(T_AP);
		T_AP.setColumns(10);
		
		JLabel L_AM = new JLabel("APMATERNO");
		L_AM.setBounds(21, 111, 96, 17);
		contentPane.add(L_AM);
		
		T_AM = new JTextField();
		T_AM.setText("");
		T_AM.setColumns(10);
		T_AM.setBounds(155, 109, 96, 20);
		contentPane.add(T_AM);
		
		JLabel L_con = new JLabel("CONTRASEÑA");
		L_con.setBounds(21, 158, 96, 17);
		contentPane.add(L_con);
		
		JComboBox sexo = new JComboBox();
		sexo.setModel(new DefaultComboBoxModel(new String[] {"Seleccione su sexo", "M", "F"}));
		sexo.setBounds(21, 186, 143, 20);
		contentPane.add(sexo);
		
		JCheckBox terminos = new JCheckBox("Acepto los terminos y condiciones");
		terminos.setBounds(21, 221, 230, 29);
		contentPane.add(terminos);
		
		JButton btnNewButton = new JButton("Crear usuario");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(terminos.isSelected() && !(sexo.getSelectedItem().toString().equals("Seleccione su sexo"))) {
					Conexion con;
					try {
						con = Conexion.getConecxion();
						Statement stm= con.getCn().createStatement();
						int id=0;
						ResultSet rs= stm.executeQuery("SELECT COUNT(*) FROM USER");
						while(rs.next()) {
							id=rs.getInt(1);
						}
						id++;
						
						String s=String.format("INSERT INTO casino.user(IDUSER,NOMBRE,APPATERNO,APMATERNO, CONTRASEÑA,SEXO) VALUES(%d,'%s','%s','%s','%s','%s')", id, T_nom.getText(), T_AP.getText(), T_AM.getText(), P_con.getText(),sexo.getSelectedItem().toString());
						
						stm.executeUpdate(s);
						
						JOptionPane.showMessageDialog(null, "The Id of the new user is "+id,"MESSAGE",JOptionPane.INFORMATION_MESSAGE);
						
						dispose();
						UserMenu u = new UserMenu(c);
						u.setVisible(true);
						
					} catch (SQLException e1) {
						JOptionPane.showMessageDialog(null,"Failed to cnnect to DB","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				
			}
		});
		btnNewButton.setBounds(21, 258, 128, 20);
		contentPane.add(btnNewButton);
		
		P_con = new JPasswordField();
		P_con.setBounds(155, 156, 96, 20);
		contentPane.add(P_con);
		
		JButton cancel = new JButton("Cancelar");
		
		cancel.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				UserMenu u = new UserMenu(c);
				u.setVisible(true);
			}
		});
		cancel.setBounds(298, 258, 128, 20);
		contentPane.add(cancel);
	}

}
