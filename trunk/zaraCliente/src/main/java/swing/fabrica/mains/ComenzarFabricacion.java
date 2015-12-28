/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.mains;

import swing.fabrica.controladores.ControladorComenzarFabricacion;
import swing.fabrica.modelos.BusinessDelegateComenzarFabricacion;
import swing.fabrica.vistas.VistaComenzarFabricacion;

public class ComenzarFabricacion {

	BusinessDelegateComenzarFabricacion mcf = new BusinessDelegateComenzarFabricacion();
	VistaComenzarFabricacion vcf = new VistaComenzarFabricacion(mcf);
	ControladorComenzarFabricacion cna = new ControladorComenzarFabricacion(mcf, vcf);

	public static void main(String args[]) {
		new ComenzarFabricacion();
	}

}
