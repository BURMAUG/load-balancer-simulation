package servers;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Server {
    private String serverName;
    private int serverID;
    private int port;
    private ServerSocket serverSocket;
    private Lock lock = new ReentrantLock();
    private BlockingQueue<Socket> socketBlockingQueue;
    private  static double circle;
    private Condition condition = lock.newCondition();

    // Metrics
    private int failureRate; // in percentages
    private int successRate;
    private long totalResponseTime;
    private long averageResponseTime;
    private int currentQueueCapacity;
    private boolean isHealthy; // if working queue is less than 70% then it's in good health

    public Server(int port) {
        this.port = port;
        serverID = new Random().nextInt(21, 999) + 21;
        socketBlockingQueue = new LinkedBlockingQueue<>(5);
    }

    public Server(int serverID, String serverName, int port) throws IOException {
        this.serverID = serverID;
        this.serverName = serverName;
        this.port =  port;
        this.socketBlockingQueue = new LinkedBlockingQueue<>(5);
        this.serverSocket = new ServerSocket(port);
    }

    public ServerSocket getServerSocket() throws IOException {
        return serverSocket;
    }

    public boolean isHealthy() {
        lock.lock();
        if((double) socketBlockingQueue.size() /(socketBlockingQueue.size() + socketBlockingQueue.remainingCapacity()) > 0.6){
            isHealthy = false;
            System.out.println(STR."Health is \{(double) socketBlockingQueue.size() /( socketBlockingQueue.size() + socketBlockingQueue.remainingCapacity())}");
            return isHealthy;
        }
        isHealthy = true;
        System.out.println(STR."Health is \{socketBlockingQueue.size() /  (socketBlockingQueue.size() + socketBlockingQueue.remainingCapacity())}");
        lock.unlock();
        return isHealthy;

    }

    public void addSocket(Socket socket) throws InterruptedException {
            lock.lock();
            try {
                while (!isHealthy()) {
                    System.out.println("Unhealthy traffic");
                    condition.await();
                }
                socketBlockingQueue.add(socket);
            }catch (InterruptedException e){
                System.out.println(e.getLocalizedMessage());
            }
            lock.unlock();
    }

    public Socket takeSocket() throws InterruptedException {
        lock.lock();
        Socket socket = null;
        try{
            socket = socketBlockingQueue.take();
            condition.signalAll();
        }catch (InterruptedException e){
            System.out.println(e.getMessage());
        }
        lock.unlock();
        return socket;
    }
}