/*
 * Ingenieria de Sistemas II - 1C2009
 * Marzo/2009
 * 
 * Ejemplo 3 - Framework Model-View-Controller con Patron Observer
 *  
 */

package swing.fabrica.vistas.guis;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.HeadlessException;
import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.Vector;

import javax.swing.BorderFactory;
import javax.swing.FocusManager;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumn;

import org.apache.commons.collections15.CollectionUtils;
import org.apache.commons.collections15.Predicate;

import swing.fabrica.controladores.ControladorNuevoArticulo;
import swing.fabrica.modelos.BusinessDelegateNuevoArticulo;
import swing.fabrica.vistas.VistaNuevoArticulo;
import fabrica.vo.MateriaPrimaVO;

public class DisenioNuevoArticulo extends JFrame {

	private static final long serialVersionUID = 1L;
	private VistaNuevoArticulo vistaPadre = null;
	private JTable tabla;
	private JPanel a = new JPanel(new BorderLayout());
	private DefaultTableModel t = new DefaultTableModel();
	private String path = System.getProperty("user.dir");
	private File file = new File(path);
	private JFileChooser jfc = new JFileChooser(file);
	private JButton btnCargarArchivo;
	private ArrayList<JButton> buttons = new ArrayList<JButton>();
	private ArrayList<JLabel> labels = new ArrayList<JLabel>();
	private ArrayList<JTextField> textfields = new ArrayList<JTextField>();
	private ArrayList<MateriaPrimaVO> materiasPrimasVO = new ArrayList<MateriaPrimaVO>();

	public VistaNuevoArticulo getVistaPadre() {
		return vistaPadre;
	}

	public void setVistaPadre(VistaNuevoArticulo vistaPadre) {
		this.vistaPadre = vistaPadre;
	}

	public DisenioNuevoArticulo(VistaNuevoArticulo vna)
			throws HeadlessException {
		super();
		vistaPadre = vna;
		initialize();
	}

	public void componentes() {
		int iPos = 10;

		Map<String, String> campos = ((ControladorNuevoArticulo) (vistaPadre
				.getControlador())).obtenerCampos();
		this.getContentPane().setLayout(null);
		this.getContentPane().add(
				getLabelsArts(campos.size() / 2, "Materias Primas", 10));
		this.getContentPane().add(
				getLabelsArts(campos.size() / 2, "Tiempo", 310));
		this.getContentPane().add(
				getTextsArts(campos.size() / 2, "Tiempo", "", 310));
		this.getContentPane().add(getButtonAgregarMatP(campos.size()));
		this.getContentPane().add(getButtonQuitarMatP(campos.size()));
		this.getContentPane().add(getButtonAlta());
		
		this.getContentPane().add(getFileChooser());
		this.getContentPane().add(getPanelTabla(campos.size()));
		int i = 0;
		Set<String> componente = campos.keySet();
		Iterator<String> nombresComponentes = componente.iterator();
		String key;
		while (nombresComponentes.hasNext()) {
			key = nombresComponentes.next();
			this.getContentPane().add(getLabelsArts(i, key, iPos));
			this.getContentPane().add(
					getTextsArts(i, key, campos.get(key), iPos));
			if (nombresComponentes.hasNext()) {
				key = nombresComponentes.next();
				this.getContentPane().add(getLabelsArts(i, key, iPos + 300));
				this.getContentPane().add(
						getTextsArts(i++, key, campos.get(key), iPos + 300));
			}
		}
		this.getContentPane().repaint();
		a.setVisible(true);
	}

	private JLabel getLabelNvoArt() {
		JLabel lbl = new JLabel("Alta de Articulos");
		lbl.setLocation(10, 10);
		lbl.setSize(100, 20);
		return lbl;
	}

