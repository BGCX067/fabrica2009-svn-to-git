/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.mvcFramework;

import java.util.Observer;

public abstract class View implements Observer {
	Model modelo;
	Controller controlador;

	public View(Model mod) {
		modelo = mod;
		mod.addObserver(this);
	}

	public void addControlador(Controller cp) {
		controlador = cp;
	}

	public Model getModelo() {
		return modelo;
	}

	public Controller getControlador() {
		return controlador;
	}
	
}
