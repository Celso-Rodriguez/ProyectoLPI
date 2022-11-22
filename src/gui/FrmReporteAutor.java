package gui;

import java.awt.SystemColor;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.BorderLayout;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import entidad.Autor;
import model.AutorModel;
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
import java.sql.Date;
import java.util.List;
import java.awt.event.ActionEvent;

public class FrmReporteAutor extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtInicio;
	private JTextField txtFin;
	private JButton btnFiltrar;
	private JPanel panelReporte;

	public FrmReporteAutor() {
		getContentPane().setForeground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte de Autor");
		setBounds(100, 100, 1200, 600);
		getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("Reporte Autor");
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblTitulo.setBounds(10, 11, 1164, 68);
		getContentPane().add(lblTitulo);
		
		txtInicio = new JTextField();
		txtInicio.setColumns(10);
		txtInicio.setBounds(110, 116, 119, 20);
		getContentPane().add(txtInicio);
		
		JLabel lblFechaFin = new JLabel("Fecha Fin");
		lblFechaFin.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFechaFin.setBounds(300, 114, 100, 25);
		getContentPane().add(lblFechaFin);
		
		txtFin = new JTextField();
		txtFin.setColumns(10);
		txtFin.setBounds(401, 116, 109, 20);
		getContentPane().add(txtFin);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(571, 115, 162, 23);
		getContentPane().add(btnFiltrar);
		
		JLabel lblFechaInicio = new JLabel("Fecha Inicio");
		lblFechaInicio.setFont(new Font("Tahoma", Font.PLAIN, 15));
		lblFechaInicio.setBounds(10, 114, 100, 25);
		getContentPane().add(lblFechaInicio);
		
		panelReporte = new JPanel();
		panelReporte.setBorder(new TitledBorder(null, "Reportes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelReporte.setBounds(10, 205, 1451, 440);
		getContentPane().add(panelReporte);
		panelReporte.setLayout(new BorderLayout());

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFiltrar) {
			actionPerformedBtnFiltrar(e);
		}
	}
	protected void actionPerformedBtnFiltrar(ActionEvent e) {
		String fecIni  = txtInicio.getText().trim();
		String fecFin  = txtFin.getText().trim();
		
		if (!fecIni.matches(Validaciones.FECHA)) {
			mensaje("La fecha Inicio tiene formato yyyy-MM-dd");
		}else if (!fecFin.matches(Validaciones.FECHA)) {
			mensaje("La fecha Fin tiene formato yyyy-MM-dd");
		}else if (FechaUtil.isNotSuperiorFechaYYYYMMdd(fecIni, fecFin)) {
			mensaje("La Fecha fin es superior a la Fecha inicio");
		}else {
			Date dtIni = Date.valueOf(fecIni);
			Date dtFin = Date.valueOf(fecFin);
			
			AutorModel model = new AutorModel();
			List<Autor> lista = model.listaPorRangoFechas(dtIni, dtFin);
			
			//Datos del reporte
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
			
			//Dise�o del reporte
			String jasper = "reporteAutor.jasper";	
			
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
