package core;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * https://mkyong.com/java/java-properties-file-examples/
 */
public class PropertiesReader {
    private PropertiesReader() {
    }

    private static final String CONFIG_PROPERTIES = "configs/config.properties";
    private static Properties configs;

    /**
     * Load a properties file config.properties from project classpath, and retrieved the property value.
     * @param propertyName
     * @return
     */
    public static String getProperty(final String propertyName) {
        try(InputStream input = PropertiesReader.class
                .getClassLoader().getResourceAsStream(CONFIG_PROPERTIES)) {
            Properties property = new Properties();
            if (input==null) {
                System.out.println("config.properties file is not fount");
            }

            property.load(input);
            configs = property;

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
//        String systemProperty = System.getProperty(propertyName);
        return configs.getProperty(propertyName, "Property reader failed");
    }

}
