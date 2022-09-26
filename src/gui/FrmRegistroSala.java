package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;

import entidad.Sala;
import entidad.Sede;
import model.SalaModel;
import util.JComboBoxBD;
import util.Validaciones;

import java.awt.Font;
import java.util.ResourceBundle;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FrmRegistroSala extends JInternalFrame implements ActionListener {

	private static final long serialVersionUID = 1L;
	private JTextField txtPiso;
	private JTextField txtNumeroDeAlumnos;
	private JTextField txtRecursos;
	private JComboBoxBD cboSede;
	private JButton btnRegistrar;
	private JLabel lblNumeroDeSala;
	private JTextField txtNumeroDeSala;
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	public FrmRegistroSala() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Sala");
		setBounds(100, 100, 784, 600);
		getContentPane().setLayout(null);
		
		JLabel lblTitulo = new JLabel("Registro de Sala");
		lblTitulo.setFont(new Font("Leelawadee UI", Font.BOLD, 46));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 23, 752, 89);
		getContentPane().add(lblTitulo);
		
		JLabel lblPiso = new JLabel("Piso");
		lblPiso.setFont(new Font("Leelawadee UI", Font.PLAIN, 20));
		lblPiso.setBounds(134, 204, 198, 27);
		getContentPane().add(lblPiso);
		
		txtPiso = new JTextField();
		txtPiso.setBounds(345, 205, 320, 26);
		getContentPane().add(txtPiso);
		txtPiso.setColumns(10);
		
		txtNumeroDeAlumnos = new JTextField();
		txtNumeroDeAlumnos.setColumns(10);
		txtNumeroDeAlumnos.setBounds(345, 267, 320, 26);
		getContentPane().add(txtNumeroDeAlumnos);
		
		txtRecursos = new JTextField();
		txtRecursos.setColumns(10);
		txtRecursos.setBounds(345, 326, 320, 26);
		getContentPane().add(txtRecursos);
		
		JLabel lblNumeroDeAlumnos = new JLabel("N\u00FAmero de Alumnos");
		lblNumeroDeAlumnos.setFont(new Font("Leelawadee UI", Font.PLAIN, 20));
		lblNumeroDeAlumnos.setBounds(134, 267, 198, 27);
		getContentPane().add(lblNumeroDeAlumnos);
		
		JLabel lblRecursos = new JLabel("Recursos");
		lblRecursos.setFont(new Font("Leelawadee UI", Font.PLAIN, 20));
		lblRecursos.setBounds(134, 326, 198, 27);
		getContentPane().add(lblRecursos);
		
		JLabel lblSede = new JLabel("Sede");
		lblSede.setFont(new Font("Leelawadee UI", Font.PLAIN, 20));
		lblSede.setBounds(134, 382, 198, 27);
		getContentPane().add(lblSede);
		
		cboSede = new JComboBoxBD(rb.getString("SQL_SEDE"));
		cboSede.setBounds(345, 382, 320, 26);
		getContentPane().add(cboSede);
		
		btnRegistrar = new JButton("Registrar");
		btnRegistrar.addActionListener(this);
		btnRegistrar.setFont(new Font("Leelawadee UI", Font.BOLD, 24));
		btnRegistrar.setBounds(290, 466, 175, 41);
		getContentPane().add(btnRegistrar);
		
		lblNumeroDeSala = new JLabel("N\u00FAmero de Sala");
		lblNumeroDeSala.setFont(new Font("Leelawadee UI", Font.PLAIN, 20));
		lblNumeroDeSala.setBounds(134, 146, 198, 27);
		getContentPane().add(lblNumeroDeSala);
		
		txtNumeroDeSala = new JTextField();
		txtNumeroDeSala.setColumns(10);
		txtNumeroDeSala.setBounds(345, 146, 320, 26);
		getContentPane().add(txtNumeroDeSala);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnRegistrar) {
			actionPerformedBtnRegistrarJButton(e);
		}
	}
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	}
	protected void actionPerformedBtnRegistrarJButton(ActionEvent e) {
		
		String nus = txtNumeroDeSala.getText().trim();
		String pis = txtPiso.getText().trim();
		String num = txtNumeroDeAlumnos.getText().trim();
		String rec = txtRecursos.getText().trim();
		int index = cboSede.getSelectedIndex();
		
		if (!nus.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("El número de sala tiene el formato 'A00X'");
		}else if (!pis.matches(Validaciones.NUMERO)) {
			mensaje("El número de piso es de 1 a 1000 caracteres");
		}else if (!num.matches(Validaciones.NUMERO)) {
			mensaje("El número de alumnos es de 1 a 1000 caracteres");
		}else if (!rec.matches(Validaciones.TEXTO)) {
			mensaje("Los recursos son de 1 a 20 caracteres");
		}else if (index == 0) {
			mensaje("Seleccione una sede");
		}else {
			String sede = cboSede.getSelectedItem().toString();
			String idSede = sede.split(":")[0];
			
			Sede objSede = new Sede();
			objSede.setIdSede(Integer.parseInt(idSede));
			
			Sala objSala = new Sala();
			objSala.setNumero(nus);
			objSala.setPiso(Integer.parseInt(pis));
			objSala.setNumAlumnos(Integer.parseInt(num));
			objSala.setRecursos(rec);
			objSala.setSede(objSede);
			
			SalaModel model = new SalaModel();
			int s = model.insertaSala(objSala);
			if (s>0) {
				mensaje("Se insertó correctamente");
			}else {
				mensaje("Error en el Registro");
			}
			
		}
	}
}
