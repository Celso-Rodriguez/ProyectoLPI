package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ItemEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.awt.event.ItemListener;

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

import entidad.Tesis;
import model.TesisModel;
import util.JComboBoxBD;

public class FrmConsultaTesis extends JInternalFrame implements ItemListener {

	private static final long serialVersionUID = 1L;
	private JComboBoxBD cboAlumno;
	private ResourceBundle rb = ResourceBundle.getBundle("combo");
	private JTable table;

	public FrmConsultaTesis() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Tesis");
		setBounds(100, 100, 1000, 600);
		getContentPane().setLayout(null);
		
		JLabel lblConsultaTesis = new JLabel("Consulta Tesis");
		lblConsultaTesis.setFont(new Font("Times New Roman", Font.BOLD | Font.ITALIC, 30));
		lblConsultaTesis.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaTesis.setBounds(10, 11, 964, 91);
		getContentPane().add(lblConsultaTesis);
		
		JLabel lblAlumno = new JLabel("Alumno :");
		lblAlumno.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblAlumno.setBounds(10, 127, 79, 30);
		getContentPane().add(lblAlumno);
		
		cboAlumno = new JComboBoxBD(rb.getString("SQL_ALUMNO"), "[Todos]");
		cboAlumno.addItemListener(this);
		cboAlumno.setBounds(99, 133, 112, 22);
		getContentPane().add(cboAlumno);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 180, 964, 379);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "Titulo", "tema", "Fecha Creaci\u00F3n", "Fecha Registro", "Estado", "Alumno"
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
	if (e.getSource() == cboAlumno) {
		itemStateChangedCboAlumnoJComboBoxBD(e);
	}
}
	
	protected void itemStateChangedCboAlumnoJComboBoxBD(ItemEvent e) {
		if (e.getStateChange() == ItemEvent.SELECTED) {
			int index = cboAlumno.getSelectedIndex();
			String item = cboAlumno.getSelectedItem().toString();
			
			System.out.println("Index ==> " + index);
			System.out.println("Item ==> " + item);
			
			TesisModel model = new TesisModel();
			List<Tesis> lst = null;
			
			if (index == 0) {
				lst = new ArrayList<Tesis>();
			}else if (index == 1) {
				lst = model.listaTesis();
			}else {
				String[] separado = item.split(":");
				int idAlumno = Integer.parseInt(separado[0]);
				lst = model.listaPorAlumno(idAlumno);
			}
			
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.setRowCount(0);
			
			for (Tesis t : lst) {
				Object[] f = {t.getIdTesis(), 
							  t.getTitulo(), 
							  t.getTema(),
							  t.getFechaCreacion(),
							  t.getFechaRegistro(),
							  t.getEstado()==1?"Activo":"Inactivo",
							  t.getAlumno().getNombres()};
				dtm.addRow(f);
			}	
		}
	}
}

