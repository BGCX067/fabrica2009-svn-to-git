/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.mains;

import swing.fabrica.controladores.ControladorFabrica;
import swing.fabrica.modelos.BusinessDelegateFabrica;
import swing.fabrica.vistas.VistaFabrica;

public class Fabrica {

	BusinessDelegateFabrica mf = new BusinessDelegateFabrica();
	VistaFabrica vf = new VistaFabrica(mf);
	ControladorFabrica cf = new ControladorFabrica(mf, vf);

	public static void main(String args[]) {
		new Fabrica();
	}

}
