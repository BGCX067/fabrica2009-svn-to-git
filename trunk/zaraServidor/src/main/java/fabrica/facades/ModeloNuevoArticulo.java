package fabrica.facades;

import java.util.ArrayList;

import javax.ejb.Local;

import fabrica.vo.ArticuloVO;
import fabrica.vo.MateriaPrimaVO;

@Local
public interface ModeloNuevoArticulo {

	public abstract ArrayList<MateriaPrimaVO> obtenerMateriasPrimas();

	public abstract int guardarArticulo(ArticuloVO articuloVO);

}