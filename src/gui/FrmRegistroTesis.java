package gui;

import java.awt.Font;
import java.sql.Date;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;


import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;
import java.awt.event.ActionEvent;

import entidad.Tesis;
import entidad.Alumno;
import model.TesisModel;
import util.JComboBoxBD;
import util.Validaciones;

public class FrmRegistroTesis extends JInternalFrame implements ActionListener {
	
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	private static final long serialVersionUID = 1L;
	private JTextField txttitulo;
	private JTextField txttema;
	private JComboBoxBD cboalumno;
	private JButton btnconfirmar;
	private JTextField txtfechacreacion;
	private JLabel lblTituloTesis;

	public FrmRegistroTesis() {
		getContentPane().setFont(new Font("Tw Cen MT Condensed", Font.PLAIN, 13));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Tesis");
		setBounds(100, 100, 755, 536);
		getContentPane().setLayout(null);
		
		lblTituloTesis = new JLabel("Registro de Tesis");
		lblTituloTesis.setBounds(69, 21, 587, 63);
		lblTituloTesis.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 24));
		lblTituloTesis.setHorizontalAlignment(SwingConstants.CENTER);
		getContentPane().add(lblTituloTesis);
		
		JLabel lbltitulo = new JLabel("T\u00EDtulo");
		lbltitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitulo.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 16));
		lbltitulo.setBounds(37, 111, 99, 46);
		getContentPane().add(lbltitulo);
		
		txttitulo = new JTextField();
		txttitulo.setBounds(186, 119, 333, 36);
		getContentPane().add(txttitulo);
		txttitulo.setColumns(10);
		
		JLabel lblTema = new JLabel("Tema");
		lblTema.setHorizontalAlignment(SwingConstants.CENTER);
		lblTema.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 16));
		lblTema.setBounds(37, 182, 99, 46);
		getContentPane().add(lblTema);
		
		txttema = new JTextField();
		txttema.setColumns(10);
		txttema.setBounds(186, 182, 333, 36);
		getContentPane().add(txttema);
		
		JLabel lblFecha = new JLabel("Fecha de creaci\u00F3n");
		lblFecha.setHorizontalAlignment(SwingConstants.CENTER);
		lblFecha.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 16));
		lblFecha.setBounds(10, 250, 142, 46);
		getContentPane().add(lblFecha);
		
		txtfechacreacion = new JTextField();
		txtfechacreacion.setColumns(10);
		txtfechacreacion.setBounds(186, 250, 333, 36);
		getContentPane().add(txtfechacreacion);
		
		JLabel lblalumno = new JLabel("Alumno");
		lblalumno.setHorizontalAlignment(SwingConstants.CENTER);
		lblalumno.setFont(new Font("Yu Gothic UI Semibold", Font.BOLD | Font.ITALIC, 16));
		lblalumno.setBounds(37, 307, 99, 46);
		getContentPane().add(lblalumno);
		
		cboalumno = new JComboBoxBD(rb.getString("SQL_ALUMNO1"));
		cboalumno.setBounds(186, 317, 142, 37);
		getContentPane().add(cboalumno);
		
		btnconfirmar = new JButton("Confirmar");
		btnconfirmar.addActionListener(this);
		btnconfirmar.setFont(new Font("Yu Gothic UI Semibold", Font.ITALIC, 17));
		btnconfirmar.setBounds(262, 399, 192, 72);
		getContentPane().add(btnconfirmar);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnconfirmar) {
			do_btnconfirmar_actionPerformed(e);
		}
	}
	public void mensaje(String ms){
		JOptionPane.showMessageDialog(this, ms);
	}
	
	protected void do_btnconfirmar_actionPerformed(ActionEvent e) {
		String tit = txttitulo.getText().trim();
		String tem = txttema.getText().trim();
		String fchc = txtfechacreacion.getText().trim();
		int posAlumno = cboalumno.getSelectedIndex();
		
		if(!tit.matches(Validaciones.TEXTO)) {
			mensaje ("El título es de 2 a 20 caracteres");
		}else if (!tem.matches(Validaciones.TEXTO)) {
			mensaje ("El tema es de 2 a 20 caracteres");
		}else if (!fchc.matches(Validaciones.FECHA)) {
			mensaje("La fecha tiene formato YYYY-MM-dd");
		}else if(posAlumno==0) {
			mensaje("Seleccione un Alumno");
		}else {
			String alumno = cboalumno.getSelectedItem().toString();
			String idAlumno = alumno.split(":")[0];
			
			Alumno objAlumno = new Alumno();
			objAlumno.setIdAlumno(Integer.parseInt(idAlumno));
			
			Tesis objTesis = new Tesis();
			objTesis.setTitulo(tit);
			objTesis.setTema(tem);
			objTesis.setFechaCreacion(Date.valueOf(fchc));
			objTesis.setFechaRegistro(Date.valueOf("2022-04-04"));
			objTesis.setEstado(1);
			objTesis.setAlumno(objAlumno);
			
			TesisModel model = new TesisModel();
			int s = model.insertaTesis(objTesis);
			
			if (s > 0) {
				mensaje("SE REGISTRO CORRECTAMENTE");
			}else {
				mensaje("ERROR EN EL REGISTRO");
			}
		}
	}
}
