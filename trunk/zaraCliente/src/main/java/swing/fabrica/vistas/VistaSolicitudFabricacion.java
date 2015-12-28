/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.vistas;

import java.util.ArrayList;
import java.util.Observable;

import swing.fabrica.modelos.BusinessDelegateSolicitudFabricacion;
import swing.fabrica.mvcFramework.View;
import swing.fabrica.vistas.guis.DisenioSolicitudFabricacion;

import exception.LoadingException;
import fabrica.vo.ArticuloEnListadoVO;


public class VistaSolicitudFabricacion extends View {
	DisenioSolicitudFabricacion vistaGrafica;

	public VistaSolicitudFabricacion(BusinessDelegateSolicitudFabricacion msf) {
		super(msf);
		try {
			vistaGrafica = new DisenioSolicitudFabricacion(this);
			vistaGrafica.pack();
			vistaGrafica.setVisible(true);
		} catch (LoadingException e) {
		}
	}

	public void agregarNvaSol() {
		vistaGrafica.agregarNvaSol();
	}
	
	public void errorArchivo(){
		vistaGrafica.errorArchivo();
	}
	public void cerrar(){
		vistaGrafica.dispose();
	}

	public void cargarArticulos(ArrayList<ArticuloEnListadoVO> fabricaciones) {
		vistaGrafica.cargarArticulos(fabricaciones);
	}

	public void update(Observable m, Object obj) {

	}

}
