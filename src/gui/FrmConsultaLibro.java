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

import entidad.Libro;
import model.LibroModel;
import util.JComboBoxBD;

public class FrmConsultaLibro extends JInternalFrame implements ItemListener {
	
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	private static final long serialVersionUID = 1L;
	private JTable tblLibro;
	private JComboBoxBD cboCategoria;

	public FrmConsultaLibro() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Libro");
		setBounds(100, 100, 1000, 600);
		getContentPane().setLayout(null);
		
		JLabel lblConsultaLibro = new JLabel("CONSULTA LIBRO");
		lblConsultaLibro.setHorizontalAlignment(SwingConstants.CENTER);
		lblConsultaLibro.setOpaque(true);
		lblConsultaLibro.setBackground(Color.BLUE);
		lblConsultaLibro.setForeground(Color.WHITE);
		lblConsultaLibro.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblConsultaLibro.setBounds(23, 11, 951, 106);
		getContentPane().add(lblConsultaLibro);
		
		JLabel lblNewLabel = new JLabel("Categor\u00EDa ");
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblNewLabel.setBounds(31, 166, 148, 22);
		getContentPane().add(lblNewLabel);
		
		cboCategoria = new JComboBoxBD(rb.getString("SQL_CATEGORIA"),"[TODOS]");
		cboCategoria.addItemListener(this);
		cboCategoria.setBounds(265, 169, 356, 22);
		getContentPane().add(cboCategoria);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(23, 221, 951, 338);
		getContentPane().add(scrollPane);
		
		tblLibro = new JTable();
		tblLibro.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"C\u00F3digo", "T\u00EDtulo", "A\u00F1o", "Serie", "Fec. Registro", "Estado", "Categor\u00EDa"
			}
		));
		scrollPane.setViewportView(tblLibro);
		//-----------------------------------------------------------------------	
	  	tblLibro.getColumnModel().getColumn(0).setPreferredWidth(15);
	  	tblLibro.getColumnModel().getColumn(1).setPreferredWidth(130);
	  	tblLibro.getColumnModel().getColumn(2).setPreferredWidth(20);
	  	tblLibro.getColumnModel().getColumn(3).setPreferredWidth(60);
	  	tblLibro.getColumnModel().getColumn(4).setPreferredWidth(60);
	  	tblLibro.getColumnModel().getColumn(5).setPreferredWidth(30);
	  	tblLibro.getColumnModel().getColumn(6).setPreferredWidth(30);
	  	scrollPane.setViewportView(tblLibro);
		//-------------------------------------------------------------------------
	  	tblLibro.setSelectionBackground(Color.BLUE);
	    //-------------------------------------------------------------------------
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		tblLibro.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		tblLibro.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		tblLibro.getColumnModel().getColumn(3).setCellRenderer(rightRenderer);
		tblLibro.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		tblLibro.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		tblLibro.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
		//-------------------------------------------------------------------------	
		tblLibro.getTableHeader().setResizingAllowed(false);
		//-------------------------------------------------------------------------		
		tblLibro.getTableHeader().setReorderingAllowed(false);
		//-------------------------------------------------------------------------				
		tblLibro.setRowSelectionAllowed(true);
		tblLibro.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		//-------------------------------------------------------------------------		
		tblLibro.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(tblLibro);
	}

	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
	public void itemStateChanged(ItemEvent e) {
		if (e.getSource() == cboCategoria) {
			itemStateChangedCboCategoriaJComboBox(e);
		}
	}
	protected void itemStateChangedCboCategoriaJComboBox(ItemEvent e) {
		
		if(e.getStateChange() == ItemEvent.SELECTED) {
			
			String item = cboCategoria.getSelectedItem().toString();
			
			int index = cboCategoria.getSelectedIndex();
			
			System.out.println(">>> Item >> " + item);	
			
			String[] separados = item.split(":");
			LibroModel model = new LibroModel();
			List<Libro> lista = null;
			
			if(index == 0) {
				lista = new ArrayList<Libro>();
			}else if (index == 1) {
				lista = model.listaTodos();
			}else {
				lista = model.listaPorCategoria(Integer.parseInt(separados[0]));
			}
			
			DefaultTableModel dtm = (DefaultTableModel) tblLibro.getModel();
			dtm.setRowCount(0);
			
			for (Libro l : lista) {
				Object[] f = {
								l.getIdLibro(),l.getTitulo(),l.getAnio(),l.getSerie(),
								l.getFechaRegistro(),l.getEstado()==1?"ACTIVO":"INACTIVO",
								l.getCategoria().getDescripcion()
							 };
				dtm.addRow(f);	
			}						
		}						
	}
}
