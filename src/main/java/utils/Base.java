package utils;

import lombok.Getter;
import lombok.Setter;

import java.io.FileInputStream;
import java.util.Properties;

public class Base {
    @Getter @Setter
    public static Properties properties=loadGlobalProperties();

    /**
     * This method will load the global properties file
     * @return Properties object
     */
    public static Properties loadGlobalProperties() {
        try {
            String filePath=System.getProperty("user.dir")+"/src/test/resources/global.properties";
            FileInputStream fileInputStream = new FileInputStream(filePath);
            Properties tempProperties= new Properties();
            tempProperties.load(fileInputStream);
            return tempProperties;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public static void loadPropertiesFromEnv(String fileName) {
        try {
            String filePath=System.getProperty("user.dir")+"/src/test/resources/"+properties.getProperty("environment")+"/"+fileName+".properties";
            FileInputStream fileInputStream = new FileInputStream(filePath);
            properties.load(fileInputStream);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
