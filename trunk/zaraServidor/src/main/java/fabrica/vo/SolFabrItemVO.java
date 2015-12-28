package fabrica.vo;

import java.io.Serializable;

public class SolFabrItemVO implements Serializable {
	
	private static final long serialVersionUID = 1L;
	private String referencia="";
	private int cantidad;
	
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
}
