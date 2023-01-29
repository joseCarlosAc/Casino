package gui;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Image;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import api.Carta;
import api.Casino;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class PokerEnd extends JFrame {

	private JPanel contentPane;
	private JLabel lbCard[]= new JLabel[25];
	private JLabel lbUserSpace[]= new JLabel[5];
	private JLabel lbHand[]= new JLabel[5];
	private ArrayList<Integer> ids= new ArrayList<>();
	
	private void setAll(Casino c,HashMap<Integer, ArrayList<Carta>> id_hand) {
		for(int i=0; i<id_hand.size();i++) {
			int id=ids.get(i);
			ArrayList<Carta> tmp= c.getBestHand(id,id_hand.get(id));
			for(int j=0; j<5; j++) {
				lbCard[j+i*5].setIcon(new ImageIcon(tmp.get(j).getImage()));
			}
			lbUserSpace[i].setText(""+id);
			lbHand[i].setText(""+c.rank(id, tmp));
		}
	}

	/**
	 * Create the frame.
	 */
	public PokerEnd(Casino c, HashMap<Integer, ArrayList<Carta>> id_hand, int id, int inipos) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 3, 755, 681);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		for(Integer i: id_hand.keySet()) {
			ids.add(i);
		}
		
		Image imgIcon = new ImageIcon(this.getClass().getResource("/logo.png")).getImage();
		setIconImage(imgIcon);
		
		lbCard[0] = new JLabel("");
		lbCard[0].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[0].setBounds(17, 11, 80, 125);
		contentPane.add(lbCard[0]);
		
		lbCard[1] = new JLabel("");
		lbCard[1].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[1].setBounds(117, 11, 80, 125);
		contentPane.add(lbCard[1]);
		
		lbCard[2] = new JLabel("");
		lbCard[2].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[2].setBounds(217, 11, 80, 125);
		contentPane.add(lbCard[2]);
		
		lbCard[3] = new JLabel("");
		lbCard[3].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[3].setBounds(317, 11, 80, 125);
		contentPane.add(lbCard[3]);
		
		lbCard[4] = new JLabel("");
		lbCard[4].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[4].setBounds(417, 11, 80, 125);
		contentPane.add(lbCard[4]);
		
		lbCard[5] = new JLabel("");
		lbCard[5].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[5].setBounds(17, 138, 80, 125);
		contentPane.add(lbCard[5]);
		
		lbCard[6] = new JLabel("");
		lbCard[6].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[6].setBounds(117, 138, 80, 125);
		contentPane.add(lbCard[6]);
		
		lbCard[7] = new JLabel("");
		lbCard[7].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[7].setBounds(217, 138, 80, 125);
		contentPane.add(lbCard[7]);
		
		lbCard[8] = new JLabel("");
		lbCard[8].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[8].setBounds(317, 138, 80, 125);
		contentPane.add(lbCard[8]);
		
		lbCard[9] = new JLabel("");
		lbCard[9].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[9].setBounds(417, 138, 80, 125);
		contentPane.add(lbCard[9]);
		
		lbCard[10] = new JLabel("");
		lbCard[10].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[10].setBounds(17, 265, 80, 125);
		contentPane.add(lbCard[10]);
		
		lbCard[11] = new JLabel("");
		lbCard[11].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[11].setBounds(117, 265, 80, 125);
		contentPane.add(lbCard[11]);
		
		lbCard[12] = new JLabel("");
		lbCard[12].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[12].setBounds(217, 265, 80, 125);
		contentPane.add(lbCard[12]);
		
		lbCard[13] = new JLabel("");
		lbCard[13].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[13].setBounds(317, 265, 80, 125);
		contentPane.add(lbCard[13]);
		
		lbCard[14] = new JLabel("");
		lbCard[14].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[14].setBounds(417, 265, 80, 125);
		contentPane.add(lbCard[14]);
		
		lbCard[15] = new JLabel("");
		lbCard[15].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[15].setBounds(17, 392, 80, 125);
		contentPane.add(lbCard[15]);
		
		lbCard[16] = new JLabel("");
		lbCard[16].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[16].setBounds(117, 392, 80, 125);
		contentPane.add(lbCard[16]);
		
		lbCard[17] = new JLabel("");
		lbCard[17].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[17].setBounds(217, 392, 80, 125);
		contentPane.add(lbCard[17]);
		
		lbCard[18] = new JLabel("");
		lbCard[18].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[18].setBounds(317, 392, 80, 125);
		contentPane.add(lbCard[18]);
		
		lbCard[19] = new JLabel("");
		lbCard[19].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[19].setBounds(417, 392, 80, 125);
		contentPane.add(lbCard[19]);
		
		lbCard[20] = new JLabel("");
		lbCard[20].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[20].setBounds(17, 519, 80, 125);
		contentPane.add(lbCard[20]);
		
		lbCard[21] = new JLabel("");
		lbCard[21].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[21].setBounds(117, 519, 80, 125);
		contentPane.add(lbCard[21]);
		
		lbCard[22] = new JLabel("");
		lbCard[22].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[22].setBounds(217, 519, 80, 125);
		contentPane.add(lbCard[22]);
		
		lbCard[23] = new JLabel("");
		lbCard[23].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[23].setBounds(317, 519, 80, 125);
		contentPane.add(lbCard[23]);
		
		lbCard[24] = new JLabel("");
		lbCard[24].setFont(new Font("Tahoma", Font.PLAIN, 34));
		lbCard[24].setBounds(417, 519, 80, 125);
		contentPane.add(lbCard[24]);
		
		lbUserSpace[0] = new JLabel("");
		lbUserSpace[0].setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbUserSpace[0].setBounds(507, 29, 21, 36);
		contentPane.add(lbUserSpace[0]);
		
		lbUserSpace[1] = new JLabel("");
		lbUserSpace[1].setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbUserSpace[1].setBounds(507, 156, 21, 36);
		contentPane.add(lbUserSpace[1]);
		
		lbUserSpace[2] = new JLabel("");
		lbUserSpace[2].setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbUserSpace[2].setBounds(507, 283, 21, 36);
		contentPane.add(lbUserSpace[2]);
		
		lbUserSpace[3] = new JLabel("");
		lbUserSpace[3].setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbUserSpace[3].setBounds(507, 410, 21, 36);
		contentPane.add(lbUserSpace[3]);
		
		lbUserSpace[4] = new JLabel("");
		lbUserSpace[4].setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbUserSpace[4].setBounds(507, 537, 21, 36);
		contentPane.add(lbUserSpace[4]);
		
		lbHand[0] = new JLabel("");
		lbHand[0].setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbHand[0].setBounds(538, 29, 166, 36);
		contentPane.add(lbHand[0]);
		
		lbHand[1] = new JLabel("");
		lbHand[1].setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbHand[1].setBounds(538, 156, 166, 36);
		contentPane.add(lbHand[1]);
		
		lbHand[2] = new JLabel("");
		lbHand[2].setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbHand[2].setBounds(538, 283, 166, 36);
		contentPane.add(lbHand[2]);
		
		lbHand[3] = new JLabel("");
		lbHand[3].setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbHand[3].setBounds(538, 410, 166, 36);
		contentPane.add(lbHand[3]);
		
		lbHand[4] = new JLabel("");
		lbHand[4].setFont(new Font("Tahoma", Font.PLAIN, 20));
		lbHand[4].setBounds(538, 537, 166, 36);
		contentPane.add(lbHand[4]);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				PokerMenu pm= new PokerMenu(c, id,inipos+1);
				pm.setVisible(true);
			}
		});
		btnBack.setBounds(642, 610, 89, 23);
		contentPane.add(btnBack);
		
		setAll(c, id_hand);
	}
}
