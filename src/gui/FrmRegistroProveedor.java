package gui;

import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import util.JComboBoxBD;

public class FrmRegistroProveedor extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JComboBoxBD cboIDPais;
	
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	public FrmRegistroProveedor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Proveedor");
		setBounds(100, 100, 680, 478);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registro de Proveedor");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblNewLabel.setBounds(10, 25, 644, 43);
		getContentPane().add(lblNewLabel);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(199, 98, 51, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(199, 134, 51, 14);
		getContentPane().add(lblApellido);
		
		JLabel lblDni_1 = new JLabel("DNI");
		lblDni_1.setBounds(199, 167, 51, 14);
		getContentPane().add(lblDni_1);
		
		JLabel lblDireccion = new JLabel("Direccion");
		lblDireccion.setBounds(199, 200, 77, 14);
		getContentPane().add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("Telefono");
		lblTelefono.setBounds(199, 233, 51, 14);
		getContentPane().add(lblTelefono);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(199, 266, 51, 14);
		getContentPane().add(lblCorreo);
		
		JLabel lblPais = new JLabel("Pais");
		lblPais.setBounds(199, 299, 51, 14);
		getContentPane().add(lblPais);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(260, 94, 147, 22);
		getContentPane().add(txtNombre);
		
	    cboIDPais = new JComboBoxBD(rb.getString("SQL_PAIS"));
		cboIDPais.setBounds(260, 295, 147, 22);
		getContentPane().add(cboIDPais);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegistrar.setBounds(223, 332, 127, 43);
		getContentPane().add(btnRegistrar);
		
		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(260, 130, 147, 22);
		getContentPane().add(txtApellido);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(260, 163, 147, 22);
		getContentPane().add(txtDni);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(260, 196, 147, 22);
		getContentPane().add(txtDireccion);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(260, 229, 147, 22);
		getContentPane().add(txtTelefono);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(260, 262, 147, 22);
		getContentPane().add(txtCorreo);
	}
}
