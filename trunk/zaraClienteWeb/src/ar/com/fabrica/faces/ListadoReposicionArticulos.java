package ar.com.fabrica.faces;

import java.util.List;

import javax.servlet.http.HttpServlet;

import ar.com.fabrica.modelos.BusinessDelegateReposicionArticulos;
import fabrica.vo.ArticuloEnListadoVO;

public class ListadoReposicionArticulos extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	private List<ArticuloEnListadoVO> articulos;
	private ArticuloEnListadoVO articulo;
	private int numRecords;	
	
	
	public int getNumRecords() {
		return numRecords;
	}

	public void setNumRecords(int numRecords) {
		this.numRecords = numRecords;
	}

	public ListadoReposicionArticulos(){
		this.articulos = new BusinessDelegateReposicionArticulos().calculaArticulosPorCD();
		if (this.articulos != null){
			this.numRecords = this.articulos.size();
		}
		else{
			this.numRecords = 0;
		}
	}
	
	
	public ArticuloEnListadoVO getArticulo() {
		return articulo;
	}

	public void setArticulo(ArticuloEnListadoVO articulo) {
		this.articulo = articulo;
	}

	public List<ArticuloEnListadoVO> getArticulos() {
		return articulos;
	}

	public void setArticulos(List<ArticuloEnListadoVO> articulos) {
		this.articulos = articulos;
	}
}
