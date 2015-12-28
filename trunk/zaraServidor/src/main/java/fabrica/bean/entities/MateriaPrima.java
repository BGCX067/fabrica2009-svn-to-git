package fabrica.bean.entities;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import fabrica.vo.MateriaPrimaVO;

@Entity
@Table(name="materiaPrima")
public class MateriaPrima implements Serializable{

	private static final long serialVersionUID = 1L;
	
	@Id
	@Column(name="codigo")
	private int codigo;
	
	@Column(name="nombre")	
	private String nombre = "";
	
	@Column(name="stock")
	private int stock;
	
	
	public MateriaPrima(){}
	
	public MateriaPrima(int codigo, int stock) {
		this.codigo = codigo;
		this.stock = stock;
	}

	public MateriaPrima(String nombre, int stock) {
		this.nombre = nombre;
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

	public int getStock() {
		return stock;
	}

	public void setStock(int stock) {
		this.stock = stock;
	}
	
	public static MateriaPrimaVO toVO(MateriaPrima materiaPrima){
		MateriaPrimaVO materiaPrimaVO = new MateriaPrimaVO();
		materiaPrimaVO.setCodigo(materiaPrima.getCodigo());
		materiaPrimaVO.setStock(materiaPrima.getStock());
		materiaPrimaVO.setNombre(materiaPrima.getNombre());
		return materiaPrimaVO;
	}
	
	public static MateriaPrima fromVO(MateriaPrimaVO materiaPrimaVO){
		MateriaPrima materiaPrima = new MateriaPrima();
		materiaPrima.setCodigo(materiaPrimaVO.getCodigo());
		materiaPrima.setStock(materiaPrimaVO.getStock());
		materiaPrima.setNombre(materiaPrimaVO.getNombre());
		return materiaPrima;
	}
	
}
