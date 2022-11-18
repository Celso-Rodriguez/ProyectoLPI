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

import entidad.Proveedor;
import model.ProveedorModel;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.swing.JRViewer;
import util.FechaUtil;
import util.GeneradorReporte;
import util.Validaciones;

public class FrmReporteProveedor extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JLabel lblReporteProveedor;
	private JLabel lblFechaInicio;
	private JLabel lblFechaFin;
	private JTextField txtInicio;
	private JTextField txtFin;
	private JButton btnFiltrar;
	private JPanel panelReporte;

	public FrmReporteProveedor() {
		getContentPane().setForeground(SystemColor.activeCaption);
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Reporte de Proveedor");
		setBounds(100, 100, 1230, 704);
		getContentPane().setLayout(null);
		
		lblReporteProveedor = new JLabel("Reporte Proveedor");
		lblReporteProveedor.setHorizontalAlignment(SwingConstants.CENTER);
		lblReporteProveedor.setFont(new Font("Tahoma", Font.BOLD, 30));
		lblReporteProveedor.setBounds(394, 36, 448, 64);
		getContentPane().add(lblReporteProveedor);
		
		lblFechaInicio = new JLabel("Fecha Inicio");
		lblFechaInicio.setBounds(55, 152, 94, 14);
		getContentPane().add(lblFechaInicio);
		
		lblFechaFin = new JLabel("Fecha Fin");
		lblFechaFin.setBounds(405, 152, 75, 14);
		getContentPane().add(lblFechaFin);
		
		txtInicio = new JTextField();
		txtInicio.setBounds(144, 149, 190, 20);
		getContentPane().add(txtInicio);
		txtInicio.setColumns(10);
		
		txtFin = new JTextField();
		txtFin.setColumns(10);
		txtFin.setBounds(490, 149, 190, 20);
		getContentPane().add(txtFin);
		
		btnFiltrar = new JButton("Filtrar");
		btnFiltrar.addActionListener(this);
		btnFiltrar.setBounds(728, 148, 154, 23);
		getContentPane().add(btnFiltrar);
		
		panelReporte = new JPanel();
		panelReporte.setBorder(new TitledBorder(null, "Reportes", TitledBorder.CENTER, TitledBorder.TOP, null, null));
		panelReporte.setBounds(22, 181, 1182, 475);
		getContentPane().add(panelReporte);
		panelReporte.setLayout(new BorderLayout());

	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnFiltrar) {
			actionPerformedBtnFiltrar(e);
		}
	}
	protected void actionPerformedBtnFiltrar(ActionEvent e) {
		
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
			
			ProveedorModel model = new ProveedorModel();
			List<Proveedor> lista = model.listaPorFechaCreacion(dtIni, dtFin);
			
			JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(lista);
			String jasper = "reporteProveedor.jasper";	
			
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
