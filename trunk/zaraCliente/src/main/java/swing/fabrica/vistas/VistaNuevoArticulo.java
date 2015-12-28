/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.vistas;

import java.util.Observable;

import swing.fabrica.modelos.BusinessDelegateNuevoArticulo;
import swing.fabrica.mvcFramework.View;
import swing.fabrica.vistas.guis.DisenioNuevoArticulo;


public class VistaNuevoArticulo extends View {
	DisenioNuevoArticulo vistaGrafica;

	public VistaNuevoArticulo(BusinessDelegateNuevoArticulo mna) {
		super(mna);
		vistaGrafica = new DisenioNuevoArticulo(this);
		vistaGrafica.pack();
		vistaGrafica.setVisible(true);
	}

	public void agregarMateriaPrima() {
		vistaGrafica.agregarMateriaPrima();
	}

	public void quitarMateriaPrima() {
		vistaGrafica.quitarMateriaPrima();
	}
	
	public void cerrar(){
		vistaGrafica.dispose();
	}

	public void agregarNvoArt() {
		vistaGrafica.agregarNvoArt();
	}

	public void altaNvoArt() {
		vistaGrafica.altaNvoArt();
	}

	public void componentes() {
		vistaGrafica.componentes();
	}

	public void errorArchivo() {
		vistaGrafica.errorArchivo();
	}

	public void errorGuardar() {
		vistaGrafica.errorGuardar();
	}
	
	public void canceloGuardar(){
		vistaGrafica.canceloGuardar();
	}

	public void okGuardar() {
		vistaGrafica.okGuardar();
	}

	public void update(Observable m, Object obj) {

	}

}
