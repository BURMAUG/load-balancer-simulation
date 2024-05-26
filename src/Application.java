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

public static void main(String[] args) throws IOException{
    ExecutorService service = Executors.newFixedThreadPool(6);
    Property property = new Property(new Properties());
    ArrayList<Server> servers = property.prepareServers();
    Client client =new Client("Burmau");
    ClientHandler clientHandler = new ClientHandler(client);
    LoadBalancers loadBalancers = new LoadBalancers(servers, property);
    service.submit(loadBalancers);
    service.submit(clientHandler);
    for (Server server : servers){
        service.submit(new ServerHandler(server, new Socket(client.getHOST(), client.getLOAD_BALANCER_PORT())));
    }
    service.shutdown();
}
