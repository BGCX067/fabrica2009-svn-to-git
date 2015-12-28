/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package fabrica.bean.session;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fabrica.bean.entities.ItemFabricacion;
import fabrica.bean.entities.ItemSolicitud;
import fabrica.cu.RepAf;
import fabrica.facades.ModeloReposicionArticulos;
import fabrica.vo.ArticuloEnListadoVO;
import fabrica.xml.XMLWriter;


@Stateless
public class ModeloReposicionArticulosBean implements ModeloReposicionArticulos{

	@PersistenceContext(unitName="Fabrica")
	EntityManager manager;
	Calendar cHoy = new GregorianCalendar();
	Calendar cFecha = new GregorianCalendar();
	
	public ArrayList<ArticuloEnListadoVO> calculaArticulosPorCD() {
		
		ArrayList<ArticuloEnListadoVO> articulosEnListado = new ArrayList<ArticuloEnListadoVO>();
		
		try{
			cHoy.setTime(GregorianCalendar.getInstance().getTime());
			cHoy.set(cHoy.get(Calendar.YEAR), cHoy.get(Calendar.MONTH), cHoy.get(Calendar.DAY_OF_MONTH), 0, 0, 0);
			
			String q = "SELECT I from ItemSolicitud I WHERE I.cantidad - I.cantidadEntregada > 0 " +
					"ORDER BY I.solicitud.centroDistribucion.nombre";
			
			Query query = manager.createQuery(q);
			ArrayList<ItemSolicitud> itemsSolicitud = new ArrayList<ItemSolicitud>(query.getResultList());
		
			for (ItemSolicitud itemSolicitud : itemsSolicitud) {
	
				int cantidadFabricada=0;
				
				for (ItemFabricacion itemFabricacion : itemSolicitud.getItemsFabricacion()) {
					
					cFecha.setTime(itemFabricacion.getFabricacion().getFecha());
					cFecha.add(Calendar.DATE, itemFabricacion.getItemSolicitud().getArticulo().getTiempoDeFabricacion());
					if (cFecha.compareTo(cHoy) <= 0) {
						cantidadFabricada+=itemFabricacion.getCantidadFabricada();
					}
				}
				
				int pendienteEntrega = cantidadFabricada - itemSolicitud.getCantidadEntregada();
				
				if (pendienteEntrega > 0){
				
					ArticuloEnListadoVO articuloEnListado = ArticuloEnListadoVO.
						getByReferenciaAndCentro(
								itemSolicitud.getArticulo().getReferencia(),
								itemSolicitud.getSolicitud().getCentroDistribucion().getNombre(),
								articulosEnListado);
			
					if (articuloEnListado == null){
							articulosEnListado.add(new ArticuloEnListadoVO(
							itemSolicitud.getSolicitud().getCentroDistribucion().getNombre(),
							itemSolicitud.getArticulo().getReferencia(),
							itemSolicitud.getArticulo().getDescripcion(),
							pendienteEntrega));
					}
					else{
						articuloEnListado.setCantidad(articuloEnListado.getCantidad() + pendienteEntrega);
					}
					itemSolicitud.setCantidadEntregada(itemSolicitud.getCantidadEntregada()+pendienteEntrega);
				}
			}
			
			this.generarReposiciones(articulosEnListado);
			return articulosEnListado;
		}
		catch (Exception e) {
			System.out.println("Hubo un problema en la reposicion de articulos, ver log del servidor");
		}
		return null;
		
	}
	
	private void generarReposiciones(ArrayList<ArticuloEnListadoVO> articulosEnListado){
		
		ArrayList<RepAf> reposiciones = new ArrayList<RepAf>();
		
		for (ArticuloEnListadoVO articuloEnListado : articulosEnListado) {
			RepAf reposicion = RepAf.obtenerByCentro(articuloEnListado.getCentro(), reposiciones);
			if(reposicion == null){
				reposicion = new RepAf();
				reposicion.addArticulo(articuloEnListado);
				reposicion.setCd(articuloEnListado.getCentro());
				reposiciones.add(reposicion);
			}
			else{
				reposicion.setCd(articuloEnListado.getCentro());
				reposicion.addArticulo(articuloEnListado);
			}
		}
		for (RepAf reposicion : reposiciones) {
			new XMLWriter().generateRepAf(reposicion);
		}
	}
}
