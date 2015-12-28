package fabrica.facades;

import java.util.ArrayList;

import javax.ejb.Local;

import fabrica.vo.ArticuloEnListadoVO;

@Local
public interface ModeloComenzarFabricacion {

	public abstract ArrayList<ArticuloEnListadoVO> calculaArticulosComenzados();

	public abstract ArrayList<ArticuloEnListadoVO> calculaArticulosPendientes();

}