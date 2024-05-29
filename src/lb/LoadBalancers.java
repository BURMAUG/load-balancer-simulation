package lb;

import servers.Server;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class LoadBalancers implements Runnable{
    private LinkedList servers;
    private static LinkedList.Node cur;
    private final Property property;
    private final Lock lock = new ReentrantLock();
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
//        lock.unlock();
        if(cur == null)
            cur = servers.getHead();
        LinkedList.Node c = cur;
        cur = cur.getNext();
//        lock.unlock();
        return c.getServer();
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
    public synchronized void run() {
        while (!Thread.currentThread().isInterrupted()){
            try(ServerSocket balancerServer = new ServerSocket(property.getLoadBalancerPort())){
                Socket socket = balancerServer.accept();
                Server server = roundRobinRouting();  // should be done on Round-Robin
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                outputStream.writeInt(server.getServerSocket().getLocalPort());
                System.out.println(STR."sent \{server.getServerSocket().getLocalPort()}");
                System.out.println(STR."Server Details \{server.toString()}");
                System.out.println();
            } catch (IOException e) {
                try {
                    throw new IOException(e.getCause());
                } catch (IOException ex) {
                    throw new RuntimeException(ex);
                }
            }
        }
    }
}
