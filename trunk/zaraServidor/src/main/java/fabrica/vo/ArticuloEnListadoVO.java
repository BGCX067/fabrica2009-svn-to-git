package fabrica.vo;

import java.io.Serializable;
import java.util.ArrayList;

import javax.persistence.Transient;

public class ArticuloEnListadoVO implements Serializable{

	private static final long serialVersionUID = -2250719913115096635L;
	private String referencia = "";
	
	@Transient
	private String nombre = "";
	
	@Transient
	private String centro = "";
	private int cantidad;

	public ArticuloEnListadoVO(String referencia, String nombre, int cantidad) {
		this.referencia = referencia;
		this.nombre = nombre;
		this.cantidad = cantidad;
	}
	
	public ArticuloEnListadoVO(String referencia, String nombre, Integer cantidad) {
		this.referencia = referencia;
		this.nombre = nombre;
		this.cantidad = cantidad;
	}
	
	public ArticuloEnListadoVO(String referencia, String nombre, String centro, Long cantidad) {
		this.referencia = referencia;
		this.nombre = nombre;
		this.centro = centro;
		this.cantidad = new Long(cantidad).intValue();
	}

	public ArticuloEnListadoVO(String centro, String referencia, String nombre,	int cantidad) {
		this.centro = centro;
		this.referencia = referencia;
		this.nombre = nombre;
		this.cantidad = cantidad;
	}
	
	public static ArticuloEnListadoVO getByReferencia(String referencia, ArrayList<ArticuloEnListadoVO> articulosEnListado){
		for (ArticuloEnListadoVO articuloEnListado : articulosEnListado) {
			if (articuloEnListado.getReferencia().equalsIgnoreCase(referencia)){
				return articuloEnListado;
			}
		}
		return null;
	}
	
	public static ArticuloEnListadoVO getByReferenciaAndCentro(String referencia, String centro, ArrayList<ArticuloEnListadoVO> articulosEnListado){
		for (ArticuloEnListadoVO articuloEnListado : articulosEnListado) {
			if (articuloEnListado.getReferencia().equalsIgnoreCase(referencia) && articuloEnListado.getCentro().equalsIgnoreCase(centro)){
				return articuloEnListado;
			}
		}
		return null;
	}
	

	public ArticuloEnListadoVO() {
		// TODO Auto-generated constructor stub
	}

	public String getReferencia() {
		return referencia;
	}

	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public String getCentro() {
		return centro;
	}

	public void setCentro(int centro) {
		this.cantidad = centro;
	}
}
