package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;

public class FrmCrudSala extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	public FrmCrudSala() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento de Sala");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);

	}

}
