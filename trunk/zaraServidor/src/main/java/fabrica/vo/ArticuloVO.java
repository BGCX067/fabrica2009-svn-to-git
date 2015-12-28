package fabrica.vo;

import java.io.Serializable;
import java.util.ArrayList;

public class ArticuloVO implements Serializable {

	private static final long serialVersionUID = 1L;
	protected int idArticulo;
	protected String seccion = "";
	protected String referencia = "";
	protected String descripcion = "";
	protected String color = "";
	protected float precioVenta;
	protected String linea = "";
	protected int tiempoDeFabricacion;
	protected ArrayList<CentroDistribucionVO> centrosDistribucion =  new ArrayList<CentroDistribucionVO>();
	protected ArrayList<MateriaPrimaVO> materiasPrimas =  new ArrayList<MateriaPrimaVO>();

	public String toString() {
		return "Seccion: " + this.getSeccion() + "\nReferencia: "
				+ this.getReferencia() + "\nDescripcion: "
				+ this.getDescripcion() + "\nColor: " + this.getColor()
				+ "\nPrecio de Venta: " + this.getPrecioVenta() + "\nLinea: "
				+ this.getLinea() + "\nTiempo de Fabricacion: "
				+ this.getTiempoDeFabricacion() + " dias";
	}
	
	public int getIdArticulo() {
		return idArticulo;
	}

	public void setIdArticulo(int idArticulo) {
		this.idArticulo = idArticulo;
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

	public String getSeccion() {
		return seccion;
	}

	public void setSeccion(String seccion) {
		this.seccion = seccion;
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

	public void setCentrosDistribucion(ArrayList<CentroDistribucionVO> centrosDistribucion) {
		this.centrosDistribucion = centrosDistribucion;
	}

	public ArrayList<MateriaPrimaVO> getMateriasPrimas() {
		return materiasPrimas;
	}

	public void setMateriasPrimas(ArrayList<MateriaPrimaVO> materiasPrimas) {
		this.materiasPrimas = materiasPrimas;
	}

}
