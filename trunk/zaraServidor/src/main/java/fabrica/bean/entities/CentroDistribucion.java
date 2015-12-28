package fabrica.bean.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="centroDistribucion")


public class CentroDistribucion implements Serializable{

	private static final long serialVersionUID = 1L;
	@Id
	@Column(name="id")
	private int id;
	@Column(name="nombre")
	private String nombre;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public static CentroDistribucion fromVO(int idCentroDist){
		CentroDistribucion centroDistribucion = new CentroDistribucion();
		centroDistribucion.setId(idCentroDist);
		return centroDistribucion;
	}
}
