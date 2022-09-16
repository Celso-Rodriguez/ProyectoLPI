package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;

public class FrmRegistroAlumno extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JTextField txtFechaDeNacimiento;
	private JTextField txtFechaRegistro;

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
		lblFechaDeNacimiento.setBounds(57, 308, 169, 22);
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
		txtFechaDeNacimiento.setBounds(267, 312, 153, 20);
		getContentPane().add(txtFechaDeNacimiento);
		
		JButton btnAgregar = new JButton("AGREGAR");
		btnAgregar.setBounds(196, 384, 169, 51);
		getContentPane().add(btnAgregar);
		
		JLabel lblEstado = new JLabel("Estado :");
		lblEstado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEstado.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
		lblEstado.setBounds(563, 216, 103, 22);
		getContentPane().add(lblEstado);
		
		JLabel lblFechaDeRegistro = new JLabel("Fecha de Registro :");
		lblFechaDeRegistro.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFechaDeRegistro.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
		lblFechaDeRegistro.setBounds(487, 158, 179, 22);
		getContentPane().add(lblFechaDeRegistro);
		
		JLabel lblPais = new JLabel("Pa\u00EDs :");
		lblPais.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPais.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
		lblPais.setBounds(563, 269, 103, 22);
		getContentPane().add(lblPais);
		
		txtFechaRegistro = new JTextField();
		txtFechaRegistro.setColumns(10);
		txtFechaRegistro.setBounds(676, 162, 153, 20);
		getContentPane().add(txtFechaRegistro);
		
		JComboBox cboEstado = new JComboBox();
		cboEstado.setModel(new DefaultComboBoxModel(new String[] {"[Seleccionar]", "Activo", "Inactivo"}));
		cboEstado.setBounds(676, 219, 153, 22);
		getContentPane().add(cboEstado);
		
		JComboBox cboPais = new JComboBox();
		cboPais.setModel(new DefaultComboBoxModel(new String[] {"[Seleccionar]", "Peru", "Argentina", "Brasil", "Bolivia", "Uruguay", "Ecuador", "Colombia"}));
		cboPais.setBounds(676, 272, 153, 22);
		getContentPane().add(cboPais);
	}
}
