import clients.Client;
import clients.ClientHandler;
import lb.LoadBalancers;
import servers.Server;
import servers.ServerHandler;

import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public static void main(String[] args) {
    ArrayList<Server> servers = new ArrayList<>(Arrays.asList(
            new Server(1111),
            new Server(2222),
            new Server(5555)));
    ExecutorService service = Executors.newFixedThreadPool(7);
    service.submit(new LoadBalancers(servers));
    for (Server server : servers)
        service.submit(new ServerHandler(server));
    service.submit(new ClientHandler(new Client("sa")));
    service.submit(new ClientHandler(new Client("dsd")));
    service.shutdown();

    //    while (!service.isTerminated())
//    {}
}
