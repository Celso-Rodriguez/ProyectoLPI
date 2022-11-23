package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import entidad.Alumno;
import model.AlumnoModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import util.FechaUtil;
import util.GeneradorReporte;
import util.Validaciones;

public class FrmReporteAlumno extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	
	private JTextField txtFechaInicio;
	private JTextField txtFechaFin;
	private JButton btnFiltrar;
	private JPanel panelReporte;

	public FrmReporteAlumno() {
		
		setMaximizable(true);
		getContentPane().setForeground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte de alumno");
		setBounds(100, 100, 1487, 686);
		getContentPane().setLayout(null);
		
		panelReporte = new JPanel();
		panelReporte.setBorder(new TitledBorder(null, "Reportes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelReporte.setBounds(10, 205, 1451, 440);
		getContentPane().add(panelReporte);
		panelReporte.setLayout(new BorderLayout());
		
		JLabel lblReportePorFecha = new JLabel("Reporte por Fecha de Nacimiento del Alumno");
		lblReportePorFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblReportePorFecha.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblReportePorFecha.setBounds(10, 22, 1451, 69);
		getContentPane().add(lblReportePorFecha);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio :");
		lblFechaInicio.setBounds(37, 141, 81, 14);
		getContentPane().add(lblFechaInicio);
		
		txtFechaInicio = new JTextField();
		txtFechaInicio.setColumns(10);
		txtFechaInicio.setBounds(111, 138, 145, 20);
		getContentPane().add(txtFechaInicio);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin :");
		lblFechaFin.setBounds(324, 141, 90, 14);
		getContentPane().add(lblFechaFin);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(598, 137, 162, 23);
		getContentPane().add(btnFiltrar);
		
		txtFechaFin = new JTextField();
		txtFechaFin.setColumns(10);
		txtFechaFin.setBounds(387, 138, 145, 20);
		getContentPane().add(txtFechaFin);

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFiltrar) {
			actionPerformedBtnFiltrarJButton(e);
		}
	}
	
	protected void actionPerformedBtnFiltrarJButton(ActionEvent e) {
		String fecIni  = txtFechaInicio.getText().trim();
		String fecFin  = txtFechaFin.getText().trim();
		
		if (!fecIni.matches(Validaciones.FECHA)) {
			mensaje("La fecha Inicio tiene formato yyyy-MM-dd");
		}else if (!fecFin.matches(Validaciones.FECHA)) {
			mensaje("La fecha Fin tiene formato yyyy-MM-dd");
		}else if (FechaUtil.isNotSuperiorFechaYYYYMMdd(fecIni, fecFin)) {
			mensaje("La Fecha fin es superior a la Fecha inicio");
		}else {
			Date dtIni = Date.valueOf(fecIni);
			Date dtFin = Date.valueOf(fecFin);
			
			AlumnoModel model = new AlumnoModel();
			List<Alumno> lista = model.listaPorRangoFechas(dtIni, dtFin);
			
			//Datos del reporte
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
			
			//Diseño del reporte
			String jasper = "reporteAlumno.jasper";	
			
			//Se obtiene el reporte
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
