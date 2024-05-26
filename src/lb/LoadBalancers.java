package lb;

import servers.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;

public class LoadBalancers implements Runnable{
    private ArrayList<Server> servers;
    private final Properties properties = new Properties();
    // I am  thinking i should also have a queue here too like 15 socket

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
        System.out.println(servers.getFirst().isHealthy());
        // getServerErrLogFile
        // getServerLogFile
    }

    public synchronized void stickyRoundRobingRouting(){

    }

    /**
     * Server error log file sends the file name to the server for access to the Server's error log file
     * @return Server error log filename
     */
    public synchronized String getServerErrLogFile() {
        return properties.get("server.rr.log") != null ? String.valueOf(properties.get("server.err.log")) : "Does Not Exist.";
    }

    /**
     * Server Log file is where the server logs health at a given instant in time
     * @return servers log file name
     */
    public synchronized String getServerLogFile(){
        return properties.get("logs.server") != null ? String.valueOf(properties.get("logs.server")) : "Does Not Exist.";
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try(ServerSocket balancerServer = new ServerSocket((int)properties.get("load.balancer.port"))){
                Socket socket = balancerServer.accept();

            } catch (IOException _) {

            }
        }
    }

    public int getServerPort() {
        return 2222; // for test
    }
}
