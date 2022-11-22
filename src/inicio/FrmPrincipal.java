package inicio;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import entidad.Opcion;
import gui.FrmConsultaAlumno;
import gui.FrmConsultaAutor;
import gui.FrmConsultaLibro;
import gui.FrmConsultaProveedor;
import gui.FrmConsultaSala;
import gui.FrmConsultaTesis;
import gui.FrmCrudAlumno;
import gui.FrmCrudAutor;
import gui.FrmCrudLibro;
import gui.FrmCrudProveedor;
import gui.FrmCrudSala;
import gui.FrmCrudTesis;
import gui.FrmRegistroAlumno;
import gui.FrmRegistroAutor;
import gui.FrmRegistroLibro;
import gui.FrmRegistroProveedor;
import gui.FrmRegistroSala;
import gui.FrmRegistroTesis;
import gui.FrmReporteAlumno;
import gui.FrmReporteAutor;
import gui.FrmReporteLibro;
import gui.FrmReporteProveedor;
import gui.FrmReporteSala;
import gui.FrmReporteTesis;
import model.UsuarioModel;
import util.DatosGlobales;

@SuppressWarnings("serial")
public class FrmPrincipal extends JFrame implements WindowListener, ActionListener {

	private JDesktopPane desktop = new JDesktopPane();

	// Paso 1: Agregar el formulario

	// Adminsitrativa
	private List<JMenuItem> listaItemMenus = new ArrayList<JMenuItem>();
	private List<JMenu> listaMenus = new ArrayList<JMenu>();
	private UsuarioModel model = new UsuarioModel();

	// Agrega el item de menu
	private JMenuItem mntRegAlumno;
	private JMenuItem mntRegAutor;
	private JMenuItem mntRegLibro;
	private JMenuItem mntRegSala;
	private JMenuItem mntRegTesis;
	private JMenuItem mntRegProveedor;
	
	private JMenuItem mntCrudAlumno;
	private JMenuItem mntCrudAutor;
	private JMenuItem mntCrudLibro;
	private JMenuItem mntCrudSala;
	private JMenuItem mntCrudTesis;
	private JMenuItem mntCrudProveedor;
	
	private JMenuItem mntConsultaAlumno;
	private JMenuItem mntConsultaAutor;
	private JMenuItem mntConsultaLibro;
	private JMenuItem mntConsultaSala;
	private JMenuItem mntConsultaTesis;
	private JMenuItem mntConsultaProveedor;
	
	private JMenuItem mntReporteAlumno;
	private JMenuItem mntReporteAutor;
	private JMenuItem mntReporteLibro;
	private JMenuItem mntReporteSala;
	private JMenuItem mntReporteTesis;
	private JMenuItem mntReporteProveedor;

	// Formularios
	public FrmRegistroAlumno frmRegistroAlumno = new FrmRegistroAlumno();
	public FrmRegistroAutor frmRegistroAutor = new FrmRegistroAutor();
	public FrmRegistroLibro frmRegistroLibro = new FrmRegistroLibro();
	public FrmRegistroSala frmRegistroSala = new FrmRegistroSala();
	public FrmRegistroTesis frmRegistroTesis = new FrmRegistroTesis();
	public FrmRegistroProveedor frmRegistroProveedor = new FrmRegistroProveedor();

	public FrmCrudAlumno frmCrudAlumno = new FrmCrudAlumno();
	public FrmCrudAutor frmCrudAutor = new FrmCrudAutor();
	public FrmCrudLibro frmCrudLibro = new FrmCrudLibro();
	public FrmCrudSala frmCrudSala = new FrmCrudSala();
	public FrmCrudTesis frmCrudTesis = new FrmCrudTesis();
	public FrmCrudProveedor frmCrudProveedor = new FrmCrudProveedor();

