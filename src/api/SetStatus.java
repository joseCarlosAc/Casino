package api;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JRadioButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JDialog;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;

@SuppressWarnings({ "unused", "serial" })
public class SetStatus extends JFrame {
	
	private static Casino c;
	private JPanel contentPane;
	private static boolean b=true;
	
	private static JDialog dialog = new JDialog();

	/**
	 * Launch the application.
	 */
	public static void set(Casino ca) {
		
		c=ca;
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				try {
					b=true;
					SetStatus frame = new SetStatus();
					dialog.getContentPane().add(frame.getContentPane());
					dialog.setModal(true);
					dialog.setSize(frame.getSize());
					dialog.setVisible(true);
				} catch (Exception e) {
					System.out.println(e.getMessage()+" error en la GUI");
				}
			}
			
		});
	}
	
	public static boolean getB() {
		return b;
	}
	
	public static void setB(boolean B) {
		b=B;
	}

	/**
	 * Create the frame.
	 */
	public SetStatus() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 164, 172);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel estado = new JLabel("New label");
		estado.setBounds(16, 0, 140, 14);
		contentPane.add(estado);
		
		if(c.checkStatus()) {
			estado.setText("Estado actual: abierto");
		}
		else {
			estado.setText("Estado actual: cerrado");
		}
		
		JRadioButton open = new JRadioButton("abrir ");
		open.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				c.open();
				dialog.dispose();
				b=false;
				dialog = new JDialog();
			}
		});
		open.setBounds(16, 21, 111, 23);
		
		
		JRadioButton close = new JRadioButton("cerrar");
		close.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try {
					c.close();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					System.out.println(e1.getMessage()+ "\n no se pudo conectar a la base de datos");
				}
				dialog.dispose();
				b=false;
				dialog = new JDialog();
			}
		});
		close.setBounds(16, 47, 111, 23);
		
		ButtonGroup g1= new ButtonGroup();
		g1.add(open);
		g1.add(close);
		
		contentPane.add(open);
		contentPane.add(close);
		
		JButton cancelar = new JButton("Cancelar");
		cancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				b=false;
				dialog.dispose();
			}
		});
		cancelar.setBounds(10, 77, 117, 23);
		contentPane.add(cancelar);
		
		
	}
}
