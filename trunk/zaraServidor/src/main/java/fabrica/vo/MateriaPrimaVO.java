package fabrica.vo;

import java.io.Serializable;

public class MateriaPrimaVO implements Serializable, Comparable<MateriaPrimaVO>{

	private static final long serialVersionUID = 1L;
	private int codigo;
	private String nombre;
	private int stock;
	
	public int getStock() {
		return stock;
	}
	public void setStock(int stock) {
		this.stock = stock;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public MateriaPrimaVO(int codigo, String nombre, int stock) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.stock = stock;
	}
	
	public MateriaPrimaVO(){
		
	}
	public int compareTo(MateriaPrimaVO other) {
		return this.nombre.compareTo(other.getNombre());
	}
}
