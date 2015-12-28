package fabrica.xml;

import java.io.File;
import java.io.IOException;
import java.util.Calendar;
import java.util.GregorianCalendar;

import org.apache.commons.io.FileUtils;

import com.thoughtworks.xstream.XStream;

import fabrica.configuration.StateManager;
import fabrica.cu.EnvProv;
import fabrica.cu.RepAf;
import fabrica.vo.ArticuloEnListadoVO;
import fabrica.vo.MateriaPrimaVO;

public class XMLWriter {

	private static final XStream STREAM = new XStream();
	private static final String XML_DIRECTORY = StateManager.getConfiguration().getXmlProperties().get("xml.write");
	private static final String HEADER = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>" + "\n";

	public XMLWriter() {
		
	}
	
	public void generatePCProv(EnvProv pedidoProveedores){
		
		//STREAM.alias("pedidoProveedores", EnvProv.class);
		STREAM.alias("envioProveedores", EnvProv.class);
		STREAM.alias("materiaPrima", MateriaPrimaVO.class);
		
    	final File conexionFile = new File(XML_DIRECTORY.concat("PCProv.xml"));
        try {
        	FileUtils.writeStringToFile(conexionFile, HEADER + STREAM.toXML(pedidoProveedores));            
        }
        catch ( IOException e ) {
        	e.printStackTrace();
        	System.out.println("Could not write the file [" + conexionFile.getAbsolutePath() + "]");
        }
    }
	
	public void generateRepAf(RepAf repAf){
		
		STREAM.alias("reposicionArticulos", RepAf.class);
		STREAM.alias("articulo", ArticuloEnListadoVO.class);
		STREAM.omitField(RepAf.class, "cd");
		STREAM.omitField(ArticuloEnListadoVO.class, "nombre");
		STREAM.omitField(ArticuloEnListadoVO.class, "centro");
		
    	final File repafFile = new File(XML_DIRECTORY.concat("RepAf_").concat(repAf.getCd()).concat(GregorianCalendar.getInstance().get(Calendar.MONTH)+"-"+GregorianCalendar.getInstance().get(Calendar.DAY_OF_MONTH)).concat(".xml"));
        try {
        	FileUtils.writeStringToFile(repafFile, HEADER + STREAM.toXML(repAf));            
        }
        catch ( IOException e ) {
        	e.printStackTrace();
        	System.out.println("Could not write the file [" + repafFile.getAbsolutePath() + "]");
        }		
		
	}
}
