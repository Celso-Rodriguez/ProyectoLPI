package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidad.Alumno;
import model.AlumnoModel;
import util.JComboBoxBD;


public class FrmConsultaAlumno extends JInternalFrame implements ItemListener {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JComboBoxBD cboPais;
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	
	
	
	
	
	public FrmConsultaAlumno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Alumno");
		setBounds(100, 100, 1235, 724);
		getContentPane().setLayout(null);
		
		JLabel lblConsultaAlumno = new JLabel("Consulta Alumno");
		lblConsultaAlumno.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaAlumno.setFont(new Font("Arial Narrow", Font.ITALIC, 33));
		lblConsultaAlumno.setBounds(10, 11, 1199, 79);
		getContentPane().add(lblConsultaAlumno);
		
		JLabel lblPais = new JLabel("Pais :");
		lblPais.setFont(new Font("Arial Narrow", Font.PLAIN, 18));
		lblPais.setBounds(52, 132, 46, 14);
		getContentPane().add(lblPais);
		
		cboPais = new JComboBoxBD(rb.getString("SQL_PAIS"), "[Todos]");
		cboPais.addItemListener(this);
		cboPais.setBounds(134, 131, 259, 22);
		getContentPane().add(cboPais);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 192, 1199, 491);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"Código", "Nombres", "Apellidos", "Teléfono", "Dni", "Correo", "Fecha de Nacimiento", "Fecha Registro", "Estado", "País"
			}
		));
		
		
		
		
				//tamano de la fila	
				table.getColumnModel().getColumn(0).setPreferredWidth(8);
				table.getColumnModel().getColumn(1).setPreferredWidth(70);
				table.getColumnModel().getColumn(2).setPreferredWidth(70);
				table.getColumnModel().getColumn(3).setPreferredWidth(50);
				table.getColumnModel().getColumn(4).setPreferredWidth(80);
				table.getColumnModel().getColumn(5).setPreferredWidth(100);
				table.getColumnModel().getColumn(6).setPreferredWidth(130);
				table.getColumnModel().getColumn(7).setPreferredWidth(130);
				table.getColumnModel().getColumn(8).setPreferredWidth(30);
				table.getColumnModel().getColumn(9).setPreferredWidth(70);
		
		scrollPane.setViewportView(table);
		
				//color de la fila seleccionada
				table.setSelectionBackground(Color.BLACK);
				
				//alineacion
				DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
				rightRenderer.setHorizontalAlignment(JLabel.CENTER);
				table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(1).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
				table.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
				
				//desabilita el cambio de tamaÃ±o
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

	
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboPais) {
			itemStateChangedCboPaisJComboBoxBD(e);
		}
	}
	protected void itemStateChangedCboPaisJComboBoxBD(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			int index = cboPais.getSelectedIndex();
			String item = cboPais.getSelectedItem().toString();
			
			System.out.println("Index ==> " + index);
			System.out.println("Item ==> " + item);
			
			AlumnoModel model = new AlumnoModel();
			List<Alumno> lst = null;
			
			if (index == 0) {
				lst = new ArrayList<Alumno>();
			}else if (index == 1) {
				lst = model.listaAlumno();
			}else {
				String[] separado = item.split(":");
				int idPais = Integer.parseInt(separado[0]);
				lst = model.listaPorPais(idPais);
			}
			
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.setRowCount(0);
			
			for (Alumno x : lst) {
				Object[] f = {x.getIdAlumno(), 
							  x.getNombres(), 
							  x.getApellidos(),
							  x.getTelefono(),
							  x.getDni(),
							  x.getCorreo(),
							  x.getFechaNacimiento(), 
							  x.getFechaRegistro(), 
							  x.getEstado()==1?"Activo":"Inactivo",
							  x.getPais().getNombre()};
				dtm.addRow(f);
			}
			
			
			
		}
		
		
	}
	
	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
}
