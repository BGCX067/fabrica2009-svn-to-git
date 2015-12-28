package fabrica.configuration;

import java.util.HashMap;

public class Configuration {
	
	private HashMap<String, String> xmlProperties = new HashMap<String, String>();

	public HashMap<String, String> getXmlProperties() {
		return xmlProperties;
	}

	public void setXmlProperties(HashMap<String, String> xmlProperties) {
		this.xmlProperties = xmlProperties;
	}
	
}
