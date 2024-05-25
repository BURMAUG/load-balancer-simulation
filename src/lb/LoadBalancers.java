package lb;

import servers.Server;

import java.io.*;
import java.util.ArrayList;
import java.util.Properties;

public class LoadBalancers implements Runnable{
    private ArrayList<Server> servers;
    private final Properties properties = new Properties();

    public LoadBalancers(){
        prepareLoadBalancer();
    }

    public LoadBalancers(ArrayList<Server> servers) {
        this.servers = servers;
        prepareLoadBalancer();
    }

    private void prepareLoadBalancer(){
        try(FileInputStream inputStream = new FileInputStream("src/application.properties")){
            properties.load(inputStream);
        }catch (IOException ioException) {
            System.err.println(ioException.getMessage());
        }
    }

    public synchronized void roundRobinRouting() {
        // server
        // getServerErrLogFile
        // getServerLogFile
    }

    public synchronized void stickyRoundRobingRouting(){

    }

    /**
     * Server error log file sends the file name to the server for access to the Server's error log file
     * @return file
     */
    public synchronized String getServerErrLogFile() {
        return properties.get("server.rr.log") != null ? String.valueOf(properties.get("server.err.log")) : "Does Not Exist.";
    }

    public synchronized String getServerLogFile(){
        return properties.get("logs.server") != null ? String.valueOf(properties.get("logs.server")) : "Does Not Exist.";
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
        }
    }
}
