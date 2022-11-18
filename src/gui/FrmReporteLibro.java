package gui;

import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.SwingConstants;

import entidad.Libro;
import model.LibroModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import util.FechaUtil;
import util.GeneradorReporte;
import util.Validaciones;

import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JPanel;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.BorderLayout;

public class FrmReporteLibro extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtFechaInicio;
	private JTextField txtFechaFin;
	private JButton btnFiltrar;
	private JPanel panelReporte;

	public FrmReporteLibro() {
		getContentPane().setForeground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte de Libro");
		setBounds(100, 100, 1200, 600);
		getContentPane().setLayout(null);
		
		JLabel lblReporteLibro = new JLabel("REPORTE LIBRO");
		lblReporteLibro.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteLibro.setBackground(Color.BLUE);
		lblReporteLibro.setOpaque(true);
		lblReporteLibro.setForeground(Color.WHITE);
		lblReporteLibro.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblReporteLibro.setBounds(25, 11, 1149, 108);
		getContentPane().add(lblReporteLibro);
		
		JLabel lblFechaInicio = new JLabel("A\u00F1o inicio");
		lblFechaInicio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFechaInicio.setBounds(25, 149, 120, 22);
		getContentPane().add(lblFechaInicio);
		
		txtFechaInicio = new JTextField();
		txtFechaInicio.setBounds(173, 153, 144, 20);
		getContentPane().add(txtFechaInicio);
		txtFechaInicio.setColumns(10);
		
		JLabel lblFechaFin = new JLabel("A\u00F1o fin");
		lblFechaFin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFechaFin.setBounds(498, 150, 120, 20);
		getContentPane().add(lblFechaFin);
		
		txtFechaFin = new JTextField();
		txtFechaFin.setBounds(629, 153, 144, 20);
		getContentPane().add(txtFechaFin);
		txtFechaFin.setColumns(10);
		
		btnFiltrar = new JButton("FILTRAR");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnFiltrar.setBounds(950, 135, 171, 50);
		getContentPane().add(btnFiltrar);
		
		panelReporte = new JPanel();
		panelReporte.setBounds(25, 203, 1149, 356);
		getContentPane().add(panelReporte);
		panelReporte.setLayout(new BorderLayout(0, 0));
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFiltrar) {
			actionPerformedBtnFiltrarJButton(e);
		}
	}
	protected void actionPerformedBtnFiltrarJButton(ActionEvent e) {
		
		String fecIni = txtFechaInicio.getText();	
		String fecFin = txtFechaFin.getText();
		
		if (!fecIni.matches(Validaciones.ANHO)) {
			mensaje("La fecha Inicio tiene formato yyyy");
		}else if (!fecFin.matches(Validaciones.ANHO)) {
			mensaje("La fecha Fin tiene formato yyyy");
		}else if (FechaUtil.isNotSuperiorFechaYYYY(fecIni, fecFin)) {
			mensaje("La año fin es superior al año inicio");
		}else {
			int dtIni = Integer.valueOf(fecIni);
			int dtFin = Integer.valueOf(fecFin);
			
			LibroModel model  = new LibroModel();
			List<Libro> lista = model.listaPorAnio(dtIni, dtFin);
			
			//Fuente de datos(data que se muestra en el reporte)
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
			
			//Diseño que se genera en el ireport
			String jasper = "reporteLibro.jasper";
			
			JasperPrint print = GeneradorReporte.genera(jasper, dataSource, null);
			JRViewer jRViewer = new JRViewer(print);
			
			panelReporte.removeAll();
			panelReporte.add(jRViewer);
			panelReporte.repaint();
			panelReporte.revalidate();
			
		}				
	}
	
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	}
}
