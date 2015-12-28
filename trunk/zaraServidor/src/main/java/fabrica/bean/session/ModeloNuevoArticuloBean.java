/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package fabrica.bean.session;

import java.util.ArrayList;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import fabrica.bean.entities.ArtHogar;
import fabrica.bean.entities.ArtRopa;
import fabrica.bean.entities.Articulo;
import fabrica.bean.entities.ArticuloMateriaPrima;
import fabrica.bean.entities.CentroDistribucion;
import fabrica.bean.entities.MateriaPrima;
import fabrica.facades.ModeloNuevoArticulo;
import fabrica.vo.ArtHogarVO;
import fabrica.vo.ArtRopaVO;
import fabrica.vo.ArticuloVO;
import fabrica.vo.CentroDistribucionVO;
import fabrica.vo.MateriaPrimaVO;


@Stateless
public class ModeloNuevoArticuloBean implements ModeloNuevoArticulo{
	
	@PersistenceContext(unitName="Fabrica")
	private EntityManager manager;
	
	public ArrayList<MateriaPrimaVO> obtenerMateriasPrimas() {
		ArrayList<MateriaPrimaVO> materiasPrimasVO = new ArrayList<MateriaPrimaVO>();
		
		try{
			String q = "SELECT MP FROM MateriaPrima MP";
			Query query = manager.createQuery(q);
			ArrayList<MateriaPrima> materiasPrimas =  new ArrayList<MateriaPrima>(query.getResultList());
			for (MateriaPrima materiaPrima : materiasPrimas) {
				materiasPrimasVO.add(MateriaPrima.toVO(materiaPrima));
			}
		}
		catch (Exception e) {
			System.out.println("Hubo un problema obteniendo las materias primas, ver log del servidor");
		}
		return materiasPrimasVO;
	}

	public int guardarArticulo(ArticuloVO articuloVO) {
		Articulo articulo = convertirArticulo(articuloVO);
		try {
			manager.persist(articulo);
			return 1;
		} catch (Exception e) {
			System.out.println("Hubo un problema guardando el articulo, ver log del servidor");
			return 0;
		}
	}
	
	private Articulo convertirArticulo(ArticuloVO articuloVO){
		Articulo articuloAux;
		if (articuloVO.getClass().equals(ArtRopaVO.class)) {
			articuloAux = new ArtRopa();
			((ArtRopa) articuloAux).setTalle(((ArtRopaVO)articuloVO).getTalle());
			((ArtRopa) articuloAux).setOrigen(((ArtRopaVO)articuloVO).getOrigen());
		}else{
			articuloAux = new ArtHogar();
			((ArtHogar) articuloAux).setNombre(((ArtHogarVO)articuloVO).getNombre());
			((ArtHogar) articuloAux).setMedidas(((ArtHogarVO)articuloVO).getMedidas());
			((ArtHogar) articuloAux).setComposicion(((ArtHogarVO)articuloVO).getComposicion());
		}
		articuloAux.setSeccion(articuloVO.getSeccion());
		articuloAux.setReferencia(articuloVO.getReferencia());
		articuloAux.setDescripcion(articuloVO.getDescripcion());
		articuloAux.setColor(articuloVO.getColor());
		articuloAux.setPrecioVenta(articuloVO.getPrecioVenta());
		articuloAux.setLinea(articuloVO.getLinea());
		articuloAux.setTiempoDeFabricacion(articuloVO.getTiempoDeFabricacion());
		
		for (CentroDistribucionVO centroDistribucionVO : articuloVO.getCentrosDistribucion()) {
			articuloAux.getCentrosDistribucion().add(CentroDistribucion.fromVO(centroDistribucionVO.getId()));
		}
		for (MateriaPrimaVO materiaPrimaVO : articuloVO.getMateriasPrimas()) {
			
			ArticuloMateriaPrima articuloMateriaPrima = new ArticuloMateriaPrima();
			articuloMateriaPrima.setCantidad(materiaPrimaVO.getStock());
			articuloMateriaPrima.setArticulo(articuloAux);
			articuloMateriaPrima.setMateriaPrima(MateriaPrima.fromVO(materiaPrimaVO));
			articuloAux.getMateriasPrimas().add(articuloMateriaPrima);
			
		}
		articuloAux.setEsNuevo(1);
		return articuloAux;
	}
}
