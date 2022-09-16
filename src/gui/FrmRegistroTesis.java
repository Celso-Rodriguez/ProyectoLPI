package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.DefaultComboBoxModel;

public class FrmRegistroTesis extends JInternalFrame {

	private static final long serialVersionUID = 1L;

	public FrmRegistroTesis() {
		getContentPane().setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 13));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Tesis");
		setBounds(100, 100, 623, 481);
		getContentPane().setLayout(null);
		
		JLabel lblTituloTesis = new JLabel("Registro de Tesis");
		lblTituloTesis.setBounds(10, 21, 587, 43);
		lblTituloTesis.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 19));
		lblTituloTesis.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblTituloTesis);
	}
}
