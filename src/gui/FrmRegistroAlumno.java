package gui;

import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import util.JComboBoxBD;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmRegistroAlumno extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JTextField txtFechaDeNacimiento;
	private JButton btnAgregar;
	private JComboBox cboPais;
	
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	public FrmRegistroAlumno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Alumno");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);

		JLabel lblTitulo = new JLabel("Registrar Alumno");
		lblTitulo.setIcon(null);
		lblTitulo.setFont(new Font("Arial Narrow", Font.ITALIC, 32));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 11, 864, 61);
		getContentPane().add(lblTitulo);

		JLabel lblNombre = new JLabel("Nombre :");
		lblNombre.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
		lblNombre.setBounds(150, 119, 103, 22);
		getContentPane().add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido :");
		lblApellido.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
		lblApellido.setBounds(150, 170, 76, 22);
		getContentPane().add(lblApellido);

		JLabel lblTelefono = new JLabel("Tel\u00E9fono :");
		lblTelefono.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
		lblTelefono.setBounds(150, 216, 82, 22);
		getContentPane().add(lblTelefono);

		JLabel lblCorreo = new JLabel("Correo :");
		lblCorreo.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
		lblCorreo.setBounds(157, 262, 69, 22);
		getContentPane().add(lblCorreo);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento :");
		lblFechaDeNacimiento.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
		lblFechaDeNacimiento.setBounds(480, 168, 169, 22);
		getContentPane().add(lblFechaDeNacimiento);

		txtNombre = new JTextField();
		txtNombre.setBounds(267, 123, 153, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(267, 174, 153, 20);
		getContentPane().add(txtApellido);

		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(267, 220, 153, 20);
		getContentPane().add(txtTelefono);

		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(267, 266, 153, 20);
		getContentPane().add(txtCorreo);

		txtFechaDeNacimiento = new JTextField();
		txtFechaDeNacimiento.setColumns(10);
		txtFechaDeNacimiento.setBounds(659, 172, 184, 20);
		getContentPane().add(txtFechaDeNacimiento);

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(283, 329, 169, 51);
		getContentPane().add(btnAgregar);

		JLabel lblPais = new JLabel("Pa\u00EDs :");
		lblPais.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPais.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
		lblPais.setBounds(490, 216, 103, 22);
		getContentPane().add(lblPais);

		cboPais = new JComboBoxBD(rb.getString("SQL_PAIS"));
		cboPais.setBounds(603, 219, 253, 22);
		getContentPane().add(cboPais);
		
		JButton btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBounds(502, 329, 169, 51);
		getContentPane().add(btnEliminar);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			actionPerformedBtnAgregarJButton(e);
		}
	}
	protected void actionPerformedBtnAgregarJButton(ActionEvent e) {
		
		
	}
}
