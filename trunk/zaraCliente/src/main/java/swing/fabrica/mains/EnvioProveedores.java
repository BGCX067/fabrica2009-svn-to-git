/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.mains;

import swing.fabrica.controladores.ControladorEnvioProveedores;
import swing.fabrica.modelos.BusinessDelegateEnvioProveedores;
import swing.fabrica.vistas.VistaEnvioProveedores;

public class EnvioProveedores {

	BusinessDelegateEnvioProveedores mep = new BusinessDelegateEnvioProveedores();
	VistaEnvioProveedores vep = new VistaEnvioProveedores(mep);
	ControladorEnvioProveedores cep = new ControladorEnvioProveedores(mep, vep);

	public static void main(String args[]) {
		new EnvioProveedores();
	}

}
