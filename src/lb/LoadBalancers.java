package lb;

import servers.Server;
import servers.ServerHandler;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class LoadBalancers implements Runnable{
    private ArrayList<Server> servers;

    public LoadBalancers(ArrayList<Server> servers) {
        this.servers = servers;
    }

    int getServer(){
        return new Random().nextInt(0, servers.size());
    }
    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()){
            try(ServerSocket lb = new ServerSocket(2000)) {
                Socket socket = lb.accept();
                // Transfer one server port to a given client
                DataOutputStream serverPort  = new DataOutputStream(socket.getOutputStream());
                serverPort.writeInt(servers.get(getServer()).getPort());
                serverPort.flush();
            } catch (IOException e) {
                System.out.println(STR."Error from Load Balancer \{e.getMessage()}");
            }
        }
    }
}
