/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.controladores;

import swing.fabrica.modelos.BusinessDelegateComenzarFabricacion;
import swing.fabrica.mvcFramework.Controller;
import swing.fabrica.vistas.VistaComenzarFabricacion;


public class ControladorComenzarFabricacion extends Controller {
	public ControladorComenzarFabricacion(BusinessDelegateComenzarFabricacion mcf,
			VistaComenzarFabricacion vcf) {
		super(mcf, vcf);
	}
	
	
	public void cerrar() {
		((VistaComenzarFabricacion) (this.getVista())).cerrar();
	}
	
	
}
