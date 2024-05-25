package servers;


import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;

public class Server {
    private String serverName;
    private int port;
    private BlockingQueue<Socket> socketBlockingQueue;
    private ServerSocket serverSocket;

    // Metrics
    private int failureRate; // in percentages
    private int successRate;
    private long totalResponseTime;
    private long averageResponseTime;
    private int currentQueueCapacity;
    private boolean isHealthy; // if working queue is less than 70% then it's in good health

    public Server(int port) {
        this.port = port;
    }
}