package gui;

import java.awt.Color;
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

public class FrmRegistroLibro extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
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
		
		JLabel lblTitulo = new JLabel("T\u00EDtulo");
		lblTitulo.setForeground(Color.BLUE);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblTitulo.setBounds(155, 156, 46, 28);
		getContentPane().add(lblTitulo);
		
		JLabel lblAnio = new JLabel("A\u00F1o");
		lblAnio.setForeground(Color.BLUE);
		lblAnio.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblAnio.setBounds(155, 195, 46, 29);
		getContentPane().add(lblAnio);
		
		JLabel lblSerie = new JLabel("Serie");
		lblSerie.setForeground(Color.BLUE);
		lblSerie.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblSerie.setBounds(158, 235, 46, 32);
		getContentPane().add(lblSerie);
		
		JLabel lblNewLabel_1 = new JLabel("Categor\u00EDa");
		lblNewLabel_1.setForeground(Color.BLUE);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 14));
		lblNewLabel_1.setBounds(158, 278, 97, 31);
		getContentPane().add(lblNewLabel_1);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(254, 162, 325, 20);
		getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		
		txtAnio = new JTextField();
		txtAnio.setBounds(254, 201, 86, 20);
		getContentPane().add(txtAnio);
		txtAnio.setColumns(10);
		
		txtSerie = new JTextField();
		txtSerie.setBounds(254, 243, 179, 20);
		getContentPane().add(txtSerie);
		txtSerie.setColumns(10);
		
		cboCategoria = new JComboBoxBD(rb.getString("SQL_CATEGORIA"));
		cboCategoria.setBounds(254, 284, 179, 22);
		getContentPane().add(cboCategoria);
		
		btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(279, 403, 154, 46);
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
