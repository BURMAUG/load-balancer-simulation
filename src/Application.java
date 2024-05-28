import clients.Client;
import clients.ClientHandler;
import lb.LinkedList;
import lb.LoadBalancers;
import lb.Property;
import servers.Server;
import servers.ServerHandler;

import java.io.IOException;
import java.net.Socket;
import java.util.Properties;
;

public static void main() throws IOException, InterruptedException {
    Property property = new Property(new Properties());
    LinkedList serverList = new LinkedList();
    for(Server s : property.prepareServers()) {
        serverList.add(s);
    }
    LoadBalancers loadBalancer = new LoadBalancers(serverList, property);
    Client client = new Client("a");
    Thread[] threads = new Thread[7];
    threads[0] = new Thread(loadBalancer);
    threads[0].start();
    threads[1] = new Thread(new ServerHandler(serverList.getHead().getServer(), new Socket(client.getHOST(), serverList.getHead().getServer().getServerSocket().getLocalPort())));
    threads[1].start();

    threads[2] = new Thread(new ClientHandler(client));
    threads[2].start();
    threads[2].join();

}