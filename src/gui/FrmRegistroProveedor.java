package gui;

import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entidad.Proveedor;
import entidad.Pais;
import model.ProveedorModel;
import util.JComboBoxBD;
import util.Validaciones;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmRegistroProveedor extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtDni;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JComboBoxBD cboPais;
	
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	public FrmRegistroProveedor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Proveedor");
		setBounds(100, 100, 680, 478);
		getContentPane().setLayout(null);
		
		JLabel lblRegistroProveedor = new JLabel("Registro de Proveedor");
		lblRegistroProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistroProveedor.setFont(new Font("Tahoma", Font.PLAIN, 27));
		lblRegistroProveedor.setBounds(10, 25, 644, 43);
		getContentPane().add(lblRegistroProveedor);
		
		JLabel lblNombre = new JLabel("Nombre");
		lblNombre.setBounds(199, 98, 51, 14);
		getContentPane().add(lblNombre);
		
		JLabel lblApellido = new JLabel("Apellido");
		lblApellido.setBounds(199, 134, 51, 14);
		getContentPane().add(lblApellido);
		
		JLabel lblDni = new JLabel("DNI");
		lblDni.setBounds(199, 167, 51, 14);
		getContentPane().add(lblDni);
		
		JLabel lblDireccion = new JLabel("Direcci\u00f3n");
		lblDireccion.setBounds(199, 200, 77, 14);
		getContentPane().add(lblDireccion);
		
		JLabel lblTelefono = new JLabel("T\u00e9lefono");
		lblTelefono.setBounds(199, 233, 51, 14);
		getContentPane().add(lblTelefono);
		
		JLabel lblCorreo = new JLabel("Correo");
		lblCorreo.setBounds(199, 266, 51, 14);
		getContentPane().add(lblCorreo);
		
		JLabel lblPais = new JLabel("Pa\u00eds");
		lblPais.setBounds(199, 299, 51, 14);
		getContentPane().add(lblPais);
		
		txtNombre = new JTextField();
		txtNombre.setColumns(10);
		txtNombre.setBounds(260, 94, 147, 22);
		getContentPane().add(txtNombre);
		
	    cboPais = new JComboBoxBD(rb.getString("SQL_PAIS"));
		cboPais.setBounds(260, 295, 147, 22);
		getContentPane().add(cboPais);
		
		JButton btnRegistrar = new JButton("Registrar");
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegistrar.setBounds(240, 337, 147, 43);
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
		
		btnRegistrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				actionPerformedBtnRegistrarJButton(e);
			}
		});
	}
	
	protected void actionPerformedBtnRegistrarJButton(ActionEvent e) {
		String nom = txtNombre.getText().trim();
		String ape = txtApellido.getText().trim();
		String dni = txtDni.getText().trim();
		String dir = txtDireccion.getText().trim();
		String tel = txtTelefono.getText().trim();
		String cor = txtCorreo.getText().trim();
		int posPais = cboPais.getSelectedIndex();
		
		if(!nom.matches(Validaciones.TEXTO)) {
			mensaje ("El Nombre es de 2 a 20 caracteres");
		}else if (!ape.matches(Validaciones.TEXTO)) {
			mensaje ("El Apellido es de 2 a 20 caracteres");
		}else if(!dni.matches(Validaciones.DNI)) {
			mensaje ("El DNI es de 8 digitos");
		}else if(!dir.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje ("La Direcci\u00f3n es de 2 a 20 caracteres");
		}else if(!tel.matches(Validaciones.FONO)) {
			mensaje ("El Tel\u00e9fono debe empezar con 9 ");
		}else if(!cor.matches(Validaciones.CORREO)) {
			mensaje ("El Correo debe tener un '@' y '.'");
		}else if(posPais == 0) {
			mensaje("Seleccione un Pa\u00eds");
		}else {
			String pais = cboPais.getSelectedItem().toString();
			String idPais = pais.split(":")[0];
			
			Pais objPais = new Pais();
			objPais.setIdPais(Integer.parseInt(idPais));
			
			Proveedor objProveedor = new Proveedor();
			objProveedor.setNombres(nom);
			objProveedor.setApellidos(ape);
			objProveedor.setDni(dni);
			objProveedor.setDireccion(dir);
			objProveedor.setTelefono(tel);
			objProveedor.setCorreo(cor);
			objProveedor.setEstado(1);
			objProveedor.setPais(objPais);
			
			ProveedorModel model = new ProveedorModel();
			int s = model.insertaProveedor(objProveedor);
			
			if (s > 0) {
				mensaje("Se Inserto Correctamente");
			}else {
				mensaje("Error en el Registro");
			}
		}
	}
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	}
}
