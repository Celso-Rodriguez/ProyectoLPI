package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

public class FrmRegistroLibro extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	public FrmRegistroLibro() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Libro");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTRO DE LIBRO");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 864, 37);
		getContentPane().add(lblNewLabel);
	}
}
