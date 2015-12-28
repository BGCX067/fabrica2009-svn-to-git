package fabrica.facades;

import java.util.ArrayList;

import javax.ejb.Local;

import fabrica.vo.MateriaPrimaVO;

@Local
public interface ModeloEnvioProveedores {

	public abstract ArrayList<MateriaPrimaVO> procesarEnvioProveedores(ArrayList<MateriaPrimaVO> materiasPrimas);

}