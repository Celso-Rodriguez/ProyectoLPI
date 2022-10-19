package gui;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableModel;

import entidad.Alumno;
import entidad.Pais;
import model.AlumnoModel;
import util.JComboBoxBD;
import util.Validaciones;

public class FrmCrudAlumno extends JInternalFrame implements ActionListener, MouseListener {
	
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	private static final long serialVersionUID = 1L;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtFechaNacimiento;
	private JTextField txtDni;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JComboBoxBD cboPais;
	private JCheckBox chkEstado;

	private JButton btnIngresar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	
	
	// Es el id de la fila seleccionado
		int idSeleccionado = -1;
		private JTable table;

	public FrmCrudAlumno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento de Alumno");
		setBounds(100, 100, 993, 633);
		getContentPane().setLayout(null);
		
		JLabel lblMantenimientoDeAlumno = new JLabel("Mantenimiento de Alumno");
		lblMantenimientoDeAlumno.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoDeAlumno.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblMantenimientoDeAlumno.setBounds(10, 11, 864, 38);
		getContentPane().add(lblMantenimientoDeAlumno);
		
		JLabel lblNombres = new JLabel("Nombres :");
		lblNombres.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		lblNombres.setBounds(115, 79, 78, 25);
		getContentPane().add(lblNombres);
		
		JLabel lblApellidos = new JLabel("Apellidos :");
		lblApellidos.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		lblApellidos.setBounds(319, 79, 78, 25);
		getContentPane().add(lblApellidos);
		
		JLabel lblTelefono = new JLabel("Telefono :");
		lblTelefono.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		lblTelefono.setBounds(561, 79, 61, 25);
		getContentPane().add(lblTelefono);
		
		JLabel lblDni = new JLabel("Nro\u00B0 Dni :");
		lblDni.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		lblDni.setBounds(757, 79, 61, 25);
		getContentPane().add(lblDni);
		
		JLabel lblCorreo = new JLabel("Correo :");
		lblCorreo.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		lblCorreo.setBounds(185, 148, 67, 25);
		getContentPane().add(lblCorreo);
		
		JLabel lblFechaDeNacimiento = new JLabel("Fecha de Nacimiento :");
		lblFechaDeNacimiento.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		lblFechaDeNacimiento.setBounds(397, 148, 147, 25);
		getContentPane().add(lblFechaDeNacimiento);
		
