package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;
import util.JComboBoxBD;

import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidad.Autor;
import model.AutorModel;

import java.awt.event.ItemListener;
import java.awt.event.ItemEvent;

public class FrmConsultaAutor extends JInternalFrame implements ItemListener {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private ResourceBundle rb = ResourceBundle.getBundle("combo");
	private JComboBoxBD cboGrado;

	public FrmConsultaAutor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Autor");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("Consulta Autor");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(0, 11, 984, 71);
		getContentPane().add(lblTitulo);
		
		JLabel lblGrado = new JLabel("Grado :");
		lblGrado.setHorizontalAlignment(SwingConstants.RIGHT);
		lblGrado.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblGrado.setBounds(10, 93, 100, 25);
		getContentPane().add(lblGrado);
		
		cboGrado = new JComboBoxBD(rb.getString("SQL_GRADO"),"[TODOS]");
		cboGrado.addItemListener(this);
		cboGrado.setBounds(120, 96, 125, 24);
		getContentPane().add(cboGrado);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 158, 864, 401);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Nombres", "Apellidos", "Fecha de Nacimiento", "Tel\u00E9fono", "Estado", "IdGrado", "Grado"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(50);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(100);
		table.getColumnModel().getColumn(5).setPreferredWidth(50);
		table.getColumnModel().getColumn(6).setPreferredWidth(0);
		table.getColumnModel().getColumn(6).setMinWidth(0);
		table.getColumnModel().getColumn(6).setMaxWidth(0);
		table.getColumnModel().getColumn(7).setPreferredWidth(100);
			scrollPane.setViewportView(table);
			
			//color de la fila seleccionada
			table.setSelectionBackground(Color.BLUE);
			
			//alineacion
			DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
			rightRenderer.setHorizontalAlignment(JLabel.CENTER);
							
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
	}
	
	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboGrado) {
			itemStateChangedCboGrado(e);
		}
	}
	protected void itemStateChangedCboGrado(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String item = cboGrado.getSelectedItem().toString();
			int index = cboGrado.getSelectedIndex();
			
			System.out.println(">>> Item >> " + item);
			String[] separados = item.split(":");
			
			AutorModel model = new AutorModel();
			List<Autor> lista = null;
			if (index == 0) {
				lista = new ArrayList<Autor>();
			}else if (index == 1) {
				lista = model.listaAutor();
			}else {
				lista = model.listaPorGrado(Integer.parseInt(separados[0]));
			}
			
			DefaultTableModel dt = (DefaultTableModel) table.getModel();
			dt.setRowCount(0);
			
			for (Autor x : lista) {
				Object[] f = {x.getIdAutor(),
							  x.getNombres(),
							  x.getApellidos(),
							  x.getFechaNacimiento(),
							  x.getTelefono(),
							  x.getEstado()==1?"Activo":"Inactivo",
							  x.getGrado().getIdGrado(),
							  x.getGrado().getDescripcion()};
		dt.addRow(f);
			}
		}
	}
}
