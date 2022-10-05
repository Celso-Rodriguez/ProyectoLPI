package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextField;

public class FrmConsultaAlumno extends JInternalFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtFecIni;
	private JTextField txtFecFin;

	public FrmConsultaAlumno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Consulta Alumno");
		setBounds(100, 100, 1000, 600);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Consulta Alumno");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Arial Narrow", Font.ITALIC, 33));
		lblNewLabel.setBounds(10, 11, 964, 79);
		getContentPane().add(lblNewLabel);
		
		JLabel lblFecIni = new JLabel("Fecha de Inicio :");
		lblFecIni.setFont(new Font("Arial Narrow", Font.ITALIC, 16));
		lblFecIni.setBounds(22, 125, 112, 22);
		getContentPane().add(lblFecIni);
		
		txtFecIni = new JTextField();
		txtFecIni.setBounds(132, 126, 160, 20);
		getContentPane().add(txtFecIni);
		txtFecIni.setColumns(10);
		
		JLabel lblFechaDeFin = new JLabel("Fecha de Fin :");
		lblFechaDeFin.setFont(new Font("Arial Narrow", Font.ITALIC, 16));
		lblFechaDeFin.setBounds(454, 125, 112, 22);
		getContentPane().add(lblFechaDeFin);
		
		txtFecFin = new JTextField();
		txtFecFin.setColumns(10);
		txtFecFin.setBounds(564, 126, 160, 20);
		getContentPane().add(txtFecFin);
	}

	public void mensaje(String ms) {
		JOptionPane.showMessageDialog(this, ms);
	}
}
