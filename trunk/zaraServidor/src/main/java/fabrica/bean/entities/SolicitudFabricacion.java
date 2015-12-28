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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "solicitudFabricacion")
 public class SolicitudFabricacion implements Serializable{
	
	private static final long serialVersionUID = 1L;

	@Id @GeneratedValue
	@Column(name="id")
	private int id;

	@ManyToOne
	private CentroDistribucion centroDistribucion;
	
	@Temporal(	TemporalType.DATE)
	@Column(name="fecha")
	private Date fecha;

	@OneToMany(mappedBy="solicitud", cascade=CascadeType.PERSIST)
	private List<ItemSolicitud> itemsSolicitud = new ArrayList<ItemSolicitud>();
	
	
	public CentroDistribucion getCentroDistribucion() {
		return centroDistribucion;
	}

	public List<ItemSolicitud> getItemsSolicitud() {
		return itemsSolicitud;
	}

	public void setItemsSolFabr(ArrayList<ItemSolicitud> itemsSolicitud) {
		this.itemsSolicitud = itemsSolicitud;
	}

	public void setCentroDistribucion(CentroDistribucion centroDistribucion) {
		this.centroDistribucion = centroDistribucion;
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
