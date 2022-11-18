package gui;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.SwingConstants;
import java.awt.Color;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;

import entidad.Categoria;
import entidad.Libro;
import model.LibroModel;
import util.JComboBoxBD;
import util.Validaciones;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.MouseListener;
import java.util.List;
import java.util.ResourceBundle;
import java.awt.event.MouseEvent;
import javax.swing.JCheckBox;

public class FrmCrudLibro extends JInternalFrame implements ActionListener, MouseListener {
	
	private ResourceBundle rb = ResourceBundle.getBundle("combo");

	private static final long serialVersionUID = 1L;
	private JTextField txtTitulo;
	private JTextField txtAnio;
	private JTextField txtSerie;
	private JTable table;
	private JButton btnIngresar;
	private JButton btnEliminar;
	private JButton btnActualizar;
	private JComboBoxBD cboCategoria;
	private JCheckBox chkEstado;
	
	int idSeleccionado = -1;

	public FrmCrudLibro() {
		getContentPane().setFont(new Font("Tahoma", Font.PLAIN, 18));
		setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		setMaximizable(true);
		setIconifiable(true);
		setClosable(true);
		setTitle("Mantenimiento de Libro");
		setBounds(100, 100, 900, 600);
		getContentPane().setLayout(null);
		
		JLabel lblMantenimientoLibro = new JLabel("MANTENIMIENTO DE LIBRO");
		lblMantenimientoLibro.setOpaque(true);
		lblMantenimientoLibro.setForeground(Color.WHITE);
		lblMantenimientoLibro.setBackground(new Color(51, 0, 255));
		lblMantenimientoLibro.setHorizontalAlignment(SwingConstants.CENTER);
		lblMantenimientoLibro.setFont(new Font("Tahoma", Font.BOLD, 25));
		lblMantenimientoLibro.setBounds(22, 11, 838, 76);
		getContentPane().add(lblMantenimientoLibro);
		
		JLabel lblTitulo = new JLabel("T\u00EDtulo");
		lblTitulo.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblTitulo.setBounds(37, 105, 127, 22);
		getContentPane().add(lblTitulo);
		
		JLabel lblAnio = new JLabel("A\u00F1o");
		lblAnio.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblAnio.setBounds(37, 150, 71, 22);
		getContentPane().add(lblAnio);
		
		JLabel lblSerie = new JLabel("Serie");
		lblSerie.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblSerie.setBounds(37, 197, 58, 14);
		getContentPane().add(lblSerie);
		
		JLabel lblCategoria = new JLabel("Categor\u00EDa");
		lblCategoria.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblCategoria.setBounds(37, 228, 127, 22);
		getContentPane().add(lblCategoria);
		
		txtTitulo = new JTextField();
		txtTitulo.setBounds(138, 109, 370, 20);
		getContentPane().add(txtTitulo);
		txtTitulo.setColumns(10);
		
		txtAnio = new JTextField();
		txtAnio.setBounds(139, 154, 111, 20);
		getContentPane().add(txtAnio);
		txtAnio.setColumns(10);
		
		txtSerie = new JTextField();
		txtSerie.setBounds(139, 197, 197, 20);
		getContentPane().add(txtSerie);
		txtSerie.setColumns(10);
	
		cboCategoria = new JComboBoxBD(rb.getString("SQL_CATEGORIA"));
		cboCategoria.setBounds(138, 231, 243, 22);
		getContentPane().add(cboCategoria);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 281, 864, 278);
		getContentPane().add(scrollPane);
		
		table = new JTable();
		table.addMouseListener(this);
		table.setModel(new DefaultTableModel(
			new Object[][] {
			},
			new String[] {
				"ID", "Título", "Año", "Serie", "Estado", "ID Categoría","Categoría"
			}
		));
		scrollPane.setViewportView(table);
		
		btnIngresar = new JButton("INGRESAR");
		btnIngresar.addActionListener(this);
		btnIngresar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnIngresar.setBounds(690, 102, 138, 51);
		getContentPane().add(btnIngresar);
		
		btnEliminar = new JButton("ELIMINAR");
		btnEliminar.addActionListener(this);
		btnEliminar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnEliminar.setBounds(690, 164, 138, 47);
		getContentPane().add(btnEliminar);
		
