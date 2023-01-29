package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import api.Casino;
import api.Poker;
import api.Slot;
import api.Table;
import api.User;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.awt.Color;

public class CasinoMenu extends JFrame {

	private JPanel contentPane;
	private JTextField tFUser;
	private JPasswordField passwordField;
	private JLabel lbUserID;
	private JLabel lbPasword;
	private JLabel lbUsersIn1;
	private JLabel lbUsersIn2;
	private JLabel lbUsersIn3;
	private JLabel userSpace[]=new JLabel[56];
	private JButton btnPlaySlot[]= new JButton[16];
	private JButton btnPlayTable[]= new JButton[8];
	private JButton btnAction;
	private Table tables[]= new Table[8];
	private String s1= new String();
	private String s2= new String();
	private String s3= new String();
	

	/**
	 * Create the frame.
	 */
	
	private void setUsersIn(Casino c) {
		s1="Users in the casino: ";
		s2="";
		s3="";
		
		for(Integer i:c.getUsers().keySet()) {
			if(s1.length()<113) s1+=i+", ";
			else if(s2.length()<113) s2+=i+", ";
			else s3+=i+", ";
		}
		
		if(s1.length()>21 && s1.length()<113)s1=s1.substring(0, s1.length()-2);
		else if(s2.length()!=0 && s2.length()<113)s2=s2.substring(0, s2.length()-2);
		else if(s3.length()!=0)s3=s3.substring(0, s3.length()-2);
	}
	
	private void setSlots(Casino c) {
		int j=40;
		for(Integer i: c.listSlot()) {
			userSpace[j].setText(""+i);
			userSpace[j].setVisible(true);
			btnPlaySlot[j-40].setVisible(true);
			j++;
		}
	}
	
	private void setTable(Casino c) {
		int k=0;
		for(Table i: c.listTable()) {
			tables[k]=i;
			if(i instanceof Poker) btnPlayTable[k].setText("Poker");
			else btnPlayTable[k].setText("Blackjack");
			btnPlayTable[k].setVisible(true);
			
			int l=k*5;
			for(Integer j:i.getUsers_t().keySet()) {
				userSpace[l].setText(""+j);
				userSpace[l].setVisible(true);
				l++;
			}
			k++;
		}
	}
	
