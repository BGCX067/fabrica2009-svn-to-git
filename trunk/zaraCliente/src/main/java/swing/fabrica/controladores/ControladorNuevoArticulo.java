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
import java.util.HashMap;
import java.util.Map;

import javax.swing.JFileChooser;

import swing.fabrica.modelos.BusinessDelegateNuevoArticulo;
import swing.fabrica.mvcFramework.Controller;
import swing.fabrica.vistas.VistaNuevoArticulo;
import swing.fabrica.xml.XMLReader;

import fabrica.vo.ArtHogarVO;
import fabrica.vo.ArtRopaVO;
import fabrica.vo.ArticuloVO;
import fabrica.vo.MateriaPrimaVO;

public class ControladorNuevoArticulo extends Controller {
	
	private ArticuloVO articuloVO;
	
	public ControladorNuevoArticulo(BusinessDelegateNuevoArticulo mna,	VistaNuevoArticulo vna) {
		super(mna, vna);
	}
	
	public void agregarMateriaPrima() {
		((VistaNuevoArticulo) (this.getVista())).agregarMateriaPrima();
	}

	public void quitarMateriaPrima() {
		((VistaNuevoArticulo) (this.getVista())).quitarMateriaPrima();
	}

	public void ObtenerNuevoArt() {
		((VistaNuevoArticulo) (this.getVista())).agregarNvoArt();
	}

	public void DarAltaNvoArt() {
		((VistaNuevoArticulo) (this.getVista())).altaNvoArt();
	}

	public void validarArchivoArticulo(Integer opcion, File archivoArticulo) {
		if (opcion == JFileChooser.APPROVE_OPTION) {
			this.articuloVO = new XMLReader().getArticulo(archivoArticulo);
			if (this.articuloVO != null){
				((VistaNuevoArticulo) (this.getVista())).componentes();
			}
			else{
				((VistaNuevoArticulo) (this.getVista())).errorArchivo();
			}
		} 
		else {
			((VistaNuevoArticulo) (this.getVista())).canceloGuardar();
		}
	}

	public void guardarArticulo(Map<String, String> campos, ArrayList<MateriaPrimaVO> materiasPrimasVO) {
		int tiempo;
		try {
			tiempo = Integer.parseInt(campos.get("Tiempo"));
		} catch (NumberFormatException nfe) {
			tiempo = 0;
		}
		
		articuloVO.setMateriasPrimas(materiasPrimasVO);
		
		if (this.articuloVO.getClass().equals(ArtRopaVO.class)) {
			((ArtRopaVO) this.articuloVO).setTalle(campos.get("Talle"));
			((ArtRopaVO) this.articuloVO).setOrigen(campos.get("Origen"));
		} else {
			((ArtHogarVO) this.articuloVO).setNombre(campos.get("Nombre"));
			((ArtHogarVO) this.articuloVO).setComposicion(campos.get("Composicion"));
			((ArtHogarVO) this.articuloVO).setMedidas(campos.get("Medidas"));
			((ArtHogarVO) this.articuloVO).setCategoria(campos.get("Categoria"));
			((ArtHogarVO) this.articuloVO).setLinea(campos.get("Linea"));
		}
		
		((ArticuloVO) this.articuloVO).setReferencia(campos.get("Referencia"));
		((ArticuloVO) this.articuloVO).setDescripcion(campos.get("Descripcion"));
		((ArticuloVO) this.articuloVO).setColor(campos.get("Color"));
		((ArticuloVO) this.articuloVO).setSeccion(campos.get("Seccion"));
		((ArticuloVO) this.articuloVO).setPrecioVenta(Float.parseFloat(campos.get("Precio Vta Unit")));
		((ArticuloVO) this.articuloVO).setTiempoDeFabricacion(tiempo);

		int id = ((BusinessDelegateNuevoArticulo) (this.getModelo())).guardarArticulo(articuloVO);
		if (id > 0) {
			((VistaNuevoArticulo) (this.getVista())).okGuardar();
		} else {
			((VistaNuevoArticulo) (this.getVista())).errorGuardar();
		}
	}
	
	public Map<String, String> obtenerCampos() {
		Map<String, String> campos = new HashMap<String, String>();
		if (this.articuloVO.getClass().equals(ArtRopaVO.class)) {
			ArtRopaVO auxArt = (ArtRopaVO) this.articuloVO;
			campos.put("Referencia", auxArt.getReferencia());
			campos.put("Linea", auxArt.getLinea());
			campos.put("Descripcion", auxArt.getDescripcion());
			campos.put("Talle", auxArt.getTalle());
			campos.put("Color", auxArt.getColor());
			campos.put("Seccion", auxArt.getSeccion());
			campos.put("Precio Vta Unit", ((Float) auxArt.getPrecioVenta()).toString());
			campos.put("Origen", auxArt.getOrigen());
		} else {
			ArtHogarVO auxArt = (ArtHogarVO) this.articuloVO;
			campos.put("Referencia", auxArt.getReferencia());
			campos.put("Seccion", auxArt.getSeccion());
			campos.put("Nombre", auxArt.getNombre());
			campos.put("Descripcion", auxArt.getDescripcion());
			campos.put("Composicion", auxArt.getComposicion());
			campos.put("Medidas", auxArt.getMedidas());
			campos.put("Precio Vta Unit", ((Float) auxArt.getPrecioVenta()).toString());
			campos.put("Color", auxArt.getColor());
			campos.put("Categoria", auxArt.getCategoria());
			campos.put("Linea", auxArt.getLinea());
		}
		return campos;
	}

	public void cerrar() {
		((VistaNuevoArticulo) (this.getVista())).cerrar();
	}
}
