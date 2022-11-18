package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidad.Pais;
import entidad.Proveedor;
import model.ProveedorModel;
import util.JComboBoxBD;
import util.Validaciones;

public class FrmCrudProveedor extends JInternalFrame implements MouseListener, ActionListener {

	private ResourceBundle rb = ResourceBundle.getBundle("combo");
	
	private static final long serialVersionUID = 1L;
	private JLabel lblMantenimientodeProveedor;
	private JLabel lblNombres;
	private JLabel lblApellidos;
	private JLabel lblDni;
	private JLabel lblPais;
	private JLabel lblDireccion;
	private JLabel lblTelefono;
	private JLabel lblCorreo;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtDni;
	private JTextField txtDireccion;
	private JTextField txtTelefono;
	private JTextField txtCorreo;
	private JComboBoxBD cboPais;
	private JCheckBox chkEstado;
	private JButton btnIngresar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private JScrollPane scrollPane;
	private JTable table;
	
	int idSeleccionado = -1;

	public FrmCrudProveedor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento de Proveedor");
		setBounds(100, 100, 988, 600);
		getContentPane().setLayout(null);
		
		lblMantenimientodeProveedor = new JLabel("Mantenimiento de Proveedor");
		lblMantenimientodeProveedor.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblMantenimientodeProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientodeProveedor.setBounds(251, 29, 426, 49);
		getContentPane().add(lblMantenimientodeProveedor);
		
		lblNombres = new JLabel("Nombres:");
		lblNombres.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNombres.setBounds(83, 118, 71, 14);
		getContentPane().add(lblNombres);
		
		lblApellidos = new JLabel("Apellidos:");
		lblApellidos.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblApellidos.setBounds(83, 154, 71, 14);
		getContentPane().add(lblApellidos);
		
		lblDni = new JLabel("DNI:");
		lblDni.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDni.setBounds(83, 193, 71, 14);
		getContentPane().add(lblDni);
		
		lblPais = new JLabel("Pa\u00eds:");
		lblPais.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPais.setBounds(83, 233, 71, 14);
		getContentPane().add(lblPais);
		
		lblDireccion = new JLabel("Direcci\u00f3n:");
		lblDireccion.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblDireccion.setBounds(469, 118, 71, 14);
		getContentPane().add(lblDireccion);
		
		lblTelefono = new JLabel("Tel\u00e9fono:");
		lblTelefono.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblTelefono.setBounds(469, 154, 71, 14);
		getContentPane().add(lblTelefono);
		
		lblCorreo = new JLabel("Correo:");
		lblCorreo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblCorreo.setBounds(469, 193, 71, 14);
		getContentPane().add(lblCorreo);
		
		txtNombres = new JTextField();
		txtNombres.setBounds(148, 115, 209, 20);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setColumns(10);
		txtApellidos.setBounds(148, 151, 209, 20);
		getContentPane().add(txtApellidos);
		
		txtDni = new JTextField();
		txtDni.setColumns(10);
		txtDni.setBounds(148, 190, 209, 20);
		getContentPane().add(txtDni);
		
		txtDireccion = new JTextField();
		txtDireccion.setColumns(10);
		txtDireccion.setBounds(532, 115, 209, 20);
		getContentPane().add(txtDireccion);
		
		txtTelefono = new JTextField();
		txtTelefono.setColumns(10);
		txtTelefono.setBounds(532, 151, 209, 20);
		getContentPane().add(txtTelefono);
		
		txtCorreo = new JTextField();
		txtCorreo.setColumns(10);
		txtCorreo.setBounds(532, 190, 209, 20);
		getContentPane().add(txtCorreo);
		
		cboPais = new JComboBoxBD(rb.getString("SQL_PAIS"));
		cboPais.setBounds(148, 229, 209, 22);
		getContentPane().add(cboPais);
		
