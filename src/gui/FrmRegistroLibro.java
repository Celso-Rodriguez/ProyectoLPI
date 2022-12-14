package gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ResourceBundle;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import entidad.Categoria;
import entidad.Libro;
import model.LibroModel;
import util.JComboBoxBD;
import util.Validaciones;

public class FrmRegistroLibro extends JInternalFrame implements ActionListener {
	
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	private static final long serialVersionUID = 1L;
	private JTextField txtTitulo;
	private JTextField txtAnio;
	private JTextField txtSerie;
	private JComboBoxBD cboCategoria;
	private JButton btnRegistrar;

	public FrmRegistroLibro() {
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Ingreso de Libro");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		JLabel lblNewLabel = new JLabel("REGISTRO DE LIBRO");
		lblNewLabel.setBackground(Color.BLUE);
		lblNewLabel.setOpaque(true);
		lblNewLabel.setForeground(Color.WHITE);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(10, 11, 864, 80);
		getContentPane().add(lblNewLabel);
		
		JLabel lblTitulo = new JLabel("T\u00EDtulo");
		lblTitulo.setForeground(Color.BLACK);
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setBounds(48, 142, 46, 28);
		getContentPane().add(lblTitulo);
		
		JLabel lblAnio = new JLabel("A\u00F1o");
		lblAnio.setForeground(Color.BLACK);
		lblAnio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAnio.setBounds(48, 194, 46, 29);
		getContentPane().add(lblAnio);
		
		JLabel lblSerie = new JLabel("Serie");
		lblSerie.setForeground(Color.BLACK);
		lblSerie.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSerie.setBounds(48, 258, 46, 32);
		getContentPane().add(lblSerie);
		
		JLabel lblCategoria = new JLabel("Categor\u00EDa");
		lblCategoria.setForeground(Color.BLACK);
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCategoria.setBounds(48, 328, 97, 31);
		getContentPane().add(lblCategoria);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(158, 149, 472, 20);
		getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		
		txtAnio = new JTextField();
		txtAnio.setBounds(158, 201, 127, 20);
		getContentPane().add(txtAnio);
		txtAnio.setColumns(10);
		
		txtSerie = new JTextField();
		txtSerie.setBounds(158, 267, 179, 20);
		getContentPane().add(txtSerie);
		txtSerie.setColumns(10);
		
		cboCategoria = new JComboBoxBD(rb.getString("SQL_CATEGORIA"));
		cboCategoria.setBounds(158, 335, 355, 28);
		getContentPane().add(cboCategoria);
		
		btnRegistrar = new JButton("REGISTRAR");
		btnRegistrar.setFont(new Font("Tahoma", Font.PLAIN, 15));
		btnRegistrar.addActionListener(this);
		btnRegistrar.setBounds(342, 450, 216, 80);
		getContentPane().add(btnRegistrar);
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
		
		String tit  = txtTitulo.getText().trim();
		String anio = txtAnio.getText().trim();
		String ser  = txtSerie.getText().trim();														
		int  index  = cboCategoria.getSelectedIndex();
		
		if(!tit.matches(Validaciones.TEXTO_NUMERO)){
			mensaje ("El titulo debe tener de 2 a 20 caracteres");
		}else if (!anio.matches(Validaciones.ANNO)) {
			mensaje ("El a?o debe tener 4 digitos");
		}else if (!ser.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje ("El numero de serie no es valido");
		}else if(index==0) {
			mensaje ("Seleccione un categoria");
		}else {
			String cate        = cboCategoria.getSelectedItem().toString();
			String idCategoria = cate.split(":")[0];
			
			Categoria objCategoria = new Categoria();
			objCategoria.setIdCategoria(Integer.parseInt(idCategoria));
			
			Libro objLibro = new Libro();
			objLibro.setTitulo(tit);
			objLibro.setAnio(Integer.parseInt(anio));
			objLibro.setSerie(ser);
			objLibro.setEstado(1);
			objLibro.setCategoria(objCategoria);
			
			LibroModel model = new LibroModel();
			int s = model.insertaLibro(objLibro);
			
			if (s > 0) {
				mensaje ("SE INSERTO CORRECTAMENTE");
			}else {
				mensaje("ERROR EN EL REGISTRO");
			}
		}
		
	}
}
