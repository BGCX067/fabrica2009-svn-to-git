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

import swing.fabrica.controladores.ControladorComenzarFabricacion;
import swing.fabrica.modelos.BusinessDelegateComenzarFabricacion;
import swing.fabrica.vistas.VistaComenzarFabricacion;
import exception.LoadingException;
import fabrica.vo.ArticuloEnListadoVO;

public class DisenioComenzarFabricacion extends JFrame {

	private static final long serialVersionUID = 1L;
	private VistaComenzarFabricacion vistaPadre = null;
	private JTable tablaComenz;
	private JTable tablaPend;
	private JPanel a = new JPanel(new BorderLayout());
	private JPanel b = new JPanel(new BorderLayout());
	private DefaultTableModel tCom = new DefaultTableModel();
	private DefaultTableModel tPen = new DefaultTableModel();

	public VistaComenzarFabricacion getVistaPadre() {
		return vistaPadre;
	}

	public void setVistaPadre(VistaComenzarFabricacion vistaPadre) {
		this.vistaPadre = vistaPadre;
	}

	public DisenioComenzarFabricacion(VistaComenzarFabricacion vcf)
			throws HeadlessException, LoadingException {
		super();
		vistaPadre = vcf;
		initialize();
	}

	void componentes() throws LoadingException{
		
		ArrayList<ArticuloEnListadoVO> fabricacionesComenzadas =
			((BusinessDelegateComenzarFabricacion) (vistaPadre.getModelo()))
			.calculaArticulosComenzados();
		
		ArrayList<ArticuloEnListadoVO> fabricacionesPendientes =
			((BusinessDelegateComenzarFabricacion) (vistaPadre.getModelo()))
			.calculaArticulosPendientes();

		if (fabricacionesComenzadas!= null && fabricacionesPendientes != null){
		
			this.getContentPane().setLayout(null);
			this.getContentPane().add(getLabelFabrArt());
			this.getContentPane().add(getLabelArtComenz());
			this.getContentPane().add(getLabelArtPend());
			this.getContentPane().add(getPanelTablaComenz(fabricacionesComenzadas));
			this.getContentPane().add(getPanelTablaPend(fabricacionesPendientes));
			this.getContentPane().add(getButtonCerrar());
			
		}
		else{
			this.errorCargar();
		}
	}
	
	private JButton getButtonCerrar() {
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setLocation(480, 440);
		btnCerrar.setSize(80, 20);
		btnCerrar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				((ControladorComenzarFabricacion) (vistaPadre.getControlador())).cerrar();
			}
		});
		return btnCerrar;
	}
	private JLabel getLabelFabrArt() {
		JLabel lbl = new JLabel("Fabricacion de Articulos:");
		lbl.setLocation(10, 10);
		lbl.setSize(160, 20);
		return lbl;
	}

	private JLabel getLabelArtComenz() {
		JLabel lbl = new JLabel("Fabricacion Iniciada:");
		lbl.setLocation(10, 50);
		lbl.setSize(160, 20);
		return lbl;
	}

	private JLabel getLabelArtPend() {
		JLabel lbl = new JLabel("Fabricacion Pendiente:");
		lbl.setLocation(10, 250);
		lbl.setSize(160, 20);
		return lbl;
	}

	private JPanel getPanelTablaComenz(ArrayList<ArticuloEnListadoVO> fabricacionesComenzadas) {
		tablaComenz = DisenioTablasHelper.obtenerTablaArticulo(tCom);
		DisenioTablasHelper.rellenarTablaArticulo(tCom, fabricacionesComenzadas);
		b.setBounds(10, 80, 500, 150);
		b.add(tablaComenz.getTableHeader(), BorderLayout.NORTH);
		b.add(new JScrollPane(tablaComenz), BorderLayout.CENTER);
		return b;
	}

	private JPanel getPanelTablaPend(ArrayList<ArticuloEnListadoVO> fabricacionesPendientes) {
		tablaPend = DisenioTablasHelper.obtenerTablaArticulo(tPen);
		DisenioTablasHelper.rellenarTablaArticulo(tPen, fabricacionesPendientes);
		a.setBounds(10, 280, 500, 150);
		a.add(tablaPend.getTableHeader(), BorderLayout.NORTH);
		a.add(new JScrollPane(tablaPend), BorderLayout.CENTER);
		return a;
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

	public void cargarArticulos(ArrayList<ArticuloEnListadoVO> comenzar,
			ArrayList<ArticuloEnListadoVO> pendiente) {
		DisenioTablasHelper.rellenarTablaArticulo(tCom, comenzar);
		DisenioTablasHelper.rellenarTablaArticulo(tPen, pendiente);
	}

	private void initialize() throws LoadingException{
		this.setMinimumSize(new java.awt.Dimension(600, 500));
		this.setPreferredSize(new java.awt.Dimension(600, 500));
		this.setLocation(100, 100);
		this.setTitle("Comenzar Fabricacion");
		this.componentes();
		this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);
	}
}
