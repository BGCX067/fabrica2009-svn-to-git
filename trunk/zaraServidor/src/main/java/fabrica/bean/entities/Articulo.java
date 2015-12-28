/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package fabrica.bean.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

@Entity
@Table(name="articulo")
@Inheritance(strategy= InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name="tipo", discriminatorType = DiscriminatorType.STRING)
@DiscriminatorValue("A")

public abstract class Articulo implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id @GeneratedValue
	@Column(name="id")
	protected int idArticulo;
	@Column(name="seccion")
	protected String seccion = "";
	@Column(name="referencia")
	protected String referencia = "";
	@Column(name="descripcion")
	protected String descripcion = "";
	@Column(name="color")
	protected String color = "";
	@Column(name="precioVenta")
	protected float precioVenta;
	@Column(name="linea")
	protected String linea = "";
	@Column(name="tiempoDeFabricacion")
	protected int tiempoDeFabricacion;
	@Column(name="fabricadoPorNuevo")
	protected int fabricadoPorNuevo;
	protected int esNuevo;
	
	@JoinTable (
			uniqueConstraints={@UniqueConstraint(columnNames={"articulo_id","centroDistribucion_id"})},
			name="articuloByCentroDistribucion",
		    joinColumns={@JoinColumn (name="articulo_id")},
		    inverseJoinColumns={@JoinColumn (name="centroDistribucion_id")}
	)
	@ManyToMany(fetch=FetchType.LAZY, cascade=CascadeType.MERGE)
	protected List<CentroDistribucion> centrosDistribucion = new ArrayList<CentroDistribucion>();
	
	@OneToMany(mappedBy="articulo", cascade=CascadeType.PERSIST)
	protected List<ArticuloMateriaPrima> articuloMateriasPrimas = new ArrayList<ArticuloMateriaPrima>();
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="articulo", cascade=CascadeType.PERSIST)
	protected List<ItemSolicitud> articuloSolicitudFabricacion = new ArrayList<ItemSolicitud>(); 
	
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

	public List<CentroDistribucion> getCentrosDistribucion() {
		return centrosDistribucion;
	}

	public void setCentrosDistribucion(
			ArrayList<CentroDistribucion> centrosDistribucion) {
		this.centrosDistribucion = centrosDistribucion;
	}

	public List<ArticuloMateriaPrima> getMateriasPrimas() {
		return articuloMateriasPrimas;
	}

	public void setMateriasPrimas(List<ArticuloMateriaPrima> articuloMateriasPrimas) {
		this.articuloMateriasPrimas = articuloMateriasPrimas;
	}

	public void setCentrosDistribucion(List<CentroDistribucion> centrosDistribucion) {
		this.centrosDistribucion = centrosDistribucion;
	}
	
	public List<ArticuloMateriaPrima> getArticuloMateriasPrimas() {
		return articuloMateriasPrimas;
	}

	public void setArticuloMateriasPrimas(
			List<ArticuloMateriaPrima> articuloMateriasPrimas) {
		this.articuloMateriasPrimas = articuloMateriasPrimas;
	}

	public List<ItemSolicitud> getArticuloSolicitudFabricacion() {
		return articuloSolicitudFabricacion;
	}

	public void setArticuloSolicitudFabricacion(
			List<ItemSolicitud> articuloSolicitudFabricacion) {
		this.articuloSolicitudFabricacion = articuloSolicitudFabricacion;
	}
	
	public int getFabricadoPorNuevo() {
		return fabricadoPorNuevo;
	}

	public void setFabricadoPorNuevo(int fabricadoPorNuevo) {
		this.fabricadoPorNuevo = fabricadoPorNuevo;
	}

	public int getEsNuevo() {
		return esNuevo;
	}

	public void setEsNuevo(int esNuevo) {
		this.esNuevo = esNuevo;
	}



}
