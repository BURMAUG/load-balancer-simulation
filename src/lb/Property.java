package lb;

import servers.Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;

public class Property {
    private final Properties properties;

    public Property(Properties properties) {
        this.properties = properties;
        loadProps();
    }

    private void loadProps() {
        try {
            properties.load(new FileInputStream("src/application.properties"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

   public ArrayList<Server> prepareServers() throws IOException {
        return new ArrayList<>( Arrays.asList(
//                new Server(Integer.parseInt( properties.getProperty("server.one.id")), properties.get("server.one.name").toString(),  Integer.parseInt(properties.getProperty("server.one.port"))),
//                new Server(Integer.parseInt(properties.getProperty("server.two.id")), properties.get("server.two.name").toString(), Integer.parseInt(properties.getProperty("server.two.port"))),
//                new Server(Integer.parseInt(properties.getProperty("server.three.id")), properties.get("server.three.name").toString(), Integer.parseInt(properties.getProperty("server.three.port")))
        ));
    }
    /**
     * Server error log file sends the file name to the server for access to the Server's error log file
     * @return Server error log filename
     */
    public String getServerErrLogFile() {
        return properties.get("server.rr.log") != null ? String.valueOf(properties.get("server.err.log")) : "Does Not Exist.";
    }

    /**
     * Server Log file is where the server logs health at a given instant in time
     * @return servers log file name
     */
    public String getServerLogFile(){
        return properties.get("logs.server") != null ? String.valueOf(properties.get("logs.server")) : "Does Not Exist.";
    }

    public int getLoadBalancerPort() {
        return Integer.parseInt(properties.getProperty("load.balancer.port"));
    }
}
