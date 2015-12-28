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

import fabrica.bean.entities.MateriaPrima;
import fabrica.facades.ModeloEnvioProveedores;
import fabrica.vo.MateriaPrimaVO;

@Stateless
public class ModeloEnvioProveedoresBean implements ModeloEnvioProveedores{
	
	@PersistenceContext(unitName="Fabrica")
	private EntityManager manager;

	public ArrayList<MateriaPrimaVO> procesarEnvioProveedores(ArrayList<MateriaPrimaVO> materiasPrimasVO) {

		try{
		
			MateriaPrima materiaPrimaAux = null;
			
			for (MateriaPrimaVO materiaPrimaVO : materiasPrimasVO) {
				MateriaPrima materiaPrima = new MateriaPrima(materiaPrimaVO.getCodigo(), materiaPrimaVO.getStock());
				
				materiaPrimaAux = manager.find(MateriaPrima.class, materiaPrima.getCodigo());
				if (materiaPrimaAux == null){
					manager.persist(materiaPrima);
				}
				else{
					materiaPrima.setNombre(materiaPrimaAux.getNombre());
					materiaPrima.setStock(materiaPrima.getStock() + materiaPrimaAux.getStock());
					manager.merge(materiaPrima);
				}
				
				materiaPrimaVO.setNombre(materiaPrima.getNombre());
			}
			return materiasPrimasVO;
		}
		catch (Exception e) {
			System.err.println("Hubo un problema procesando las materias primas, ver log del servidor");
		}
		return null;
	}
}
