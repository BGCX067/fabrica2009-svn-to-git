/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package fabrica.bean.session;

import java.util.ArrayList;
import java.util.GregorianCalendar;
import java.util.Hashtable;

import javax.ejb.Stateless;
import javax.naming.InitialContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fabrica.bean.entities.Articulo;
import fabrica.bean.entities.ArticuloMateriaPrima;
import fabrica.bean.entities.FabricacionComenzada;
import fabrica.bean.entities.ItemFabricacion;
import fabrica.bean.entities.ItemSolicitud;
import fabrica.bean.entities.MateriaPrima;
import fabrica.cu.EnvProv;
import fabrica.facades.InterfaceHelperBean;
import fabrica.facades.ModeloComenzarFabricacion;
import fabrica.vo.ArticuloEnListadoVO;
import fabrica.vo.MateriaPrimaVO;
import fabrica.xml.XMLWriter;

@Stateless
public class ModeloComenzarFabricacionBean implements ModeloComenzarFabricacion{
	
	@PersistenceContext(unitName="Fabrica")
	EntityManager manager;
	private String helper = "zaraEAR/HelperBean/local";
	private InterfaceHelperBean helperBean;
	private InitialContext initialContext = null;
	
	
	public ModeloComenzarFabricacionBean() {
		this.inicializarContexto();
	}
	
	ArrayList<ArticuloEnListadoVO> articulosEnListado = new ArrayList<ArticuloEnListadoVO>();

	public ArrayList<ArticuloEnListadoVO> calculaArticulosComenzados() {
		
		articulosEnListado = new ArrayList<ArticuloEnListadoVO>();
		
		try{
			this.comenzarFabricacionNuevosArticulos();
			this.comenzarFabricacionItemSolicitud();
			this.pedirMateriasPrimas();
			manager.flush();
			return articulosEnListado;
		}
		catch (Exception e) {
			System.err.println("Hubo un problema comenzando la fabricacion, ver log del servidor");
		}
		
		return null;
	}
	
	private void pedirMateriasPrimas() {
		EnvProv envProv = new EnvProv();
		this.pedirMateriasPrimasForArticulos(envProv);
		this.pedirMateriasPrimasForItemSolicitud(envProv);
		this.restarStockActual(envProv);
		new XMLWriter().generatePCProv(envProv);
	}
	
	private void comenzarFabricacionNuevosArticulos(){
		
		FabricacionComenzada fabricacionComenzada = new FabricacionComenzada();
		fabricacionComenzada.setFecha(GregorianCalendar.getInstance().getTime());

		String q = "SELECT A from Articulo A WHERE A.esNuevo = 1";
		Query query = manager.createQuery(q);
		ArrayList<Articulo> articulos = new ArrayList<Articulo>(query.getResultList());
		
		for (Articulo articulo : articulos) {
			
			int cantidadFabricar = 30 - articulo.getFabricadoPorNuevo();
			cantidadFabricar = this.getHelper().obtenerCantidadFabricarParaMP(articulo, cantidadFabricar);
			
			if (cantidadFabricar > 0){
				if (cantidadFabricar + articulo.getFabricadoPorNuevo() == 30){
					articulo.setEsNuevo(0);
				}
				articulo.setFabricadoPorNuevo(articulo.getFabricadoPorNuevo() + cantidadFabricar);
				this.getHelper().procesarStock(articulo, cantidadFabricar, articulosEnListado);
				fabricacionComenzada.getItemsFabricacion().add(new ItemFabricacion(cantidadFabricar, articulo, fabricacionComenzada));
			}
		}
		
		manager.persist(fabricacionComenzada);
		manager.flush();
	}

