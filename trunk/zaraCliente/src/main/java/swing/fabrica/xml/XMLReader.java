package swing.fabrica.xml;

import java.io.File;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;

import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;

import com.thoughtworks.xstream.XStream;

import fabrica.cu.EnvProv;
import fabrica.vo.ArtHogarVO;
import fabrica.vo.ArtRopaVO;
import fabrica.vo.ArticuloVO;
import fabrica.vo.CentroDistribucionVO;
import fabrica.vo.MateriaPrimaVO;
import fabrica.vo.SolFabrItemVO;
import fabrica.vo.SolFabrVO;

public class XMLReader {
	
	private static final XStream STREAM = new XStream();
	final static Logger logger = Logger.getLogger(XMLReader.class);
	final static String encoding = "UTF-8";
	
	public XMLReader() {
		
	}
	
	private ArticuloVO convertirArticulo(final NuevoArticuloXml nuevoArticulo){
		final ArticuloVO articuloVO;
		if (nuevoArticulo.getTipo().equalsIgnoreCase("ropa")){
			articuloVO = new ArtRopaVO();
			((ArtRopaVO) articuloVO).setTalle(nuevoArticulo.getTalle());
			((ArtRopaVO) articuloVO).setOrigen(nuevoArticulo.getOrigen());
		}else{
			articuloVO = new ArtHogarVO();
			((ArtHogarVO) articuloVO).setNombre(nuevoArticulo.getNombre());
			((ArtHogarVO) articuloVO).setCategoria(nuevoArticulo.getCategoria());
			((ArtHogarVO) articuloVO).setMedidas(nuevoArticulo.getMedidas());
			((ArtHogarVO) articuloVO).setComposicion(nuevoArticulo.getComposicion());
		}
		
		System.out.println(nuevoArticulo.getSeccion());
		articuloVO.setSeccion(nuevoArticulo.getSeccion());
		articuloVO.setReferencia(nuevoArticulo.getReferencia());
		articuloVO.setDescripcion(nuevoArticulo.getDescripcion());
		articuloVO.setColor(nuevoArticulo.getColor());
		articuloVO.setPrecioVenta(nuevoArticulo.getPrecioVenta());
		articuloVO.setLinea(nuevoArticulo.getLinea());
		articuloVO.setTiempoDeFabricacion(nuevoArticulo.getTiempoDeFabricacion());
		articuloVO.setCentrosDistribucion(nuevoArticulo.getCentrosDistribucion());
		
		return articuloVO;
	}

	public ArticuloVO getArticulo(final File archivoArticulo) {

		STREAM.alias("nuevoArticulo", NuevoArticuloXml.class);
		STREAM.alias("centroDistribucion", CentroDistribucionVO.class);
		STREAM.aliasField("precio", NuevoArticuloXml.class, "precioVenta");
		logger.info("Obteniendo Nuevo Articulo a partir del XML "+archivoArticulo.getAbsolutePath());
		NuevoArticuloXml articulo = new NuevoArticuloXml();
		try {
			articulo = (NuevoArticuloXml) (STREAM.fromXML(FileUtils.readFileToString(archivoArticulo, encoding)));
			return convertirArticulo(articulo);
		} catch (Exception e) {
			System.err.println(archivoArticulo.getAbsolutePath()+" No es un XML valido, puede ver mas detalles en el log");
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			logger.error(sw.toString());
		}
		return null;
		  
	}

	public SolFabrVO getNuevasFabricaciones(final File archivoFabricaciones) {

		STREAM.alias("solicitudFabricacion", SolFabrVO.class);
		STREAM.alias("articulos", ArrayList.class);
		STREAM.alias("articulo", SolFabrItemVO.class);

		SolFabrVO solicitudDeFabricacion = new SolFabrVO();
		try {
			solicitudDeFabricacion = (SolFabrVO) (STREAM.fromXML(FileUtils.readFileToString(archivoFabricaciones, encoding)));
			return solicitudDeFabricacion;
		} catch (Exception e) {
			System.err.println(archivoFabricaciones.getAbsolutePath()+" No es un XML valido, puede ver mas detalles en el log");
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			logger.error(sw.toString());
		}
		return null;

	}

	public ArrayList<MateriaPrimaVO> getEnvioProveedores(File archivoFabricaciones) {
		
		STREAM.alias("envioProveedores", EnvProv.class);
		STREAM.alias("materiasPrima", ArrayList.class);
		STREAM.alias("materiaPrima", MateriaPrimaVO.class);

		EnvProv envioProveedores = new EnvProv();
		try {
			envioProveedores = (EnvProv) (STREAM.fromXML(FileUtils.readFileToString(archivoFabricaciones, encoding)));
			return envioProveedores.getMateriasPrimas();
		} catch (Exception e) {
			System.err.println(archivoFabricaciones.getAbsolutePath()+" No es un XML valido, puede ver mas detalles en el log");
			StringWriter sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			logger.error(sw.toString());
		}
		return null;
	}

}