	public FrmConsultaAlumno frmConsultaAlumno = new FrmConsultaAlumno();
	public FrmConsultaAutor frmConsultaAutor = new FrmConsultaAutor();
	public FrmConsultaLibro frmConsultaLibro = new FrmConsultaLibro();
	public FrmConsultaSala frmConsultaSala = new FrmConsultaSala();
	public FrmConsultaTesis frmConsultaTesis = new FrmConsultaTesis();
	public FrmConsultaProveedor frmConsultaProveedor = new FrmConsultaProveedor();

	public FrmReporteAlumno frmReporteAlumno = new FrmReporteAlumno();
	public FrmReporteAutor frmReporteAutor = new FrmReporteAutor();
	public FrmReporteLibro frmReporteLibro = new FrmReporteLibro();
	public FrmReporteSala frmReporteSala = new FrmReporteSala();
	public FrmReporteTesis frmReporteTesis = new FrmReporteTesis();
	public FrmReporteProveedor frmReporteProveedor = new FrmReporteProveedor();

	public FrmPrincipal(String cad, int x, int y) {
		super(cad);
		this.setLocation(0, 0);
		this.setSize(636, 365);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.setResizable(false);

		desktop.setSize(1000, 500);
		desktop.setBackground(new Color(44, 62, 80));

		getContentPane().add(desktop, BorderLayout.CENTER);

		addWindowListener(this);

		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);

		JMenu mnAdministracin = new JMenu("Registros (PC1)");
		mnAdministracin.setVisible(false);
		menuBar.add(mnAdministracin);

		JMenu mnCrud = new JMenu("Crud (PC02)");
		mnCrud.setVisible(false);
		menuBar.add(mnCrud);

		JMenu mnConsultas = new JMenu("Consulta (PC03)");
		mnConsultas.setVisible(false);
		menuBar.add(mnConsultas);

		JMenu mnReporte = new JMenu("Reporte (PC03)");
		mnReporte.setVisible(false);
		menuBar.add(mnReporte);

		// pc01
		mntRegAlumno = new JMenuItem("Registro Alumno");
		mntRegAlumno.setVisible(false);
		mntRegAlumno.addActionListener(this);
		mnAdministracin.add(mntRegAlumno);

		mntRegAutor = new JMenuItem("Registro Autor");
		mntRegAutor.setVisible(false);
		mntRegAutor.addActionListener(this);
		mnAdministracin.add(mntRegAutor);

		mntRegLibro = new JMenuItem("Registro Libro");
		mntRegLibro.setVisible(false);
		mntRegLibro.addActionListener(this);
		mnAdministracin.add(mntRegLibro);

		mntRegSala = new JMenuItem("Registro Sala");
		mntRegSala.setVisible(false);
		mntRegSala.addActionListener(this);
		mnAdministracin.add(mntRegSala);

		mntRegTesis = new JMenuItem("Registro Tesis");
		mntRegTesis.setVisible(false);
		mntRegTesis.addActionListener(this);
		mnAdministracin.add(mntRegTesis);

		mntRegProveedor = new JMenuItem("Registro Proveedor");
		mntRegProveedor.setVisible(false);
		mntRegProveedor.addActionListener(this);
		mnAdministracin.add(mntRegProveedor);		
		
		// pc02
		mntCrudAlumno = new JMenuItem("Crud Alumno");
		mntCrudAlumno.setVisible(false);
		mntCrudAlumno.addActionListener(this);
		mnCrud.add(mntCrudAlumno);

		mntCrudAutor = new JMenuItem("Crud Autor");
		mntCrudAutor.setVisible(false);
		mntCrudAutor.addActionListener(this);
		mnCrud.add(mntCrudAutor);

		mntCrudLibro = new JMenuItem("Crud Libro");
		mntCrudLibro.setVisible(false);
		mntCrudLibro.addActionListener(this);
		mnCrud.add(mntCrudLibro);

		mntCrudSala = new JMenuItem("Crud Sala");
		mntCrudSala.setVisible(false);
		mntCrudSala.addActionListener(this);
		mnCrud.add(mntCrudSala);

