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
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public static void main() throws IOException, InterruptedException {
    Property property = new Property(new Properties());
    LinkedList serverList = new LinkedList();

    for (Server s : property.prepareServers()) {
        serverList.add(s);
    }

    LoadBalancers loadBalancer = new LoadBalancers(serverList, property);
    ExecutorService ex = Executors.newFixedThreadPool(14);

    ex.execute(loadBalancer);
    Client client = new Client("a");
    LinkedList.Node cur = serverList.getHead();

    while (cur != null) {
        ex.execute(new ServerHandler(cur.getServer(), new Socket(client.getHOST(), client.getLOAD_BALANCER_PORT())));
        cur = cur.getNext();
    }

    ex.execute(new ClientHandler(client));
    ex.shutdown();

    while (!ex.isTerminated()) {
        Thread.sleep(100);  // Check periodically if the threads have finished
    }

}