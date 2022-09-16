package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import util.JComboBoxBD;

public class FrmRegistroTesis extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txttitulotesis;
	private JTextField txttematesis;
	private JTextField txtfechacretesis;
	private JComboBoxBD cboidalumnotesis;
	private JButton btnprocesartesis;
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	public FrmRegistroTesis() {
		getContentPane().setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 13));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Tesis");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		JLabel lblTituloTesis = new JLabel("Registro de Tesis");
		lblTituloTesis.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 18));
		lblTituloTesis.setHorizontalAlignment(SwingConstants.CENTER);
		lblTituloTesis.setBounds(10, 21, 864, 56);
		getContentPane().add(lblTituloTesis);
		
		JLabel lbltitulotesis = new JLabel("T\u00EDtulo");
		lbltitulotesis.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lbltitulotesis.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitulotesis.setBounds(10, 115, 127, 27);
		getContentPane().add(lbltitulotesis);
		
		txttitulotesis = new JTextField();
		txttitulotesis.setBounds(178, 115, 315, 24);
		getContentPane().add(txttitulotesis);
		txttitulotesis.setColumns(10);
		
		JLabel lbltematesis = new JLabel("Tema");
		lbltematesis.setHorizontalAlignment(SwingConstants.CENTER);
		lbltematesis.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lbltematesis.setBounds(10, 153, 127, 27);
		getContentPane().add(lbltematesis);
		
		txttematesis = new JTextField();
		txttematesis.setBounds(178, 156, 315, 23);
		getContentPane().add(txttematesis);
		txttematesis.setColumns(10);
		
		JLabel lblfechacre = new JLabel("Fecha de Creaci\u00F3n");
		lblfechacre.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblfechacre.setHorizontalAlignment(SwingConstants.CENTER);
		lblfechacre.setBounds(10, 200, 127, 27);
		getContentPane().add(lblfechacre);
		
		txtfechacretesis = new JTextField();
		txtfechacretesis.setBounds(178, 204, 315, 23);
		getContentPane().add(txtfechacretesis);
		txtfechacretesis.setColumns(10);
		
		JLabel lblalumnotesis = new JLabel("Alumno");
		lblalumnotesis.setHorizontalAlignment(SwingConstants.CENTER);
		lblalumnotesis.setFont(new Font("Times New Roman", Font.PLAIN, 14));
		lblalumnotesis.setBounds(10, 254, 127, 27);
		getContentPane().add(lblalumnotesis);
		
		cboidalumnotesis = new JComboBoxBD(rb.getString("SQL_ALUMNO"));
		cboidalumnotesis.setBounds(178, 248, 315, 40);
		getContentPane().add(cboidalumnotesis);
		
		btnprocesartesis = new JButton("Procesar");
		btnprocesartesis.addActionListener(this);
		btnprocesartesis.setFont(new Font("Times New Roman", Font.PLAIN, 18));
		btnprocesartesis.setBounds(340, 339, 244, 48);
		getContentPane().add(btnprocesartesis);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnprocesartesis) {
			do_btnprocesartesis_actionPerformed(e);
		}
	}
	protected void do_btnprocesartesis_actionPerformed(ActionEvent e) {
	}
}