		mntCrudTesis = new JMenuItem("Crud Tesis");
		mntCrudTesis.setVisible(false);
		mntCrudTesis.addActionListener(this);
		mnCrud.add(mntCrudTesis);


		mntCrudProveedor = new JMenuItem("Crud Proveedor");
		mntCrudProveedor.setVisible(false);
		mntCrudProveedor.addActionListener(this);
		mnCrud.add(mntCrudProveedor);
		
		// pc03
		mntConsultaAlumno = new JMenuItem("Consulta Alumno");
		mntConsultaAlumno.setVisible(false);
		mntConsultaAlumno.addActionListener(this);
		mnConsultas.add(mntConsultaAlumno);

		mntConsultaAutor = new JMenuItem("Consulta Autor");
		mntConsultaAutor.setVisible(false);
		mntConsultaAutor.addActionListener(this);
		mnConsultas.add(mntConsultaAutor);

		mntConsultaLibro = new JMenuItem("Consulta Libro");
		mntConsultaLibro.setVisible(false);
		mntConsultaLibro.addActionListener(this);
		mnConsultas.add(mntConsultaLibro);

		mntConsultaSala = new JMenuItem("Consulta Sala");
		mntConsultaSala.setVisible(false);
		mntConsultaSala.addActionListener(this);
		mnConsultas.add(mntConsultaSala);

		mntConsultaTesis = new JMenuItem("Consulta Tesis");
		mntConsultaTesis.setVisible(false);
		mntConsultaTesis.addActionListener(this);
		mnConsultas.add(mntConsultaTesis);
		
		mntConsultaProveedor = new JMenuItem("Consulta Proveedor");
		mntConsultaProveedor.setVisible(false);
		mntConsultaProveedor.addActionListener(this);
		mnConsultas.add(mntConsultaProveedor);
		
		
		// pc03
		mntReporteAlumno = new JMenuItem("Reporte Alumno");
		mntReporteAlumno.setVisible(false);
		mntReporteAlumno.addActionListener(this);
		mnReporte.add(mntReporteAlumno);

		mntReporteAutor = new JMenuItem("Reporte Autor");
		mntReporteAutor.setVisible(false);
		mntReporteAutor.addActionListener(this);
		mnReporte.add(mntReporteAutor);

		mntReporteLibro = new JMenuItem("Reporte Libro");
		mntReporteLibro.setVisible(false);
		mntReporteLibro.addActionListener(this);
		mnReporte.add(mntReporteLibro);

		mntReporteSala = new JMenuItem("Reporte Sala");
		mntReporteSala.setVisible(false);
		mntReporteSala.addActionListener(this);
		mnReporte.add(mntReporteSala);

		mntReporteTesis = new JMenuItem("Reporte Tesis");
		mntReporteTesis.setVisible(false);
		mntReporteTesis.addActionListener(this);
		mnReporte.add(mntReporteTesis);

		mntReporteProveedor = new JMenuItem("Reporte Proveedor");
		mntReporteProveedor.setVisible(false);
		mntReporteProveedor.addActionListener(this);
		mnReporte.add(mntReporteProveedor);

		// Paso 2: Se a�ade los menus a la lista
		listaMenus.add(mnAdministracin);
		listaMenus.add(mnCrud);
		listaMenus.add(mnConsultas);
		listaMenus.add(mnReporte);
		
		// Paso 3: Se a�ade los items de menu a la lista
		listaItemMenus.add(mntRegAlumno);
		listaItemMenus.add(mntRegAutor);
		listaItemMenus.add(mntRegLibro);
		listaItemMenus.add(mntRegSala);
		listaItemMenus.add(mntRegTesis);
		listaItemMenus.add(mntRegProveedor);

		listaItemMenus.add(mntCrudAlumno);
		listaItemMenus.add(mntCrudAutor);
		listaItemMenus.add(mntCrudLibro);
		listaItemMenus.add(mntCrudSala);
		listaItemMenus.add(mntCrudTesis);
		listaItemMenus.add(mntCrudProveedor);
		
