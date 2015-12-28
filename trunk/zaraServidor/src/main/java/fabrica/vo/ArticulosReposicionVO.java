package fabrica.vo;

import java.util.Date;

public class ArticulosReposicionVO {
	private static final long serialVersionUID = -2250719913115096635L;
	private String referencia = "";
	private String nombre = "";
	private String centro = "";
	private int cantidad;
	private Date fecha;
	protected int tiempo;
	public String getReferencia() {
		return referencia;
	}
	public void setReferencia(String referencia) {
		this.referencia = referencia;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getCentro() {
		return centro;
	}
	public void setCentro(String centro) {
		this.centro = centro;
	}
	public int getCantidad() {
		return cantidad;
	}
	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	public int getTiempo() {
		return tiempo;
	}
	public void setTiempo(int tiempo) {
		this.tiempo = tiempo;
	}
	public ArticulosReposicionVO(String referencia, String nombre,
			String centro, long cantidad, Date fecha, int tiempo) {
		this.referencia = referencia;
		this.nombre = nombre;
		this.centro = centro;
		this.cantidad = new Long(cantidad).intValue();
		this.fecha = fecha;
		this.tiempo = tiempo;
	}
	
	
	
}