		chkEstado = new JCheckBox("Activo");
		chkEstado.setBounds(532, 229, 97, 23);
		getContentPane().add(chkEstado);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(this);
		btnIngresar.setBounds(837, 114, 125, 23);
		getContentPane().add(btnIngresar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(837, 150, 125, 23);
		getContentPane().add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(837, 189, 125, 23);
		getContentPane().add(btnEliminar);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 280, 952, 279);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00f3digo", "Nombres", "Apellidos", "DNI", "Direcci\u00f3n", "T\u00e9lefono", "Correo", "Estado","ID Pais", "Pa\u00eds"
			}
		));
		table.addMouseListener(this);
		scrollPane.setViewportView(table);
		
		//Estilos para la tabla
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(200);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		table.getColumnModel().getColumn(9).setPreferredWidth(150);
		scrollPane.setViewportView(table);
		
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		
		
		table.getTableHeader().setResizingAllowed(false);
		
		
		table.getTableHeader().setReorderingAllowed(false);
		
		
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
				
		
		table.getColumnModel().getColumn(8).setMinWidth(0);
		table.getColumnModel().getColumn(8).setMaxWidth(0);
		
		table.setSelectionBackground(Color.RED);
		

		lista();

	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			mouseClickedTable(e);
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
	protected void mouseClickedTable(MouseEvent e) {
		busca();
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
		if (e.getSource() == btnIngresar) {
			actionPerformedBtnIngresar(e);
		}
	}
	protected void actionPerformedBtnIngresar(ActionEvent e) {
		ingresa();
	}
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		actualiza();
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		elimina();
	}
	
	public void lista() {
		DefaultTableModel dt = (DefaultTableModel) table.getModel();
		dt.setRowCount(0);
		
		ProveedorModel model = new ProveedorModel();
		List<Proveedor> lista = model.listaProveedor();
		
		for (Proveedor x : lista) {
			Object[] f = {x.getIdProveedor(), x.getNombres(), x.getApellidos(), x.getDni(), x.getDireccion(), x.getTelefono(),x.getCorreo(),
							getDesEstado(x.getEstado()), x.getPais().getIdPais(), x.getPais().getNombre()};
			dt.addRow(f);
			}
		}
	
	public String getDesEstado(int x) {
		if(x ==0) 	return "Inactivo";
		else		return "Activo";
	}
	
	public void ingresa() {
		String nom = txtNombres.getText().trim();
		String ape = txtApellidos.getText().trim();
		String dni = txtDni.getText().trim();
		String dir = txtDireccion.getText().trim();
		String tel = txtTelefono.getText().trim();
		String cor = txtCorreo.getText().trim();
		int posPais = cboPais.getSelectedIndex();
		boolean est = chkEstado.isSelected();
		if (!nom.matches(Validaciones.TEXTO)) {
			mensaje("El Nombre es de 2 a 20 caracteres");
		}else if (!ape.matches(Validaciones.TEXTO)) {
			mensaje("El Apellido es de 2 a 20 caracteres");
		}else if (!dni.matches(Validaciones.DNI)) {
			mensaje("El DNI debe ser de maximo 8 caracteres");
		}else if (!dir.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("La Direcci\u00f3n es de 2 a 20 caracteres");
		}else if (!tel.matches(Validaciones.FONO)) {
			mensaje("El T\u00e9lefono debe empezar con '9'");
		}else if (!cor.matches(Validaciones.CORREO)) {
			mensaje("El Correo debe contener '@' y '.'");
		}else if (posPais == 0) {
			mensaje("Selecciona un Pa\u00eds");
		}else {
			String pais = cboPais.getSelectedItem().toString();
			String idPais = pais.split(":")[0];
			
			Pais objPais = new Pais();
			objPais.setIdPais(Integer.parseInt(idPais));
			
			Proveedor objProv = new Proveedor();
			objProv.setNombres(nom);
			objProv.setApellidos(ape);
			objProv.setDni(dni);
			objProv.setDireccion(dir);
			objProv.setTelefono(tel);
			objProv.setCorreo(cor);
			objProv.setPais(objPais);
			if(est)
				objProv.setEstado(1);
			else 
				objProv.setEstado(0);
		
			
			ProveedorModel model = new ProveedorModel();
			int salida = model.insertaProveedor(objProv);
			if (salida > 0) {
				mensaje("Se insert\u00f3 correctamente");
				lista();
				limpiarCajasTexto();
			}else {
				mensaje("Error en el Registro");
			}
			
		}
	}
	
	public void busca() {
		
		int fila = table.getSelectedRow();
		idSeleccionado =  (Integer)table.getValueAt(fila, 0);
		String nombre =   (String)table.getValueAt(fila, 1);
		String apellido = (String)table.getValueAt(fila, 2);
		String dni = 	  (String)table.getValueAt(fila, 3);
		String direccion =(String)table.getValueAt(fila, 4);
		String telefono = (String)table.getValueAt(fila, 5);	
		String correo =   (String)table.getValueAt(fila, 6);
		String estado =   (String)table.getValueAt(fila, 7);
		Integer idPais =  (Integer)table.getValueAt(fila, 8);
		String nomPais =  (String)table.getValueAt(fila, 9);
		
		txtNombres.setText(nombre);
		txtApellidos.setText(apellido);
		txtDni.setText(dni);
		txtDireccion.setText(direccion);
		txtTelefono.setText(telefono);
		txtCorreo.setText(correo);
		chkEstado.setSelected(getBooleanEstado(estado));
		cboPais.setSelectedItem(idPais + ": " + nomPais);

	}
	
	private boolean getBooleanEstado(String estado) {
		return estado == "Activo"? true:false;
	}
	
	public void actualiza() {
		
		String nom = txtNombres.getText().trim();
		String ape = txtApellidos.getText().trim();
		String dni = txtDni.getText().trim();
		String dir = txtDireccion.getText().trim();
		String tel = txtTelefono.getText().trim();
		String cor = txtCorreo.getText().trim();
		int posPais = cboPais.getSelectedIndex();
		boolean est = chkEstado.isSelected();
		
		if(idSeleccionado == -1) {
			mensaje("Seleccione una Fila");
		}else if (!nom.matches(Validaciones.TEXTO)) {
			mensaje("El Nombre es de 2 a 20 caracteres");
		}else if (!ape.matches(Validaciones.TEXTO)) {
			mensaje("El Apellido es de 2 a 20 caracteres");
		}else if (!dni.matches(Validaciones.DNI)) {
			mensaje("El DNI debe ser de maximo 8 caracteres");
		}else if (!dir.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("La Direcci\u00f3n es de 2 a 20 caracteres");
		}else if (!tel.matches(Validaciones.FONO)) {
			mensaje("El T\u00e9lefono debe empezar con '9'");
		}else if (!cor.matches(Validaciones.CORREO)) {
			mensaje("El Correo debe contener '@' y '.'");
		}else if (posPais == 0) {
			mensaje("Selecciona un Pa\u00eds");
		}else {
			String pais = cboPais.getSelectedItem().toString();
			String idPais = pais.split(":")[0];
			
			Pais objPais = new Pais();
			objPais.setIdPais(Integer.parseInt(idPais));
			
			Proveedor objProv = new Proveedor();
			objProv.setIdProveedor(idSeleccionado);
			objProv.setNombres(nom);
			objProv.setApellidos(ape);
			objProv.setDni(dni);
			objProv.setDireccion(dir);
			objProv.setTelefono(tel);
			objProv.setCorreo(cor);
			objProv.setPais(objPais);
			if(est)
				objProv.setEstado(1);
			else 
				objProv.setEstado(0);
		
			
			ProveedorModel model = new ProveedorModel();
			int salida = model.actualizaProveedor(objProv);
			if (salida > 0) {
				lista();
				idSeleccionado = -1;
				mensaje("Se actualiz\u00f3 correctamente");
				limpiarCajasTexto();
				
			}else {
				mensaje("Error al actualizar");
			}
			
		}
 	}
	
	public void elimina() {
		
		ProveedorModel model = new ProveedorModel();
		if(idSeleccionado == -1) {
			mensaje ("Seleccione una fila");
		}else {
			int salida = model.eliminaProveedor(idSeleccionado);
			
			if (salida > 0) {
				lista();
				idSeleccionado = -1;
				mensaje("Se elimin\u00f3 correctamente");
				limpiarCajasTexto();
				
			}else {
				mensaje("Error en el Eliminaci\u00f3n");
			}
		}
	 }
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}

	void limpiarCajasTexto() {
		
		txtNombres.setText("");
		txtApellidos.setText("");
		txtDni.setText("");
		txtDireccion.setText("");
		txtTelefono.setText("");
		txtCorreo.setText("");
		cboPais.setSelectedIndex(0);
		chkEstado.setSelected(false);
		txtNombres.requestFocus();
	}
}