		listaItemMenus.add(mntConsultaAlumno);
		listaItemMenus.add(mntConsultaAutor);
		listaItemMenus.add(mntConsultaLibro);
		listaItemMenus.add(mntConsultaSala);
		listaItemMenus.add(mntConsultaTesis);
		listaItemMenus.add(mntConsultaProveedor);
		
		listaItemMenus.add(mntReporteAlumno);
		listaItemMenus.add(mntReporteAutor);
		listaItemMenus.add(mntReporteLibro);
		listaItemMenus.add(mntReporteSala);
		listaItemMenus.add(mntReporteTesis);
		listaItemMenus.add(mntReporteProveedor);
	
		// Paso 4: Se los formularios al contenedor
		desktop.add(frmRegistroAlumno);
		desktop.add(frmRegistroAutor);
		desktop.add(frmRegistroLibro);
		desktop.add(frmRegistroSala);
		desktop.add(frmRegistroTesis);
		desktop.add(frmRegistroProveedor);

		desktop.add(frmCrudAlumno);
		desktop.add(frmCrudAutor);
		desktop.add(frmCrudLibro);
		desktop.add(frmCrudSala);
		desktop.add(frmCrudTesis);
		desktop.add(frmCrudProveedor);

		desktop.add(frmConsultaAlumno);
		desktop.add(frmConsultaAutor);
		desktop.add(frmConsultaLibro);
		desktop.add(frmConsultaSala);
		desktop.add(frmConsultaTesis);
		desktop.add(frmConsultaProveedor);

		desktop.add(frmReporteAlumno);
		desktop.add(frmReporteAutor);
		desktop.add(frmReporteLibro);
		desktop.add(frmReporteSala);
		desktop.add(frmReporteTesis);
		desktop.add(frmReporteProveedor);		
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.acryl.AcrylLookAndFeel");

			FrmPrincipal jf = new FrmPrincipal("Sistema de gesti�n de biblioteca", 900, 600);
			jf.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
			jf.setVisible(false);

			FrmLogin frm = new FrmLogin(jf);
			frm.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
			frm.setVisible(true);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void windowOpened(WindowEvent e) {
	}

	public void windowClosing(WindowEvent e) {
		int n = JOptionPane.showConfirmDialog(e.getWindow(), "Desea cerrar la aplicaci\\u00f3n?", "Confirmaci\\u00f3n",
				JOptionPane.YES_NO_OPTION);
		if (n == JOptionPane.YES_OPTION) {
			System.exit(0);
		}
	}

	public void windowClosed(WindowEvent e) {
	}

	public void windowIconified(WindowEvent e) {
	}

	public void windowDeiconified(WindowEvent e) {
	}

	public void windowActivated(WindowEvent e) {
	}

	public void windowDeactivated(WindowEvent e) {
	}

