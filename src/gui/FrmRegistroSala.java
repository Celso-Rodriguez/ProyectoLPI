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
import javax.swing.JButton;

public class FrmRegistroSala extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField textField;
	private JTextField textField_1;
	private JTextField textField_2;
	private JComboBoxBD comboBox;
	private JButton btnNewButton;
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	public FrmRegistroSala() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Sala");
		setBounds(100, 100, 784, 600);
		getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("Registro de Sala");
		lblTitulo.setFont(new Font("Leelawadee UI", Font.BOLD, 46));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 23, 752, 89);
		getContentPane().add(lblTitulo);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setFont(new Font("Leelawadee UI", Font.PLAIN, 20));
		lblPiso.setBounds(135, 149, 198, 27);
		getContentPane().add(lblPiso);
		
		textField = new JTextField();
		textField.setBounds(346, 150, 320, 26);
		getContentPane().add(textField);
		textField.setColumns(10);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(346, 212, 320, 26);
		getContentPane().add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(346, 271, 320, 26);
		getContentPane().add(textField_2);
		
		JLabel lblNumeroDeAlumnos = new JLabel("N\u00FAmero de Alumnos");
		lblNumeroDeAlumnos.setFont(new Font("Leelawadee UI", Font.PLAIN, 20));
		lblNumeroDeAlumnos.setBounds(135, 212, 198, 27);
		getContentPane().add(lblNumeroDeAlumnos);
		
		JLabel lblRecursos = new JLabel("Recursos");
		lblRecursos.setFont(new Font("Leelawadee UI", Font.PLAIN, 20));
		lblRecursos.setBounds(135, 271, 198, 27);
		getContentPane().add(lblRecursos);
		
		JLabel lblSede = new JLabel("Sede");
		lblSede.setFont(new Font("Leelawadee UI", Font.PLAIN, 20));
		lblSede.setBounds(135, 327, 198, 27);
		getContentPane().add(lblSede);
		
		comboBox = new JComboBoxBD(rb.getString("SQL_SEDE"));
		comboBox.setBounds(346, 332, 320, 26);
		getContentPane().add(comboBox);
		
		btnNewButton = new JButton("Registrar");
		btnNewButton.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		btnNewButton.setBounds(296, 433, 175, 41);
		getContentPane().add(btnNewButton);
	}
}
