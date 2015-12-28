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

import swing.fabrica.modelos.BusinessDelegateReposicionArticulos;
import swing.fabrica.mvcFramework.View;
import swing.fabrica.vistas.guis.DisenioReposicionArticulos;
import exception.LoadingException;
import fabrica.vo.ArticuloEnListadoVO;


public class VistaReposicionArticulos extends View {
	DisenioReposicionArticulos vistaGrafica;

	public VistaReposicionArticulos(BusinessDelegateReposicionArticulos mra) {
		super(mra);
		try {
			vistaGrafica = new DisenioReposicionArticulos(this);
			vistaGrafica.pack();
			vistaGrafica.setVisible(true);
		} catch (LoadingException e) {

		}
	}

	public void update(Observable m, Object obj) {

	}
	
	public void cerrar(){
		vistaGrafica.dispose();
	}

	public void cargarArticulos(ArrayList<ArticuloEnListadoVO> articulosPorCD) {
		vistaGrafica.cargarArticulos(articulosPorCD);
	}
}
