package servers;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;


public class Server {
    private final String serverName;
    private final int serverID;
    private final int port;
    private final ServerSocket serverSocket;
    private final Lock lock = new ReentrantLock();
    private final Map<Integer, Socket> socketMap = new HashMap<>(); // port associated with the server
    private final Condition condition = lock.newCondition();

    public Server(int serverID, String serverName, int port) throws IOException {
        this.serverID = serverID;
        this.serverName = serverName;
        this.port = port;
        serverSocket = new ServerSocket(port);
    }

    public Server(String serverName, int serverID, int port, ServerSocket serverSocket) {
        this.serverName = serverName;
        this.serverID = serverID;
        this.port = port;
        this.serverSocket = serverSocket;
    }
    // Metrics
    private long totalResponseTime;
    private long averageResponseTime;
    private static volatile boolean isHealthy = true; // if working queue is less than 70% then it's in good health
    private static volatile double failureRate = 0.0; // in percentages
    private static volatile double successRate = 0.0;

    private volatile int currentQueueCapacity = socketMap.size();

    /**
     * ServerSocket this should return a given the appropriate serverSocket that needs to work
     * @return ServerSocket
     */
    public ServerSocket getServerSocket() {
        return serverSocket;
    }

    /**
     * Given the current state of the HashMap the health is returned.
     * @return serverHealth
     */
    public boolean isHealthy() {
        return isHealthy;
    }

    /**
     * AddSocket adds a socket to the volatile socketmap for later access.
     * @param socket
     */
    public void addSocket(Socket socket) {

    }

    public double failureRate(){
        return failureRate;
    }

    public void increaseFailureRateCount(){

    }

    public double successRate(){
        return successRate;
    }

    public void increaseSuccessRateCount(){

    }

    public int currentCapacity(){
        return currentQueueCapacity;
    }

    /**
     *  Removes the socket that is ready for data transfers.
     * @return Socket
     */
    public Socket removeSocket() {
        return new Socket();
    }

    @Override
    public String toString() {
        return STR."Server{serverName='\{serverName}\{'\''}, serverID=\{serverID}, port=\{port}, serverSocket=\{serverSocket}\{'}'}";
    }
}