/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.controladores;


import org.apache.log4j.Logger;

import swing.fabrica.mains.SolicitudFabricacion;
import swing.fabrica.mains.ComenzarFabricacion;
import swing.fabrica.mains.EnvioProveedores;
import swing.fabrica.mains.NuevoArticulo;
import swing.fabrica.mains.ReposicionArticulos;
import swing.fabrica.modelos.BusinessDelegateFabrica;
import swing.fabrica.mvcFramework.Controller;
import swing.fabrica.vistas.VistaFabrica;

public class ControladorFabrica extends Controller {
	
	final static Logger logger = Logger.getLogger(ControladorFabrica.class);
	
	public ControladorFabrica(BusinessDelegateFabrica mf, VistaFabrica vf) {
		super(mf, vf);
	}
	public void llamarEnvioProveedores(){
		logger.info("Inicializando Envio Proveedores");
		new EnvioProveedores();
	}
	public void llamarNuevoArticulo(){
		logger.info("Inicializando Nuevo Articulo");
		new NuevoArticulo();
	}
	public void llamarReposicionArticulos(){
		logger.info("Inicializando Reposiocion Articulos");
		new ReposicionArticulos();
	}
	public void llamarSolicitudFabricacion(){
		logger.info("Inicializando Solicitud Fabricacion");
		new SolicitudFabricacion();
	}
	public void llamarComenzarFabricacion() {
		logger.info("Inicializando Comenzar Fabricacion");
		new ComenzarFabricacion();
	}
}
