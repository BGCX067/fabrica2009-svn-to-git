package fabrica.cu;

import java.util.ArrayList;

import fabrica.vo.MateriaPrimaVO;

//CLASS THAT REPRESENTS AN PCPROV or ENVPROV XML

public class EnvProv {

	private ArrayList<MateriaPrimaVO> materiasPrimas = new ArrayList<MateriaPrimaVO>();

	public ArrayList<MateriaPrimaVO> getMateriasPrimas() {
		return materiasPrimas;
	}

	public void setMateriasPrimas(ArrayList<MateriaPrimaVO> materiasPrimas) {
		this.materiasPrimas = materiasPrimas;
	}

	public void addMateriaPrima(MateriaPrimaVO materiaPrima) {
		this.materiasPrimas.add(materiaPrima);
	}

	public MateriaPrimaVO getByCodigo(int codigo) {
		for (MateriaPrimaVO materiaPrimaVO : materiasPrimas) {
			if (materiaPrimaVO.getCodigo() == codigo) {
				return materiaPrimaVO;
			}
		}
		return null;
	}

}
