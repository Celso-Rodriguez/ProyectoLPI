package gui;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.SystemColor;
import java.awt.event.ActionEvent;
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

import entidad.Sala;
import model.SalaModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import util.FechaUtil;
import util.GeneradorReporte;
import util.Validaciones;
import java.awt.event.ActionListener;

public class FrmReporteSala extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JButton btnFiltrar;
	private JTextField txtInicio;
	private JTextField txtFin;
	private JPanel panelReporte;
	private JLabel lblReporteDeSala;
	private JLabel lblFechaInicio;
	private JLabel lblFechaFin;

	public FrmReporteSala() {
		getContentPane().setForeground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte de Sala");
		setBounds(100, 100, 1200, 708);
		getContentPane().setLayout(null);
		
		lblReporteDeSala = new JLabel("Reporte de Sala");
		lblReporteDeSala.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteDeSala.setFont(new Font("Tahoma", Font.BOLD, 35));
		lblReporteDeSala.setBounds(10, 22, 1168, 71);
		getContentPane().add(lblReporteDeSala);
		
		lblFechaInicio = new JLabel("Fecha Inicio");
		lblFechaInicio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFechaInicio.setBounds(45, 135, 121, 22);
		getContentPane().add(lblFechaInicio);
		
		lblFechaFin = new JLabel("Fecha Fin");
		lblFechaFin.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblFechaFin.setBounds(467, 135, 104, 22);
		getContentPane().add(lblFechaFin);
		
		txtInicio = new JTextField();
		txtInicio.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtInicio.setBounds(163, 135, 210, 24);
		getContentPane().add(txtInicio);
		txtInicio.setColumns(10);
		
		txtFin = new JTextField();
		txtFin.setFont(new Font("Tahoma", Font.PLAIN, 12));
		txtFin.setColumns(10);
		txtFin.setBounds(568, 135, 210, 24);
		getContentPane().add(txtFin);
		
		
		panelReporte = new JPanel();
		panelReporte.setBorder(new TitledBorder (null, "Reportes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelReporte.setBounds(45, 208, 1096, 427);
		getContentPane().add(panelReporte);
		panelReporte.setLayout(new BorderLayout());
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnFiltrar.setBounds(849, 134, 139, 27);
		getContentPane().add(btnFiltrar);

	}
	
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFiltrar) {
			actionPerformedBtnFiltrarJButton(e);
		}
	}
	protected void actionPerformedBtnFiltrarJButton(ActionEvent e) {
		String fecIni = txtInicio.getText();
		String fecFin = txtFin.getText();
		
		
		if (!fecIni.matches(Validaciones.FECHA)) {
			mensaje("La fecha Inicio tiene formato yyyy-MM-dd");
		}else if (!fecFin.matches(Validaciones.FECHA)) {
			mensaje("La fecha Fin tiene formato yyyy-MM-dd");
		}else if (FechaUtil.isNotSuperiorFechaYYYYMMdd(fecIni, fecFin)) {
			mensaje("La Fecha fin es superior a la Fecha inicio");
		}else {
			Date dtIni = Date.valueOf(fecIni);
			Date dtFin = Date.valueOf(fecFin);
			
			SalaModel model = new SalaModel();
			List<Sala> lista = model.listaPorFechaCreacion(dtIni, dtFin);
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
			String jasper = "reporteSala.jasper";	
			
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
