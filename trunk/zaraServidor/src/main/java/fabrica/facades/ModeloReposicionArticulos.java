package fabrica.facades;

import java.util.ArrayList;

import javax.ejb.Local;

import fabrica.vo.ArticuloEnListadoVO;

@Local
public interface ModeloReposicionArticulos {

	public abstract ArrayList<ArticuloEnListadoVO> calculaArticulosPorCD();

}