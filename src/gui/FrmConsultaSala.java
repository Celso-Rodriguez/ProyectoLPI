package gui;

import java.awt.Font;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ResourceBundle;
import java.util.ArrayList;
import java.util.List;

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


import entidad.Sala;
import model.SalaModel;
import util.FechaUtil;
import util.JComboBoxBD;

public class FrmConsultaSala extends JInternalFrame implements ItemListener {

	private static final long serialVersionUID = 1L;
	private JTable table;
	private JComboBoxBD cboSede;
	
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	public FrmConsultaSala() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Sala");
		setBounds(100, 100, 1000, 600);
		getContentPane().setLayout(null);
		
		JLabel lblConsultaDeSala = new JLabel("Consulta de Sala");
		lblConsultaDeSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaDeSala.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblConsultaDeSala.setBounds(10, 39, 968, 72);
		getContentPane().add(lblConsultaDeSala);
		
		JLabel lblSede = new JLabel("Sede");
		lblSede.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSede.setBounds(85, 166, 75, 22);
		getContentPane().add(lblSede);
		
		cboSede = new JComboBoxBD(rb.getString("SQL_SEDE"), "[Todos]");
		cboSede.addItemListener(this);
		cboSede.setFont(new Font("Tahoma", Font.PLAIN, 14));
		cboSede.setBounds(170, 166, 251, 22);
		getContentPane().add(cboSede);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 227, 968, 334);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "N\u00FAmero de Sala", "Piso", "N\u00FAmero de Alumnos", "Recursos", "Fecha de Registro", "Estado",  "Sede"
			}
		));
		table.getColumnModel().getColumn(0).setPreferredWidth(70);
		table.getColumnModel().getColumn(1).setPreferredWidth(130);
		table.getColumnModel().getColumn(2).setPreferredWidth(80);
		table.getColumnModel().getColumn(3).setPreferredWidth(150);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(140);
		table.getColumnModel().getColumn(6).setPreferredWidth(90);
		table.getColumnModel().getColumn(7).setPreferredWidth(110);
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
		table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		
		table.setDefaultEditor(Object.class, null);
		
		table.getTableHeader().setResizingAllowed(false);
	}

	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboSede) {
			itemStateChangedCboSedeJComboBoxBD(e);
		}
	}
	protected void itemStateChangedCboSedeJComboBoxBD(ItemEvent e) {
		
		if(e.getStateChange() == ItemEvent.SELECTED) {
			String item = cboSede.getSelectedItem().toString();
			int index = cboSede.getSelectedIndex();
			
			String[] separados = item.split(":");
			
			SalaModel model = new SalaModel();
			List<Sala> lista = null;
			if (index == 0) {
				lista = new ArrayList<Sala>();
			}else if (index == 1) {
				lista = model.listaSala();
			}else {
				lista = model.listaPorSede(Integer.parseInt(separados[0]));
			}
			
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.setRowCount(0);
			
			for (Sala x : lista) {
				Object[] f = {x.getIdSala(), x.getNumero(), x.getPiso(), 
							x.getNumAlumnos(), x.getRecursos(), FechaUtil.getFechaYYYYMMddHHmmss(x.getFechaRegistro()),
							x.getEstado()==1?"Activo":"Inactivo", x.getSede().getNombre()};
				dtm.addRow(f);
			}
		}
		
	}
}
