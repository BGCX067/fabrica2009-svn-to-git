package fabrica.bean.entities;

import java.io.Serializable;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("H")

public class ArtHogar extends Articulo implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nombre = "";
	private String categoria = "";
	private String medidas = "";
	private String composicion = "" ;

	public String toString() {
		return super.toString() + "\nNombre: " + this.getNombre()
				+ "\nCategoria: " + this.getCategoria() + "\nMedidas: "
				+ this.getMedidas() + "\nComposicion: " + this.getComposicion();
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

}
