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
import java.util.GregorianCalendar;

import javax.swing.JFileChooser;

import swing.fabrica.modelos.BusinessDelegateSolicitudFabricacion;
import swing.fabrica.mvcFramework.Controller;
import swing.fabrica.vistas.VistaSolicitudFabricacion;
import swing.fabrica.xml.XMLReader;
import fabrica.vo.ArticuloEnListadoVO;
import fabrica.vo.SolFabrVO;

public class ControladorSolicitudFabricacion extends Controller {
	private SolFabrVO fabricacionesPendientes;
	
	public ControladorSolicitudFabricacion(BusinessDelegateSolicitudFabricacion msf,
			VistaSolicitudFabricacion vsf) {
		super(msf, vsf);
	}

	public void ObtenerNuevaSol() {
		((VistaSolicitudFabricacion) (this.getVista())).agregarNvaSol();
	}

	public void procesarSolicitudFabricacion(Integer opcion, File archivoFabricaciones) {
		if (opcion == JFileChooser.APPROVE_OPTION) {
			fabricacionesPendientes = new XMLReader().getNuevasFabricaciones(archivoFabricaciones);
			if (fabricacionesPendientes != null){
				fabricacionesPendientes.setFecha(GregorianCalendar.getInstance().getTime());
				ArrayList<ArticuloEnListadoVO> fabricaciones = ((BusinessDelegateSolicitudFabricacion) (this.getModelo())).procesarNuevasFabricaciones(fabricacionesPendientes);
				((VistaSolicitudFabricacion) (this.getVista())).cargarArticulos(fabricaciones);
			}else{
				((VistaSolicitudFabricacion) (this.getVista())).errorArchivo();
			}
		}
	}
	public void cerrar() {
		((VistaSolicitudFabricacion) (this.getVista())).cerrar();
	}

}
