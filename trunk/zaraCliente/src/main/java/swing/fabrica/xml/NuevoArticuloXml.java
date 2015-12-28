package swing.fabrica.xml;

import java.util.ArrayList;

import fabrica.vo.CentroDistribucionVO;

public class NuevoArticuloXml {
	private String seccion = "";
	private String referencia = "";
	private String descripcion = "";
	private String color = "";
	private float precioVenta;
	private String linea = "";
	private int tiempoDeFabricacion;
	private ArrayList<CentroDistribucionVO> centrosDistribucion = new ArrayList<CentroDistribucionVO>();
	private String nombre = "";
	private String categoria = "";
	private String medidas = "";
	private String composicion = "";
	private String talle = "";
	private String origen = "";
	private String tipo = "";
	public String getSeccion() {
		return seccion;
	}
	public void setSeccion(String seccion) {
		this.seccion = seccion;
	}
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public float getPrecioVenta() {
		return precioVenta;
	}
	public void setPrecioVenta(float precioVenta) {
		this.precioVenta = precioVenta;
	}
	public String getLinea() {
		return linea;
	}
	public void setLinea(String linea) {
		this.linea = linea;
	}
	public int getTiempoDeFabricacion() {
		return tiempoDeFabricacion;
	}
	public void setTiempoDeFabricacion(int tiempoDeFabricacion) {
		this.tiempoDeFabricacion = tiempoDeFabricacion;
	}
	public ArrayList<CentroDistribucionVO> getCentrosDistribucion() {
		return centrosDistribucion;
	}
	public void setCentrosDistribucion(
			ArrayList<CentroDistribucionVO> centrosDistribucion) {
		this.centrosDistribucion = centrosDistribucion;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCategoria() {
		return categoria;
	}
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}
	public String getMedidas() {
		return medidas;
	}
	public void setMedidas(String medidas) {
		this.medidas = medidas;
	}
	public String getComposicion() {
		return composicion;
	}
	public void setComposicion(String composicion) {
		this.composicion = composicion;
	}
	public String getTalle() {
		return talle;
	}
	public void setTalle(String talle) {
		this.talle = talle;
	}
	public String getOrigen() {
		return origen;
	}
	public void setOrigen(String origen) {
		this.origen = origen;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
}
