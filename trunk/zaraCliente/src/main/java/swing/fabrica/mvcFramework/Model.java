/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.mvcFramework;

import java.util.Observable;

public abstract class Model extends Observable {

	protected Model() {
		super();
	}

}
