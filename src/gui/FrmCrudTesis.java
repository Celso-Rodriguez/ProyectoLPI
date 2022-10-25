package gui;

import java.awt.Color;
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
import entidad.Tesis;
import model.TesisModel;
import util.JComboBoxBD;
import util.Validaciones;

public class FrmCrudTesis extends JInternalFrame implements ActionListener, MouseListener {
	
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	private static final long serialVersionUID = 1L;
	private JTextField txttitulo;
	private JTextField txttema;
	private JTextField txtfeccre;
	private JButton btnIngresar;
	private JLabel lblalumno;
	private JComboBoxBD cboalumno;
	private JButton btnEliminar;
	private JTable table;
	private JButton btnActualizar;
	private JCheckBox chkEstado;
	
	int idSeleccionado = -1;

	public FrmCrudTesis() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento de Tesis");
		setBounds(100, 100, 1000, 671);
		getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("Mantenimiento de Tesis");
		lblTitulo.setBounds(10, 11, 964, 59);
		lblTitulo.setForeground(Color.DARK_GRAY);
		lblTitulo.setBackground(Color.WHITE);
		lblTitulo.setFont(new Font("Yu Gothic Medium", Font.ITALIC, 30));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblTitulo);
		
		JLabel lbltitulo = new JLabel("T\u00EDtulo");
		lbltitulo.setBounds(10, 99, 104, 14);
		getContentPane().add(lbltitulo);
		
		JLabel lbltema = new JLabel("Tema");
		lbltema.setBounds(10, 141, 104, 14);
		getContentPane().add(lbltema);
		
		JLabel lblfechacre = new JLabel("Fecha creaci\u00F3n");
		lblfechacre.setBounds(10, 183, 104, 14);
		getContentPane().add(lblfechacre);
		
		txttitulo = new JTextField();
		txttitulo.setBounds(137, 96, 254, 20);
		getContentPane().add(txttitulo);
		txttitulo.setColumns(10);
		
		txttema = new JTextField();
		txttema.setBounds(137, 138, 254, 20);
		getContentPane().add(txttema);
		txttema.setColumns(10);
		
		txtfeccre = new JTextField();
		txtfeccre.setBounds(137, 180, 254, 20);
		getContentPane().add(txtfeccre);
		txtfeccre.setColumns(10);
		
		JCheckBox chkestado = new JCheckBox("Activo");
		chkestado.setBounds(529, 137, 97, 23);
		getContentPane().add(chkestado);
		
		lblalumno = new JLabel("Alumno");
		lblalumno.setBounds(529, 99, 46, 14);
		getContentPane().add(lblalumno);
		
		cboalumno = new JComboBoxBD(rb.getString("SQL_Alumno"));
		cboalumno.setBounds(637, 95, 221, 22);
		getContentPane().add(cboalumno);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.setBounds(108, 221, 182, 35);
		btnIngresar.addActionListener(this);
		getContentPane().add(btnIngresar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.setBounds(399, 221, 176, 35);
		btnEliminar.addActionListener(this);
		getContentPane().add(btnEliminar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.setBounds(691, 221, 167, 35);
		btnActualizar.addActionListener(this);
		getContentPane().add(btnActualizar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 278, 964, 352);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Codigo", "T\u00EDtulo", "Tema", "Fecha Creaci\u00F3n", "Estado", "Alumno"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(8);
		table.getColumnModel().getColumn(1).setPreferredWidth(70);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(50);
		table.getColumnModel().getColumn(4).setPreferredWidth(50);
		table.getColumnModel().getColumn(5).setPreferredWidth(60);
		table.getColumnModel().getColumn(6).setPreferredWidth(80);
		scrollPane.setColumnHeaderView(table);

	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnActualizar) {
			do_btnActualizar_actionPerformed(e);
		}
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
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	protected void mouseClickedTableJTable(MouseEvent e) {
		busca();
	}
	protected void do_btnEliminar_actionPerformed(ActionEvent e) {
	}
	protected void do_btnActualizar_actionPerformed(ActionEvent e) {
	}
	
	
	public void lista() {
		DefaultTableModel dt = (DefaultTableModel) table.getModel();
		dt.setRowCount(0);
		
		TesisModel model = new TesisModel();
		List<Tesis> lista = model.listaTesis();
		
		for (Tesis x : lista) {
			Object[] f = {x.getIdTesis(), x.getTitulo(), x.getTema(), x.getFechaCreacion(), x.getFechaRegistro(), x.getEstado(), x.getAlumno()};
			dt.addRow(f);
			}
			
		}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
	void limpiarCajasTexto() {
		
		txttitulo.setText("");
		txttema.setText("");
		txtfeccre.setText("");
		cboalumno.setSelectedIndex(0);
		chkEstado.setSelected(false);
		txttitulo.requestFocus();
	}
	
	public void ingresa() {
		String tit = txttitulo.getText().trim();
		String tem = txttema.getText().trim();
		String feccre = txtfeccre.getText().trim();
		int posAlumno = cboalumno.getSelectedIndex();
		boolean est = chkEstado.isSelected();
		if (!tit.matches(Validaciones.TEXTO)) {
			mensaje("El Nombre es de 2 a 20 caracteres");
		}else if (!tem.matches(Validaciones.TEXTO)) {
			mensaje("El Apellido es de 2 a 20 caracteres");
		}else if (!feccre.matches(Validaciones.FONO)) {
			mensaje("El Telefono debe empezar con '9'");
		}else if (posAlumno == 0) {
			mensaje("Selecciona un Alumno");
		}else {
			String Alumno = cboalumno.getSelectedItem().toString();
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
		txttema.setText(tema);
		txtfeccre.setText(String.valueOf(fechaCreacion));
		chkEstado.setSelected(getBooleanEstado(estado));
		cboalumno.setSelectedItem(idAlumno + ": " + nomAlumno);

	}
public void actualiza() {
		String tit = txttitulo.getText().trim();
		String tem = txttema.getText().trim();
		String feccre = txtfeccre.getText().trim();
		int posAlumno = cboalumno.getSelectedIndex();
		boolean est = chkEstado.isSelected();
		
		if(idSeleccionado == -1) {
			mensaje("Seleccione una Fila");
		}else if (!tit.matches(Validaciones.TEXTO)) {
			mensaje("El titulo es de 2 a 20 caracteres");
		}else if (!tem.matches(Validaciones.TEXTO)) {
			mensaje("El tema es de 2 a 20 caracteres");
		}else if (!feccre.matches(Validaciones.FECHA)) {
			mensaje("la fecha tiene formato YYYY-MM-dd");
		}else if (posAlumno == 0) {
			mensaje("Selecciona un País");
		}else {
			String alumno = cboalumno.getSelectedItem().toString();
			String idalumno = alumno.split(":")[0];
			
			Alumno objalumno = new Alumno();
			objalumno.setIdAlumno(Integer.parseInt(idalumno));
			
			Tesis objtesis= new Tesis();
			objtesis.setIdTesis(idSeleccionado);
			objtesis.setTitulo(tit);
			objtesis.setTema(tem);
			objtesis.setFechaCreacion(Date.valueOf(feccre));
			objtesis.setAlumno(objalumno);
			if(est)
				objtesis.setEstado(1);
			else 
				objtesis.setEstado(0);
		
			
			TesisModel model = new TesisModel();
			int salida = model.actualizaTesis(objtesis);
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
	
	
	
	
}
