/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package ar.com.fabrica.modelos;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Hashtable;

import javax.naming.InitialContext;

import org.apache.log4j.Logger;

import fabrica.facades.Facade;
import fabrica.vo.ArticuloVO;
import fabrica.vo.MateriaPrimaVO;

public class BusinessDelegateNuevoArticulo {

	private Facade facade = null;
	private InitialContext initialContext = null;
	private String naming = "zaraEAR/SessionFacade/remote";
	private Logger logger = Logger.getLogger(BusinessDelegateNuevoArticulo.class);

	public BusinessDelegateNuevoArticulo() {
		inicializarContexto();
	}

	public ArrayList<MateriaPrimaVO> obtenerMateriasPrimas() {
		Facade facade = obtenerFacade();
		if (facade != null){
			return facade.obtenerMateriasPrimas();
		}
		return null;
	}
	
	public int guardarArticulo(ArticuloVO articuloVO) {
		Facade facade = obtenerFacade();
		if (facade != null){
			return facade.guardarArticulo(articuloVO);
		}
		return 0;
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
