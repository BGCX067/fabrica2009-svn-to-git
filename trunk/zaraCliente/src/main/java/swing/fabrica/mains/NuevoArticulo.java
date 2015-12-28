/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.mains;

import org.apache.log4j.Logger;

import swing.fabrica.controladores.ControladorNuevoArticulo;
import swing.fabrica.modelos.BusinessDelegateNuevoArticulo;
import swing.fabrica.vistas.VistaNuevoArticulo;

public class NuevoArticulo {

	final static Logger logger = Logger.getLogger(NuevoArticulo.class); 
	
	BusinessDelegateNuevoArticulo mna = new BusinessDelegateNuevoArticulo();
	VistaNuevoArticulo vna = new VistaNuevoArticulo(mna);
	ControladorNuevoArticulo cna = new ControladorNuevoArticulo(mna, vna);

	public static void main(String args[]) {
		new NuevoArticulo();
	}

}
