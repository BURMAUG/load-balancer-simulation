package servers;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ServerHandler implements Runnable{
    private final Server server;
    private Socket socket; // should come from lb
    private final Lock lock = new ReentrantLock();

    // Constructors
    public ServerHandler(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            lock.lock();
            try{
                ServerSocket serverSocket =  server.getServerSocket();
                socket = serverSocket.accept();
                server.addSocket(socket); // what should be here
                DataInputStream inputStream = new DataInputStream(socket.getInputStream());
                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                outputStream.writeDouble(inputStream.readDouble() /2);
                System.out.println("here");
                System.out.println(STR."+++++++\{server.isHealthy()}");

                Socket socket1 = server.takeSocket();
                socket1.close();
                System.out.println();
            } catch (IOException e) {
                System.out.println(STR."ServerHandler \{e.getMessage()}");
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            lock.unlock();
        }
    }
}
