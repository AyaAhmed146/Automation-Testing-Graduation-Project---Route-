package Utils;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {
    private static Properties properties;

    static {
        try {
            FileInputStream fis = new FileInputStream(
                    "src/main/resources/config.properties"
            );
            properties = new Properties();
            properties.load(fis);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static int getTimeout() {
        String timeout = properties.getProperty("timeout", "10");
        return Integer.parseInt(timeout);
    }


}
