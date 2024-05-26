package lb;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Property {
    private final Properties properties;

    public Property(Properties properties) {
        this.properties = properties;
    }

    private void loadProps() {
        try {
            properties.load(new FileInputStream("src/application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