	public void actionPerformed(ActionEvent arg0) {
		//PC01 Registros
		if (arg0.getSource() == mntRegAlumno) {
			centrar(frmRegistroAlumno);
			frmRegistroAlumno.setVisible(true);
		}
		if (arg0.getSource() == mntRegAutor) {
			centrar(frmRegistroAutor);
			frmRegistroAutor.setVisible(true);
		}
		if (arg0.getSource() == mntRegLibro) {
			centrar(frmRegistroLibro);
			frmRegistroLibro.setVisible(true);
		}
		if (arg0.getSource() == mntRegSala) {
			centrar(frmRegistroSala);
			frmRegistroSala.setVisible(true);
		}
		if (arg0.getSource() == mntRegTesis) {
			centrar(frmRegistroTesis);
			frmRegistroTesis.setVisible(true);
		}
		if (arg0.getSource() == mntRegProveedor) {
			centrar(frmRegistroProveedor);
			frmRegistroProveedor.setVisible(true);
		}

		//PC02 Crud
		if (arg0.getSource() == mntCrudAlumno) {
			centrar(frmCrudAlumno);
			frmCrudAlumno.setVisible(true);
		}
		if (arg0.getSource() == mntCrudAutor) {
			centrar(frmCrudAutor);
			frmCrudAutor.setVisible(true);
		}
		if (arg0.getSource() == mntCrudLibro) {
			centrar(frmCrudLibro);
			frmCrudLibro.setVisible(true);
		}
		if (arg0.getSource() == mntCrudSala) {
			centrar(frmCrudSala);
			frmCrudSala.setVisible(true);
		}
		if (arg0.getSource() == mntCrudTesis) {
			centrar(frmCrudTesis);
			frmCrudTesis.setVisible(true);
		}
		if (arg0.getSource() == mntCrudProveedor) {
			centrar(frmCrudProveedor);
			frmCrudProveedor.setVisible(true);
		}

		//PC03 Consultas
		if (arg0.getSource() == mntConsultaAlumno) {
			centrar(frmConsultaAlumno);
			frmConsultaAlumno.setVisible(true);
		}
		if (arg0.getSource() == mntConsultaAutor) {
			centrar(frmConsultaAutor);
			frmConsultaAutor.setVisible(true);
		}
		if (arg0.getSource() == mntConsultaLibro) {
			centrar(frmConsultaLibro);
			frmConsultaLibro.setVisible(true);
		}
		if (arg0.getSource() == mntConsultaSala) {
			centrar(frmConsultaSala);
			frmConsultaSala.setVisible(true);
		}
		if (arg0.getSource() == mntConsultaTesis) {
			centrar(frmConsultaTesis);
			frmConsultaTesis.setVisible(true);
		}
		if (arg0.getSource() == mntConsultaProveedor) {
			centrar(frmConsultaProveedor);
			frmConsultaProveedor.setVisible(true);
		}
		
		//PC03 reportes
		if (arg0.getSource() == mntReporteAlumno) {
			centrar(frmReporteAlumno);
			frmReporteAlumno.setVisible(true);
		}
		if (arg0.getSource() == mntReporteAutor) {
			centrar(frmReporteAutor);
			frmReporteAutor.setVisible(true);
		}
		if (arg0.getSource() == mntReporteLibro) {
			centrar(frmReporteLibro);
			frmReporteLibro.setVisible(true);
		}
		if (arg0.getSource() == mntReporteSala) {
			centrar(frmReporteSala);
			frmReporteSala.setVisible(true);
		}
		if (arg0.getSource() == mntReporteTesis) {
			centrar(frmReporteTesis);
			frmReporteTesis.setVisible(true);
		}
		if (arg0.getSource() == mntReporteProveedor) {
			centrar(frmReporteProveedor);
			frmReporteProveedor.setVisible(true);
		}
				
		
	}

	public void muestraOpciones() {
		// Mostrar los items
		List<Opcion> data = model.obtieneOpciones(DatosGlobales.ID_USUARIO);
		for (Opcion aux : data) {
			for (JMenuItem aux2 : listaItemMenus) {
				if (aux.getNombre().equalsIgnoreCase(aux2.getText())) {
					aux2.setVisible(true);
					break;
				}
			}
		}
		// muestra los menus
		for (JMenu aux : listaMenus) {
			verificaMenu(aux);
		}
	}

	public void verificaMenu(JMenu aux) {
		for (int i = 0; i < aux.getItemCount(); i++) {
			if (aux.getItem(i).isVisible()) {
				aux.setVisible(true);
				break;
			}
		}
	}

	public void centrar(JInternalFrame frm) {
		// Dimensiones de la pantalla
		Dimension pantalla = Toolkit.getDefaultToolkit().getScreenSize();
		// Dimensiones del formulario
		Dimension ventana = frm.getSize();

		int posX = (int) (pantalla.getWidth() - ventana.getWidth()) / 2;
		frm.setLocation(posX, 40);
	}
}