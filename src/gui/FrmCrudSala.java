package gui;

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
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidad.Sala;
import entidad.Sede;
import model.SalaModel;
import util.JComboBoxBD;
import util.Validaciones;

public class FrmCrudSala extends JInternalFrame implements ActionListener, MouseListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtNumeroDeSala;
	private JTextField txtPiso;
	private JTextField txtNumeroDeAlumnos;
	private JTextField txtRecursos;
	private JButton btnIngresar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JTable table;
	private JComboBoxBD cboSede;
	private JCheckBox chkEstado;
	private ResourceBundle rb = ResourceBundle.getBundle("combo");
	
	int idSeleccionado = -1;

	public FrmCrudSala() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento de Sala");
		setBounds(100, 100, 942, 640);
		getContentPane().setLayout(null);
		
		JLabel lblMantenimientoDeSala = new JLabel("Mantenimiento de Sala");
		lblMantenimientoDeSala.setFont(new Font("Tahoma", Font.BOLD, 33));
		lblMantenimientoDeSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoDeSala.setBounds(10, 10, 910, 63);
		getContentPane().add(lblMantenimientoDeSala);
		
		JLabel lblNumeroDeSala = new JLabel("N\u00FAmero de Sala");
		lblNumeroDeSala.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNumeroDeSala.setBounds(53, 105, 153, 27);
		getContentPane().add(lblNumeroDeSala);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblPiso.setBounds(53, 142, 153, 27);
		getContentPane().add(lblPiso);
		
		JLabel lblNumeroDeAlumnos = new JLabel("N\u00FAmero de Alumnos");
		lblNumeroDeAlumnos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNumeroDeAlumnos.setBounds(53, 179, 192, 27);
		getContentPane().add(lblNumeroDeAlumnos);
		
		JLabel lblRecursos = new JLabel("Recursos");
		lblRecursos.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblRecursos.setBounds(53, 216, 192, 27);
		getContentPane().add(lblRecursos);
		
		JLabel lblSede = new JLabel("Sede");
		lblSede.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSede.setBounds(53, 253, 192, 27);
		getContentPane().add(lblSede);
		
		txtNumeroDeSala = new JTextField();
		txtNumeroDeSala.setBounds(255, 108, 295, 27);
		getContentPane().add(txtNumeroDeSala);
		txtNumeroDeSala.setColumns(10);
		
		txtPiso = new JTextField();
		txtPiso.setColumns(10);
		txtPiso.setBounds(255, 145, 295, 27);
		getContentPane().add(txtPiso);
		
		txtNumeroDeAlumnos = new JTextField();
		txtNumeroDeAlumnos.setColumns(10);
		txtNumeroDeAlumnos.setBounds(255, 182, 295, 27);
		getContentPane().add(txtNumeroDeAlumnos);
		
		txtRecursos = new JTextField();
		txtRecursos.setColumns(10);
		txtRecursos.setBounds(255, 219, 295, 27);
		getContentPane().add(txtRecursos);
		
		cboSede = new JComboBoxBD(rb.getString("SQL_SEDE"));
		cboSede.setBounds(255, 256, 295, 27);
		getContentPane().add(cboSede);
		
		chkEstado = new JCheckBox("Activo");
		chkEstado.setSelected(true);
		chkEstado.setFont(new Font("Tahoma", Font.PLAIN, 14));
		chkEstado.setBounds(593, 258, 97, 23);
		getContentPane().add(chkEstado);
		
		btnIngresar = new JButton("Ingresar");
		btnIngresar.addActionListener(this);
		btnIngresar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnIngresar.setBounds(720, 151, 161, 38);
		getContentPane().add(btnIngresar);
		
		btnEliminar = new JButton("Eliminar");
		btnEliminar.addActionListener(this);
		btnEliminar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnEliminar.setBounds(720, 199, 161, 38);
		getContentPane().add(btnEliminar);
		
		btnActualizar = new JButton("Actualizar");
		btnActualizar.addActionListener(this);
		btnActualizar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnActualizar.setBounds(720, 247, 161, 38);
		getContentPane().add(btnActualizar);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 311, 910, 290);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(this);
		table.setFont(new Font("Tahoma", Font.PLAIN, 13));
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Código", "N\u00FAmero de Sala", "Piso", "N\u00FAmero de Alumnos", "Recursos", "Estado", "ID Sede", "Sede"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(120);
		table.getColumnModel().getColumn(6).setPreferredWidth(70);
		table.getColumnModel().getColumn(7).setPreferredWidth(140);
		scrollPane.setViewportView(table);
		
		table.getTableHeader().setReorderingAllowed(false);
		
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);

		table.getColumnModel().getColumn(6).setMinWidth(0);
		table.getColumnModel().getColumn(6).setMaxWidth(0);
		table.getColumnModel().getColumn(6).setWidth(0);
		
		table.setDefaultEditor(Object.class, null);
		
		table.getTableHeader().setResizingAllowed(false);
		
		lista();

	}
	
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
	
	void limpiarCajasTexto() {
		txtNumeroDeAlumnos.setText("");
		txtNumeroDeSala.setText("");
		txtPiso.setText("");
		txtRecursos.setText("");
		cboSede.setSelectedIndex(0);
		chkEstado.setSelected(true);
		txtNumeroDeSala.requestFocus();
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
		registra();
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
	
	private void lista() {
		DefaultTableModel dt = (DefaultTableModel) table.getModel();
		dt.setRowCount(0);
		
		SalaModel model = new SalaModel();
		List<Sala> lista = model.listaSala();
		
		for (Sala x : lista) {
			Object[] f = {x.getIdSala(), x.getNumero(), x.getPiso(), x.getNumAlumnos(), x.getRecursos(), 
							getDesEstado(x.getEstado()), x.getSede().getIdSede(), x.getSede().getNombre()};
			dt.addRow(f);
		}
	}
	
	public String getDesEstado(int x) {
		if(x == 0) 	return "Inactivo";
		else		return "Activo";
	}
	private void registra() {
		String nus = txtNumeroDeSala.getText().trim();
		String pis = txtPiso.getText().trim();
		String num = txtNumeroDeAlumnos.getText().trim();
		String rec = txtRecursos.getText().trim();
		int index = cboSede.getSelectedIndex();
		boolean est = chkEstado.isSelected();
		
		if (!nus.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("El número de sala tiene el formato 'A00X'");
		}else if (!pis.matches(Validaciones.NUMERO)) {
			mensaje("El número de piso es de 1 a 1000 caracteres");
		}else if (!num.matches(Validaciones.NUMERO)) {
			mensaje("El número de alumnos es de 1 a 1000 caracteres");
		}else if (!rec.matches(Validaciones.TEXTO)) {
			mensaje("Los recursos son de 1 a 20 caracteres");
		}else if (index == 0) {
			mensaje("Seleccione una sede");
		}else {
			String sede = cboSede.getSelectedItem().toString();
			String idSede = sede.split(":")[0];
			
			Sede objSede = new Sede();
			objSede.setIdSede(Integer.parseInt(idSede));
			
			Sala objSala = new Sala();
			objSala.setNumero(nus);
			objSala.setPiso(Integer.parseInt(pis));
			objSala.setNumAlumnos(Integer.parseInt(num));
			objSala.setRecursos(rec);
			objSala.setSede(objSede);
			if(est)
				objSala.setEstado(1);
			else 
				objSala.setEstado(0);
			
			SalaModel model = new SalaModel();
			int s = model.insertaSala(objSala);
			if (s>0) {
				lista();
				limpiarCajasTexto();
				mensaje("Se insertó correctamente");		
			}else {
				mensaje("Error en el Registro");
			}			
		}
	}
	private void busca() {
		int posRow = table.getSelectedRow();
		idSeleccionado = (Integer)table.getValueAt(posRow, 0);
		String numSala = (String)table.getValueAt(posRow, 1);
		Integer piso = (Integer)table.getValueAt(posRow, 2);
		Integer numAlu = (Integer)table.getValueAt(posRow, 3);
		String rec = (String)table.getValueAt(posRow, 4);
		String estado = (String)table.getValueAt(posRow, 5);
		Integer idSede = (Integer)table.getValueAt(posRow, 6);
		String nomSede = (String)table.getValueAt(posRow, 7);
		
		txtNumeroDeSala.setText(numSala);
		txtPiso.setText(String.valueOf(piso));
		txtNumeroDeAlumnos.setText(String.valueOf(numAlu));
		txtRecursos.setText(rec);
		chkEstado.setSelected(estado.equals("Activo")?true:false);
		cboSede.setSelectedItem(idSede + ": " + nomSede);
	}
	private void actualiza() {
		String nus = txtNumeroDeSala.getText().trim();
		String pis = txtPiso.getText().trim();
		String num = txtNumeroDeAlumnos.getText().trim();
		String rec = txtRecursos.getText().trim();
		int index = cboSede.getSelectedIndex();
		boolean est = chkEstado.isSelected();
		
		if (idSeleccionado == -1) {
			mensaje("Selecciones una fila");
		}else if (!nus.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("El número de sala tiene el formato 'A00X'");
		}else if (!pis.matches(Validaciones.NUMERO)) {
			mensaje("El número de piso es de 1 a 1000 caracteres");
		}else if (!num.matches(Validaciones.NUMERO)) {
			mensaje("El número de alumnos es de 1 a 1000 caracteres");
		}else if (!rec.matches(Validaciones.TEXTO)) {
			mensaje("Los recursos son de 1 a 20 caracteres");
		}else if (index == 0) {
			mensaje("Seleccione una sede");
		}else {
			String sede = cboSede.getSelectedItem().toString();
			String idSede = sede.split(":")[0];
			
			Sede objSede = new Sede();
			objSede.setIdSede(Integer.parseInt(idSede));
			
			Sala objSala = new Sala();
			objSala.setIdSala(idSeleccionado);
			objSala.setNumero(nus);
			objSala.setPiso(Integer.parseInt(pis));
			objSala.setNumAlumnos(Integer.parseInt(num));
			objSala.setRecursos(rec);
			objSala.setSede(objSede);
			if(est)
				objSala.setEstado(1);
			else 
				objSala.setEstado(0);
			
			SalaModel model = new SalaModel();
			int s = model.actualizaSala(objSala);
			if (s>0) {
				lista();
				limpiarCajasTexto();
				idSeleccionado = -1;
				mensaje("Se actualizó correctamente");		
			}else {
				mensaje("Error al actualizar");
			}		
		}
	}
	private void elimina() {
		if (idSeleccionado == -1) {
			mensaje("Seleccione una fila");
		}else {
			SalaModel model = new SalaModel();
			int s = model.eliminaSala(idSeleccionado);
			if (s>0) {
				lista();
				limpiarCajasTexto();
				idSeleccionado = -1;
				mensaje("Se eliminó correctamente");		
			}else {
				mensaje("Error en la eliminación");
			}
		}
	}
}
