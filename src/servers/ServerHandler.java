package servers;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServerHandler implements Runnable{

    private final Lock lock = new ReentrantLock();
    private Server server;

    public ServerHandler(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            lock.lock();
            try(ServerSocket socket = new ServerSocket(server.getPort())){
                Socket client = socket.accept();
                server.calculateRadiusBasedOnGivenDiameterOfCircle( new DataInputStream(client.getInputStream()), client);
            } catch (IOException e) {
                System.out.println(STR."Error \{e.getMessage()}");
            }
            lock.unlock();
        }
    }
}
