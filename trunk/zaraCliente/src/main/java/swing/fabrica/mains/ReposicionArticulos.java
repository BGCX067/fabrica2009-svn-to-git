/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.mains;

import swing.fabrica.controladores.ControladorReposicionArticulos;
import swing.fabrica.modelos.BusinessDelegateReposicionArticulos;
import swing.fabrica.vistas.VistaReposicionArticulos;

public class ReposicionArticulos {

	BusinessDelegateReposicionArticulos mra = new BusinessDelegateReposicionArticulos();
	VistaReposicionArticulos vra = new VistaReposicionArticulos(mra);
	ControladorReposicionArticulos csf = new ControladorReposicionArticulos(mra, vra);

	public static void main(String args[]) {
		new ReposicionArticulos();
	}

}
