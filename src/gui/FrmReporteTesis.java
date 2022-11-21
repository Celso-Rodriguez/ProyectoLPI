package gui;

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

import entidad.Tesis;
import model.TesisModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import util.FechaUtil;
import util.GeneradorReporte;
import util.Validaciones;
import java.awt.BorderLayout;
import java.awt.Color;

public class FrmReporteTesis extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtfecini;
	private JButton btnfiltrar;
	private JTextField txtfecfin;
	private JPanel panelReporte;

	public FrmReporteTesis() {
		
		getContentPane().setForeground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte de Tesis");
		setBounds(100, 100, 1200, 600);
		getContentPane().setLayout(null);
		
		JLabel lblreporte = new JLabel("Reporte Tesis");
		lblreporte.setForeground(new Color(0, 0, 160));
		lblreporte.setBackground(new Color(0, 51, 255));
		lblreporte.setFont(new Font("Leelawadee UI", Font.ITALIC, 35));
		lblreporte.setHorizontalAlignment(SwingConstants.CENTER);
		lblreporte.setBounds(10, 11, 1164, 87);
		getContentPane().add(lblreporte);
		
		JLabel lblfecCre = new JLabel("Fecha inicio");
		lblfecCre.setBounds(20, 109, 97, 38);
		getContentPane().add(lblfecCre);
		
		txtfecini = new JTextField();
		txtfecini.setBounds(98, 118, 164, 20);
		getContentPane().add(txtfecini);
		txtfecini.setColumns(10);
		
		btnfiltrar = new JButton("FILTRAR");
		btnfiltrar.setBackground(new Color(0, 102, 255));
		btnfiltrar.addActionListener(this);
		btnfiltrar.setBounds(925, 109, 97, 29);
		getContentPane().add(btnfiltrar);
		
		JLabel lblNewLabel = new JLabel("Fecha Fin");
		lblNewLabel.setBounds(378, 121, 58, 14);
		getContentPane().add(lblNewLabel);
		
		txtfecfin = new JTextField();
		txtfecfin.setBounds(493, 118, 225, 20);
		getContentPane().add(txtfecfin);
		txtfecfin.setColumns(10);
		
		panelReporte = new JPanel();
		panelReporte.setBorder(new TitledBorder(null, "Reportes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelReporte.setBounds(10, 170, 1164, 389);
		getContentPane().add(panelReporte);
		panelReporte.setLayout(new BorderLayout());

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnfiltrar) {
			actionPerformedBtnFiltrarJButton(e);
		}
	}
	
	public void mensaje(String ms){
			JOptionPane.showMessageDialog(this, ms);
		}
	
	
	protected void actionPerformedBtnFiltrarJButton(ActionEvent e) {
		String fecIni  = txtfecini.getText().trim();
		String fecFin  = txtfecfin.getText().trim();
		
		if (!fecIni.matches(Validaciones.FECHA)) {
			mensaje("La fecha Inicio tiene formato yyyy-MM-dd");
		}else if (!fecFin.matches(Validaciones.FECHA)) {
			mensaje("La fecha Fin tiene formato yyyy-MM-dd");
		}else if (FechaUtil.isNotSuperiorFechaYYYYMMdd(fecIni, fecFin)) {
			mensaje("La Fecha fin es superior a la Fecha inicio");
		}else {
			
			Date dtIni = Date.valueOf(fecIni);
			Date dtFin = Date.valueOf(fecFin);
			
			TesisModel model = new TesisModel();
			List<Tesis> lista = model.listaPorFecha(dtIni, dtFin);
			
			//Datos del reporte
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
			
			//Dise�o del reporte
			String jasper = "ReporteTesis.jasper";	
			//Se obtiene el reporte
			JasperPrint print = GeneradorReporte.genera(jasper, dataSource, null);
			
			JRViewer jRViewer = new JRViewer(print);
			
			panelReporte.removeAll();
			panelReporte.add(jRViewer);
			panelReporte.repaint();
			panelReporte.revalidate();
			
		}	
	}
}
