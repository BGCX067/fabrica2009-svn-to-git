package fabrica.configuration;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;

import com.thoughtworks.xstream.XStream;

import fabrica.exception.BusinessException;

public final class StateManager {
	
	private static final String ARCHIVO_DE_CONEXION = "C:/configuracion.xml";
	private static final XStream STREAM = new XStream();

    public static Configuration getConfiguration() {
    	final File conexionFile = new File(ARCHIVO_DE_CONEXION);
    	Configuration configuration=null;
    	try {
    		configuration= (Configuration) (STREAM.fromXML(FileUtils.readFileToString(conexionFile)));
            return configuration;
        }
        catch ( IOException e ) {
            throw new BusinessException("Could not read the file [" + conexionFile.getAbsolutePath() + "]");
        }    	
    }
	
    public static void SaveConfiguration(final Configuration configuration){
    	final File conexionFile = new File(ARCHIVO_DE_CONEXION);
        try {
        	FileUtils.writeStringToFile(conexionFile, STREAM.toXML(configuration));            
        }
        catch ( IOException e ) {
        	e.printStackTrace();
        	throw new BusinessException("Could not write the file [" + conexionFile.getAbsolutePath() + "]");
        }
    }
}
