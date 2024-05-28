import clients.Client;
import clients.ClientHandler;
import lb.LinkedList;
import lb.LoadBalancers;
import lb.Property;
import servers.Server;

import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public static void main() throws IOException {
    Property property = new Property(new Properties());
    LinkedList serverList = new LinkedList();
    for(Server s : property.prepareServers())
        serverList.add(s);
    LoadBalancers loadBalancer = new LoadBalancers(serverList, property);
    LinkedList.Node head = serverList.getHead();
    ExecutorService executor = Executors.newFixedThreadPool(6);
    Client client = new Client("a");
    ClientHandler clientHandler = new ClientHandler(client);
    executor.execute(loadBalancer);
    executor.execute(clientHandler);
    executor.shutdown();
}