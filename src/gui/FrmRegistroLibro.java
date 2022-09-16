package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import util.JComboBoxBD;

import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import java.awt.Color;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmRegistroLibro extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtLibroID;
	private JTextField txtTitulo;
	private JTextField txtAnio;
	private JTextField txtSerie;
	private JComboBoxBD cboCategoria;
	
	private ResourceBundle rb = ResourceBundle.getBundle("combo");
	private JButton btnRegistrar;

	public FrmRegistroLibro() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Libro");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTRO DE LIBRO");
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 864, 37);
		getContentPane().add(lblNewLabel);
		
		JLabel lblLibroId = new JLabel("Libro ID");
		lblLibroId.setForeground(Color.BLUE);
		lblLibroId.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblLibroId.setBounds(155, 141, 63, 31);
		getContentPane().add(lblLibroId);
		
		JLabel lblTitulo = new JLabel("Titulo");
		lblTitulo.setForeground(Color.BLUE);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitulo.setBounds(155, 183, 46, 28);
		getContentPane().add(lblTitulo);
		
		JLabel lblAnio = new JLabel("A\u00F1o");
		lblAnio.setForeground(Color.BLUE);
		lblAnio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAnio.setBounds(155, 222, 46, 29);
		getContentPane().add(lblAnio);
		
		JLabel lblSerie = new JLabel("Serie");
		lblSerie.setForeground(Color.BLUE);
		lblSerie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSerie.setBounds(158, 262, 46, 32);
		getContentPane().add(lblSerie);
		
		JLabel lblNewLabel_1 = new JLabel("Categoria");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(158, 305, 97, 31);
		getContentPane().add(lblNewLabel_1);
		
		txtLibroID = new JTextField();
		txtLibroID.setBounds(254, 148, 118, 20);
		getContentPane().add(txtLibroID);
		txtLibroID.setColumns(10);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(254, 189, 325, 20);
		getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		
		txtAnio = new JTextField();
		txtAnio.setBounds(254, 228, 86, 20);
		getContentPane().add(txtAnio);
		txtAnio.setColumns(10);
		
		txtSerie = new JTextField();
		txtSerie.setBounds(254, 270, 179, 20);
		getContentPane().add(txtSerie);
		txtSerie.setColumns(10);
		
		cboCategoria = new JComboBoxBD(rb.getString("SQL_CATEGORIA"));
		cboCategoria.setBounds(254, 311, 179, 22);
		getContentPane().add(cboCategoria);
		
		btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(279, 430, 89, 23);
		getContentPane().add(btnRegistrar);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrarJButton(e);
		}
	}
	
	protected void actionPerformedBtnRegistrarJButton(ActionEvent e) {
	}
}
