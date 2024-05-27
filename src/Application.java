import clients.Client;
import clients.ClientHandler;
import lb.LoadBalancers;
import lb.Property;
import servers.Server;
import servers.ServerHandler;

import java.io.IOException;

import java.net.Socket;
import java.util.ArrayList;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;

public class Application {
    static Property property = new Property(new Properties());
    static ArrayList<Server> servers;

    static {
        try {
            servers = property.prepareServers();
        } catch (IOException _) {

        }
    }

    public static void main(String[] args) throws IOException {
        ExecutorService service = Executors.newFixedThreadPool(6);
//    Property property = new Property(new Properties());
//    ArrayList<Server> servers = property.prepareServers();
        Client client = new Client("Burmau");
        ClientHandler clientHandler = new ClientHandler(client);
        LoadBalancers loadBalancers = new LoadBalancers(servers, property);
        service.execute(loadBalancers);
        service.execute(clientHandler);
        for (Server server : servers) {
            service.execute(new ServerHandler(server, new Socket(client.getHOST(), client.getLOAD_BALANCER_PORT())));
        }
        service.shutdown();
        LinkedBlockingQueue<Integer> s = new LinkedBlockingQueue<>(5);
        s.add(23);
        s.add(23);
        System.out.println(s.size());
        System.out.println((double) s.size() / (s.size() + s.remainingCapacity()));
    }
}