		txtNombres = new JTextField();
		txtNombres.setBounds(63, 105, 168, 20);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(262, 105, 168, 20);
		getContentPane().add(txtApellidos);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(this);
		btnIngresar.setBounds(837, 166, 130, 30);
		getContentPane().add(btnIngresar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(837, 202, 130, 30);
		getContentPane().add(btnEliminar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(837, 236, 130, 30);
		getContentPane().add(btnActualizar);
		
		txtFechaNacimiento = new JTextField();
		txtFechaNacimiento.setColumns(10);
		txtFechaNacimiento.setBounds(367, 184, 191, 20);
		getContentPane().add(txtFechaNacimiento);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(716, 105, 136, 20);
		getContentPane().add(txtDni);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(510, 105, 168, 20);
		getContentPane().add(txtTelefono);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(77, 184, 241, 20);
		getContentPane().add(txtCorreo);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 277, 957, 315);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(this);
		table.setFont(new Font("Arial Narrow", Font.PLAIN, 15));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "Nombres", "Apellidos", "Telefono", "Dni", "Correo", "Fecha de Nacimiento", "Estado", "Pais"
			}
		));
		scrollPane.setViewportView(table);
		
		JLabel lblPais = new JLabel("Pais :");
		lblPais.setHorizontalAlignment(SwingConstants.CENTER);
		lblPais.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		lblPais.setBounds(609, 148, 147, 25);
		getContentPane().add(lblPais);
		
		cboPais = new JComboBoxBD(rb.getString("SQL_PAIS"));
		cboPais.setBounds(596, 183, 174, 22);
		getContentPane().add(cboPais);
		
		chkEstado = new JCheckBox("Activo");
		chkEstado.setBounds(447, 229, 97, 23);
		getContentPane().add(chkEstado);
		
		lista();
	}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}

	void limpiarCajasTexto() {
		txtNombres.setText("");
		txtApellidos.setText("");
		txtTelefono.setText("");
		txtDni.setText("");
		txtCorreo.setText("");
		txtFechaNacimiento.setText("");
		txtNombres.requestFocus();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizarJButton(e);
		}
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminarJButton(e);
		}
		if (e.getSource() == btnIngresar) {
			actionPerformedBtnIngresarJButton(e);
		}
	}
	protected void actionPerformedBtnIngresarJButton(ActionEvent e) {
		ingresa();
	}
	protected void actionPerformedBtnEliminarJButton(ActionEvent e) {
		elimina();
	}
	protected void actionPerformedBtnActualizarJButton(ActionEvent e) {
		actualiza();
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			mouseClickedTableJTable(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void mouseClickedTableJTable(MouseEvent e) {
		busca();
	}
	
	public void lista() {
		DefaultTableModel dt = (DefaultTableModel) table.getModel();
		dt.setRowCount(0);
		
		AlumnoModel model = new AlumnoModel();
		List<Alumno> lista = model.listaAlumno();
		
		for (Alumno x : lista) {
			Object[] f = {x.getIdAlumno(), x.getNombres(), x.getApellidos(), x.getTelefono(), x.getDni(), x.getCorreo(), x.getFechaNacimiento(),
							getDesEstado(x.getEstado()), x.getPais().getNombre()};
			dt.addRow(f);
			}
			
		}
	
	//Agregando nombre al Estado que es Bool
		public String getDesEstado(int x) {
			if(x ==0) 	return "Inactivo";
			else		return "Activo";
		}
		
	public void ingresa() {
		String nom = txtNombres.getText().trim();
		String ape = txtApellidos.getText().trim();
		String tel = txtTelefono.getText().trim();
		String dni = txtDni.getText().trim();
		String cor = txtCorreo.getText().trim();
		String fec = txtFechaNacimiento.getText().trim();
		int posPais = cboPais.getSelectedIndex();
		boolean est = chkEstado.isSelected();
		if (!nom.matches(Validaciones.TEXTO)) {
			mensaje("El Nombre es de 2 a 20 caracteres");
		}else if (!ape.matches(Validaciones.TEXTO)) {
			mensaje("El Apellido es de 2 a 20 caracteres");
		}else if (!tel.matches(Validaciones.FONO)) {
			mensaje("El Telefono debe empezar con '9'");
		}else if (!dni.matches(Validaciones.DNI)) {
			mensaje("El DNI debe ser de maximo 8 caracteres");
		}else if (!cor.matches(Validaciones.CORREO)) {
			mensaje("El Correo debe contener '@' y '.'");
		}else if (!fec.matches(Validaciones.FECHA)) {
			mensaje("la fecha tiene formato YYYY-MM-dd");
		}else if (posPais == 0) {
			mensaje("Selecciona un País");
		}else {
			String pais = cboPais.getSelectedItem().toString();
			String idPais = pais.split(":")[0];
			
			Pais objPais = new Pais();
			objPais.setIdPais(Integer.parseInt(idPais));
			
			Alumno objAlum= new Alumno();
			objAlum.setNombres(nom);
			objAlum.setApellidos(ape);
			objAlum.setTelefono(tel);
			objAlum.setDni(dni);
			objAlum.setCorreo(cor);
			objAlum.setFechaNacimiento(Date.valueOf(fec));
			objAlum.setPais(objPais);
			if(est)
				objAlum.setEstado(1);
			else 
				objAlum.setEstado(0);
		
			
			AlumnoModel model = new AlumnoModel();
			int salida = model.insertaAlumno(objAlum);
			if (salida > 0) {
				lista();
				mensaje("Se insertó correctamente");
				limpiarCajasTexto();
			}else {
				mensaje("Error en el Registro");
			}
			
		}
		
	}
	public void busca() {
		//OBTIENE LA FILA SELECCIONADA
		int fila = table.getSelectedRow();
		//OBTIENE CELDA POR CELDA
		int codigo = (Integer)table.getValueAt(fila, 0);
		String nombre = (String)table.getValueAt(fila, 1);
		String apellido = (String)table.getValueAt(fila, 2);
		String telefono = (String)table.getValueAt(fila, 3);
		String dni = (String)table.getValueAt(fila, 4);
		String correo = (String)table.getValueAt(fila, 5);
		Date fec = (Date)table.getValueAt(fila, 6);
	
	
		idSeleccionado = codigo;
		txtNombres.setText(nombre);
		txtApellidos.setText(apellido);
		txtTelefono.setText(telefono);
		txtDni.setText(dni);
		txtCorreo.setText(correo);
		txtFechaNacimiento.setText(String.valueOf(fec));
		
		
	}
	public void actualiza() {}
	public void elimina() {}
}
