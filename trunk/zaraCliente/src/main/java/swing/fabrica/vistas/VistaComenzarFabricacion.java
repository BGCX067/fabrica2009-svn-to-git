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

import swing.fabrica.modelos.BusinessDelegateComenzarFabricacion;
import swing.fabrica.mvcFramework.View;
import swing.fabrica.vistas.guis.DisenioComenzarFabricacion;

import exception.LoadingException;
import fabrica.vo.ArticuloEnListadoVO;


public class VistaComenzarFabricacion extends View {
	DisenioComenzarFabricacion vistaGrafica;

	public VistaComenzarFabricacion(BusinessDelegateComenzarFabricacion mcf) {
		super(mcf);
		try {
			vistaGrafica = new DisenioComenzarFabricacion(this);
			vistaGrafica.pack();
			vistaGrafica.setVisible(true);
		} catch (LoadingException e) {
			//nothing to do
		}
	}

	public void cargarArticulos(ArrayList<ArticuloEnListadoVO> comenzar,
			ArrayList<ArticuloEnListadoVO> pendiente) {
		vistaGrafica.cargarArticulos(comenzar, pendiente);
	}

	public void update(Observable m, Object obj) {

	}
	public void cerrar(){
		vistaGrafica.dispose();
	}
}
