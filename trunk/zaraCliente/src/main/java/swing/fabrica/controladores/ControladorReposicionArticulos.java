/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.controladores;

import swing.fabrica.modelos.BusinessDelegateReposicionArticulos;
import swing.fabrica.mvcFramework.Controller;
import swing.fabrica.vistas.VistaReposicionArticulos;




public class ControladorReposicionArticulos extends Controller {
	public ControladorReposicionArticulos(BusinessDelegateReposicionArticulos mra,
			VistaReposicionArticulos vra) {
		super(mra, vra);
	}
	public void cerrar() {
		((VistaReposicionArticulos) (this.getVista())).cerrar();
	}

}
