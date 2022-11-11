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
import javax.swing.SwingConstants;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidad.Proveedor;
import model.ProveedorModel;
import util.JComboBoxBD;

public class FrmConsultaProveedor extends JInternalFrame implements ItemListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblConsultaProveedor;
	private JLabel lblPais;
	private JComboBoxBD cboPais;
	private JScrollPane scrollPane;
	private JTable table;
	
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	public FrmConsultaProveedor() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Proveedor");
		setBounds(100, 100, 1032, 600);
		getContentPane().setLayout(null);
		
		lblConsultaProveedor = new JLabel("Consulta Proveedor");
		lblConsultaProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaProveedor.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblConsultaProveedor.setBounds(342, 37, 359, 50);
		getContentPane().add(lblConsultaProveedor);
		
		lblPais = new JLabel("Pa\u00eds");
		lblPais.setBounds(67, 126, 46, 14);
		getContentPane().add(lblPais);
		
		cboPais = new JComboBoxBD(rb.getString("SQL_PAIS"), "[Todos]");
		cboPais.addItemListener(this);
		cboPais.setBounds(109, 122, 183, 22);
		getContentPane().add(cboPais);
		
		scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 173, 996, 386);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00f3digo", "Nombres", "Apellidos", "DNI", "Direcci\u00f3n", "T\u00e9lefono", "Correo", "Fecha Registro", "Estado", "Pa\u00eds"
			}
		));
		scrollPane.setViewportView(table);
		
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(150);
		table.getColumnModel().getColumn(2).setPreferredWidth(150);
		table.getColumnModel().getColumn(3).setPreferredWidth(100);
		table.getColumnModel().getColumn(4).setPreferredWidth(200);
		table.getColumnModel().getColumn(5).setPreferredWidth(100);
		table.getColumnModel().getColumn(6).setPreferredWidth(200);
		table.getColumnModel().getColumn(7).setPreferredWidth(200);
		table.getColumnModel().getColumn(9).setPreferredWidth(150);
		scrollPane.setViewportView(table);
		
		
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(7).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(8).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(9).setCellRenderer(rightRenderer);
		
		
		table.getTableHeader().setResizingAllowed(false);
		
		
		table.getTableHeader().setReorderingAllowed(false);
		
		
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
				
		table.setSelectionBackground(Color.BLUE);
		
	}

	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboPais) {
			itemStateChangedCboPais(e);
		}
	}
	protected void itemStateChangedCboPais(ItemEvent e) {
		
		if (e.getStateChange() == ItemEvent.SELECTED) {
			String item = cboPais.getSelectedItem().toString();
			int index = cboPais.getSelectedIndex();
			
			System.out.println(">>> Item >> " + item);
			String[] separados = item.split(":");
			
			ProveedorModel model = new ProveedorModel();
			List<Proveedor> lista = null;
			if (index == 0) {
				lista = new ArrayList<Proveedor>();
			}else if (index == 1) {
				lista = model.listaProveedor();
			}else {
				lista = model.listaPorPais(Integer.parseInt(separados[0]));
			}
			
			DefaultTableModel dtm = (DefaultTableModel) table.getModel();
			dtm.setRowCount(0);
			
			for (Proveedor x : lista) {
				Object[] f = {x.getIdProveedor(), 
							  x.getNombres(),
							  x.getApellidos(),
							  x.getDni(),
							  x.getDireccion(),
							  x.getTelefono(),
							  x.getCorreo(),
							  x.getFechaRegistro(),
							  x.getEstado()==1?"Activo":"Inactivo",
							  x.getPais().getNombre()} ;
				dtm.addRow(f);
			}
		}
		
	}
}