	private void comenzarFabricacionItemSolicitud(){
		String q = "SELECT I from ItemSolicitud I ORDER BY I.solicitud.fecha, I.solicitud.id asc";
		Query query = manager.createQuery(q);
		ArrayList<ItemSolicitud> items = new ArrayList<ItemSolicitud>(query.getResultList());
		
		FabricacionComenzada fabricacionComenzada = new FabricacionComenzada();
		fabricacionComenzada.setFecha(GregorianCalendar.getInstance().getTime());
		
		for (ItemSolicitud itemSolicitud : items) {
			
			q = "SELECT IF " +
				" FROM ItemFabricacion IF " +
				" WHERE IF.itemSolicitud is null " +
					" AND IF.cantidadFabricada > 0" +
					" AND IF.articulo.id="+ itemSolicitud.getArticulo().getIdArticulo();
			
			query = manager.createQuery(q);
			ArrayList<ItemFabricacion> itemsFabricacion = new ArrayList<ItemFabricacion>(query.getResultList());

			
			int cantidadFabricada= this.getHelper().obtenerCantidadFabricadaByItemSolicitud(itemSolicitud.getId());
			int cantidadFabricar = itemSolicitud.getCantidad() - cantidadFabricada;
			
			//FabricacionComenzada fabricacionComenzada2 = new FabricacionComenzada();
			//fabricacionComenzada2.setFecha(itemFabricacion.getFabricacion().getFecha());
			
			for (ItemFabricacion itemFabricacion : itemsFabricacion) {
				
				FabricacionComenzada fabricacionComenzada2 = new FabricacionComenzada();
				fabricacionComenzada2.setFecha(itemFabricacion.getFabricacion().getFecha());
				
				if (itemFabricacion.getCantidadFabricada()>= cantidadFabricar){
					fabricacionComenzada2.getItemsFabricacion().add(
							new ItemFabricacion(cantidadFabricar, itemSolicitud, fabricacionComenzada2));
						
					itemFabricacion.setCantidadFabricada(itemFabricacion.getCantidadFabricada() - cantidadFabricar);
					cantidadFabricar = 0;
				}
				else{
						
					fabricacionComenzada2.getItemsFabricacion().add(
							new ItemFabricacion(itemFabricacion.getCantidadFabricada(), 
												itemSolicitud, 
												fabricacionComenzada2)
							);
					cantidadFabricar = cantidadFabricar - itemFabricacion.getCantidadFabricada();
					itemFabricacion.setCantidadFabricada(0);
					
				}
				manager.persist(fabricacionComenzada2);
				manager.flush();
			}
			//manager.persist(fabricacionComenzada2);
			//manager.flush();

			//AGARRO LO QUE ALGUNA VEZ FABRIQUE DE ESTE PEDIDO
			cantidadFabricada= this.getHelper().obtenerCantidadFabricadaByItemSolicitud(itemSolicitud.getId());
			cantidadFabricar = itemSolicitud.getCantidad() - cantidadFabricada;
			cantidadFabricar = this.getHelper().obtenerCantidadFabricarParaMP(itemSolicitud.getArticulo(), cantidadFabricar);
			
			if (cantidadFabricar > 0){
				fabricacionComenzada.getItemsFabricacion().add(new ItemFabricacion(cantidadFabricar, itemSolicitud, fabricacionComenzada));
				this.getHelper().procesarStock(itemSolicitud.getArticulo(), cantidadFabricar, articulosEnListado);
			}
		}
		manager.persist(fabricacionComenzada);
		manager.flush();

	}
	
	private void pedirMateriasPrimasForArticulos(EnvProv envProv){
		String q = "SELECT A from Articulo A WHERE A.esNuevo = 1";
		Query query = manager.createQuery(q);
		ArrayList<Articulo> articulos = new ArrayList<Articulo>(query.getResultList());
		
		for (Articulo articulo : articulos) {
			int cantidadFabricada = articulo.getFabricadoPorNuevo();
			int cantidadPendiente = 30 - cantidadFabricada;
			if (cantidadPendiente > 0){
				this.agregarMateriasPrimas(envProv, articulo, cantidadPendiente);
			}
		}
	}
	
	private void pedirMateriasPrimasForItemSolicitud(EnvProv envProv){
		
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
					this.agregarMateriasPrimas(envProv, articuloAux, totalPorArticulo - (30-articuloAux.getFabricadoPorNuevo()));
				}
				else if(articuloAux.getFabricadoPorNuevo() == 30){
					if (totalPorArticulo>0) {
						this.agregarMateriasPrimas(envProv, articuloAux, totalPorArticulo);
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
				this.agregarMateriasPrimas(envProv, articuloAux, totalPorArticulo - (30-articuloAux.getFabricadoPorNuevo()));
			}
			else if(articuloAux.getFabricadoPorNuevo() == 30){
				if (totalPorArticulo>0) {
					this.agregarMateriasPrimas(envProv, articuloAux, totalPorArticulo);
				}
			}		
		}
	}
	
	private void agregarMateriasPrimas(EnvProv envProv, Articulo articulo, int cantidadPendiente){
		
		for (ArticuloMateriaPrima articuloMateriaPrima : articulo.getArticuloMateriasPrimas()) {
			int materiaPrimaPedir = cantidadPendiente * articuloMateriaPrima.getCantidad();
			MateriaPrimaVO materiaPrimaVO = envProv.getByCodigo(articuloMateriaPrima.getMateriaPrima().getCodigo());
			
			if (materiaPrimaVO != null) {
				materiaPrimaVO.setStock(materiaPrimaVO.getStock() + materiaPrimaPedir);
			} 
			else {
				materiaPrimaVO = new MateriaPrimaVO();
				materiaPrimaVO.setCodigo(articuloMateriaPrima.getMateriaPrima().getCodigo());
				materiaPrimaVO.setStock((materiaPrimaPedir));
				envProv.addMateriaPrima(materiaPrimaVO);
			}
		}
	}
	
	private void restarStockActual(EnvProv envProv){
		ArrayList<MateriaPrimaVO> materiaPrimaSacar = new ArrayList<MateriaPrimaVO>();
		for (MateriaPrimaVO materiaPrimaVO : envProv.getMateriasPrimas()) {
			MateriaPrima materiaPrima = manager.find(MateriaPrima.class, materiaPrimaVO.getCodigo());
			if (materiaPrima.getStock() >= materiaPrimaVO.getStock()){
				materiaPrimaSacar.add(materiaPrimaVO);
			}
			else{
				materiaPrimaVO.setStock(materiaPrimaVO.getStock() - materiaPrima.getStock());
			}
		}
		envProv.getMateriasPrimas().removeAll(materiaPrimaSacar);
	}

	public ArrayList<ArticuloEnListadoVO> calculaArticulosPendientes() {
		return new ArrayList<ArticuloEnListadoVO>();
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
