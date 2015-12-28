package fabrica.vo;

import java.io.Serializable;

public class ArtHogarVO extends ArticuloVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String nombre = "";
	private String categoria = "";
	private String medidas = "";
	private String composicion = "";

	// private Medida medidas = new Medida();
	// private ArrayList<MateriaPrima> composicion = new
	// ArrayList<MateriaPrima>();

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
