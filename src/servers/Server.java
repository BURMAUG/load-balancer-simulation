package servers;


import lb.LoadBalancers;

import java.net.Socket;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class Server {
    private String serverName;
    private int serverID;
    private int port;
    private final BlockingQueue<Socket> socketBlockingQueue = new LinkedBlockingQueue<>(5); //for each server
    private LoadBalancers loadBalancers; // has access to the props file and i need it to give ports here

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
    }

    public Server(String serverName, LoadBalancers loadBalancers) {
        this.serverName = serverName;
        this.loadBalancers = loadBalancers;
        serverID = new Random().nextInt(21, 999) + 21;
    }

    public void messageController(){

    }


}