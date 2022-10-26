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
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidad.Alumno;
import entidad.Pais;
import entidad.Tesis;
import model.AlumnoModel;
import model.TesisModel;
import util.JComboBoxBD;
import util.Validaciones;

public class FrmCrudTesis extends JInternalFrame implements ActionListener, MouseListener {
	
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	private static final long serialVersionUID = 1L;
	private JTextField txttitulo;
	private JTextField txttem;
	private JTextField txtfeccre;
	private JComboBoxBD cboAlumno;
	private JCheckBox chkEstado;

	private JButton btnIngresar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	
	
	// Es el id de la fila seleccionado
		int idSeleccionado = -1;
		private JTable table;

	public FrmCrudTesis() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento de Tesis");
		setBounds(100, 100, 949, 607);
		getContentPane().setLayout(null);
		
		JLabel lblMantenimientoDeTesis = new JLabel("Mantenimiento de Tesis");
		lblMantenimientoDeTesis.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoDeTesis.setFont(new Font("Tahoma", Font.BOLD, 19));
		lblMantenimientoDeTesis.setBounds(10, 11, 906, 38);
		getContentPane().add(lblMantenimientoDeTesis);
		
		JLabel lbltit = new JLabel("T\u00EDtulo");
		lbltit.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		lbltit.setBounds(32, 80, 78, 25);
		getContentPane().add(lbltit);
		
		JLabel lbltem = new JLabel("Tema");
		lbltem.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		lbltem.setBounds(32, 120, 78, 25);
		getContentPane().add(lbltem);
		
		JLabel lblfeccre = new JLabel("Fecha de Creaci\u00F3n");
		lblfeccre.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		lblfeccre.setBounds(439, 80, 147, 25);
		getContentPane().add(lblfeccre);
		
		txttitulo = new JTextField();
		txttitulo.setBounds(120, 83, 241, 20);
		getContentPane().add(txttitulo);
		txttitulo.setColumns(10);
		
