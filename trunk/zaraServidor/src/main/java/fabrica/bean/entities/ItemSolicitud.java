package fabrica.bean.entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "itemSolicitud")
public class ItemSolicitud implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "articulo_id")
	private Articulo articulo;

	@ManyToOne
	@JoinColumn(name = "solicitud_id")
	private SolicitudFabricacion solicitud;

	@Column(name = "cantidad")
	private int cantidad;
	
	@Column(name = "cantidad_entregada")
	private int cantidadEntregada;
	
	@OneToMany(fetch=FetchType.LAZY, mappedBy="itemSolicitud", cascade=CascadeType.PERSIST)
	private List<ItemFabricacion> itemsFabricacion = new ArrayList<ItemFabricacion>();

	public List<ItemFabricacion> getItemsFabricacion() {
		return itemsFabricacion;
	}

	public void setItemsFabricacion(List<ItemFabricacion> itemsFabricacion) {
		this.itemsFabricacion = itemsFabricacion;
	}

	public int getCantidadEntregada() {
		return cantidadEntregada;
	}

	public void setCantidadEntregada(int cantidadEntregada) {
		this.cantidadEntregada = cantidadEntregada;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}
	
	public SolicitudFabricacion getSolicitud() {
		return solicitud;
	}

	public void setSolicitud(SolicitudFabricacion solicitud) {
		this.solicitud = solicitud;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}


}