		btnActualizar = new JButton("ACTUALIZAR");
		btnActualizar.addActionListener(this);
		btnActualizar.setFont(new Font("Tahoma", Font.BOLD, 13));
		btnActualizar.setBounds(690, 219, 138, 51);
		getContentPane().add(btnActualizar);
		
		chkEstado = new JCheckBox("Activo");
		chkEstado.setFont(new Font("Tahoma", Font.PLAIN, 15));
		chkEstado.setBounds(464, 231, 97, 23);
		getContentPane().add(chkEstado);
		
		//Tamaño a las columnas
	  	table.getColumnModel().getColumn(0).setPreferredWidth(20);
  		table.getColumnModel().getColumn(1).setPreferredWidth(130);
  		table.getColumnModel().getColumn(2).setPreferredWidth(20);
	  	table.getColumnModel().getColumn(3).setPreferredWidth(60);
	  	table.getColumnModel().getColumn(4).setPreferredWidth(30);
	  	table.getColumnModel().getColumn(6).setPreferredWidth(40);
	  	scrollPane.setViewportView(table);
		
		//color de la fila seleccionada
		table.setSelectionBackground(Color.BLUE);
		
		//alineacion
		DefaultTableCellRenderer rightRenderer = new DefaultTableCellRenderer();
		rightRenderer.setHorizontalAlignment(JLabel.CENTER);
		table.getColumnModel().getColumn(0).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(2).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(4).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(5).setCellRenderer(rightRenderer);
		table.getColumnModel().getColumn(6).setCellRenderer(rightRenderer);
	
			
		//desabilita el cambio de tamaÃ±o
		table.getTableHeader().setResizingAllowed(false);
		
		//desabilita mover las columnas
		table.getTableHeader().setReorderingAllowed(false);
				
		//selecciona una sola fila
		table.setRowSelectionAllowed(true);
		table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		
		//Desahilitar la edicion en las celdas
		table.setDefaultEditor(Object.class, null);
		scrollPane.setViewportView(table);
				
		//Ocultar la fila	
		table.getColumnModel().getColumn(5).setMinWidth(0);
		table.getColumnModel().getColumn(5).setMaxWidth(0);
		
