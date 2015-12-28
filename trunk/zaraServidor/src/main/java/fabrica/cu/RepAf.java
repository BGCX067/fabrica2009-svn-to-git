package fabrica.cu;

import java.util.ArrayList;

import javax.persistence.Transient;

import fabrica.vo.ArticuloEnListadoVO;

public class RepAf {
	@Transient
	@org.jboss.cache.aop.annotation.Transient
	private String cd="";
	private ArrayList<ArticuloEnListadoVO> articulos = new ArrayList<ArticuloEnListadoVO>();

	public ArrayList<ArticuloEnListadoVO> getArticulos() {
		return articulos;
	}
	
	public static RepAf obtenerByCentro(String centro, ArrayList<RepAf> reposiciones){
		for (RepAf repAf : reposiciones) {
			if (repAf.getCd().equals(centro)){
				return repAf;
			}
		}
		return null;
	}

	public void setArticulos(ArrayList<ArticuloEnListadoVO> articulos) {
		this.articulos = articulos;
	}
	
	public void addArticulo(ArticuloEnListadoVO articulo){
		articulos.add(articulo);
	}

	public String getCd() {
		return cd;
	}

	public void setCd(String cd) {
		this.cd = cd;
	}
	
	
	
}
