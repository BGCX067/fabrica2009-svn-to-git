package fabrica.bean.entities;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "fabricacionComenzada")
 public class FabricacionComenzada implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name="id")
	private int id;

	@Temporal(TemporalType.DATE)
	@Column(name="fecha")
	private Date fecha;

	@OneToMany(mappedBy="fabricacion", cascade=CascadeType.PERSIST)
	private List<ItemFabricacion> itemsFabricacion = new ArrayList<ItemFabricacion>();
	
	public List<ItemFabricacion> getItemsFabricacion() {
		return itemsFabricacion;
	}

	public void setItemsFabricacion(List<ItemFabricacion> itemsFabricacion) {
		this.itemsFabricacion = itemsFabricacion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date date) {
		this.fecha = date;
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

}
