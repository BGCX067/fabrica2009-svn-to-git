/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.vistas;

import java.util.Observable;

import swing.fabrica.modelos.BusinessDelegateFabrica;
import swing.fabrica.mvcFramework.View;
import swing.fabrica.vistas.guis.DisenioFabrica;


public class VistaFabrica extends View {
	DisenioFabrica vistaGrafica;

	public VistaFabrica(BusinessDelegateFabrica mf) {
		super(mf);
		vistaGrafica = new DisenioFabrica(this);
		vistaGrafica.pack();
		vistaGrafica.setVisible(true);
	}

	public void update(Observable m, Object obj) {

	}

}
