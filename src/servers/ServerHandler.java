package servers;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServerHandler implements Runnable{

    private final Lock lock = new ReentrantLock();
    private Server server;
    private BlockingQueue<Socket> socketsBlockingQueue; // This should be queue of request pool
    private boolean health; // this should signal to the Load-balancer that the server is well and ready to receive more connections

    public ServerHandler(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            lock.lock();
            try(ServerSocket socket = new ServerSocket(server.getPort(), 1000)){
                Socket client = socket.accept();
                server.calculateRadiusBasedOnGivenDiameterOfCircle( new DataInputStream(client.getInputStream()), client);
                Thread.sleep(2);
            } catch (IOException e) {
                System.out.println(STR."Error \{e.getMessage()}");
            } catch (InterruptedException e) {
                System.out.println(e.getLocalizedMessage());
            }
            lock.unlock();
        }
    }
}
