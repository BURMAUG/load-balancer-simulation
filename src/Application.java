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
    ExecutorService service = Executors.newFixedThreadPool(5);
    service.submit(new LoadBalancers(servers));
    for (Server server : servers)
        service.submit(new ServerHandler(server));
    service.submit(new ClientHandler());
    service.shutdown();
//    new Thread(new LoadBalancers(servers)).start();
//    for (Server server : servers)
//        new Thread(new ServerHandler(server)).start();
}
