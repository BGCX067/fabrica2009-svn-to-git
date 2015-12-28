/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.vistas.guis;

import java.awt.Color;
import java.awt.Font;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import swing.fabrica.controladores.ControladorFabrica;
import swing.fabrica.vistas.VistaFabrica;

public class DisenioFabrica extends JFrame {

	private static final long serialVersionUID = 1L;
	private VistaFabrica vistaPadre = null;
	private JPanel a = new JPanel();

	public VistaFabrica getVistaPadre() {
		return vistaPadre;
	}

	public void setVistaPadre(VistaFabrica vistaPadre) {
		this.vistaPadre = vistaPadre;
	}

	public DisenioFabrica(VistaFabrica vf) throws HeadlessException {
		super();
		vistaPadre = vf;
		initialize();
	}
	
	

	void componentes() {
		this.getContentPane().setLayout(null);
		this.getContentPane().add(getLabel());
		this.getContentPane().add(getPanel());
		this.getContentPane().add(getBtnNvoArt());
		this.getContentPane().add(getBtnNvoArt());
		this.getContentPane().add(getBtnSolFabr());
		this.getContentPane().add(getBtnRepArt());
		this.getContentPane().add(getBtnComFabr());
		this.getContentPane().add(getButtonCerrar());
		this.getContentPane().add(getBtnEnvProv());
	}
	private JButton getButtonCerrar() {
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setLocation(480, 440);
		btnCerrar.setSize(80, 20);
		btnCerrar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				System.exit(0);
			}
		});
		return btnCerrar;
	}
	private JLabel getLabel() {
		JLabel titulo = new JLabel(" Menu Principal: FABRICA");
		Font f = new Font("Verdana", Font.PLAIN, 20);
		titulo.setFont(f);
		titulo.setForeground(Color.LIGHT_GRAY);
		titulo.setLocation(10, 10);
		titulo.setBackground(Color.black);
		titulo.setBorder(BorderFactory.createLineBorder(Color.gray, 5));
		titulo.setSize(300, 40);
		return titulo;
	}

	private JButton getBtnNvoArt() {
		JButton boton = new JButton("Recibir informacion nuevo articulo");
		boton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				((ControladorFabrica) (vistaPadre.getControlador())).llamarNuevoArticulo();
			}
		});
		boton.setBounds(200, 170, 230, 20);
		return boton;
	}

	private JButton getBtnSolFabr() {
		JButton boton = new JButton("Solicitar Fabricacion");
		boton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				((ControladorFabrica) (vistaPadre.getControlador())).llamarSolicitudFabricacion();
			}
		});
		boton.setBounds(200, 200, 230, 20);
		return boton;
	}

	private JButton getBtnRepArt() {
		JButton boton = new JButton("Reposicion de articulos");
		boton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				((ControladorFabrica) (vistaPadre.getControlador())).llamarReposicionArticulos();
			}
		});
		boton.setBounds(200, 230, 230, 20);
		return boton;
	}

	private JButton getBtnComFabr() {
		JButton boton = new JButton("Comenzar fabricacion");
		boton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				((ControladorFabrica) (vistaPadre.getControlador())).llamarComenzarFabricacion();
			}
		});
		boton.setBounds(200, 260, 230, 20);
		return boton;
	}
	
	private JButton getBtnEnvProv() {
		JButton boton = new JButton("Cargar Envio a Proveedores");
		boton.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e) {
				((ControladorFabrica) (vistaPadre.getControlador())).llamarEnvioProveedores();
			}
		});
		boton.setBounds(200, 290, 230, 20);
		return boton;
	}

	private JPanel getPanel() {
		a.setLayout(null);
		a.setOpaque(false);
		a.setBounds(60, 150, 500, 200);
		a.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
		return a;
	}

	private void initialize() {
		this.setMinimumSize(new java.awt.Dimension(600, 500));
		this.setPreferredSize(new java.awt.Dimension(600, 500));
		this.setTitle("Modulo Fabrica");
		this.componentes();
		this.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
	}

}
