package fabrica.vo;

import java.io.Serializable;

public class ArtRopaVO extends ArticuloVO implements Serializable{

	private static final long serialVersionUID = 1L;
	private String talle = "";
	private String origen = "";

	public String toString() {
		return super.toString() + "\nTalle: " + this.getTalle() + "\nOrigen: "
				+ this.getOrigen();
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

}
