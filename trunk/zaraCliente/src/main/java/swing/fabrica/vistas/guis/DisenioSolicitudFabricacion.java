/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.vistas.guis;

import java.awt.BorderLayout;
import java.awt.HeadlessException;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import swing.fabrica.controladores.ControladorSolicitudFabricacion;
import swing.fabrica.modelos.BusinessDelegateSolicitudFabricacion;
import swing.fabrica.vistas.VistaSolicitudFabricacion;
import exception.LoadingException;
import fabrica.vo.ArticuloEnListadoVO;


public class DisenioSolicitudFabricacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private VistaSolicitudFabricacion vistaPadre = null;
	private JTable tabla;
	private JPanel a = new JPanel(new BorderLayout());
	private DefaultTableModel t = new DefaultTableModel();
	private String path = System.getProperty("user.dir");
	private File file = new File(path);
	private JFileChooser jfc = new JFileChooser(file);

	public VistaSolicitudFabricacion getVistaPadre() {
		return vistaPadre;
	}

	public void setVistaPadre(VistaSolicitudFabricacion vistaPadre) {
		this.vistaPadre = vistaPadre;
	}

	public DisenioSolicitudFabricacion(VistaSolicitudFabricacion vsf)	throws HeadlessException, LoadingException {
		super();
		vistaPadre = vsf;
		initialize();
	}

	void componentes() throws LoadingException {
		
		ArrayList<ArticuloEnListadoVO> articulos = ((BusinessDelegateSolicitudFabricacion) (vistaPadre
				.getModelo())).cargarSolicitudesAnteriores();
		if (articulos != null){
			this.getContentPane().setLayout(null);
			this.getContentPane().add(getLabelSolFabr());
			this.getContentPane().add(getButtonSolFabr());
			this.getContentPane().add(getFileChooser());
			this.getContentPane().add(getLabelArticulosSolicitados());
			this.getContentPane().add(getPanelTabla(articulos));
			this.getContentPane().add(getButtonCerrar());
		}else{
			this.errorCargar();
			throw new LoadingException();
		}
	}
	
	private JButton getButtonCerrar() {
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setLocation(480, 430);
		btnCerrar.setSize(80, 20);
		btnCerrar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				((ControladorSolicitudFabricacion) (vistaPadre.getControlador())).cerrar();
			}
		});
		return btnCerrar;
	}

	private JLabel getLabelSolFabr() {
		JLabel lbl = new JLabel("Solicitud Fabricacion");
		lbl.setLocation(10, 10);
		lbl.setSize(120, 20);
		return lbl;
	}

	private JButton getButtonSolFabr() {
		JButton cbo = new JButton("Cargar Solicitud");
		cbo.setBounds(150, 10, 150, 20);
		cbo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				((ControladorSolicitudFabricacion) (vistaPadre.getControlador())).ObtenerNuevaSol();
			}
		});
		return cbo;
	}

	private JLabel getLabelArticulosSolicitados() {
		JLabel lbl = new JLabel("Articulos Solicitados:");
		lbl.setLocation(10, 70);
		lbl.setSize(200, 20);
		return lbl;
	}
	public void errorArchivo() {
		JOptionPane.showMessageDialog(getContentPane(),
				"Error Parseando El XML", "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	private JFileChooser getFileChooser() {
		if (jfc.isFileHidingEnabled())
			jfc.setFileHidingEnabled(false);
		return jfc;
	}

	private JPanel getPanelTabla(ArrayList<ArticuloEnListadoVO> articulos) {
		tabla = DisenioTablasHelper.obtenerTablaArticulo(t);
		DisenioTablasHelper.rellenarTablaArticulo(t, articulos);
		a.setBounds(10, 40 + 50, 500, 200);
		a.add(tabla.getTableHeader(), BorderLayout.NORTH);
		a.add(new JScrollPane(tabla), BorderLayout.CENTER);
		return a;
	}
	
	public void okGuardar() {
		JOptionPane.showMessageDialog(getContentPane(),
				"Carga de Solicitud Exitosa", "OK",
				JOptionPane.INFORMATION_MESSAGE);
	}
	
	public void errorCargar(){
		int i = JOptionPane.showConfirmDialog(getContentPane()
				,"No se pudo establecer la conexion con el Servidor. Consulte el Log"
				,"Solicitud Fabricacion"
				,JOptionPane.DEFAULT_OPTION , JOptionPane.INFORMATION_MESSAGE);
		if (i==JOptionPane.OK_OPTION){
			dispose();
		}
	}


	public void agregarNvaSol() {
		int estado = jfc.showOpenDialog(null);
		((ControladorSolicitudFabricacion) (this.getVistaPadre()
				.getControlador())).procesarSolicitudFabricacion(estado, jfc.getSelectedFile());
		getContentPane().remove(jfc);
	}

	public void cargarArticulos(ArrayList<ArticuloEnListadoVO> articulos) {
		DisenioTablasHelper.rellenarTablaArticulo(t, articulos);
		this.okGuardar();
	}

	private void initialize() throws LoadingException {
		this.setMinimumSize(new java.awt.Dimension(600, 500));
		this.setPreferredSize(new java.awt.Dimension(600, 500));
		this.setLocation(100, 100);
		this.setTitle("Solicitudes de Fabricacion");
		this.componentes();
		this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
	}

}
