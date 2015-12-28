package fabrica.facades;

import java.util.ArrayList;

import javax.ejb.Local;

import fabrica.vo.ArticuloEnListadoVO;
import fabrica.vo.SolFabrVO;

@Local
public interface ModeloSolicitudFabricacion {

	public abstract ArrayList<ArticuloEnListadoVO> cargarSolicitudesAnteriores();

	public abstract ArrayList<ArticuloEnListadoVO> procesarNuevasFabricaciones(SolFabrVO solFabrVo);

}