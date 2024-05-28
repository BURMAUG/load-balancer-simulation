import clients.Client;
import clients.ClientHandler;
import lb.LinkedList;
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

public static void main() throws IOException {
    Property property = new Property(new Properties());
    LinkedList serverList = new LinkedList();
    for(Server s : property.prepareServers()) {
        serverList.add(s);
    }
    LoadBalancers loadBalancer = new LoadBalancers(serverList, property);
    ExecutorService executor = Executors.newFixedThreadPool(7);
    Client client = new Client("a");
    ClientHandler clientHandler = new ClientHandler(client);
    executor.execute(loadBalancer);
    executor.execute(clientHandler);
    ServerHandler serverHandler = new ServerHandler(serverList.getHead().getServer(), new Socket(client.getHOST(), client.getLOAD_BALANCER_PORT()));
    ServerHandler serverHandler1 = new ServerHandler(serverList.getHead().getServer(), new Socket(client.getHOST(), client.getLOAD_BALANCER_PORT()));
    executor.execute(serverHandler);
    executor.execute(serverHandler1);
    executor.shutdown();
}