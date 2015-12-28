package fabrica.facades;

import java.util.ArrayList;

import javax.ejb.Local;

import fabrica.bean.entities.Articulo;
import fabrica.vo.ArticuloEnListadoVO;

@Local
public interface InterfaceHelperBean {

	public abstract void hidratarArticuloEnListado(Articulo articulo,
			int cantidadFabricar,
			ArrayList<ArticuloEnListadoVO> articulosEnListado);

	public abstract int obtenerCantidadFabricadaByItemSolicitud(int solicitudId);

	public abstract int obtenerCantidadFabricadaByArticulo(int articuloId);
	
	public abstract int obtenerCantidadFabricarParaMP(Articulo articulo, int cantidadFabricar);
	
	public abstract void procesarStock(Articulo articulo, 
			int cantidadFabricar,
			ArrayList<ArticuloEnListadoVO> articulosEnListado);
	

}