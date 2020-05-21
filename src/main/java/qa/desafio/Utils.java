package qa.desafio;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;


public class Utils {

    public static PropertiesConfiguration getProperties(String propertiesPath){
        try {
            return new PropertiesConfiguration(propertiesPath);
        } catch (ConfigurationException e) {
            e.printStackTrace();
            return null;
        }
    }

}
