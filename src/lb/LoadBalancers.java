package lb;

import servers.Server;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Properties;

public class LoadBalancers implements Runnable{
    private final ArrayList<Server> servers;
    private final Properties properties = new Properties();

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
        int port = (int) properties.get("server.one.port");
        System.out.println(port);
    }

    private void roundRobinRouting(){

    }

    private void stickyRoundRobingRouting(){

    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
        }
    }
}
