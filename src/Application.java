import servers.Server;
import servers.ServerHandler;

import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public static void main(String[] args) throws IOException, InterruptedException {
    ArrayList<Server> servers = new ArrayList<>(List.of(new Server(1, "ServerA", 2111)));
    ServerHandler handler = new ServerHandler(servers.getFirst(), new Socket("localhost", 2111));
    ServerHandler handler2 = new ServerHandler(servers.getFirst(), new Socket("localhost", 2111));
    ServerHandler handler3 = new ServerHandler(servers.getFirst(), new Socket("localhost", 2111));
    ServerHandler handler4 = new ServerHandler(servers.getFirst(), new Socket("localhost", 2111));
    ServerHandler handler5 = new ServerHandler(servers.getFirst(), new Socket("localhost", 2111));
    ServerHandler handler6 = new ServerHandler(servers.getFirst(), new Socket("localhost", 2111));
    ServerHandler handler7 = new ServerHandler(servers.getFirst(), new Socket("localhost", 2111));
    Thread[] threads = {
    new Thread(handler),
    new Thread(handler2),
    new Thread(handler3),
    new Thread(handler4),
    new Thread(handler5),
    new Thread(handler6),
    new Thread(handler7)};
    for(Thread thread : threads)
        thread.start();
    for(Thread thread : threads){
        thread.join();
    }
}
