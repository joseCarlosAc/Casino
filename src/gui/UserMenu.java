package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import api.Casino;
import api.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JPasswordField;

public class UserMenu extends JFrame {

	private JPanel contentPane;
	private JTextField tFUser;
	private JTextField tFAmount;
	private JPasswordField passwordField;
	private JLabel lbUserID;
	private JLabel lbPasword;
	private JLabel lbAmount;
	

	/**
	 * Create the frame.
	 */
	public UserMenu(Casino c) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Image imgIcon = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		setIconImage(imgIcon);
		
		tFUser = new JTextField();
		tFUser.setBounds(291, 32, 96, 20);
		contentPane.add(tFUser);
		tFUser.setColumns(10);
		tFUser.setVisible(false);
		
		lbUserID = new JLabel("User ID");
		lbUserID.setBounds(207, 35, 74, 14);
		contentPane.add(lbUserID);
		lbUserID.setVisible(false);
		
		lbPasword = new JLabel("Pasword");
		lbPasword.setBounds(207, 68, 74, 14);
		contentPane.add(lbPasword);
		lbPasword.setVisible(false);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(291, 66, 96, 20);
		contentPane.add(passwordField);
		passwordField.setVisible(false);
		
		lbAmount = new JLabel("Amount");
		lbAmount.setBounds(207, 103, 74, 14);
		contentPane.add(lbAmount);
		lbAmount.setVisible(false);
		
		tFAmount = new JTextField();
		tFAmount.setBounds(291, 100, 96, 20);
		contentPane.add(tFAmount);
		tFAmount.setColumns(10);
		tFAmount.setVisible(false);
		
