package fabrica.facades;

import java.util.ArrayList;

import javax.ejb.Remote;

import fabrica.vo.ArticuloEnListadoVO;
import fabrica.vo.ArticuloVO;
import fabrica.vo.MateriaPrimaVO;
import fabrica.vo.SolFabrVO;

@Remote
public interface Facade {
	
	//COMENZAR FABRICACION
	public abstract ArrayList<ArticuloEnListadoVO> calculaArticulosComenzados();

	public abstract ArrayList<ArticuloEnListadoVO> calculaArticulosPendientes();
	

	//ENVIO PROVEEDORES
	public abstract ArrayList<MateriaPrimaVO> procesarEnvioProveedores(ArrayList<MateriaPrimaVO> materiasPrimas);
	
	
	//NUEVO ARTICULO
	public abstract ArrayList<MateriaPrimaVO> obtenerMateriasPrimas();

	public abstract int guardarArticulo(ArticuloVO articuloVO);
	
	
	//REPOSICION ARTICULOS
	public abstract ArrayList<ArticuloEnListadoVO> calculaArticulosPorCD();
	
	
	//SOLICITUD FABRICACION
	public abstract ArrayList<ArticuloEnListadoVO> cargarSolicitudesAnteriores();

	public abstract ArrayList<ArticuloEnListadoVO> procesarNuevasFabricaciones(SolFabrVO solFabrVo);

	
}