	public CasinoMenu(Casino c) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 20, 638, 657);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		Image imgIcon = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		setIconImage(imgIcon);
		
		Image imgTable= new ImageIcon(this.getClass().getResource("/table.png")).getImage();
		
		Image imgSlot= new ImageIcon(this.getClass().getResource("/slot.png")).getImage();
		
		JLabel status = new JLabel("");
		status.setBounds(21, 11, 83, 14);
		contentPane.add(status);
		if(c.checkStatus()) {
			status.setText("Status: Open");
		}
		else {
			status.setText("Status: Close");
		}
		
		setUsersIn(c);
		
		lbUsersIn1 = new JLabel(s1);
		lbUsersIn1.setBounds(12, 25, 602, 20);
		getContentPane().add(lbUsersIn1);
		
		lbUsersIn2 = new JLabel(s2);
		lbUsersIn2.setBounds(12, 40, 612, 20);
		getContentPane().add(lbUsersIn2);
		
		lbUsersIn3 = new JLabel(s3);
		lbUsersIn3.setBounds(12, 55, 167, 20);
		getContentPane().add(lbUsersIn3);
		
		JLabel lbTable1 = new JLabel();
		lbTable1.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbTable1.setBounds(177, 86, 110, 65);
		contentPane.add(lbTable1);
		lbTable1.setIcon(new ImageIcon(imgTable));
		
		JLabel lbTable2 = new JLabel();
		lbTable2.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbTable2.setBounds(177, 203, 110, 65);
		contentPane.add(lbTable2);
		lbTable2.setIcon(new ImageIcon(imgTable));
		
		JLabel lbTable3 = new JLabel();
		lbTable3.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbTable3.setBounds(177, 320, 110, 65);
		contentPane.add(lbTable3);
		lbTable3.setIcon(new ImageIcon(imgTable));
		
		JLabel lbTable4 = new JLabel();
		lbTable4.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbTable4.setBounds(177, 437, 110, 65);
		contentPane.add(lbTable4);
		lbTable4.setIcon(new ImageIcon(imgTable));
		
		JLabel lbTable5 = new JLabel();
		lbTable5.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbTable5.setBounds(333, 86, 110, 65);
		contentPane.add(lbTable5);
		lbTable5.setIcon(new ImageIcon(imgTable));
		
		JLabel lbTable6 = new JLabel();
		lbTable6.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbTable6.setBounds(333, 203, 110, 65);
		contentPane.add(lbTable6);
		lbTable6.setIcon(new ImageIcon(imgTable));
		
		JLabel lbTable7 = new JLabel();
		lbTable7.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbTable7.setBounds(333, 320, 110, 65);
		contentPane.add(lbTable7);
		lbTable7.setIcon(new ImageIcon(imgTable));
		
		JLabel lbTable8 = new JLabel();
		lbTable8.setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbTable8.setBounds(333, 437, 110, 65);
		contentPane.add(lbTable8);
		lbTable8.setIcon(new ImageIcon(imgTable));
		
		JLabel lbSlot1 = new JLabel();
		lbSlot1.setBounds(81, 86, 32, 32);
		contentPane.add(lbSlot1);
		lbSlot1.setIcon(new ImageIcon(imgSlot));
		
		JLabel lbSlot2 = new JLabel();
		lbSlot2.setBounds(81, 140, 32, 32);
		contentPane.add(lbSlot2);
		lbSlot2.setIcon(new ImageIcon(imgSlot));
		
		JLabel lbSlot3 = new JLabel();
		lbSlot3.setBounds(81, 194, 32, 32);
		contentPane.add(lbSlot3);
		lbSlot3.setIcon(new ImageIcon(imgSlot));
		
		JLabel lbSlot4 = new JLabel();
		lbSlot4.setBounds(81, 248, 32, 32);
		contentPane.add(lbSlot4);
		lbSlot4.setIcon(new ImageIcon(imgSlot));
		
		JLabel lbSlot5 = new JLabel();
		lbSlot5.setBounds(81, 302, 32, 32);
		contentPane.add(lbSlot5);
		lbSlot5.setIcon(new ImageIcon(imgSlot));
		
		JLabel lbSlot6 = new JLabel();
		lbSlot6.setBounds(81, 356, 32, 32);
		contentPane.add(lbSlot6);
		lbSlot6.setIcon(new ImageIcon(imgSlot));
		
		JLabel lbSlot7 = new JLabel();
		lbSlot7.setBounds(81, 410, 32, 32);
		contentPane.add(lbSlot7);
		lbSlot7.setIcon(new ImageIcon(imgSlot));
		
		JLabel lbSlot8 = new JLabel();
		lbSlot8.setBounds(81, 464, 32, 32);
		contentPane.add(lbSlot8);
		lbSlot8.setIcon(new ImageIcon(imgSlot));
		
		JLabel lbSlot9 = new JLabel();
		lbSlot9.setBounds(515, 86, 32, 32);
		contentPane.add(lbSlot9);
		lbSlot9.setIcon(new ImageIcon(imgSlot));
		
		JLabel lbSlot10 = new JLabel();
		lbSlot10.setBounds(515, 140, 32, 32);
		contentPane.add(lbSlot10);
		lbSlot10.setIcon(new ImageIcon(imgSlot));
		
		JLabel lbSlot11 = new JLabel();
		lbSlot11.setBounds(515, 194, 32, 32);
		contentPane.add(lbSlot11);
		lbSlot11.setIcon(new ImageIcon(imgSlot));
		
		JLabel lbSlot12 = new JLabel();
		lbSlot12.setBounds(515, 248, 32, 32);
		contentPane.add(lbSlot12);
		lbSlot12.setIcon(new ImageIcon(imgSlot));
		
		JLabel lbSlot13 = new JLabel();
		lbSlot13.setBounds(515, 302, 32, 32);
		contentPane.add(lbSlot13);
		lbSlot13.setIcon(new ImageIcon(imgSlot));
		
		JLabel lbSlot14 = new JLabel();
		lbSlot14.setBounds(515, 356, 32, 32);
		contentPane.add(lbSlot14);
		lbSlot14.setIcon(new ImageIcon(imgSlot));
		
		JLabel lbSlot15 = new JLabel();
		lbSlot15.setBounds(515, 410, 32, 32);
		contentPane.add(lbSlot15);
		lbSlot15.setIcon(new ImageIcon(imgSlot));
		
		JLabel lbSlot16 = new JLabel();
		lbSlot16.setBounds(515, 464, 32, 32);
		contentPane.add(lbSlot16);
		lbSlot16.setIcon(new ImageIcon(imgSlot));
		
		userSpace[0] = new JLabel();
		userSpace[0].setForeground(Color.RED);
		userSpace[0].setBackground(Color.BLACK);
		userSpace[0].setBounds(161, 129, 14, 21);
		contentPane.add(userSpace[0]);
		userSpace[0].setVisible(false);
		
		userSpace[1] = new JLabel();
		userSpace[1].setForeground(Color.RED);
		userSpace[1].setBackground(Color.BLACK);
		userSpace[1].setBounds(197, 147, 14, 21);
		contentPane.add(userSpace[1]);
		userSpace[1].setVisible(false);
		
		userSpace[2] = new JLabel();
		userSpace[2].setForeground(Color.RED);
		userSpace[2].setBackground(Color.BLACK);
		userSpace[2].setBounds(221, 147, 14, 21);
		contentPane.add(userSpace[2]);
		userSpace[2].setVisible(false);
		
		userSpace[3] = new JLabel();
		userSpace[3].setForeground(Color.RED);
		userSpace[3].setBackground(Color.BLACK);
		userSpace[3].setBounds(246, 147, 14, 21);
		contentPane.add(userSpace[3]);
		userSpace[3].setVisible(false);
		
		userSpace[4] = new JLabel();
		userSpace[4].setForeground(Color.RED);
		userSpace[4].setBackground(Color.BLACK);
		userSpace[4].setBounds(284, 129, 14, 21);
		contentPane.add(userSpace[4]);
		userSpace[4].setVisible(false);
		
		userSpace[5] = new JLabel();
		userSpace[5].setForeground(Color.RED);
		userSpace[5].setBackground(Color.BLACK);
		userSpace[5].setBounds(161, 247, 14, 21);
		contentPane.add(userSpace[5]);
		userSpace[5].setVisible(false);
		
		userSpace[6] = new JLabel();
		userSpace[6].setForeground(Color.RED);
		userSpace[6].setBackground(Color.BLACK);
		userSpace[6].setBounds(197, 265, 14, 21);
		contentPane.add(userSpace[6]);
		userSpace[6].setVisible(false);
		
		userSpace[7] = new JLabel();
		userSpace[7].setForeground(Color.RED);
		userSpace[7].setBackground(Color.BLACK);
		userSpace[7].setBounds(221, 265, 14, 21);
		contentPane.add(userSpace[7]);
		userSpace[7].setVisible(false);
		
		userSpace[8] = new JLabel();
		userSpace[8].setForeground(Color.RED);
		userSpace[8].setBackground(Color.BLACK);
		userSpace[8].setBounds(246, 265, 14, 21);
		contentPane.add(userSpace[8]);
		userSpace[8].setVisible(false);
		
		userSpace[9] = new JLabel();
		userSpace[9].setForeground(Color.RED);
		userSpace[9].setBackground(Color.BLACK);
		userSpace[9].setBounds(284, 247, 14, 21);
		contentPane.add(userSpace[9]);
		userSpace[9].setVisible(false);
		
		userSpace[10] = new JLabel();
		userSpace[10].setForeground(Color.RED);
		userSpace[10].setBackground(Color.BLACK);
		userSpace[10].setBounds(161, 363, 14, 21);
		contentPane.add(userSpace[10]);
		userSpace[10].setVisible(false);
		
		userSpace[11] = new JLabel();
		userSpace[11].setForeground(Color.RED);
		userSpace[11].setBackground(Color.BLACK);
		userSpace[11].setBounds(197, 381, 14, 21);
		contentPane.add(userSpace[11]);
		userSpace[11].setVisible(false);
		
		userSpace[12] = new JLabel();
		userSpace[12].setForeground(Color.RED);
		userSpace[12].setBackground(Color.BLACK);
		userSpace[12].setBounds(221, 381, 14, 21);
		contentPane.add(userSpace[12]);
		userSpace[12].setVisible(false);
		
		userSpace[13] = new JLabel();
		userSpace[13].setForeground(Color.RED);
		userSpace[13].setBackground(Color.BLACK);
		userSpace[13].setBounds(246, 381, 14, 21);
		contentPane.add(userSpace[13]);
		userSpace[13].setVisible(false);
		
		userSpace[14] = new JLabel();
		userSpace[14].setForeground(Color.RED);
		userSpace[14].setBackground(Color.BLACK);
		userSpace[14].setBounds(284, 363, 14, 21);
		contentPane.add(userSpace[14]);
		userSpace[14].setVisible(false);
		
		userSpace[15] = new JLabel();
		userSpace[15].setForeground(Color.RED);
		userSpace[15].setBackground(Color.BLACK);
		userSpace[15].setBounds(161, 483, 14, 21);
		contentPane.add(userSpace[15]);
		userSpace[15].setVisible(false);
		
		userSpace[16] = new JLabel();
		userSpace[16].setForeground(Color.RED);
		userSpace[16].setBackground(Color.BLACK);
		userSpace[16].setBounds(197, 501, 14, 21);
		contentPane.add(userSpace[16]);
		userSpace[16].setVisible(false);
		
		userSpace[17] = new JLabel();
		userSpace[17].setForeground(Color.RED);
		userSpace[17].setBackground(Color.BLACK);
		userSpace[17].setBounds(221, 501, 14, 21);
		contentPane.add(userSpace[17]);
		userSpace[17].setVisible(false);
		
		userSpace[18] = new JLabel();
		userSpace[18].setForeground(Color.RED);
		userSpace[18].setBackground(Color.BLACK);
		userSpace[18].setBounds(246, 501, 14, 21);
		contentPane.add(userSpace[18]);
		userSpace[18].setVisible(false);
		
		userSpace[19] = new JLabel();
		userSpace[19].setForeground(Color.RED);
		userSpace[19].setBackground(Color.BLACK);
		userSpace[19].setBounds(284, 483, 14, 21);
		contentPane.add(userSpace[19]);
		userSpace[19].setVisible(false);
		
		userSpace[20] = new JLabel();
		userSpace[20].setForeground(Color.RED);
		userSpace[20].setBackground(Color.BLACK);
		userSpace[20].setBounds(319, 129, 14, 21);
		contentPane.add(userSpace[20]);
		userSpace[20].setVisible(false);
		
		userSpace[21] = new JLabel();
		userSpace[21].setForeground(Color.RED);
		userSpace[21].setBackground(Color.BLACK);
		userSpace[21].setBounds(355, 147, 14, 21);
		contentPane.add(userSpace[21]);
		userSpace[21].setVisible(false);
		
		userSpace[22] = new JLabel();
		userSpace[22].setForeground(Color.RED);
		userSpace[22].setBackground(Color.BLACK);
		userSpace[22].setBounds(379, 147, 14, 21);
		contentPane.add(userSpace[22]);
		userSpace[22].setVisible(false);
		
		userSpace[23] = new JLabel();
		userSpace[23].setForeground(Color.RED);
		userSpace[23].setBackground(Color.BLACK);
		userSpace[23].setBounds(404, 147, 14, 21);
		contentPane.add(userSpace[23]);
		userSpace[23].setVisible(false);
		
		userSpace[24] = new JLabel();
		userSpace[24].setForeground(Color.RED);
		userSpace[24].setBackground(Color.BLACK);
		userSpace[24].setBounds(442, 129, 14, 21);
		contentPane.add(userSpace[24]);
		userSpace[24].setVisible(false);
		
		userSpace[25] = new JLabel();
		userSpace[25].setForeground(Color.RED);
		userSpace[25].setBackground(Color.BLACK);
		userSpace[25].setBounds(319, 247, 14, 21);
		contentPane.add(userSpace[25]);
		userSpace[25].setVisible(false);
		
		userSpace[26] = new JLabel();
		userSpace[26].setForeground(Color.RED);
		userSpace[26].setBackground(Color.BLACK);
		userSpace[26].setBounds(355, 265, 14, 21);
		contentPane.add(userSpace[26]);
		userSpace[26].setVisible(false);
		
		userSpace[27] = new JLabel();
		userSpace[27].setForeground(Color.RED);
		userSpace[27].setBackground(Color.BLACK);
		userSpace[27].setBounds(379, 265, 14, 21);
		contentPane.add(userSpace[27]);
		userSpace[27].setVisible(false);
		
		userSpace[28] = new JLabel();
		userSpace[28].setForeground(Color.RED);
		userSpace[28].setBackground(Color.BLACK);
		userSpace[28].setBounds(404, 265, 14, 21);
		contentPane.add(userSpace[28]);
		userSpace[28].setVisible(false);
		
		userSpace[29] = new JLabel();
		userSpace[29].setForeground(Color.RED);
		userSpace[29].setBackground(Color.BLACK);
		userSpace[29].setBounds(442, 247, 14, 21);
		contentPane.add(userSpace[29]);
		userSpace[29].setVisible(false);
		
		userSpace[30] = new JLabel();
		userSpace[30].setForeground(Color.RED);
		userSpace[30].setBackground(Color.BLACK);
		userSpace[30].setBounds(319, 363, 14, 21);
		contentPane.add(userSpace[30]);
		userSpace[30].setVisible(false);
		
		userSpace[31] = new JLabel();
		userSpace[31].setForeground(Color.RED);
		userSpace[31].setBackground(Color.BLACK);
		userSpace[31].setBounds(355, 381, 14, 21);
		contentPane.add(userSpace[31]);
		userSpace[31].setVisible(false);
		
		userSpace[32] = new JLabel();
		userSpace[32].setForeground(Color.RED);
		userSpace[32].setBackground(Color.BLACK);
		userSpace[32].setBounds(379, 381, 14, 21);
		contentPane.add(userSpace[32]);
		userSpace[32].setVisible(false);
		
		userSpace[33] = new JLabel();
		userSpace[33].setForeground(Color.RED);
		userSpace[33].setBackground(Color.BLACK);
		userSpace[33].setBounds(404, 381, 14, 21);
		contentPane.add(userSpace[33]);
		userSpace[33].setVisible(false);
		
		userSpace[34] = new JLabel();
		userSpace[34].setForeground(Color.RED);
		userSpace[34].setBackground(Color.BLACK);
		userSpace[34].setBounds(442, 363, 14, 21);
		contentPane.add(userSpace[34]);
		userSpace[34].setVisible(false);
		
		userSpace[35] = new JLabel();
		userSpace[35].setForeground(Color.RED);
		userSpace[35].setBackground(Color.BLACK);
		userSpace[35].setBounds(319, 483, 14, 21);
		contentPane.add(userSpace[35]);
		userSpace[35].setVisible(false);
		
		userSpace[36] = new JLabel();
		userSpace[36].setForeground(Color.RED);
		userSpace[36].setBackground(Color.BLACK);
		userSpace[36].setBounds(355, 501, 14, 21);
		contentPane.add(userSpace[36]);
		userSpace[36].setVisible(false);
		
		userSpace[37] = new JLabel();
		userSpace[37].setForeground(Color.RED);
		userSpace[37].setBackground(Color.BLACK);
		userSpace[37].setBounds(379, 501, 14, 21);
		contentPane.add(userSpace[37]);
		userSpace[37].setVisible(false);
		
		userSpace[38] = new JLabel();
		userSpace[38].setForeground(Color.RED);
		userSpace[38].setBackground(Color.BLACK);
		userSpace[38].setBounds(404, 501, 14, 21);
		contentPane.add(userSpace[38]);
		userSpace[38].setVisible(false);
		
		userSpace[39] = new JLabel();
		userSpace[39].setForeground(Color.RED);
		userSpace[39].setBackground(Color.BLACK);
		userSpace[39].setBounds(442, 483, 14, 21);
		contentPane.add(userSpace[39]);
		userSpace[39].setVisible(false);
		
		userSpace[40] = new JLabel();
		userSpace[40].setForeground(Color.RED);
		userSpace[40].setBackground(Color.BLACK);
		userSpace[40].setBounds(115, 92, 14, 21);
		contentPane.add(userSpace[40]);
		userSpace[40].setVisible(false);
		
		userSpace[41] = new JLabel();
		userSpace[41].setForeground(Color.RED);
		userSpace[41].setBackground(Color.BLACK);
		userSpace[41].setBounds(115, 144, 14, 21);
		contentPane.add(userSpace[41]);
		userSpace[41].setVisible(false);
		
		userSpace[42] = new JLabel();
		userSpace[42].setForeground(Color.RED);
		userSpace[42].setBackground(Color.BLACK);
		userSpace[42].setBounds(115, 199, 14, 21);
		contentPane.add(userSpace[42]);
		userSpace[42].setVisible(false);
		
		userSpace[43] = new JLabel();
		userSpace[43].setForeground(Color.RED);
		userSpace[43].setBackground(Color.BLACK);
		userSpace[43].setBounds(115, 251, 14, 21);
		contentPane.add(userSpace[43]);
		userSpace[43].setVisible(false);
		
		userSpace[44] = new JLabel();
		userSpace[44].setForeground(Color.RED);
		userSpace[44].setBackground(Color.BLACK);
		userSpace[44].setBounds(115, 307, 14, 21);
		contentPane.add(userSpace[44]);
		userSpace[44].setVisible(false);
		
		userSpace[45] = new JLabel();
		userSpace[45].setForeground(Color.RED);
		userSpace[45].setBackground(Color.BLACK);
		userSpace[45].setBounds(115, 359, 14, 21);
		contentPane.add(userSpace[45]);
		userSpace[45].setVisible(false);
		
		userSpace[46] = new JLabel();
		userSpace[46].setForeground(Color.RED);
		userSpace[46].setBackground(Color.BLACK);
		userSpace[46].setBounds(115, 414, 14, 21);
		contentPane.add(userSpace[46]);
		userSpace[46].setVisible(false);
		
		userSpace[47] = new JLabel();
		userSpace[47].setForeground(Color.RED);
		userSpace[47].setBackground(Color.BLACK);
		userSpace[47].setBounds(115, 466, 14, 21);
		contentPane.add(userSpace[47]);
		userSpace[47].setVisible(false);
		
		userSpace[48] = new JLabel();
		userSpace[48].setForeground(Color.RED);
		userSpace[48].setBackground(Color.BLACK);
		userSpace[48].setBounds(496, 92, 14, 21);
		contentPane.add(userSpace[48]);
		userSpace[48].setVisible(false);
		
		userSpace[49] = new JLabel();
		userSpace[49].setForeground(Color.RED);
		userSpace[49].setBackground(Color.BLACK);
		userSpace[49].setBounds(496, 144, 14, 21);
		contentPane.add(userSpace[49]);
		userSpace[49].setVisible(false);
		
		userSpace[50] = new JLabel();
		userSpace[50].setForeground(Color.RED);
		userSpace[50].setBackground(Color.BLACK);
		userSpace[50].setBounds(496, 199, 14, 21);
		contentPane.add(userSpace[50]);
		userSpace[50].setVisible(false);
		
		userSpace[51] = new JLabel();
		userSpace[51].setForeground(Color.RED);
		userSpace[51].setBackground(Color.BLACK);
		userSpace[51].setBounds(491, 251, 14, 21);
		contentPane.add(userSpace[51]);
		userSpace[51].setVisible(false);
		
		userSpace[52] = new JLabel();
		userSpace[52].setForeground(Color.RED);
		userSpace[52].setBackground(Color.BLACK);
		userSpace[52].setBounds(491, 307, 14, 21);
		contentPane.add(userSpace[52]);
		userSpace[52].setVisible(false);
		
		userSpace[53] = new JLabel();
		userSpace[53].setForeground(Color.RED);
		userSpace[53].setBackground(Color.BLACK);
		userSpace[53].setBounds(491, 359, 14, 21);
		contentPane.add(userSpace[53]);
		userSpace[53].setVisible(false);
		
		userSpace[54] = new JLabel();
		userSpace[54].setForeground(Color.RED);
		userSpace[54].setBackground(Color.BLACK);
		userSpace[54].setBounds(491, 414, 14, 21);
		contentPane.add(userSpace[54]);
		userSpace[54].setVisible(false);
		
		userSpace[55] = new JLabel();
		userSpace[55].setForeground(Color.RED);
		userSpace[55].setBackground(Color.BLACK);
		userSpace[55].setBounds(491, 466, 14, 21);
		contentPane.add(userSpace[55]);
		userSpace[55].setVisible(false);
		
		btnPlaySlot[0] = new JButton("Play");
		btnPlaySlot[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(userSpace[40].getText());
				dispose();
				SlotsMenu sm=new SlotsMenu(c, id);
				sm.setVisible(true);
			}
		});
		btnPlaySlot[0].setBounds(12, 92, 59, 23);
		contentPane.add(btnPlaySlot[0]);
		btnPlaySlot[0].setVisible(false);
		
		btnPlaySlot[1] = new JButton("Play");
		btnPlaySlot[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(userSpace[41].getText());
				dispose();
				SlotsMenu sm=new SlotsMenu(c, id);
				sm.setVisible(true);
			}
		});
		btnPlaySlot[1].setBounds(12, 150, 59, 23);
		contentPane.add(btnPlaySlot[1]);
		btnPlaySlot[1].setVisible(false);
		
		btnPlaySlot[2] = new JButton("Play");
		btnPlaySlot[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(userSpace[42].getText());
				dispose();
				SlotsMenu sm=new SlotsMenu(c, id);
				sm.setVisible(true);
			}
		});
		btnPlaySlot[2].setBounds(12, 200, 59, 23);
		contentPane.add(btnPlaySlot[2]);
		btnPlaySlot[2].setVisible(false);
		
		btnPlaySlot[3] = new JButton("Play");
		btnPlaySlot[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(userSpace[43].getText());
				dispose();
				SlotsMenu sm=new SlotsMenu(c, id);
				sm.setVisible(true);
			}
		});
		btnPlaySlot[3].setBounds(12, 254, 59, 23);
		contentPane.add(btnPlaySlot[3]);
		btnPlaySlot[3].setVisible(false);
		
		btnPlaySlot[4] = new JButton("Play");
		btnPlaySlot[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(userSpace[44].getText());
				dispose();
				SlotsMenu sm=new SlotsMenu(c, id);
				sm.setVisible(true);
			}
		});
		btnPlaySlot[4].setBounds(12, 308, 59, 23);
		contentPane.add(btnPlaySlot[4]);
		btnPlaySlot[4].setVisible(false);
		
		btnPlaySlot[5] = new JButton("Play");
		btnPlaySlot[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(userSpace[45].getText());
				dispose();
				SlotsMenu sm=new SlotsMenu(c, id);
				sm.setVisible(true);
			}
		});
		btnPlaySlot[5].setBounds(12, 366, 59, 23);
		contentPane.add(btnPlaySlot[5]);
		btnPlaySlot[5].setVisible(false);
		
		btnPlaySlot[6] = new JButton("Play");
		btnPlaySlot[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(userSpace[46].getText());
				dispose();
				SlotsMenu sm=new SlotsMenu(c, id);
				sm.setVisible(true);
			}
		});
		btnPlaySlot[6].setBounds(12, 416, 59, 23);
		contentPane.add(btnPlaySlot[6]);
		btnPlaySlot[6].setVisible(false);
		
		btnPlaySlot[7] = new JButton("Play");
		btnPlaySlot[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(userSpace[47].getText());
				dispose();
				SlotsMenu sm=new SlotsMenu(c, id);
				sm.setVisible(true);
			}
		});
		btnPlaySlot[7].setBounds(12, 470, 59, 23);
		contentPane.add(btnPlaySlot[7]);
		btnPlaySlot[7].setVisible(false);
		
		btnPlaySlot[8] = new JButton("Play");
		btnPlaySlot[8].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(userSpace[48].getText());
				dispose();
				SlotsMenu sm=new SlotsMenu(c, id);
				sm.setVisible(true);
			}
		});
		btnPlaySlot[8].setBounds(554, 91, 59, 23);
		contentPane.add(btnPlaySlot[8]);
		btnPlaySlot[8].setVisible(false);
		
		btnPlaySlot[9] = new JButton("Play");
		btnPlaySlot[9].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(userSpace[49].getText());
				dispose();
				SlotsMenu sm=new SlotsMenu(c, id);
				sm.setVisible(true);
			}
		});
		btnPlaySlot[9].setBounds(554, 149, 59, 23);
		contentPane.add(btnPlaySlot[9]);
		btnPlaySlot[9].setVisible(false);
		
		btnPlaySlot[10] = new JButton("Play");
		btnPlaySlot[10].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(userSpace[50].getText());
				dispose();
				SlotsMenu sm=new SlotsMenu(c, id);
				sm.setVisible(true);
			}
		});
		btnPlaySlot[10].setBounds(554, 199, 59, 23);
		contentPane.add(btnPlaySlot[10]);
		btnPlaySlot[10].setVisible(false);
		
		btnPlaySlot[11] = new JButton("Play");
		btnPlaySlot[11].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(userSpace[51].getText());
				dispose();
				SlotsMenu sm=new SlotsMenu(c, id);
				sm.setVisible(true);
			}
		});
		btnPlaySlot[11].setBounds(554, 253, 59, 23);
		contentPane.add(btnPlaySlot[11]);
		btnPlaySlot[11].setVisible(false);
		
		btnPlaySlot[12] = new JButton("Play");
		btnPlaySlot[12].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(userSpace[52].getText());
				dispose();
				SlotsMenu sm=new SlotsMenu(c, id);
				sm.setVisible(true);
			}
		});
		btnPlaySlot[12].setBounds(554, 307, 59, 23);
		contentPane.add(btnPlaySlot[12]);
		btnPlaySlot[12].setVisible(false);
		
		btnPlaySlot[13] = new JButton("Play");
		btnPlaySlot[13].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(userSpace[53].getText());
				dispose();
				SlotsMenu sm=new SlotsMenu(c, id);
				sm.setVisible(true);
			}
		});
		btnPlaySlot[13].setBounds(554, 365, 59, 23);
		contentPane.add(btnPlaySlot[13]);
		btnPlaySlot[13].setVisible(false);
		
		btnPlaySlot[14] = new JButton("Play");
		btnPlaySlot[14].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(userSpace[54].getText());
				dispose();
				SlotsMenu sm=new SlotsMenu(c, id);
				sm.setVisible(true);
			}
		});
		btnPlaySlot[14].setBounds(554, 415, 59, 23);
		contentPane.add(btnPlaySlot[14]);
		btnPlaySlot[14].setVisible(false);
		
		btnPlaySlot[15] = new JButton("Play");
		btnPlaySlot[15].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id=Integer.parseInt(userSpace[55].getText());
				dispose();
				SlotsMenu sm=new SlotsMenu(c, id);
				sm.setVisible(true);
			}
		});
		btnPlaySlot[15].setBounds(554, 469, 59, 23);
		contentPane.add(btnPlaySlot[15]);
		btnPlaySlot[15].setVisible(false);
		
		btnPlayTable[0] = new JButton();
		btnPlayTable[0].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id;
				if(!userSpace[0].getText().equals("")) id=Integer.parseInt(userSpace[0].getText());
				else if(!userSpace[1].getText().equals("")) id=Integer.parseInt(userSpace[1].getText());
				else if(!userSpace[2].getText().equals("")) id=Integer.parseInt(userSpace[2].getText());
				else if(!userSpace[3].getText().equals("")) id=Integer.parseInt(userSpace[3].getText());
				else id=Integer.parseInt(userSpace[4].getText());
				
				Table tmp=c.searchTeable(id);
				if(tmp instanceof Poker) {
					if(tmp.getUsers_t().size()<2) {
						JOptionPane.showMessageDialog(null,"A minimum of 2 players is required to play poker.","INFO",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					dispose();
					PokerMenu pm= new PokerMenu(c, id,0);
					pm.setVisible(true);
				}
				else {
					dispose();
					BlackjackMenu bm= new BlackjackMenu(c, id);
					bm.setVisible(true);
				}
			}
		});
		btnPlayTable[0].setBounds(186, 59, 90, 23);
		contentPane.add(btnPlayTable[0]);
		btnPlayTable[0].setVisible(false);

		btnPlayTable[1] = new JButton();
		btnPlayTable[1].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id;
				if(!userSpace[5].getText().equals("")) id=Integer.parseInt(userSpace[5].getText());
				else if(!userSpace[6].getText().equals("")) id=Integer.parseInt(userSpace[6].getText());
				else if(!userSpace[7].getText().equals("")) id=Integer.parseInt(userSpace[7].getText());
				else if(!userSpace[8].getText().equals("")) id=Integer.parseInt(userSpace[8].getText());
				else id=Integer.parseInt(userSpace[9].getText());
				
				Table tmp=c.searchTeable(id);
				if(tmp instanceof Poker) {
					if(tmp.getUsers_t().size()<2) {
						JOptionPane.showMessageDialog(null,"A minimum of 2 players is required to play poker.","INFO",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					dispose();
					PokerMenu pm= new PokerMenu(c, id,0);
					pm.setVisible(true);
				}
				else {
					dispose();
					BlackjackMenu bm= new BlackjackMenu(c, id);
					bm.setVisible(true);
				}
			}
		});
		btnPlayTable[1].setBounds(186, 176, 90, 23);
		contentPane.add(btnPlayTable[1]);
		btnPlayTable[1].setVisible(false);
		
		btnPlayTable[2] = new JButton();
		btnPlayTable[2].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id;
				if(!userSpace[10].getText().equals("")) id=Integer.parseInt(userSpace[10].getText());
				else if(!userSpace[11].getText().equals("")) id=Integer.parseInt(userSpace[11].getText());
				else if(!userSpace[12].getText().equals("")) id=Integer.parseInt(userSpace[12].getText());
				else if(!userSpace[13].getText().equals("")) id=Integer.parseInt(userSpace[13].getText());
				else id=Integer.parseInt(userSpace[14].getText());
				
				Table tmp=c.searchTeable(id);
				if(tmp instanceof Poker) {
					if(tmp.getUsers_t().size()<2) {
						JOptionPane.showMessageDialog(null,"A minimum of 2 players is required to play poker.","INFO",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					dispose();
					PokerMenu pm= new PokerMenu(c, id,0);
					pm.setVisible(true);
				}
				else {
					dispose();
					BlackjackMenu bm= new BlackjackMenu(c, id);
					bm.setVisible(true);
				}
			}
		});
		btnPlayTable[2].setBounds(186, 293, 90, 23);
		contentPane.add(btnPlayTable[2]);
		btnPlayTable[2].setVisible(false);
		
		btnPlayTable[3] = new JButton();
		btnPlayTable[3].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id;
				if(!userSpace[15].getText().equals("")) id=Integer.parseInt(userSpace[15].getText());
				else if(!userSpace[16].getText().equals("")) id=Integer.parseInt(userSpace[16].getText());
				else if(!userSpace[17].getText().equals("")) id=Integer.parseInt(userSpace[17].getText());
				else if(!userSpace[18].getText().equals("")) id=Integer.parseInt(userSpace[18].getText());
				else id=Integer.parseInt(userSpace[19].getText());
				
				Table tmp=c.searchTeable(id);
				if(tmp instanceof Poker) {
					if(tmp.getUsers_t().size()<2) {
						JOptionPane.showMessageDialog(null,"A minimum of 2 players is required to play poker.","INFO",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					dispose();
					PokerMenu pm= new PokerMenu(c, id,0);
					pm.setVisible(true);
				}
				else {
					dispose();
					BlackjackMenu bm= new BlackjackMenu(c, id);
					bm.setVisible(true);
				}
			}
		});
		btnPlayTable[3].setBounds(186, 410, 90, 23);
		contentPane.add(btnPlayTable[3]);
		btnPlayTable[3].setVisible(false);
		
		btnPlayTable[4] = new JButton();
		btnPlayTable[4].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id;
				if(!userSpace[20].getText().equals("")) id=Integer.parseInt(userSpace[20].getText());
				else if(!userSpace[21].getText().equals("")) id=Integer.parseInt(userSpace[21].getText());
				else if(!userSpace[22].getText().equals("")) id=Integer.parseInt(userSpace[22].getText());
				else if(!userSpace[23].getText().equals("")) id=Integer.parseInt(userSpace[23].getText());
				else id=Integer.parseInt(userSpace[24].getText());
				
				Table tmp=c.searchTeable(id);
				if(tmp instanceof Poker) {
					if(tmp.getUsers_t().size()<2) {
						JOptionPane.showMessageDialog(null,"A minimum of 2 players is required to play poker.","INFO",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					dispose();
					PokerMenu pm= new PokerMenu(c, id,0);
					pm.setVisible(true);
				}
				else {
					dispose();
					BlackjackMenu bm= new BlackjackMenu(c, id);
					bm.setVisible(true);
				}
			}
		});
		btnPlayTable[4].setBounds(342, 59, 90, 23);
		contentPane.add(btnPlayTable[4]);
		btnPlayTable[4].setVisible(false);
		
		btnPlayTable[5] = new JButton();
		btnPlayTable[5].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id;
				if(!userSpace[25].getText().equals("")) id=Integer.parseInt(userSpace[25].getText());
				else if(!userSpace[26].getText().equals("")) id=Integer.parseInt(userSpace[26].getText());
				else if(!userSpace[27].getText().equals("")) id=Integer.parseInt(userSpace[27].getText());
				else if(!userSpace[28].getText().equals("")) id=Integer.parseInt(userSpace[28].getText());
				else id=Integer.parseInt(userSpace[29].getText());
				
				Table tmp=c.searchTeable(id);
				if(tmp instanceof Poker) {
					if(tmp.getUsers_t().size()<2) {
						JOptionPane.showMessageDialog(null,"A minimum of 2 players is required to play poker.","INFO",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					dispose();
					PokerMenu pm= new PokerMenu(c, id,0);
					pm.setVisible(true);
				}
				else {
					dispose();
					BlackjackMenu bm= new BlackjackMenu(c, id);
					bm.setVisible(true);
				}
			}
		});
		btnPlayTable[5].setBounds(342, 176, 90, 23);
		contentPane.add(btnPlayTable[5]);
		btnPlayTable[5].setVisible(false);
		
		btnPlayTable[6] = new JButton();
		btnPlayTable[6].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id;
				if(!userSpace[30].getText().equals("")) id=Integer.parseInt(userSpace[30].getText());
				else if(!userSpace[31].getText().equals("")) id=Integer.parseInt(userSpace[31].getText());
				else if(!userSpace[32].getText().equals("")) id=Integer.parseInt(userSpace[32].getText());
				else if(!userSpace[33].getText().equals("")) id=Integer.parseInt(userSpace[33].getText());
				else id=Integer.parseInt(userSpace[34].getText());
				
				Table tmp=c.searchTeable(id);
				if(tmp instanceof Poker) {
					if(tmp.getUsers_t().size()<2) {
						JOptionPane.showMessageDialog(null,"A minimum of 2 players is required to play poker.","INFO",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					dispose();
					PokerMenu pm= new PokerMenu(c, id,0);
					pm.setVisible(true);
				}
				else {
					dispose();
					BlackjackMenu bm= new BlackjackMenu(c, id);
					bm.setVisible(true);
				}
			}
		});
		btnPlayTable[6].setBounds(342, 293, 90, 23);
		contentPane.add(btnPlayTable[6]);
		btnPlayTable[6].setVisible(false);
		
		btnPlayTable[7] = new JButton();
		btnPlayTable[7].addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int id;
				if(!userSpace[35].getText().equals("")) id=Integer.parseInt(userSpace[35].getText());
				else if(!userSpace[36].getText().equals("")) id=Integer.parseInt(userSpace[36].getText());
				else if(!userSpace[37].getText().equals("")) id=Integer.parseInt(userSpace[37].getText());
				else if(!userSpace[38].getText().equals("")) id=Integer.parseInt(userSpace[38].getText());
				else id=Integer.parseInt(userSpace[39].getText());
				
				Table tmp=c.searchTeable(id);
				if(tmp instanceof Poker) {
					if(tmp.getUsers_t().size()<2) {
						JOptionPane.showMessageDialog(null,"A minimum of 2 players is required to play poker.","INFO",JOptionPane.INFORMATION_MESSAGE);
						return;
					}
					dispose();
					PokerMenu pm= new PokerMenu(c, id,0);
					pm.setVisible(true);
				}
				else {
					dispose();
					BlackjackMenu bm= new BlackjackMenu(c, id);
					bm.setVisible(true);
				}
			}
		});
		btnPlayTable[7].setBounds(342, 410, 90, 23);
		contentPane.add(btnPlayTable[7]);
		btnPlayTable[7].setVisible(false);
		
		JButton btnStatus = new JButton("Set status");
		btnStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				SetStatus s= new SetStatus(c);
				s.setVisible(true);
				dispose();
			}
		});
		btnStatus.setBounds(10, 521, 96, 23);
		contentPane.add(btnStatus);
		
		tFUser = new JTextField();
		tFUser.setBounds(96, 555, 96, 20);
		contentPane.add(tFUser);
		tFUser.setColumns(10);
		tFUser.setVisible(false);
		
		lbUserID = new JLabel("User ID");
		lbUserID.setBounds(12, 558, 74, 14);
		contentPane.add(lbUserID);
		lbUserID.setVisible(false);
		
		lbPasword = new JLabel("Pasword");
		lbPasword.setBounds(12, 591, 74, 14);
		contentPane.add(lbPasword);
		lbPasword.setVisible(false);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(96, 589, 96, 20);
		contentPane.add(passwordField);
		passwordField.setVisible(false);
		
		JButton btnUserIn = new JButton("Check in");
		btnUserIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.checkStatus()==false) {
					JOptionPane.showMessageDialog(null,"The casino is close\n(Open it to use this button)","INFO",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				btnAction.setText("Check in");
				btnAction.setVisible(true);
				tFUser.setVisible(true);
				passwordField.setVisible(true);
				lbUserID.setVisible(true);
				lbPasword.setVisible(true);
				tFUser.setText("");
				passwordField.setText("");
			}
		});
		btnUserIn.setBounds(128, 521, 96, 23);
		contentPane.add(btnUserIn);
		
		JButton btnUserOut = new JButton("Check out");
		btnUserOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.checkStatus()==false) {
					JOptionPane.showMessageDialog(null,"The casino is close\n(Open it to use this button)","INFO",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				btnAction.setText("Check out");
				btnAction.setVisible(true);
				tFUser.setVisible(true);
				passwordField.setVisible(false);
				lbUserID.setVisible(true);
				lbPasword.setVisible(false);
				tFUser.setText("");
			}
		});
		btnUserOut.setBounds(253, 521, 96, 23);
		contentPane.add(btnUserOut);
		
		JButton btnSlotsIn = new JButton("Enter slot");
		btnSlotsIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.checkStatus()==false) {
					JOptionPane.showMessageDialog(null,"The casino is close\n(Open it to use this button)","INFO",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				btnAction.setVisible(true);
				tFUser.setVisible(true);
				passwordField.setVisible(false);
				lbUserID.setVisible(true);
				lbPasword.setVisible(false);
				tFUser.setText("");
				btnAction.setText("Enter slot");
			}
		});
		btnSlotsIn.setBounds(517, 521, 96, 23);
		contentPane.add(btnSlotsIn);
		
		JButton btnSlotsOut = new JButton("Exit slot");
		btnSlotsOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.checkStatus()==false) {
					JOptionPane.showMessageDialog(null,"The casino is close\n(Open it to use this button)","INFO",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				btnAction.setVisible(true);
				tFUser.setVisible(true);
				passwordField.setVisible(false);
				lbUserID.setVisible(true);
				lbPasword.setVisible(false);
				tFUser.setText("");
				btnAction.setText("Exit slot");
			}
		});
		btnSlotsOut.setBounds(517, 554, 96, 23);
		contentPane.add(btnSlotsOut);
		
		JButton btnPokerIn = new JButton("Enter Poker");
		btnPokerIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.checkStatus()==false) {
					JOptionPane.showMessageDialog(null,"The casino is close\n(Open it to use this button)","INFO",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				btnAction.setVisible(true);
				tFUser.setVisible(true);
				passwordField.setVisible(false);
				lbUserID.setVisible(true);
				lbPasword.setVisible(false);
				tFUser.setText("");
				btnAction.setText("Enter Poker");
			}
		});
		btnPokerIn.setBounds(365, 519, 124, 23);
		contentPane.add(btnPokerIn);
		
		JButton btnBlackjackIn = new JButton("Enter Blackjack");
		btnBlackjackIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.checkStatus()==false) {
					JOptionPane.showMessageDialog(null,"The casino is close\n(Open it to use this button)","INFO",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				btnAction.setVisible(true);
				tFUser.setVisible(true);
				passwordField.setVisible(false);
				lbUserID.setVisible(true);
				lbPasword.setVisible(false);
				tFUser.setText("");
				btnAction.setText("Enter Blackjack");
			}
		});
		btnBlackjackIn.setBounds(365, 552, 124, 23);
		contentPane.add(btnBlackjackIn);
		
		JButton btnTableOut = new JButton("Exit Table");
		btnTableOut.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(c.checkStatus()==false) {
					JOptionPane.showMessageDialog(null,"The casino is close\n(Open it to use this button)","INFO",JOptionPane.INFORMATION_MESSAGE);
					return;
				}
				btnAction.setVisible(true);
				tFUser.setVisible(true);
				passwordField.setVisible(false);
				lbUserID.setVisible(true);
				lbPasword.setVisible(false);
				tFUser.setText("");
				btnAction.setText("Exit Table");
			}
		});
		btnTableOut.setBounds(365, 587, 124, 23);
		contentPane.add(btnTableOut);
		
		btnAction = new JButton("");
		btnAction.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(btnAction.getText().equals("Check in")) {
					try {
						User u= new User(Integer.parseInt(tFUser.getText()), passwordField.getText());
						if(u.getId()==-1) {
							JOptionPane.showMessageDialog(null,"wrong password","ERROR",JOptionPane.ERROR_MESSAGE); 
							return;
						}
						else if(u.getId()== 0) {
							JOptionPane.showMessageDialog(null,"The user doesn't exist","ERROR",JOptionPane.ERROR_MESSAGE);
							return;
						}
						c.userInCasino(u);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,"A caracter isn't a number","ERROR", JOptionPane.ERROR_MESSAGE);
					}catch(SQLException e1) {
						JOptionPane.showMessageDialog(null,"Failed to connect to DB","ERROR", JOptionPane.ERROR_MESSAGE);
					}catch (Exception e1) {
						JOptionPane.showMessageDialog(null,"Error in the program","ERROR", JOptionPane.ERROR_MESSAGE);
						
					}
				}
				
				else if(btnAction.getText().equals("Check out")) {
					try {
						int id=Integer.parseInt(tFUser.getText());
						if(c.searchTeable(id)!=null) {
							Table tmp= c.searchTeable(id);
							int posT=0;
							for(int i=0;i<8;i++) {
								if(tmp==tables[i]) {
									posT=i;
									break;
								}
							}
							
							int posU;
							switch(posT) {
							case 1: posU=5;
							break;
							case 2: posU=10;
							break;
							case 3: posU=15;
							break;
							case 4: posU=20;
							break;
							case 5: posU=25;
							break;
							case 6: posU=30;
							break;
							case 7: posU=35;
							break;
							default: posU=0;
							}
							
							int con=0;
							for(int i=posU;i<posU+5;i++) {
								if(userSpace[i].getText().equals(""+id)) {
									userSpace[i].setText("");
									userSpace[i].setVisible(false);
								}
								if(!userSpace[i].getText().equals("")) {
									con++;
								}
							}
							if(con==0) {
								tables[posT]=null;
								btnPlayTable[posT].setText("");
								btnPlayTable[posT].setVisible(false);
							}
						
							c.userOutTable(id);
						}
						
						c.userOutCasino(id);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,"A caracter isn't a number","ERROR", JOptionPane.ERROR_MESSAGE);
					}catch(SQLException e1) {
						JOptionPane.showMessageDialog(null,"Failed to connect to DB","ERROR", JOptionPane.ERROR_MESSAGE);
					}catch (Exception e1) {
						JOptionPane.showMessageDialog(null,"Error in the program","ERROR", JOptionPane.ERROR_MESSAGE);
						
					}
				}
				
				else if(btnAction.getText().equals("Enter slot")) {
					try {
						int id=Integer.parseInt(tFUser.getText());
						if(c.searchUser(id)==null) {
							JOptionPane.showMessageDialog(null,"User out of the casino\n(Check in the user to enter a slot)","ERROR", JOptionPane.ERROR_MESSAGE);
							tFUser.setText("");
							return;
						}
						
						if(c.searchTeable(id)!=null) {
							JOptionPane.showMessageDialog(null,"User in a table\n(exit the table to enter a slot)","ERROR", JOptionPane.ERROR_MESSAGE);
							tFUser.setText("");
							return;
						}
						
						if(c.inSlot(id)) {
							JOptionPane.showMessageDialog(null,"Full slots","INFO", JOptionPane.INFORMATION_MESSAGE);
							tFUser.setText("");
							return;
						}
						
						int pos=40;
						for(int i=40;i<56;i++) {
							if(userSpace[i].getText().equals(""+id)) {
								JOptionPane.showMessageDialog(null,"User already in a slot","INFO", JOptionPane.INFORMATION_MESSAGE);
								tFUser.setText("");
								return;
							}
							else if(userSpace[i].getText().equals("") && !userSpace[pos].getText().equals("")) {
								pos=i;
							}
						}
						userSpace[pos].setText(tFUser.getText());
						userSpace[pos].setVisible(true);
						btnPlaySlot[pos-40].setVisible(true);

						
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,"A caracter isn't a number","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				else if(btnAction.getText().equals("Exit slot")) {
					try {
						int id=Integer.parseInt(tFUser.getText());
						for(int i=40;i<56;i++) {
							if(userSpace[i].getText().equals(""+id)) {
								userSpace[i].setText("");
								userSpace[i].setVisible(false);
								btnPlaySlot[i-40].setVisible(false);
								break;
							}
						}
						c.outSlot(id);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,"A caracter isn't a number","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				else if(btnAction.getText().equals("Enter Poker")) {
					try {
						int id=Integer.parseInt(tFUser.getText());
						if(c.searchUser(id)==null) {
							JOptionPane.showMessageDialog(null,"User out of the casino\n(Check in the user to enter a table)","ERROR", JOptionPane.ERROR_MESSAGE);
							tFUser.setText("");
							return;
						}
						
						for(Integer i: c.listSlot()) {
							if(i==id) {
								JOptionPane.showMessageDialog(null,"User in a slot\n(Exit the slot to enter a table)","ERROR", JOptionPane.ERROR_MESSAGE);
								tFUser.setText("");
								return;
							}
						}
						
						if(c.searchTeable(id)!=null) {
							JOptionPane.showMessageDialog(null,"User already in a table","INFO", JOptionPane.INFORMATION_MESSAGE);
							tFUser.setText("");
							return;
						}
						if(c.userInTablePoker(id)) {
							JOptionPane.showMessageDialog(null,"Full talbes","INFO", JOptionPane.INFORMATION_MESSAGE);
							tFUser.setText("");
							return;
						}
						
						Table tmp=c.searchTeable(id);
						
						int posT=0;
						for(int i=0;i<8;i++) {
							if(tmp==tables[i]) {
								posT=i;
								break;
							}
							if(tables[i]==null && tables[posT]!=null) {
								posT=i;
							}
						}
						tables[posT]=tmp;
						btnPlayTable[posT].setText("Poker");
						btnPlayTable[posT].setVisible(true);
						
						int posU;
						switch(posT) {
						case 1: posU=5;
						break;
						case 2: posU=10;
						break;
						case 3: posU=15;
						break;
						case 4: posU=20;
						break;
						case 5: posU=25;
						break;
						case 6: posU=30;
						break;
						case 7: posU=35;
						break;
						default: posU=0;
						}
						
						for(int i=posU;i<posU+5;i++) {
							if(userSpace[i].getText().equals("") && !userSpace[posU].getText().equals("")) {
								posU=i;
							}
						}
						
						userSpace[posU].setText(tFUser.getText());
						userSpace[posU].setVisible(true);
						
					}catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,"A caracter isn't a number","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				else if(btnAction.getText().equals("Enter Blackjack")) {
					try {
						int id=Integer.parseInt(tFUser.getText());
						if(c.searchUser(id)==null) {
							JOptionPane.showMessageDialog(null,"User out of the casino\n(Check in the user to enter a table)","ERROR", JOptionPane.ERROR_MESSAGE);
							tFUser.setText("");
							return;
						}
						
						for(Integer i: c.listSlot()) {
							if(i==id) {
								JOptionPane.showMessageDialog(null,"User in a slot\n(Exit the slot to enter a table)","ERROR", JOptionPane.ERROR_MESSAGE);
								tFUser.setText("");
								return;
							}
						}
						
						if(c.searchTeable(id)!=null) {
							JOptionPane.showMessageDialog(null,"User already in a table","INFO", JOptionPane.INFORMATION_MESSAGE);
							tFUser.setText("");
							return;
						}
						if(c.userInTableBlackjack(id)) {
							JOptionPane.showMessageDialog(null,"Full talbes","INFO", JOptionPane.INFORMATION_MESSAGE);
							tFUser.setText("");
							return;
						}
						Table tmp=c.searchTeable(id);
						
						int posT=0;
						for(int i=0;i<8;i++) {
							if(tmp==tables[i]) {
								posT=i;
								break;
							}
							if(tables[i]==null && tables[posT]!=null) {
								posT=i;
							}
						}
						
						tables[posT]=tmp;
						btnPlayTable[posT].setText("Blackjack");
						btnPlayTable[posT].setVisible(true);
						
						int posU;
						switch(posT) {
						case 1: posU=5;
						break;
						case 2: posU=10;
						break;
						case 3: posU=15;
						break;
						case 4: posU=20;
						break;
						case 5: posU=25;
						break;
						case 6: posU=30;
						break;
						case 7: posU=35;
						break;
						default: posU=0;
						}
						
						for(int i=posU;i<posU+5;i++) {
							if(userSpace[i].getText().equals("") && !userSpace[posU].getText().equals("")) {
								posU=i;
							}
						}
						
						userSpace[posU].setText(tFUser.getText());
						userSpace[posU].setVisible(true);
						
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,"A caracter isn't a number","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				else if(btnAction.getText().equals("Exit Table")) {
					try {
						int id=Integer.parseInt(tFUser.getText());
						Table tmp= c.searchTeable(id);
						int posT=0;
						for(int i=0;i<8;i++) {
							if(tmp==tables[i]) {
								posT=i;
								break;
							}
						}
						
						int posU;
						switch(posT) {
						case 1: posU=5;
						break;
						case 2: posU=10;
						break;
						case 3: posU=15;
						break;
						case 4: posU=20;
						break;
						case 5: posU=25;
						break;
						case 6: posU=30;
						break;
						case 7: posU=35;
						break;
						default: posU=0;
						}
						
						int con=0;
						for(int i=posU;i<posU+5;i++) {
							if(userSpace[i].getText().equals(""+id)) {
								userSpace[i].setText("");
								userSpace[i].setVisible(false);
							}
							if(!userSpace[i].getText().equals("")) {
								con++;
							}
						}
						if(con==0) {
							tables[posT]=null;
							btnPlayTable[posT].setText("");
							btnPlayTable[posT].setVisible(false);
						}
					
						c.userOutTable(id);
						
					}catch (SQLException e1) {
						JOptionPane.showMessageDialog(null,"Failed to cnnect to DB","ERROR", JOptionPane.ERROR_MESSAGE);
					} catch (NumberFormatException e1) {
						JOptionPane.showMessageDialog(null,"A caracter isn't a number","ERROR", JOptionPane.ERROR_MESSAGE);
					}
				}
				
				setUsersIn(c);
				lbUsersIn1.setText(s1);
				lbUsersIn2.setText(s2);
				lbUsersIn3.setText(s3);
				
				tFUser.setText("");
				passwordField.setText("");
			}
		});
		btnAction.setBounds(225, 586, 124, 23);
		btnAction.setVisible(false);
		contentPane.add(btnAction);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Start u = new Start(c);
				u.frame.setVisible(true);
			}
		});
		btnBack.setBounds(517, 587, 96, 23);
		contentPane.add(btnBack);
		
		setSlots(c);
		setTable(c);
	}
}
