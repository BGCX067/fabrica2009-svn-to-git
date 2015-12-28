package fabrica.bean.session;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.ejb.Stateful;
import javax.naming.InitialContext;

import fabrica.facades.Facade;
import fabrica.facades.ModeloComenzarFabricacion;
import fabrica.facades.ModeloEnvioProveedores;
import fabrica.facades.ModeloNuevoArticulo;
import fabrica.facades.ModeloReposicionArticulos;
import fabrica.facades.ModeloSolicitudFabricacion;
import fabrica.vo.ArticuloEnListadoVO;
import fabrica.vo.ArticuloVO;
import fabrica.vo.MateriaPrimaVO;
import fabrica.vo.SolFabrVO;

@Stateful
public class SessionFacade implements Facade {
	
	private InitialContext initialContext = null;
	private String comenzaFabricacion = "zaraEAR/ModeloComenzarFabricacionBean/local";
	private String envioProveedores = "zaraEAR/ModeloEnvioProveedoresBean/local";
	private String nuevoArticulo = "zaraEAR/ModeloNuevoArticuloBean/local";
	private String reposicionArticulos = "zaraEAR/ModeloReposicionArticulosBean/local";
	private String solicitudFabricacion = "zaraEAR/ModeloSolicitudFabricacionBean/local";
	private ModeloComenzarFabricacion modeloComenzaFabricacion;
	private ModeloEnvioProveedores modeloEnvioProveedores;
	private ModeloNuevoArticulo modeloNuevoArticulo;
	private ModeloReposicionArticulos modeloReposicionArticulos;
	private ModeloSolicitudFabricacion modeloSolicitudFabricacion;
	
	public SessionFacade() {
		inicializarContexto();
	}

	public ArrayList<ArticuloEnListadoVO> calculaArticulosComenzados(){
		return getModeloComenzaFabricacion().calculaArticulosComenzados();
	}

	public ArrayList<ArticuloEnListadoVO> calculaArticulosPendientes(){
		//return getModeloComenzaFabricacion().calculaArticulosPendientes();
		
		return getModeloSolicitudFabricacion().cargarSolicitudesAnteriores();
	}
	
	public ArrayList<MateriaPrimaVO> procesarEnvioProveedores(ArrayList<MateriaPrimaVO> materiasPrimas){
		return getModeloEnvioProveedores().procesarEnvioProveedores(materiasPrimas);
	}
	
	public ArrayList<MateriaPrimaVO> obtenerMateriasPrimas(){
		return getModeloNuevoArticulo().obtenerMateriasPrimas();
	}

	public int guardarArticulo(ArticuloVO articuloVO){
		return getModeloNuevoArticulo().guardarArticulo(articuloVO);
	}
	
	public ArrayList<ArticuloEnListadoVO> calculaArticulosPorCD(){
		return getModeloReposicionArticulos().calculaArticulosPorCD();
	}
	
	public ArrayList<ArticuloEnListadoVO> cargarSolicitudesAnteriores(){
		return getModeloSolicitudFabricacion().cargarSolicitudesAnteriores();
	}	

	public ArrayList<ArticuloEnListadoVO> procesarNuevasFabricaciones(SolFabrVO solFabrVo){
		return getModeloSolicitudFabricacion().procesarNuevasFabricaciones(solFabrVo);
	}
	
	private ModeloComenzarFabricacion getModeloComenzaFabricacion(){
		try{
			if(modeloComenzaFabricacion == null){
				modeloComenzaFabricacion = (ModeloComenzarFabricacion)initialContext.lookup(comenzaFabricacion);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return modeloComenzaFabricacion;
	}
	
	private ModeloEnvioProveedores getModeloEnvioProveedores(){
		try{
			if(modeloEnvioProveedores == null){
				modeloEnvioProveedores = (ModeloEnvioProveedores)initialContext.lookup(envioProveedores);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return modeloEnvioProveedores;
	}
	
	private ModeloNuevoArticulo getModeloNuevoArticulo(){
		try{
			if(modeloNuevoArticulo == null){
				modeloNuevoArticulo = (ModeloNuevoArticulo)initialContext.lookup(nuevoArticulo);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return modeloNuevoArticulo;
	}
	
	private ModeloReposicionArticulos getModeloReposicionArticulos(){
		try{
			if(modeloReposicionArticulos == null){
				modeloReposicionArticulos = (ModeloReposicionArticulos)initialContext.lookup(reposicionArticulos);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return modeloReposicionArticulos;
	}
	
	private ModeloSolicitudFabricacion getModeloSolicitudFabricacion(){
		try{
			if(modeloSolicitudFabricacion == null){
				modeloSolicitudFabricacion = (ModeloSolicitudFabricacion)initialContext.lookup(solicitudFabricacion);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return modeloSolicitudFabricacion;
	}
	
	private void inicializarContexto() {
		try{
			Hashtable <String,String> props = new Hashtable<String,String>();
			props.put(InitialContext.INITIAL_CONTEXT_FACTORY,"org.jnp.interfaces.NamingContextFactory");
			props.put(InitialContext.PROVIDER_URL,"jnp://127.0.0.1:1099");
			initialContext = new InitialContext(props);
		}catch(Exception ex){
			ex.printStackTrace();
		}
	}

}
