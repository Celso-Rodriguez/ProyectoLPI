package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;

public class FrmRegistroAlumno extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	public FrmRegistroAlumno() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Alumno");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Registrar Alumno");
		lblNewLabel.setFont(new Font("Arial Narrow", Font.ITALIC, 32));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 864, 61);
		getContentPane().add(lblNewLabel);
	}
}