		txttem = new JTextField();
		txttem.setColumns(10);
		txttem.setBounds(120, 123, 241, 20);
		getContentPane().add(txttem);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(this);
		btnIngresar.setBounds(89, 223, 146, 30);
		getContentPane().add(btnIngresar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setBounds(358, 223, 146, 30);
		getContentPane().add(btnEliminar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setBounds(693, 223, 146, 30);
		getContentPane().add(btnActualizar);
		
		txtfeccre = new JTextField();
		txtfeccre.setColumns(10);
		txtfeccre.setBounds(579, 83, 191, 20);
		getContentPane().add(txtfeccre);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 277, 906, 289);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "T\u00EDtulo", "Tema", "Fecha Creacion", "Fecha Registro", "Estado", "Alumno"
			}
		));
		table.addMouseListener(this);
		scrollPane.setViewportView(table);
		
		JLabel lblalumno = new JLabel("Alumno");
		lblalumno.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		lblalumno.setBounds(439, 120, 78, 25);
		getContentPane().add(lblalumno);
		
		cboAlumno = new JComboBoxBD(rb.getString("SQL_ALUMNO1"));
		cboAlumno.setBounds(579, 122, 206, 22);
		getContentPane().add(cboAlumno);
		
		chkEstado = new JCheckBox("Activo");
		chkEstado.setBounds(579, 158, 97, 23);
		getContentPane().add(chkEstado);
		
		//alineacion
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		JLabel lblestado = new JLabel("Estado");
		lblestado.setFont(new Font("Arial Narrow", Font.BOLD, 15));
		lblestado.setBounds(439, 162, 46, 14);
		getContentPane().add(lblestado);
		
		lista();
	}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}

	void limpiarCajasTexto() {
		
		txttitulo.setText("");
		txttem.setText("");
		txtfeccre.setText("");
		cboAlumno.setSelectedIndex(0);
		chkEstado.setSelected(false);
		txttitulo.requestFocus();
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
			do_table_mouseClicked(e);
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
	
	
	public void lista() {
		 DefaultTableModel dt = (DefaultTableModel) table.getModel();
		dt.setRowCount(0);
		
		TesisModel model = new TesisModel();
		List<Tesis> lista = model.listaTesis();
		
		for (Tesis x : lista) {
			Object[] f = {x.getIdTesis(), x.getTitulo(), x.getTema(), x.getFechaCreacion(), getDesEstado(x.getEstado()), x.getAlumno().getIdAlumno()};
			dt.addRow(f);
			}
		}
	
		public String getDesEstado(int x) {
			if(x ==0) 	return "Inactivo";
			else		return "Activo";
		}
		
		public void ingresa() {
			String tit = txttitulo.getText().trim();
			String tem = txttem.getText().trim();
			String feccre = txtfeccre.getText().trim();
			int posAlumno = cboAlumno.getSelectedIndex();
			boolean est = chkEstado.isSelected();
			if (!tit.matches(Validaciones.TEXTO)) {
				mensaje("El titulo es de 2 a 20 caracteres");
			}else if (!tem.matches(Validaciones.TEXTO)) {
				mensaje("El tema es de 2 a 20 caracteres");
			}else if (!feccre.matches(Validaciones.FECHA)) {
				mensaje("la fecha tiene formato YYYY-MM-dd");
			}else if (posAlumno == 0) {
				mensaje("Selecciona un Alumno");
			}else {
				String Alumno = cboAlumno.getSelectedItem().toString();
				String idAlumno = Alumno.split(":")[0];
				
				Alumno objPais = new Alumno();
				objPais.setIdAlumno(Integer.parseInt(idAlumno));
				
				Tesis objTesis= new Tesis();
				objTesis.setTitulo(tit);
				objTesis.setTema(tem);
				objTesis.setFechaCreacion(Date.valueOf(feccre));
				objTesis.setAlumno(objPais);
				if(est)
					objTesis.setEstado(1);
				else 
					objTesis.setEstado(0);
				TesisModel model = new TesisModel();
				int salida = model.insertaTesis(objTesis);
				if (salida > 0) {
					mensaje("Se insertó correctamente");
					lista();
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
		idSeleccionado = 	(Integer)table.getValueAt(fila, 0);
		String titulo = 	(String)table.getValueAt(fila, 1);
		String tema = 	(String)table.getValueAt(fila, 2);
		String fechaCreacion = 	(String)table.getValueAt(fila, 3);
		String estado =  	(String)table.getValueAt(fila, 4);
		Integer idAlumno = 	(Integer)table.getValueAt(fila, 5);
		String nomAlumno = 	(String)table.getValueAt(fila, 6);
		
		/*System.out.println(idSeleccionado 	+ " - " + titulo 
											+ " - " + tema
											+ " - " + fechaCreacion
											+ " - " + estado 
											+ " - " + idAlumno
											+ " - " + nomAlumno);*/
		
	
		
		txttitulo.setText(titulo);
		txttem.setText(tema);
		txtfeccre.setText(String.valueOf(fechaCreacion));
		chkEstado.setSelected(getBooleanEstado(estado));
		cboAlumno.setSelectedItem(idAlumno + ": " + nomAlumno);

	}
	public void actualiza() {
		
		String nom = txttitulo.getText().trim();
		String ape = txttem.getText().trim();
		String fec = txtfeccre.getText().trim();
		int posPais = cboAlumno.getSelectedIndex();
		boolean est = chkEstado.isSelected();
		
		if(idSeleccionado == -1) {
			mensaje("Seleccione una Fila");
		}else if (!nom.matches(Validaciones.TEXTO)) {
			mensaje("El Nombre es de 2 a 20 caracteres");
		}else if (!ape.matches(Validaciones.TEXTO)) {
			mensaje("El Correo debe contener '@' y '.'");
		}else if (!fec.matches(Validaciones.FECHA)) {
			mensaje("la fecha tiene formato YYYY-MM-dd");
		}else if (posPais == 0) {
			mensaje("Selecciona un País");
		}else {
			String pais = cboAlumno.getSelectedItem().toString();
			String idPais = pais.split(":")[0];
			
			Pais objPais = new Pais();
			objPais.setIdPais(Integer.parseInt(idPais));
			
			Alumno objAlum= new Alumno();
			objAlum.setIdAlumno(idSeleccionado);
			objAlum.setNombres(nom);
			objAlum.setApellidos(ape);
			objAlum.setFechaNacimiento(Date.valueOf(fec));
			objAlum.setPais(objPais);
			if(est)
				objAlum.setEstado(1);
			else 
				objAlum.setEstado(0);
		
			
			AlumnoModel model = new AlumnoModel();
			int salida = model.actualizaAlumno(objAlum);
			if (salida > 0) {
				lista();
				idSeleccionado = -1;
				mensaje("Se actualizo correctamente");
				limpiarCajasTexto();
				
			}else {
				mensaje("Error al actualizar");
			}
			
		}
		
	}
public void elimina() {
		
		TesisModel model = new TesisModel();
		if(idSeleccionado == -1) {
			mensaje ("Seleccione una fila");
		}else {
			int salida = model.eliminaTesis(idSeleccionado);
			
			if (salida > 0) {
				lista();
				idSeleccionado = -1;
				mensaje("Se elimino correctamente");
				limpiarCajasTexto();
				
			}else {
				mensaje("Error en el Eliminación");
			}
		}
	}
	
	private boolean getBooleanEstado(String estado) {
		return estado == "Activo"? true:false;
	}
	protected void do_table_mouseClicked(MouseEvent e) {
		busca();
	}
}