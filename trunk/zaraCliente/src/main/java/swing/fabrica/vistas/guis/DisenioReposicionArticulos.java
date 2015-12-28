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
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import swing.fabrica.controladores.ControladorReposicionArticulos;
import swing.fabrica.modelos.BusinessDelegateReposicionArticulos;
import swing.fabrica.vistas.VistaReposicionArticulos;
import exception.LoadingException;
import fabrica.vo.ArticuloEnListadoVO;

public class DisenioReposicionArticulos extends JFrame {

	private static final long serialVersionUID = 1L;
	private VistaReposicionArticulos vistaPadre = null;
	private JTable tabla;
	private JPanel a = new JPanel(new BorderLayout());
	private DefaultTableModel t = new DefaultTableModel();
	private JLabel lblLog;

	public VistaReposicionArticulos getVistaPadre() {
		return vistaPadre;
	}

	public void setVistaPadre(VistaReposicionArticulos vistaPadre) {
		this.vistaPadre = vistaPadre;
	}

	public DisenioReposicionArticulos(VistaReposicionArticulos vra)	throws HeadlessException, LoadingException {
		super();
		vistaPadre = vra;
		initialize();
	}

	void componentes() throws LoadingException {
		
		
		ArrayList<ArticuloEnListadoVO> articulosPorCD = ((BusinessDelegateReposicionArticulos) (vistaPadre
				.getModelo())).calculaArticulosPorCD();
		
		if (articulosPorCD != null){
		
			this.getContentPane().setLayout(null);
			this.getContentPane().add(getLabelRepos());
			this.getContentPane().add(getLabelArticulosAEnviar());
			this.getContentPane().add(getPanelTabla(articulosPorCD));
			this.getContentPane().add(getLabelLog());
			this.getContentPane().add(getButtonCerrar());
		}
		else{
			this.errorCargar();
		}
	}
	
	private JButton getButtonCerrar() {
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setLocation(480, 430);
		btnCerrar.setSize(80, 20);
		btnCerrar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				((ControladorReposicionArticulos) (vistaPadre.getControlador())).cerrar();
			}
		});
		return btnCerrar;
	}

	private JLabel getLabelRepos() {
		JLabel lbl = new JLabel("Reposicion Articulos");
		lbl.setLocation(10, 10);
		lbl.setSize(120, 20);
		return lbl;
	}

	private JLabel getLabelArticulosAEnviar() {
		JLabel lbl = new JLabel("Articulos a Enviar:");
		lbl.setLocation(10, 70);
		lbl.setSize(200, 20);
		return lbl;
	}

	private JPanel getPanelTabla(ArrayList<ArticuloEnListadoVO> articulosPorCD) {
		tabla = DisenioTablasHelper.obtenerTablaArticuloPorCD(t);
		this.cargarArticulos(articulosPorCD);
		a.setBounds(10, 40 + 50, 500, 200);
		a.add(tabla.getTableHeader(), BorderLayout.NORTH);
		a.add(new JScrollPane(tabla), BorderLayout.CENTER);
		return a;
	}

	private JLabel getLabelLog() {
		lblLog = new JLabel("La operacion se realizo con exito.");
		lblLog.setLocation(200, 40 * 8 + 50);
		lblLog.setSize(300, 20);
		lblLog.setVisible(false);
		return lblLog;
	}
	public void errorCargar() throws LoadingException{
		int i = JOptionPane.showConfirmDialog(getContentPane()
				,"No se pudo establecer la conexion con el Servidor. Consulte el Log"
				,"Solicitud Fabricacion"
				,JOptionPane.DEFAULT_OPTION , JOptionPane.INFORMATION_MESSAGE);
		if (i==JOptionPane.OK_OPTION){
			throw new LoadingException();
		}
	}

	public void cargarArticulos(ArrayList<ArticuloEnListadoVO> articulosPorCD) {
		DisenioTablasHelper.rellenarTablaArticuloPorCD(t, articulosPorCD);
	}

	private void initialize() throws LoadingException {
		this.setMinimumSize(new java.awt.Dimension(600, 500));
		this.setPreferredSize(new java.awt.Dimension(600, 500));
		this.setLocation(100, 100);
		this.setTitle("Reposicion de Articulos");
		this.componentes();
		this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
	}

}
