/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.vistas;

import java.util.ArrayList;
import java.util.Observable;

import swing.fabrica.modelos.BusinessDelegateEnvioProveedores;
import swing.fabrica.mvcFramework.View;
import swing.fabrica.vistas.guis.DisenioEnvioProveedores;

import fabrica.vo.MateriaPrimaVO;

public class VistaEnvioProveedores extends View {
	DisenioEnvioProveedores vistaGrafica;

	public VistaEnvioProveedores(BusinessDelegateEnvioProveedores mep) {
		super(mep);
		vistaGrafica = new DisenioEnvioProveedores(this);
		vistaGrafica.pack();
		vistaGrafica.setVisible(true);
	}

	public void agregarMatPrimas() {
		vistaGrafica.agregarMateriasPrimas();
	}
	
	public void errorCargar(){
		vistaGrafica.errorCargar();
	}
	
	public void errorArchivo(){
		vistaGrafica.errorArchivo();
	}
	
	public void cerrar(){
		vistaGrafica.dispose();
	}

	public void cargarArticulos(ArrayList<MateriaPrimaVO> materiasPrimas) {
		vistaGrafica.cargarMateriasPrimas(materiasPrimas);
	}

	public void update(Observable m, Object obj) {

	}

}
