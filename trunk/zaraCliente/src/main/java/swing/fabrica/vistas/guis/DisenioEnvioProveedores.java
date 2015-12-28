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

import swing.fabrica.controladores.ControladorEnvioProveedores;
import swing.fabrica.vistas.VistaEnvioProveedores;
import fabrica.vo.MateriaPrimaVO;

public class DisenioEnvioProveedores extends JFrame {

	private static final long serialVersionUID = 1L;
	private VistaEnvioProveedores vistaPadre = null;
	private JTable tabla;
	private JPanel a = new JPanel(new BorderLayout());
	private DefaultTableModel t = new DefaultTableModel();
	private String path = System.getProperty("user.dir");
	private File file = new File(path);
	private JFileChooser jfc = new JFileChooser(file);

	public VistaEnvioProveedores getVistaPadre() {
		return vistaPadre;
	}

	public void setVistaPadre(VistaEnvioProveedores vistaPadre) {
		this.vistaPadre = vistaPadre;
	}

	public DisenioEnvioProveedores(VistaEnvioProveedores vep)
			throws HeadlessException {
		super();
		vistaPadre = vep;
		initialize();
	}

	void componentes() {
		this.getContentPane().setLayout(null);
		this.getContentPane().add(getLabelEnvProv());
		this.getContentPane().add(getButtonEnvProv());
		this.getContentPane().add(getFileChooser());
		this.getContentPane().add(getLabelMateriasPrimas());
		this.getContentPane().add(getPanelTabla());
		this.getContentPane().add(getButtonCerrar());
	}

	private JLabel getLabelEnvProv() {
		JLabel lbl = new JLabel("Envio de proveedores");
		lbl.setLocation(10, 10);
		lbl.setSize(130, 20);
		return lbl;
	}
	private JButton getButtonCerrar() {
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setLocation(480, 430);
		btnCerrar.setSize(80, 20);
		btnCerrar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				((ControladorEnvioProveedores) (vistaPadre.getControlador())).cerrar();
			}
		});
		return btnCerrar;
	}

	private JButton getButtonEnvProv() {
		JButton cbo = new JButton("Cargar Envio");
		cbo.setBounds(150, 10, 110, 20);
		cbo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				((ControladorEnvioProveedores) (vistaPadre.getControlador()))
						.obtenerMateriasPrimas();
			}
		});
		return cbo;
	}

	private JFileChooser getFileChooser() {
		if (jfc.isFileHidingEnabled())
			jfc.setFileHidingEnabled(false);
		return jfc;
	}

	private JLabel getLabelMateriasPrimas() {
		JLabel lbl = new JLabel("Materias Primas Recibidas:");
		lbl.setLocation(10, 70);
		lbl.setSize(200, 20);
		return lbl;
	}

	private JPanel getPanelTabla() {
		tabla = DisenioTablasHelper.obtenerTablaMateriaPrima(t);
		a.setBounds(10, 40 + 50, 410, 200);
		a.add(tabla.getTableHeader(), BorderLayout.NORTH);
		a.add(new JScrollPane(tabla), BorderLayout.CENTER);
		return a;
	}

	public void okGuardar() {
		JOptionPane.showMessageDialog(getContentPane(),
				"Carga de Pedido Exitosa", "OK",
				JOptionPane.INFORMATION_MESSAGE);
	}
	public void errorCargar(){
		JOptionPane.showConfirmDialog(getContentPane()
				,"No se pudo establecer la conexion con el Servidor. Consulte el Log"
				,"Solicitud Fabricacion"
				,JOptionPane.DEFAULT_OPTION , JOptionPane.INFORMATION_MESSAGE);
	}
	public void errorArchivo() {
		JOptionPane.showMessageDialog(getContentPane(),
				"Error Parseando El XML", "Error",
				JOptionPane.ERROR_MESSAGE);
	}


	public void agregarMateriasPrimas() {
		int estado = jfc.showOpenDialog(null);
		((ControladorEnvioProveedores) (this.getVistaPadre().getControlador()))
				.procesarEnvioProveedores(estado, jfc.getSelectedFile());
		getContentPane().remove(jfc);
	}

	public void cargarMateriasPrimas(ArrayList<MateriaPrimaVO> materiasPrimas) {
		DisenioTablasHelper.rellenarTablaMateriaPrima(t, materiasPrimas);
		this.okGuardar();
	}

	private void initialize() {
		this.setMinimumSize(new java.awt.Dimension(600, 500));
		this.setPreferredSize(new java.awt.Dimension(600, 500));
		this.setLocation(100, 100);
		this.setTitle("Envio de proveedores");
		this.componentes();
		this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
	}

}
