/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package fabrica.bean.session;

import java.util.ArrayList;
import java.util.Hashtable;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fabrica.bean.entities.Articulo;
import fabrica.bean.entities.CentroDistribucion;
import fabrica.bean.entities.ItemSolicitud;
import fabrica.bean.entities.SolicitudFabricacion;
import fabrica.facades.InterfaceHelperBean;
import fabrica.facades.ModeloSolicitudFabricacion;
import fabrica.vo.ArticuloEnListadoVO;
import fabrica.vo.SolFabrItemVO;
import fabrica.vo.SolFabrVO;


@Stateless
public class ModeloSolicitudFabricacionBean implements ModeloSolicitudFabricacion{

	@PersistenceContext(unitName="Fabrica")
	EntityManager manager;
	ArrayList<ArticuloEnListadoVO> articulosEnListado = new ArrayList<ArticuloEnListadoVO>();
	private String helper = "zaraEAR/HelperBean/local";
	private InterfaceHelperBean helperBean;
	private InitialContext initialContext = null;
	
	private ModeloSolicitudFabricacionBean(){
		this.inicializarContexto();
	}
	
	public ArrayList<ArticuloEnListadoVO> cargarSolicitudesAnteriores() {
		articulosEnListado = new ArrayList<ArticuloEnListadoVO>();
		return obtenerFabricacionesPendientes();
	}
	
	public ArrayList<ArticuloEnListadoVO> procesarNuevasFabricaciones(SolFabrVO solicitudFabricacionVO) {
		articulosEnListado = new ArrayList<ArticuloEnListadoVO>();
		SolicitudFabricacion solFabr = convertirSolFabr(solicitudFabricacionVO);
		try {
			manager.persist(solFabr);
			manager.flush();
			return obtenerFabricacionesPendientes();
		} 
		catch (Exception e) {
			System.out.println("Hubo un problema procesando las nuevas fabricaciones, ver log del servidor");
			return null;
		}
	}
	
	private void obtenerFabricacionsPendientesByArticulo(){
		String q = "SELECT A from Articulo A WHERE A.esNuevo = 1 ";
		Query query = manager.createQuery(q);
		ArrayList<Articulo> articulos = new ArrayList<Articulo>(query.getResultList());
		
		for (Articulo articulo : articulos) {
			
			int cantidadFabricar = 30 - articulo.getFabricadoPorNuevo();
			
			if (cantidadFabricar > 0){
				this.getHelper().hidratarArticuloEnListado(articulo, cantidadFabricar, articulosEnListado);
			}
		}
	}
	
	private void obtenerFabricacionsPendientesByItemSolicitud(){
		
		Articulo articuloAux = null;
		
		String q = "SELECT I from ItemSolicitud I ORDER BY I.articulo";
		Query query = manager.createQuery(q);
		ArrayList<ItemSolicitud> items = new ArrayList<ItemSolicitud>(query.getResultList());
		
		if (items.size() > 0){
			articuloAux = items.get(0).getArticulo();
		}
		
		int cantidadFabricada;
		int cantidadFabricar;
		int totalPorArticulo = 0;
		
		for (ItemSolicitud itemSolicitud : items) {
		
			if (itemSolicitud.getArticulo().equals(articuloAux)) {
				cantidadFabricada = this.getHelper().obtenerCantidadFabricadaByItemSolicitud(itemSolicitud.getId());
				cantidadFabricar = itemSolicitud.getCantidad() - cantidadFabricada;
				
				if (cantidadFabricar > 0){
					totalPorArticulo += cantidadFabricar;
				}
			}
			else{
				if (totalPorArticulo > (30 - articuloAux.getFabricadoPorNuevo())){
					this.getHelper().hidratarArticuloEnListado(articuloAux, totalPorArticulo - (30-articuloAux.getFabricadoPorNuevo()), articulosEnListado);
				}
				else if(articuloAux.getFabricadoPorNuevo() == 30){
					if (totalPorArticulo>0) {
						this.getHelper().hidratarArticuloEnListado(articuloAux, totalPorArticulo, articulosEnListado);
					}
				}
				articuloAux = itemSolicitud.getArticulo();
				cantidadFabricada = this.getHelper().obtenerCantidadFabricadaByItemSolicitud(itemSolicitud.getId());
				cantidadFabricar = itemSolicitud.getCantidad() - cantidadFabricada;
				
				totalPorArticulo = cantidadFabricar;
			}
		}
		if (items.size() > 0){
			if (totalPorArticulo > (30 - articuloAux.getFabricadoPorNuevo())){
				this.getHelper().hidratarArticuloEnListado(articuloAux, totalPorArticulo - (30-articuloAux.getFabricadoPorNuevo()), articulosEnListado);
			}
			else if(articuloAux.getFabricadoPorNuevo() == 30){
				if (totalPorArticulo>0) {
					this.getHelper().hidratarArticuloEnListado(articuloAux, totalPorArticulo, articulosEnListado);
				}
			}
		}
		
	}
	
		
	private ArrayList<ArticuloEnListadoVO> obtenerFabricacionesPendientes(){
		try{
			obtenerFabricacionsPendientesByArticulo();
			obtenerFabricacionsPendientesByItemSolicitud();
			return articulosEnListado;
		}
		catch (Exception e) {
			System.out.println("Hubo un problema obteniendo las fabricaciones pendientes, ver log del servidor");
			return null;
		}
	}

	private SolicitudFabricacion convertirSolFabr (SolFabrVO solicitudFabricacionVO){
		
		SolicitudFabricacion solicitudFabricacion = new SolicitudFabricacion();
		solicitudFabricacion.setFecha(solicitudFabricacionVO.getFecha());
		solicitudFabricacion.setCentroDistribucion(CentroDistribucion.fromVO(solicitudFabricacionVO.getIdCentroDist()));

		for (SolFabrItemVO fabricacionPendienteVO : solicitudFabricacionVO.getFabricaciones()) {
			Articulo articulo = obtenerByReferencia(fabricacionPendienteVO.getReferencia());
			ItemSolicitud articuloSolFabr = new ItemSolicitud();
			articuloSolFabr.setCantidad(fabricacionPendienteVO.getCantidad());
			articuloSolFabr.setArticulo(articulo);
			articuloSolFabr.setSolicitud(solicitudFabricacion);
			solicitudFabricacion.getItemsSolicitud().add(articuloSolFabr);
		}
		
		return solicitudFabricacion;
		
	}
	
	private Articulo obtenerByReferencia(String referencia){
		String q = "SELECT A FROM Articulo A WHERE A.referencia = :d";
		Query query = manager.createQuery(q);
		query.setParameter("d",	referencia);
		Articulo articulo = (Articulo) query.getSingleResult();
		return articulo;
	}
	
	private InterfaceHelperBean getHelper(){
		try{
			if(helperBean == null){
				helperBean = (InterfaceHelperBean)initialContext.lookup(helper);
			}
		}catch(Exception ex){
			ex.printStackTrace();
		}
		return helperBean;
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
