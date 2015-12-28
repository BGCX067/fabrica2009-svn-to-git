package fabrica.configuration;

import java.util.HashMap;

public class InitialConfiguration {

	
	public static void main(String[] args) {
        crearConnection();
    }
	
	private static void crearConnection(){
		Configuration configuration = new Configuration();
		setXMLProperties(configuration);
        StateManager.SaveConfiguration(configuration);
	}
	
	private static void setXMLProperties(Configuration configuration){
		HashMap<String, String> xmlProperties = new HashMap<String, String>();
		
		xmlProperties.put("xml.write", "/home/pablo/workspace/zara/zaraCliente/xml/");
		xmlProperties.put("xml.tempdir", "/home/pablo/workspace/zara/zaraCliente/xml/");
		
		configuration.setXmlProperties(xmlProperties);
	}
}
