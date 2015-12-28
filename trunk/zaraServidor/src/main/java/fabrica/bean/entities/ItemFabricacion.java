package fabrica.bean.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "itemFabricacionComenzada")
public class ItemFabricacion implements Serializable{

	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name="id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "itemSolicitud_id")
	private ItemSolicitud itemSolicitud;

	@ManyToOne
	@JoinColumn(name = "fabricacion_id")
	private FabricacionComenzada fabricacion;
	
	@ManyToOne
	@JoinColumn(name = "articulo_id")
	private Articulo articulo;

	@Column(name = "cantidad_fabricada")
	private int cantidadFabricada;
	
	public ItemFabricacion(){}
	
	public ItemFabricacion(int cantidad, ItemSolicitud itemSolicitud, FabricacionComenzada fabricacion){
		this.cantidadFabricada = cantidad;
		this.itemSolicitud = itemSolicitud;
		this.fabricacion = fabricacion;
		this.articulo = null;
	}

	public ItemFabricacion(int cantidad, Articulo articulo, FabricacionComenzada fabricacion){
		this.cantidadFabricada = cantidad;
		this.itemSolicitud = null;
		this.fabricacion = fabricacion;
		this.articulo = articulo;
	}
	
	public ItemSolicitud getItemSolicitud() {
		return itemSolicitud;
	}


	public void setItemSolFabr(ItemSolicitud itemSolicitud) {
		this.itemSolicitud = itemSolicitud;
	}

	public FabricacionComenzada getFabricacion() {
		return fabricacion;
	}

	public void setFabricacion(FabricacionComenzada fabricacion) {
		this.fabricacion = fabricacion;
	}

	public int getCantidadFabricada() {
		return cantidadFabricada;
	}

	public void setCantidadFabricada(int cantidadFabricada) {
		this.cantidadFabricada = cantidadFabricada;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
	
	public Articulo getArticulo() {
		return articulo;
	}

	public void setArticulo(Articulo articulo) {
		this.articulo = articulo;
	}


}