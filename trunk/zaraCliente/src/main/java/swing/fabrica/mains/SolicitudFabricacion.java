/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.mains;

import swing.fabrica.controladores.ControladorSolicitudFabricacion;
import swing.fabrica.modelos.BusinessDelegateSolicitudFabricacion;
import swing.fabrica.vistas.VistaSolicitudFabricacion;

public class SolicitudFabricacion {

	BusinessDelegateSolicitudFabricacion msf = new BusinessDelegateSolicitudFabricacion();
	VistaSolicitudFabricacion vsf = new VistaSolicitudFabricacion(msf);
	ControladorSolicitudFabricacion csf = new ControladorSolicitudFabricacion(msf, vsf);

	public static void main(String args[]) {
		new SolicitudFabricacion();
	}

}
