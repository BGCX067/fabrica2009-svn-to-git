/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.controladores;

import java.io.File;
import java.util.ArrayList;

import javax.swing.JFileChooser;

import swing.fabrica.modelos.BusinessDelegateEnvioProveedores;
import swing.fabrica.mvcFramework.Controller;
import swing.fabrica.vistas.VistaEnvioProveedores;
import swing.fabrica.xml.XMLReader;
import fabrica.vo.MateriaPrimaVO;

public class ControladorEnvioProveedores extends Controller {
	public ControladorEnvioProveedores(BusinessDelegateEnvioProveedores mep,
			VistaEnvioProveedores vep) {
		super(mep, vep);
	}

	public void obtenerMateriasPrimas() {
		((VistaEnvioProveedores) (this.getVista())).agregarMatPrimas();
	}

	public void procesarEnvioProveedores(Integer opcion, File archivo) {
		
		if (opcion == JFileChooser.APPROVE_OPTION) {
			ArrayList<MateriaPrimaVO> materiasPrimasVO = new XMLReader().getEnvioProveedores(archivo);
			
			if (materiasPrimasVO != null){
				materiasPrimasVO = ((BusinessDelegateEnvioProveedores) (this.getModelo())).procesarEnvioProveedores(materiasPrimasVO);
				
				if (materiasPrimasVO != null){
					((VistaEnvioProveedores) (this.getVista())).cargarArticulos(materiasPrimasVO);
				}
				else{
					((VistaEnvioProveedores) (this.getVista())).errorCargar();
				}
			}
			else{
				((VistaEnvioProveedores) (this.getVista())).errorArchivo();
			}
		} 
	}
	public void cerrar() {
		((VistaEnvioProveedores) (this.getVista())).cerrar();
	}
}