		JButton btnBack = new JButton("Back");
		btnBack.setBounds(337, 229, 89, 23);
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Start u = new Start(c);
				u.frame.setVisible(true);
			}
		});
		contentPane.add(btnBack);
		
		JButton btnAction = new JButton();
		btnAction.setBounds(26, 217, 89, 23);
		btnAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnAction.getText().equals("Add")) {
					try {
						User tmp;
						if(c.searchUser(Integer.parseInt(tFUser.getText()))==null) {
							tmp = new User(Integer.parseInt(tFUser.getText()), passwordField.getText());
						}else {
							tmp= c.searchUser(Integer.parseInt(tFUser.getText()));
						}
						
						if(tmp.getId()==-1) {
							JOptionPane.showMessageDialog(null,"wrong password","ERROR",JOptionPane.ERROR_MESSAGE); 
							return;
						}
						else if(tmp.getId()== 0) {
							JOptionPane.showMessageDialog(null,"The user doesn't exist","ERROR",JOptionPane.ERROR_MESSAGE);
							return;
						}
						tmp.addMoney(Integer.parseInt(tFAmount.getText()));
						JOptionPane.showMessageDialog(null, "Money added successfully","MESSAGE",JOptionPane.INFORMATION_MESSAGE);
						
					} catch ( SQLException e1) {
						// TODO Auto-generated catch block
						JOptionPane.showMessageDialog(null,"Failed to connect to DB","ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,"ID isn't a number","ERROR", JOptionPane.ERROR_MESSAGE);
					}catch (Exception e1) {
						JOptionPane.showMessageDialog(null,"Error in the program","ERROR", JOptionPane.ERROR_MESSAGE);
						
					}
				}
				else if(btnAction.getText().equals("Withdraw")) {
					try {
						User tmp;
						if(c.searchUser(Integer.parseInt(tFUser.getText()))==null) {
							tmp = new User(Integer.parseInt(tFUser.getText()), passwordField.getText());
						}else {
							tmp= c.searchUser(Integer.parseInt(tFUser.getText()));
						}
						
						if(tmp.getId()==-1) {
							JOptionPane.showMessageDialog(null,"wrong password","ERROR",JOptionPane.ERROR_MESSAGE); 
							return;
						} else if(tmp.getId()== 0) {
							JOptionPane.showMessageDialog(null,"The user doesn't exist","ERROR",JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						JOptionPane.showMessageDialog(null,String.format("User %d withdraw %.2f $", tmp.getId(),tmp.retirar()),"MESSAGE", JOptionPane.INFORMATION_MESSAGE);
					
					} catch ( SQLException e1) {
						JOptionPane.showMessageDialog(null,"Failed to cnnect to DB","ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,"A caracter isn't a number","ERROR", JOptionPane.ERROR_MESSAGE);
					}catch (Exception e1) {
						JOptionPane.showMessageDialog(null,"Error in the program","ERROR", JOptionPane.ERROR_MESSAGE);
						
					}
				}
				else {
					try {
						User tmp;
						if(c.searchUser(Integer.parseInt(tFUser.getText()))==null) {
							tmp = new User(Integer.parseInt(tFUser.getText()), passwordField.getText());
						}else {
							tmp= c.searchUser(Integer.parseInt(tFUser.getText()));
						}
						
						if(tmp.getId()==-1) {
							JOptionPane.showMessageDialog(null,"wrong password","ERROR",JOptionPane.ERROR_MESSAGE); 
							return;
						} else if(tmp.getId()== 0) {
							JOptionPane.showMessageDialog(null,"The user doesn't exist","ERROR",JOptionPane.ERROR_MESSAGE);
							return;
						}
						
						JOptionPane.showMessageDialog(null,String.format("User %d has %.2f $", tmp.getId(),tmp.consulMoney()),"MESSAGE", JOptionPane.INFORMATION_MESSAGE);
					
					} catch ( SQLException e1) {
						JOptionPane.showMessageDialog(null,"Failed to cnnect to DB","ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,"A caracter isn't a number","ERROR", JOptionPane.ERROR_MESSAGE);
					}catch (Exception e1) {
						JOptionPane.showMessageDialog(null,"Error in the program","ERROR", JOptionPane.ERROR_MESSAGE);
						
					}
				}
				tFAmount.setText("");
				tFUser.setText("");
				passwordField.setText("");
			}
		});
		contentPane.add(btnAction);
		btnAction.setVisible(false);
		
		JButton btnAU = new JButton("Add User");
		btnAU.setBounds(26, 31, 147, 23);
		btnAU.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AddUser AU= new AddUser(c);
				AU.setVisible(true);
			}
		});
		contentPane.add(btnAU);
		
		JButton btnAM = new JButton("Add Money");
		btnAM.setBounds(26, 65, 147, 23);
		btnAM.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tFUser.setVisible(true);
				lbUserID.setVisible(true);
				lbPasword.setVisible(true);
				passwordField.setVisible(true);
				btnAction.setVisible(true);
				lbAmount.setVisible(true);
				tFAmount.setVisible(true);
				btnAction.setText("Add");
				tFAmount.setText("");
				tFUser.setText("");
				passwordField.setText("");
				
			}
		});
		contentPane.add(btnAM);
		
		JButton btnRetiro = new JButton("Withdraw Money");
		btnRetiro.setBounds(26, 99, 147, 23);
		btnRetiro.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tFUser.setVisible(true);
				lbUserID.setVisible(true);
				lbPasword.setVisible(true);
				passwordField.setVisible(true);
				btnAction.setVisible(true);
				lbAmount.setVisible(false);
				tFAmount.setVisible(false);
				btnAction.setText("Withdraw");
				tFUser.setText("");
				passwordField.setText("");
			}
		});
		contentPane.add(btnRetiro);
		
		JButton btnNewButton = new JButton("Consult Money");
		btnNewButton.setBounds(26, 133, 147, 23);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tFUser.setVisible(true);
				lbUserID.setVisible(true);
				lbPasword.setVisible(true);
				passwordField.setVisible(true);
				btnAction.setVisible(true);
				lbAmount.setVisible(false);
				tFAmount.setVisible(false);
				btnAction.setText("Consult");
				tFUser.setText("");
				passwordField.setText("");
			}
		});
		contentPane.add(btnNewButton);
		
		
	}
}
