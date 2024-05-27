package servers;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server {
    private String serverName;
    private int serverID;
    private int port;
    private ServerSocket serverSocket;
    private final Lock lock = new ReentrantLock();
    private final Queue<Socket> socketBlockingQueue = new LinkedList<>();
    private final Condition condition = lock.newCondition();

    // Metrics
    private long totalResponseTime;
    private long averageResponseTime;
    private static volatile boolean isHealthy = true; // if working queue is less than 70% then it's in good health
    private volatile int failureRate; // in percentages
    private volatile int successRate;
    private volatile int currentQueueCapacity;


    public ServerSocket getServerSocket() throws IOException {
        return serverSocket;
    }

    public boolean isHealthy() {
        return isHealthy;
    }

    public void addSocket(Socket socket) throws InterruptedException {
    }

    public Socket takeSocket() throws InterruptedException {
        return new Socket();
    }
}