	private JButton getButtonArt() {
		btnCargarArchivo = new JButton("Agregar Articulo");
		btnCargarArchivo.setBounds(110, 10, 150, 20);
		btnCargarArchivo.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				((ControladorNuevoArticulo) (vistaPadre.getControlador()))
						.ObtenerNuevoArt();
			}
		});
		return btnCargarArchivo;
	}

	private JLabel getLabelsArts(int i, String nombreCampo, int iPos) {
		JLabel lbl = new JLabel(nombreCampo);
		lbl.setLocation(iPos, 40 * i + 50);
		lbl.setSize(100, 20);
		labels.add(lbl);
		return lbl;
	}

	private JTextField getTextsArts(int i, String nombreCampo, String valor,
			int iPos) {
		JTextField txf = new JTextField();
		txf = new JTextField();
		txf.setName(nombreCampo);
		txf.setText(valor);
		if (valor != null && !("").equals(valor.trim())) {
			txf.setEnabled(false);
			txf.setBackground(new Color(120, 120, 120));
			txf.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		}
		txf.setBounds(100 + iPos, 40 * i + 50, 150, 20);
		textfields.add(txf);
		return (txf);
	}

	private JButton getButtonAgregarMatP(int cantComponentes) {
		JButton btnAgregarMateriaPrima = new JButton("Agregar");
		btnAgregarMateriaPrima.setLocation(20, 40 * (cantComponentes) / 2 + 80);
		btnAgregarMateriaPrima.setSize(100, 20);
		btnAgregarMateriaPrima
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						((ControladorNuevoArticulo) (vistaPadre
								.getControlador())).agregarMateriaPrima();
					}
				});
		buttons.add(btnAgregarMateriaPrima);
		return btnAgregarMateriaPrima;
	}

	private JButton getButtonQuitarMatP(int cantComponentes) {
		JButton btnQuitarMateriaPrima = new JButton("Quitar");
		btnQuitarMateriaPrima.setLocation(120, 40 * (cantComponentes) / 2 + 80);
		btnQuitarMateriaPrima.setSize(100, 20);
		btnQuitarMateriaPrima
				.addActionListener(new java.awt.event.ActionListener() {
					public void actionPerformed(java.awt.event.ActionEvent e) {
						((ControladorNuevoArticulo) (vistaPadre
								.getControlador())).quitarMateriaPrima();
					}
				});
		buttons.add(btnQuitarMateriaPrima);
		return btnQuitarMateriaPrima;
	}

	private JButton getButtonAlta() {
		JButton btnAltaArticulos = new JButton("Confirmar");
		btnAltaArticulos.setLocation(410, 40 * 8 + 50);
		btnAltaArticulos.setSize(145, 20);
		btnAltaArticulos.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				FocusManager.getCurrentManager().focusNextComponent();
				((ControladorNuevoArticulo) (vistaPadre.getControlador()))
				.DarAltaNvoArt();
			}
		});
		buttons.add(btnAltaArticulos);
		return btnAltaArticulos;
	}
	private JButton getButtonCerrar() {
		JButton btnCerrar = new JButton("Cerrar");
		btnCerrar.setLocation(480, 440);
		btnCerrar.setSize(80, 20);
		btnCerrar.addActionListener(new java.awt.event.ActionListener() {
			public void actionPerformed(java.awt.event.ActionEvent e) {
				((ControladorNuevoArticulo) (vistaPadre.getControlador())).cerrar();
			}
		});
		return btnCerrar;
	}

	private JPanel getPanelTabla(int cantComponentes) {
		tabla = new JTable();
		tabla.setModel(new javax.swing.table.DefaultTableModel());
		tabla.setModel(t);
		t.addColumn((Object) "Materia");
		t.addColumn((Object) "Cantidad");
		t.addRow(new Object[] { "", ""});
		a.setBounds(10, 40 * (((cantComponentes) / 2) + 1) + 70, 300, 100);
		a.add(tabla.getTableHeader(), BorderLayout.NORTH);
		a.add(new JScrollPane(tabla), BorderLayout.CENTER);
		a.setVisible(false);

		this.materiasPrimasVO = ((BusinessDelegateNuevoArticulo) (this
				.getVistaPadre().getModelo())).obtenerMateriasPrimas();
		
		if (this.materiasPrimasVO != null){
		
			String[] values = new String[materiasPrimasVO.size()];
	
			for (int i = 0; i < materiasPrimasVO.size(); i++) {
				values[i] = materiasPrimasVO.get(i).getNombre();
			}
	
			TableColumn col = tabla.getColumnModel().getColumn(0);
			col.setCellEditor(new ComboBoxHelper.MyComboBoxEditor(values));

		}
		
		return a;
	}

	private JFileChooser getFileChooser() {
		if (jfc.isFileHidingEnabled())
			jfc.setFileHidingEnabled(false);
		return jfc;
	}

	public void agregarNvoArt() {
		int estado = jfc.showOpenDialog(null);
		((ControladorNuevoArticulo) (this.getVistaPadre().getControlador()))
				.validarArchivoArticulo(estado, jfc.getSelectedFile());
		getContentPane().remove(btnCargarArchivo);
		getContentPane().remove(jfc);
	}

	public void altaNvoArt() {
		Map<String, String> campos = new TreeMap<String, String>();
		for (JTextField textfield : textfields) {
			campos.put(textfield.getName(), textfield.getText());
		}
		ArrayList<MateriaPrimaVO> materiasPrimasVO = obtenerMateriasPrimas();
		((ControladorNuevoArticulo) (this.getVistaPadre().getControlador()))
				.guardarArticulo(campos,materiasPrimasVO);
	}
	
	public ArrayList<MateriaPrimaVO> obtenerMateriasPrimas(){
		ArrayList<MateriaPrimaVO> materiasPrimasVOAux =  new ArrayList<MateriaPrimaVO> ();
		if (t.getRowCount()>0) {
			for(int i = 0; i< t.getRowCount();i++){
				String nombre = (String)((Vector<?>)t.getDataVector().elementAt(i)).elementAt(0);
				String cantidad = (String)((Vector<?>)t.getDataVector().elementAt(i)).elementAt(1);
				if (getByName(nombre) != null){
					int id = getByName(nombre).getCodigo();
					materiasPrimasVOAux.add(new MateriaPrimaVO(id,nombre,Integer.parseInt(cantidad)));
				}
			}
		}
		return materiasPrimasVOAux;
	}
	
	public MateriaPrimaVO getByName(final String name) {
        return CollectionUtils.find(materiasPrimasVO, new Predicate<MateriaPrimaVO>() {
            public boolean evaluate(final MateriaPrimaVO materiaPrimaVO) {
                return (name.equals(materiaPrimaVO.getNombre()));
            }
        });
    }

	public void errorArchivo() {
		this.canceloGuardar();
		JOptionPane.showMessageDialog(getContentPane(),
				"Error Parseando El XML", "Error",
				JOptionPane.ERROR_MESSAGE);
	}

	public void errorGuardar() {
		this.canceloGuardar();
		JOptionPane.showMessageDialog(getContentPane(),
				"No fue posible el alta de Articulo", "Error",
				JOptionPane.ERROR_MESSAGE);
	}
	
	public void canceloGuardar() {
		this.cleanScreen();
		this.getContentPane().add(getButtonArt());
	}

	public void okGuardar() {
		this.cleanScreen();
		this.getContentPane().add(getButtonArt());
		JOptionPane.showMessageDialog(getContentPane(),
				"Alta de articulo exitosa", "OK",
				JOptionPane.INFORMATION_MESSAGE);
	}

	public void cleanScreen() {
		for (JButton button : buttons) {
			getContentPane().remove(button);
		}
		for (JTextField textField : textfields) {
			getContentPane().remove(textField);
		}
		for (JLabel label : labels) {
			getContentPane().remove(label);
		}
		getContentPane().remove(a);
		getContentPane().repaint();
		// RE-INITIALIZE COMPONENTS
		textfields = new ArrayList<JTextField>();
		labels = new ArrayList<JLabel>();
		buttons = new ArrayList<JButton>();
		a = new JPanel(new BorderLayout());
		t = new DefaultTableModel();
	}

	public void agregarMateriaPrima() {
		t.addRow(new Object[] { "", "" });
		this.invalidate();
	}

	public void quitarMateriaPrima() {
		if (t.getRowCount() > 0) {
			t.removeRow(t.getRowCount() - 1);
			this.invalidate();
		}
	}

	private void initialize() {
		this.setPreferredSize(new java.awt.Dimension(600, 500));
		this.setLocation(100, 100);
		this.setTitle("Informacion de Articulos");
		// Container c = this.getContentPane();
		this.getContentPane().setLayout(null);
		this.getContentPane().add(getLabelNvoArt());
		this.getContentPane().add(getButtonArt());
		this.getContentPane().add(getButtonCerrar());
		this.setDefaultCloseOperation(javax.swing.JFrame.DISPOSE_ON_CLOSE);

	}
}
