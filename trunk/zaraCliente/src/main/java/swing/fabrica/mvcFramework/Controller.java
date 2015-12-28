/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.mvcFramework;

public abstract class Controller {
	View vista;
	Model modelo;

	protected Controller(Model mod, View vis) {
		vista = vis;
		modelo = mod;
		vista.addControlador(this);
	}

	public Model getModelo() {
		return modelo;
	}

	public View getVista() {
		return vista;
	}

}
