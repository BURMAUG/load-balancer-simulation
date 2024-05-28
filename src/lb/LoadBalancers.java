package lb;

import servers.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class LoadBalancers implements Runnable{
    private LinkedList servers;
    LinkedList.Node cur;
    private final Property property;
    // I am  thinking I should also have a queue here too like 15 socket

    public LoadBalancers(Property property){
        Properties properties = new Properties();
        this.property = new Property(properties);
        cur = servers.getHead();
    }

    public LoadBalancers(LinkedList servers, Property property) {
        this.servers = servers;
        this.property = property;
        cur = servers.getHead();
    }



    public synchronized Server roundRobinRouting() {
        if (cur.getNext() == null){
            cur = servers.getHead();
            return cur.getServer();
        }
        return cur.getServer(); // I feel like problems may arise here.
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
                Server server = roundRobinRouting();  // should be done on Round-Robin
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                outputStream.writeInt(server.getServerSocket().getLocalPort());
                System.out.println(STR."sent \{server.getServerSocket().getLocalPort()}");
            } catch (IOException _) {
            }
        }
    }
}
