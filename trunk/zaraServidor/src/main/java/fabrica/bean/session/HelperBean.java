package fabrica.bean.session;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fabrica.bean.entities.Articulo;
import fabrica.bean.entities.ArticuloMateriaPrima;
import fabrica.facades.InterfaceHelperBean;
import fabrica.vo.ArticuloEnListadoVO;

@Stateless
public class HelperBean implements InterfaceHelperBean {
	
	@PersistenceContext(unitName="Fabrica")
	EntityManager manager;

	
	public void hidratarArticuloEnListado(Articulo articulo, int cantidadFabricar, ArrayList<ArticuloEnListadoVO> articulosEnListado){
		
		ArticuloEnListadoVO articuloEnListadoAux = getByReferencia(articulo.getReferencia(), articulosEnListado);
		
		if (articuloEnListadoAux != null){
			articuloEnListadoAux.setCantidad(articuloEnListadoAux.getCantidad() + cantidadFabricar);
		}
		else{
			articulosEnListado.add(new ArticuloEnListadoVO(articulo.getReferencia(),
					articulo.getDescripcion(), cantidadFabricar));
		}
	}
	
	public ArticuloEnListadoVO getByReferencia(String referencia, ArrayList<ArticuloEnListadoVO> articulosEnListado){
		for (ArticuloEnListadoVO articuloEnListado : articulosEnListado) {
			if (articuloEnListado.getReferencia().equalsIgnoreCase(referencia)){
				return articuloEnListado;
			}
		}
		return null;
	}

	
	private int obtenerCantidadFabricadaGroupBy(int id, String groupBy) {
		String q = "SELECT sum(IF.cantidadFabricada) "
				+ " FROM ItemFabricacion IF "
				+ " WHERE IF." + groupBy + ".id = " + id
				+ " GROUP BY IF." + groupBy;

		Query query = manager.createQuery(q);
		
		ArrayList<Long> cantFabricada = (ArrayList<Long>) (query.getResultList());
		
		if (cantFabricada == null || cantFabricada.size() == 0) {
			return 0;
		}

		return cantFabricada.get(0).intValue();
	}
	
	public int obtenerCantidadFabricadaByItemSolicitud(int solicitudId){
		return obtenerCantidadFabricadaGroupBy(solicitudId, "itemSolicitud");
	}
	
	public int obtenerCantidadFabricadaByArticulo(int articuloId){
		return obtenerCantidadFabricadaGroupBy(articuloId, "articulo");
	}
	
	public void procesarStock(Articulo articulo, 
			int cantidadFabricar,
			ArrayList<ArticuloEnListadoVO> articulosEnListado){
		for (ArticuloMateriaPrima articuloMateriaPrima : articulo.getArticuloMateriasPrimas()) {
			//FABRICA ESA CANTIDAD DE UNIDADES Y ACTUALIZO LOS STOCKS DE LAS MATERIAS PRIMAS
			int stockActual = articuloMateriaPrima.getMateriaPrima().getStock(); 
			articuloMateriaPrima.getMateriaPrima().setStock(stockActual - (cantidadFabricar * articuloMateriaPrima.getCantidad()));
		}
		
		//AGREGO LOS ARTICULOS FABRICADOS
		
		this.hidratarArticuloEnListado(articulo, cantidadFabricar, articulosEnListado);	
	}
	
	public int obtenerCantidadFabricarParaMP(Articulo articulo, int cantidadFabricar){
		for (ArticuloMateriaPrima articuloMateriaPrima : articulo.getArticuloMateriasPrimas()) {
			//AGARRO LA MENOR CANTIDAD QUE PUEDO FABRICAR SEGUN LAS MATERIAS PRIMAS QUE TENGO
			int puedoFabricar = articuloMateriaPrima.getMateriaPrima().getStock() / articuloMateriaPrima.getCantidad();
			cantidadFabricar= (puedoFabricar > cantidadFabricar) ? cantidadFabricar : puedoFabricar;
		}
		return cantidadFabricar;
	}

}
