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

import entidad.Autor;
import entidad.Grado;
import model.AutorModel;
import util.JComboBoxBD;
import util.Validaciones;

public class FrmRegistroAutor extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtFechaNacimiento;
	private JTextField txtTelefono;
	private JComboBoxBD cboGrado;
	private JButton btnRegistrar;
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	public FrmRegistroAutor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Autor");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		JLabel lblRegistrarAutor = new JLabel("Registrar Autor");
		lblRegistrarAutor.setHorizontalAlignment(SwingConstants.CENTER);
		lblRegistrarAutor.setFont(new Font("Yu Gothic UI", Font.BOLD, 40));
		lblRegistrarAutor.setBounds(10, 11, 864, 84);
		getContentPane().add(lblRegistrarAutor);
		
		JLabel lblNombres = new JLabel("Nombres :");
		lblNombres.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
		lblNombres.setHorizontalAlignment(SwingConstants.TRAILING);
		lblNombres.setBounds(89, 150, 280, 25);
		getContentPane().add(lblNombres);
		
		JLabel lblApellidos = new JLabel("Apellidos :");
		lblApellidos.setHorizontalAlignment(SwingConstants.TRAILING);
		lblApellidos.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
		lblApellidos.setBounds(89, 200, 280, 25);
		getContentPane().add(lblApellidos);
		
		JLabel lblFechaNacimiento = new JLabel("Fecha de Nacimiento :");
		lblFechaNacimiento.setHorizontalAlignment(SwingConstants.TRAILING);
		lblFechaNacimiento.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
		lblFechaNacimiento.setBounds(89, 250, 280, 25);
		getContentPane().add(lblFechaNacimiento);
		
		JLabel lblTelfono = new JLabel("Tel\u00e9fono :");
		lblTelfono.setHorizontalAlignment(SwingConstants.TRAILING);
		lblTelfono.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
		lblTelfono.setBounds(89, 300, 280, 25);
		getContentPane().add(lblTelfono);
		
		JLabel lblGrado = new JLabel("Grado :");
		lblGrado.setHorizontalAlignment(SwingConstants.TRAILING);
		lblGrado.setFont(new Font("Yu Gothic UI", Font.BOLD, 25));
		lblGrado.setBounds(89, 350, 280, 25);
		getContentPane().add(lblGrado);
		
		txtNombres = new JTextField();
		txtNombres.setFont(new Font("Arial Narrow", Font.ITALIC, 20));
		txtNombres.setBounds(379, 150, 300, 30);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setFont(new Font("Arial Narrow", Font.ITALIC, 20));
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(379, 200, 300, 30);
		getContentPane().add(txtApellidos);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setFont(new Font("Arial Narrow", Font.ITALIC, 20));
		txtFechaNacimiento.setColumns(10);
		txtFechaNacimiento.setBounds(379, 250, 200, 30);
		getContentPane().add(txtFechaNacimiento);
		
		txtTelefono = new JTextField();
		txtTelefono.setFont(new Font("Arial Narrow", Font.ITALIC, 20));
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(379, 300, 200, 30);
		getContentPane().add(txtTelefono);
		
		cboGrado = new JComboBoxBD(rb.getString("SQL_GRADO"));
		cboGrado.setFont(new Font("Arial", Font.ITALIC, 20));
		cboGrado.setBounds(379, 350, 200, 30);
		getContentPane().add(cboGrado);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setFont(new Font("Yu Gothic UI", Font.BOLD, 20));
		btnRegistrar.setBounds(355, 450, 250, 50);
		getContentPane().add(btnRegistrar);
	}
	
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrar(e);
		}
	}
	protected void actionPerformedBtnRegistrar(ActionEvent e) {
		
		String nom = txtNombres.getText().trim();
		String ape = txtApellidos.getText().trim();
		String fec = txtFechaNacimiento.getText().trim();
		String tel = txtTelefono.getText().trim();
		int index = cboGrado.getSelectedIndex();
		
		if(!nom.matches(Validaciones.TEXTO)) {
			mensaje("El nombre es de 2 a 20 caracteres");
		}else if(!ape.matches(Validaciones.TEXTO)) {
			mensaje("El apellido es de 2 a 20 caracteres");
		}else if(!fec.matches(Validaciones.FECHA)) {
			mensaje("La fecha tiene formato YYY-MM-dd");
		}else if(!tel.matches(Validaciones.FONO)) {
			mensaje("El tel\u00e9fono es de 9 d\u00edgitos y empieza en 9");
		}else if(index == 0) {
			mensaje("Seleccione un Grado");
		}else {
			String grado = cboGrado.getSelectedItem().toString();
			String idGrado = grado.split(":")[0];
			
			Grado objGrado = new Grado();
			objGrado.setIdGrado(Integer.parseInt(idGrado));
			
			Autor objAutor = new Autor();
			objAutor.setNombres(nom);
			objAutor.setApellidos(ape);
			objAutor.setFechaNacimiento(Date.valueOf(fec));
			objAutor.setTelefono(Integer.parseInt(tel));
			objAutor.setGrado(objGrado);
			
			AutorModel model = new AutorModel();
			int s = model.insertaAutor(objAutor);
			if (s>0) {
				mensaje("Se registr\u00f3 correctamente");
			}else {
				mensaje("Error en el registro");
			}
		}
	}
}






