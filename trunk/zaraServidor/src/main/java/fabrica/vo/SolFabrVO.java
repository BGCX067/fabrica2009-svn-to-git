package fabrica.vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;

public class SolFabrVO implements Serializable {

	private static final long serialVersionUID = 1L;
	private int idCentroDist;
	
	//private CentroDistribucionVO centroDistribucion = new CentroDistribucionVO();
	private ArrayList<SolFabrItemVO> articulos = new ArrayList<SolFabrItemVO>();
	private Date fecha;
	
	public ArrayList<SolFabrItemVO> getFabricaciones() {
		return articulos;
	}

	public void setFabricaciones(ArrayList<SolFabrItemVO> fabricaciones) {
		this.articulos = fabricaciones;
	}
	
	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date date) {
		this.fecha = date;
	}
	
	public int getIdCentroDist() {
		return idCentroDist;
	}

	public void setIdCentroDist(int idCentroDist) {
		this.idCentroDist = idCentroDist;
	}

	
}
