/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.modelos;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import swing.fabrica.mvcFramework.Model;

import fabrica.facades.Facade;
import fabrica.vo.ArticuloEnListadoVO;
import fabrica.vo.SolFabrVO;

public class BusinessDelegateSolicitudFabricacion extends Model {

	private Facade facade = null;
	private InitialContext initialContext = null;
	private String naming = "zaraEAR/SessionFacade/remote";
	private Logger logger = Logger.getLogger(BusinessDelegateSolicitudFabricacion.class);
	
	public BusinessDelegateSolicitudFabricacion() {
		inicializarContexto();
	}

	public ArrayList<ArticuloEnListadoVO> cargarSolicitudesAnteriores() {
		Facade facade = obtenerFacade();
		if (facade != null){
			return obtenerFacade().cargarSolicitudesAnteriores();
		}
		else {return null;}
	}

	public ArrayList<ArticuloEnListadoVO> procesarNuevasFabricaciones(SolFabrVO solFabrVO) {
		Facade facade = obtenerFacade();
		if (facade != null){
			return obtenerFacade().procesarNuevasFabricaciones(solFabrVO);
		}
		else {return null;}
	}
	
	private Facade obtenerFacade(){
		try{
			if(facade == null){
				facade = (Facade)initialContext.lookup(naming);
			}
		}
		catch(Exception ex){
			System.err.println(" No es posible establecer conexion con el servidor de aplicaciones, puede ver mas detalles en el log");
			StringWriter sw = new StringWriter();
			ex.printStackTrace(new PrintWriter(sw));
			logger.error(sw.toString());
		}
		return facade;
	}
	
	
	private void inicializarContexto() {
		try{
			Hashtable <String,String> props = new Hashtable<String,String>();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			props.put(InitialContext.PROVIDER_URL,"jnp://127.0.0.1:1099");
			initialContext = new InitialContext(props);
		}catch(Exception ex){
			System.err.println(" No es posible establecer conexion con el servidor de aplicaciones, puede ver mas detalles en el log");
			StringWriter sw = new StringWriter();
			ex.printStackTrace(new PrintWriter(sw));
			logger.error(sw.toString());
		}
	}

}
