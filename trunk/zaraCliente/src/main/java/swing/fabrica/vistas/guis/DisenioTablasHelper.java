package swing.fabrica.vistas.guis;

import java.awt.Color;
import java.awt.Font;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import fabrica.vo.ArticuloEnListadoVO;
import fabrica.vo.MateriaPrimaVO;


public class DisenioTablasHelper {

	public static void vaciarTabla(DefaultTableModel t) {
		int filas = t.getRowCount();
		for (int j = 0; j < filas; j++) {
			t.removeRow(0);
		}
	}

	public static void rellenarTablaMateriaPrima(DefaultTableModel t, ArrayList<MateriaPrimaVO> materiasPrimas) {

		vaciarTabla(t);
		for (MateriaPrimaVO materiaPrimaVO : materiasPrimas) {
			t.addRow(new Object[] { materiaPrimaVO.getNombre(),
					materiaPrimaVO.getStock() });
		}

	}

	public static void rellenarTablaArticuloPorCD(DefaultTableModel t, ArrayList<ArticuloEnListadoVO> articulosPorCD) {

		vaciarTabla(t);

		String centro = "";
		for (ArticuloEnListadoVO reposicion : articulosPorCD) {
			if ((centro != null) && (centro.equals(reposicion.getCentro()))) {
				t.addRow(new Object[] { null, reposicion.getReferencia(),
						reposicion.getNombre(), reposicion.getCantidad() });
			} else {
				centro = reposicion.getCentro();
				t.addRow(new Object[] { reposicion.getCentro(),
						reposicion.getReferencia(), reposicion.getNombre(),
						reposicion.getCantidad() });
			}
		}
	}

	public static void rellenarTablaArticulo(DefaultTableModel t,
			ArrayList<ArticuloEnListadoVO> articulos) {

		vaciarTabla(t);

		for (ArticuloEnListadoVO articulo : articulos) {
			t.addRow(new Object[] { articulo.getReferencia(),
					articulo.getNombre(), articulo.getCantidad() });
		}

	}

	public static JTable obtenerTablaMateriaPrima(DefaultTableModel t) {
		JTable tabla = new JTable();
		tabla.setModel(new javax.swing.table.DefaultTableModel());
		tabla.setModel(t);
		tabla.setEnabled(false);
		t.addColumn((Object) "Materia Prima");
		t.addColumn((Object) "Cantidad");
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn colRef = tabla.getColumnModel().getColumn(0);
		colRef.setPreferredWidth(327);
		colRef.setMaxWidth(327);
		colRef.setMinWidth(327);
		TableColumn colCant = tabla.getColumnModel().getColumn(1);
		colCant.setPreferredWidth(80);
		colCant.setMaxWidth(80);
		colCant.setMinWidth(80);
		pintarTabla(tabla);
		return tabla;
	}

	public static JTable obtenerTablaArticulo(DefaultTableModel t) {
		JTable tabla = new JTable();
		tabla.setModel(new javax.swing.table.DefaultTableModel());
		tabla.setModel(t);
		tabla.setEnabled(false);
		t.addColumn((Object) "Referencia");
		t.addColumn((Object) "Articulo");
		t.addColumn((Object) "Cantidad");
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn colRef = tabla.getColumnModel().getColumn(0);
		colRef.setPreferredWidth(90);
		colRef.setMaxWidth(90);
		colRef.setMinWidth(90);
		TableColumn colArt = tabla.getColumnModel().getColumn(1);
		colArt.setPreferredWidth(327);
		colArt.setMaxWidth(327);
		colArt.setMinWidth(327);
		TableColumn colCant = tabla.getColumnModel().getColumn(2);
		colCant.setPreferredWidth(80);
		colCant.setMaxWidth(80);
		colCant.setMinWidth(80);
		pintarTabla(tabla);
		return tabla;
	}

	public static JTable obtenerTablaArticuloPorCD(DefaultTableModel t) {
		JTable tabla = new JTable();
		tabla.setModel(new javax.swing.table.DefaultTableModel());
		tabla.setModel(t);
		tabla.setEnabled(false);
		t.addColumn((Object) "Centro");
		t.addColumn((Object) "Referencia");
		t.addColumn((Object) "Articulo");
		t.addColumn((Object) "Cantidad");
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		TableColumn colCod = tabla.getColumnModel().getColumn(0);
		colCod.setPreferredWidth(50);
		colCod.setMaxWidth(50);
		colCod.setMinWidth(50);
		TableColumn colRef = tabla.getColumnModel().getColumn(1);
		colRef.setPreferredWidth(90);
		colRef.setMaxWidth(90);
		colRef.setMinWidth(90);
		TableColumn colArt = tabla.getColumnModel().getColumn(2);
		colArt.setPreferredWidth(307);
		colArt.setMaxWidth(297);
		colArt.setMinWidth(297);
		TableColumn colCant = tabla.getColumnModel().getColumn(3);
		colCant.setPreferredWidth(60);
		colCant.setMaxWidth(60);
		colCant.setMinWidth(60);
		pintarTabla(tabla);
		return tabla;
	}

	public static void pintarTabla(JTable tabla) {
		tabla.setBackground(new Color(210, 210, 210));
		tabla.setForeground(new Color(17, 119, 204));
		Font font = new Font("Serif", Font.PLAIN, 12);
		tabla.setFont(font);

	}
}
