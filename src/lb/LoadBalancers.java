package lb;

import servers.Server;


import java.util.ArrayList;
import java.util.Properties;
import java.util.Random;

public class LoadBalancers implements Runnable{
    private ArrayList<Server> servers;
    private Properties properties;


    public LoadBalancers(ArrayList<Server> servers) {
        this.servers = servers;
    }

    int getServer(){
        return new Random().nextInt(0, servers.size());
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
        }
    }
}
