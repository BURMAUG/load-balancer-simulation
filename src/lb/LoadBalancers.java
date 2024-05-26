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
    private Property property;
    // I am  thinking i shouldld also have a queue here too like 15 socket

    public LoadBalancers(Property property){
        this.property = new Property(properties);
    }

    public LoadBalancers(ArrayList<Server> servers, Property property) {
        this.servers = servers;
        this.property = property;
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
    public synchronized String getServerErrLogFilePath() {
        return property.getServerErrLogFile();
    }

    /**
     * Server Log file is where the server logs health at a given instant in time
     * @return servers log file name
     */
    public synchronized String getServerLogFilePath(){
        return property.getServerLogFile();
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try(ServerSocket balancerServer = new ServerSocket(property.getLoadBalancerPort())){
                Socket socket = balancerServer.accept();
                Server server = servers.getFirst();  // should be done on Round-Robin
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                outputStream.writeInt(server.getServerSocket().getLocalPort());
                System.out.println(STR."sent \{server.getServerSocket().getLocalPort()}");
            } catch (IOException _) {

            }
        }
    }

    public int getServerPort() {
        return 2222; // for test
    }
}
