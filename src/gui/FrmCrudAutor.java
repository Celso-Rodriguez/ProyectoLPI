package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Color;
import java.awt.Font;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.SwingConstants;

import util.JComboBoxBD;
import entidad.Autor;
import entidad.Grado;
import model.AutorModel;
import util.Validaciones;

import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JCheckBox;
import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.sql.Date;
import java.awt.event.MouseEvent;

public class FrmCrudAutor extends JInternalFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtNombres;
	private JTextField txtApellidos;
	private JTextField txtFecNac;
	private JTextField txtTelefono;
	private JComboBoxBD cboGrado;
	private JCheckBox chkEstado;
	private JTable table;
	private JButton btnRegistrar;
	private JButton btnActualizar;
	private JButton btnEliminar;
	private ResourceBundle rb = ResourceBundle.getBundle("combo");
	// -1 indica que no se ha selecionado nada en la grilla o Jtable
	int idSeleccionado = -1;
	int hoveredRow = -1, hoveredColumn = -1;

	public FrmCrudAutor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento de Autor");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("Mantenimiento Autor");
		lblTitulo.setBounds(10, 11, 874, 69);
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 35));
		getContentPane().add(lblTitulo);
		
		JLabel lblNombres = new JLabel("Nombres :");
		lblNombres.setBounds(10, 108, 140, 25);
		lblNombres.setHorizontalAlignment(SwingConstants.RIGHT);
		lblNombres.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lblNombres);
		
		JLabel lblApellidos = new JLabel("Apellidos :");
		lblApellidos.setBounds(10, 144, 140, 25);
		lblApellidos.setHorizontalAlignment(SwingConstants.RIGHT);
		lblApellidos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lblApellidos);
		
		JLabel lblFecNac = new JLabel("Fecha de Nacimiento :");
		lblFecNac.setBounds(60, 180, 180, 25);
		lblFecNac.setHorizontalAlignment(SwingConstants.RIGHT);
		lblFecNac.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lblFecNac);
		
		JLabel lblTelefono = new JLabel("Teléfono :");
		lblTelefono.setBounds(420, 108, 100, 25);
		lblTelefono.setHorizontalAlignment(SwingConstants.RIGHT);
		lblTelefono.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lblTelefono);
		
		JLabel lblGrado = new JLabel("Grado :");
		lblGrado.setBounds(420, 144, 100, 25);
		lblGrado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGrado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		getContentPane().add(lblGrado);
		
		txtNombres = new JTextField();
		txtNombres.setBounds(160, 109, 250, 24);
		getContentPane().add(txtNombres);
		txtNombres.setColumns(10);
		
		txtApellidos = new JTextField();
		txtApellidos.setBounds(160, 145, 250, 24);
		txtApellidos.setColumns(10);
		getContentPane().add(txtApellidos);
		
		txtFecNac = new JTextField();
		txtFecNac.setBounds(250, 181, 100, 24);
		txtFecNac.setColumns(10);
		getContentPane().add(txtFecNac);
		
		txtTelefono = new JTextField();
		txtTelefono.setBounds(530, 105, 125, 24);
		txtTelefono.setColumns(10);
		getContentPane().add(txtTelefono);
		
		cboGrado = new JComboBoxBD(rb.getString("SQL_GRADO"));
		cboGrado.setBounds(530, 145, 125, 24);
		cboGrado.setFont(new Font("Arial", Font.ITALIC, 14));
		getContentPane().add(cboGrado);
		
		chkEstado = new JCheckBox("Activo");
		chkEstado.setFont(new Font("Tahoma", Font.BOLD, 14));
		chkEstado.setSelected(true);
		chkEstado.setBounds(356, 181, 80, 24);
		getContentPane().add(chkEstado);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 243, 864, 316);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Nombres", "Apellidos", "Fecha de Nacimiento", "Tel\u00E9fono", "Estado", "IdGrado", "Grado"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(6).setPreferredWidth(100);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
		scrollPane.setViewportView(table);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnRegistrar.setBounds(711, 93, 120, 30);
		getContentPane().add(btnRegistrar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnActualizar.setBounds(711, 134, 120, 30);
		getContentPane().add(btnActualizar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEliminar.setBounds(711, 175, 120, 30);
		getContentPane().add(btnEliminar);
		scrollPane.setViewportView(table);

		//color de la fila seleccionada
		table.setSelectionBackground(Color.BLUE);
		
		//alineacion
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
						
		//desabilita el cambio de tamaño
		table.getTableHeader().setResizingAllowed(false);
				
		//desabilita mover las columnas
		table.getTableHeader().setReorderingAllowed(false);
						
		//selecciona una sola fila
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
				
		//Desahilitar la edicion en las celdas
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
		
		//Ocultar fila 6
	    table.getColumnModel().getColumn(6).setMinWidth(0);
	    table.getColumnModel().getColumn(6).setMaxWidth(0);
	    table.getColumnModel().getColumn(6).setWidth(0);
				
		lista();

	}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}

	void limpiarCajasTexto() {
		txtNombres.setText("");
		txtApellidos.setText("");
		txtFecNac.setText("");
		txtTelefono.setText("");
		cboGrado.setSelectedIndex(0);
		chkEstado.setSelected(true);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminar(e);
		}
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizar(e);
		}
		if (e.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrar(e);
		}
	}
	protected void actionPerformedBtnRegistrar(ActionEvent e) {
		registra();
	}
	protected void actionPerformedBtnActualizar(ActionEvent e) {
		actualiza();
	}
	protected void actionPerformedBtnEliminar(ActionEvent e) {
		elimina();
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
	
	private void lista() {
		DefaultTableModel dt = (DefaultTableModel) table.getModel();
		dt.setRowCount(0);
		
		AutorModel model = new AutorModel();
		List<Autor> lista = model.listaAutor();		
		for (Autor x : lista) {
			Object[] f = {x.getIdAutor(), x.getNombres(), x.getApellidos(), x.getFechaNacimiento(), x.getTelefono(),
						 getDesEstado(x.getEstado()), x.getGrado().getIdGrado(), x.getGrado().getDescripcion()};
			dt.addRow(f);
		}

	}
	
	public String getDesEstado(int x) {
		if(x == 0) return "Inactivo";
		else return "Activo";
	}
	
	public void registra() {
		String nom = txtNombres.getText().trim();
		String ape = txtApellidos.getText().trim();
		String fec = txtFecNac.getText().trim();
		String tel = txtTelefono.getText().trim();
		int index = cboGrado.getSelectedIndex();
		
		if(!nom.matches(Validaciones.TEXTO)) {
			mensaje("El nombre es de 2 a 20 caracteres");
		}else if(!ape.matches(Validaciones.TEXTO)) {
			mensaje("El apellido es de 2 a 20 caracteres");
		}else if(!fec.matches(Validaciones.FECHA)) {
			mensaje("La fecha debe tener formato YYY-MM-dd");
		}else if(!tel.matches(Validaciones.FONO)) {
			mensaje("El teléfono es de 9 d\u00edgitos y empieza en 9");
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
				lista();
				mensaje("Se registr\u00f3 correctamente");
			}else {
				mensaje("Error en el registro");
			}
		}
	}
	public void actualiza() {
		String nom = txtNombres.getText().trim();
		String ape = txtApellidos.getText().trim();
		String fec = txtFecNac.getText().trim();
		String tel = txtTelefono.getText().trim();
		int iGrado = cboGrado.getSelectedIndex();
		boolean est = chkEstado.isSelected();
		
		if (idSeleccionado == -1) {
			mensaje("Seleccione una fila");
		}else if (!nom.matches(Validaciones.TEXTO)) {
			mensaje("El nombre es de 2 a 20 caracteres");
		}else if (!ape.matches(Validaciones.TEXTO)) {
			mensaje("El apellido es de 2 a 20 caracteres");
		}else if (!fec.matches(Validaciones.FECHA)) {
			mensaje("la fecha tiene formato YYYY-MM-dd");
		}else if (!tel.matches(Validaciones.FONO)) {
			mensaje("El telefono debe tener 9 digitos");
		}else if(iGrado == 0) {
			mensaje("Selecciona un Grado");
		}else {
			String grado = cboGrado.getSelectedItem().toString();
			String idGrado = grado.split(":")[0];
			
			Grado objGrado = new Grado();
			objGrado.setIdGrado(Integer.parseInt(idGrado));
			
			Autor objAutor = new Autor();
			objAutor.setIdAutor(idSeleccionado);
			objAutor.setNombres(nom);
			objAutor.setApellidos(ape);
			objAutor.setFechaNacimiento(Date.valueOf(fec));
			objAutor.setTelefono(Integer.parseInt(tel));
			objAutor.setGrado(objGrado);
			if (est) 
				objAutor.setEstado(1);
			else 
				objAutor.setEstado(0);
			
			AutorModel model = new AutorModel();
			int salida = model.actualizaAutor(objAutor);
			if (salida > 0) {
				lista();
				idSeleccionado = -1;
				mensaje("Se actualizo correctamente");
				limpiarCajasTexto();
			}else {
				mensaje("Error al Actualizar");
			}
		}
		
	}
	public void elimina() {
		if (idSeleccionado == -1) {
			mensaje("Seleccione una fila");
		}else {
			AutorModel model = new AutorModel();
			int salida = model.eliminaAutor(idSeleccionado);
			if (salida > 0) {
				lista();
				limpiarCajasTexto();
				idSeleccionado = -1;
				mensaje("Se elimin\u00f3 correctamente");
			}else {
				mensaje("Error en la eliminaci\u00f3n");
			}
		}
	}
	public void busca() {
		int posRow = table.getSelectedRow();
		
		idSeleccionado = (Integer)table.getValueAt(posRow,0);
		String nombres = (String)table.getValueAt(posRow, 1);
		String apellidos = (String)table.getValueAt(posRow, 2);
		Date fecNac = (Date)table.getValueAt(posRow, 3);
		Integer telefono = (Integer)table.getValueAt(posRow, 4);
		String estado = (String)table.getValueAt(posRow, 5);
		Integer idGrado = (Integer)table.getValueAt(posRow, 6);
		String nomGrado = (String)table.getValueAt(posRow, 7);
		
		txtNombres.setText(nombres);
		txtApellidos.setText(apellidos);
		txtFecNac.setText(String.valueOf(fecNac));
		txtTelefono.setText(String.valueOf(telefono));
		chkEstado.setSelected(estado.equals("Activo")?true : false);
		cboGrado.setSelectedItem(idGrado + ": " + nomGrado);
		
	}
}