		lista();

	}
	void mensaje(String m) {
		JOptionPane.showMessageDialog(this, m);
	}
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnActualizar) {
			actionPerformedBtnActualizarJButton(e);
		}
		if (e.getSource() == btnEliminar) {
			actionPerformedBtnEliminarJButton(e);
		}
		if (e.getSource() == btnIngresar) {
			actionPerformedBtnIngresarJButton(e);
		}
	}
	protected void actionPerformedBtnIngresarJButton(ActionEvent e) {
		registra();
	}
	protected void actionPerformedBtnEliminarJButton(ActionEvent e) {
		elimina();
	}
	protected void actionPerformedBtnActualizarJButton(ActionEvent e) {
		actualiza();
	}
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == table) {
			mouseClickedTableJTable(e);
		}
	}
	public void mouseEntered(MouseEvent e) {
	}
	public void mouseExited(MouseEvent e) {
	}
	public void mousePressed(MouseEvent e) {
	}
	public void mouseReleased(MouseEvent e) {
	}
	protected void mouseClickedTableJTable(MouseEvent e) {
		busca();
	}
	
	public void lista(){
		DefaultTableModel dt = (DefaultTableModel) table.getModel();
		dt.setRowCount(0);
		
		LibroModel model = new LibroModel();
		List<Libro> lista = model.listaTodos();
		
		for (Libro x : lista) {
			Object[] f = {x.getIdLibro(), x.getTitulo(), x.getAnio(),x.getSerie(),  getDesEstado(x.getEstado()),x.getCategoria().getIdCategoria(), x.getCategoria().getDescripcion()};
			dt.addRow(f);
		}
		
	}
	public String getDesEstado(int x) {
		if (x == 0) return "Inactivo";
		else return 	   "Activo";
	}
	void limpiarCajasTexto() {
		txtTitulo.setText("");
		txtAnio.setText("");
		txtSerie.setText("");
		cboCategoria.setSelectedIndex(0);
		chkEstado.setSelected(true);
		txtTitulo.requestFocus();
	}
	
	public void registra() {
		String titulo = txtTitulo.getText().trim();
		String anio   = txtAnio.getText().trim();
		String serie = txtSerie.getText().trim();
		int categoria = cboCategoria.getSelectedIndex();
		boolean est = chkEstado.isSelected();
		
		if (!titulo.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje("El nombre es de 2 a 20 caracteres");
		}else if (!anio.matches(Validaciones.ANHO)) {
			mensaje("la fecha tiene formato YYYY");	
		}else if (!serie.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje ("El numero de serie no es valido");
		}else if (categoria == 0) {
			mensaje("Selecciona un País");
		}else {
			String cate = cboCategoria.getSelectedItem().toString();
			String idCategoria = cate.split(":")[0];
			
			Categoria objCategoria = new Categoria();
			objCategoria.setIdCategoria(Integer.parseInt(idCategoria));
			
			Libro objLibro = new Libro();
			objLibro.setTitulo(titulo);
			objLibro.setAnio(Integer.parseInt(anio));
			objLibro.setSerie(serie);			
			objLibro.setCategoria(objCategoria);
			if (est) 
				objLibro.setEstado(1);
			else 
				objLibro.setEstado(0);
			
			
			LibroModel model = new LibroModel();
			int salida = model.insertaLibro(objLibro);
			if (salida > 0) {
				lista();
				limpiarCajasTexto();
				mensaje("Se insertó correctamente");
			}else {
				mensaje("Error en el registro");
			}
		}
	}
	
	public void busca() {
		int posRow = table.getSelectedRow(); //seleccion la posicion de la fila 
		
		//obtiene la celda segun la fila y la columna
		idSeleccionado  = (Integer)table.getValueAt(posRow, 0); //idSeleccionado  es variable global
		String titulo = (String)table.getValueAt(posRow, 1);
		Integer anio = (Integer)table.getValueAt(posRow, 2);
		String serie = (String)table.getValueAt(posRow, 3);
		String estado = (String)table.getValueAt(posRow, 4);
		Integer idCategoria = (Integer)table.getValueAt(posRow, 5);
		String desCategoria = (String)table.getValueAt(posRow, 6);
		
		txtTitulo.setText(titulo);
		txtAnio.setText(String.valueOf(anio));
		txtSerie.setText(serie);
		cboCategoria.setSelectedItem(idCategoria + ": " + desCategoria);
		chkEstado.setSelected(estado.equals("Activo")?true:false);
		
	}
	public void elimina() {
		if (idSeleccionado == -1) {

			mensaje("Seleccione una Fila");

		}else {

			LibroModel model = new LibroModel();
			int salida = model.eliminaLibro(idSeleccionado);
			if (salida > 0) {
			
				limpiarCajasTexto();
				lista();
				idSeleccionado = -1;
				mensaje("Se eliminó correctamente");

			}else {
 			mensaje("Error en la eliminación");

			}
		}
		
	}
	public void actualiza() {
		String titulo = txtTitulo.getText().trim();
		String anio   = txtAnio.getText().trim();
		String serie = txtSerie.getText().trim();
		int categoria = cboCategoria.getSelectedIndex();
		boolean est = chkEstado.isSelected();
		
		if(idSeleccionado == -1) {
			mensaje("Seleccione una fila");
		}else if (!titulo.matches(Validaciones.TEXTO)) {
			mensaje("El nombre es de 2 a 20 caracteres");
		}else if (!anio.matches(Validaciones.ANHO)) {
			mensaje("la fecha tiene formato YYYY");	
		}else if (!serie.matches(Validaciones.TEXTO_NUMERO)) {
			mensaje ("El numero de serie no es valido");
		}else if (categoria == 0) {
			mensaje("Selecciona un País");
		}else {
			String cate = cboCategoria.getSelectedItem().toString();
			String idCategoria = cate.split(":")[0];
			
			Categoria objCategoria = new Categoria();
			objCategoria.setIdCategoria(Integer.parseInt(idCategoria));
			
			Libro objLibro = new Libro();
			objLibro.setIdLibro(idSeleccionado);
			objLibro.setTitulo(titulo);
			objLibro.setAnio(Integer.parseInt(anio));
			objLibro.setSerie(serie);			
			objLibro.setCategoria(objCategoria);
			if (est) 
				objLibro.setEstado(1);
			else 
				objLibro.setEstado(0);
			
			
			LibroModel model = new LibroModel();
			int salida = model.actualizaLibro(objLibro);
			if (salida > 0) {
				lista();
				idSeleccionado = -1;
				mensaje("Se actualizó");
				limpiarCajasTexto();
				
			}else {
				mensaje("Error en la actualizacion");
			}
		}

	}

}
