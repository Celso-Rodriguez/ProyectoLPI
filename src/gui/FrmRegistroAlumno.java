package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entidad.Alumno;
import entidad.Pais;
import model.AlumnoModel;
import util.JComboBoxBD;
import util.Validaciones;

public class FrmRegistroAlumno extends JInternalFrame implements ActionListener {
	
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	private static final long serialVersionUID = 1L;
	private JTextField txtNombre;
	private JTextField txtApellido;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JTextField txtFechaDeNacimiento;
	private JButton btnAgregar, btnEliminar;
	private JComboBoxBD cboPais;
	private JTextField txtDni;
	
	


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
		lblNombre.setBounds(154, 119, 82, 22);
		getContentPane().add(lblNombre);

		JLabel lblApellido = new JLabel("Apellido :");
		lblApellido.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
		lblApellido.setBounds(567, 119, 82, 22);
		getContentPane().add(lblApellido);

		JLabel lblTelefono = new JLabel("Tel\u00E9fono :");
		lblTelefono.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
		lblTelefono.setBounds(148, 216, 82, 22);
		getContentPane().add(lblTelefono);

		JLabel lblCorreo = new JLabel("Correo :");
		lblCorreo.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
		lblCorreo.setBounds(576, 168, 69, 22);
		getContentPane().add(lblCorreo);

		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento :");
		lblFechaDeNacimiento.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
		lblFechaDeNacimiento.setBounds(61, 264, 169, 22);
		getContentPane().add(lblFechaDeNacimiento);

		txtNombre = new JTextField();
		txtNombre.setBounds(250, 123, 174, 20);
		getContentPane().add(txtNombre);
		txtNombre.setColumns(10);

		txtApellido = new JTextField();
		txtApellido.setColumns(10);
		txtApellido.setBounds(655, 123, 184, 20);
		getContentPane().add(txtApellido);

		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(244, 220, 180, 20);
		getContentPane().add(txtTelefono);

		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(659, 172, 180, 20);
		getContentPane().add(txtCorreo);

		txtFechaDeNacimiento = new JTextField();
		txtFechaDeNacimiento.setColumns(10);
		txtFechaDeNacimiento.setBounds(240, 268, 184, 20);
		getContentPane().add(txtFechaDeNacimiento);

		btnAgregar = new JButton("AGREGAR");
		btnAgregar.addActionListener(this);
		btnAgregar.setBounds(286, 339, 169, 51);
		getContentPane().add(btnAgregar);

		JLabel lblPais = new JLabel("Pa\u00EDs :");
		lblPais.setHorizontalAlignment(SwingConstants.RIGHT);
		lblPais.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
		lblPais.setBounds(505, 248, 103, 22);
		getContentPane().add(lblPais);

		cboPais = new JComboBoxBD(rb.getString("SQL_PAIS"));
		cboPais.setBounds(618, 251, 240, 22);
		getContentPane().add(cboPais);
		
		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.setBounds(560, 339, 169, 51);
		getContentPane().add(btnEliminar);
		
		JLabel lblDni = new JLabel("N\u00B0 DNI :");
		lblDni.setFont(new Font("Arial Narrow", Font.BOLD | Font.ITALIC, 18));
		lblDni.setBounds(161, 168, 69, 22);
		getContentPane().add(lblDni);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(244, 172, 180, 20);
		getContentPane().add(txtDni);
	}
	
	
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnAgregar) {
			actionPerformedBtnAgregarJButton(e);
		}
	}
	protected void actionPerformedBtnAgregarJButton(ActionEvent e) {
		String nom = txtNombre.getText().trim();
		String ape = txtApellido.getText().trim();
		String tel = txtTelefono.getText().trim();
		String dni = txtDni.getText().trim();
		String cor = txtCorreo.getText().trim();
		String fec = txtFechaDeNacimiento.getText().trim();
		int index = cboPais.getSelectedIndex();
		
		if(!nom.matches(Validaciones.TEXTO)) {
			mensaje ("El NOMBRE es de 2 a 20 caracteres");
		}else if (!ape.matches(Validaciones.TEXTO)) {
			mensaje ("El APELLIDO es de 2 a 20 caracteres");
		}else if(!tel.matches(Validaciones.FONO)) {
			mensaje ("El TELÉFONO debe empezar con 9 ");
		}else if(!dni.matches(Validaciones.DNI)) {
			mensaje ("El DNI es de 8 digitos");
		}else if(!cor.matches(Validaciones.CORREO)) {
			mensaje ("El CORREO debe tener un '@' y '.'");
		}else if (!fec.matches(Validaciones.FECHA)) {
			mensaje("La fecha tiene formato YYYY-MM-dd");
		}else if(index==0) {
			mensaje("Seleccione un País");
		}else {
			String pais = cboPais.getSelectedItem().toString();
			String idPais = pais.split(":")[0];
			
			Pais objPais = new Pais();
			objPais.setIdPais(Integer.parseInt(idPais));
			
			Alumno objAlumno = new Alumno();
			objAlumno.setNombres(nom);
			objAlumno.setApellidos(ape);
			objAlumno.setTelefono(tel);
			objAlumno.setDni(dni);
			objAlumno.setCorreo(cor);
			objAlumno.setFechaNacimiento(Date.valueOf(fec));
			objAlumno.setEstado(1);
			objAlumno.setPais(objPais);
			
			AlumnoModel model = new AlumnoModel();
			int s = model.insertaAlumno(objAlumno);
			
			
			if (s > 0) {
				mensaje("SE INSERTO CORRECTAMENTE");
			}else {
				mensaje("ERROR EN EL REGISTRO");
			}
			
		}
		
		
	}
}
