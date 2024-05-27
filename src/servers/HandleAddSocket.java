package servers;


import java.net.Socket;

public class HandleAddSocket implements Runnable{
    private final Server server;
    private final Socket socket;

    public HandleAddSocket(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
        while(!Thread.currentThread().isInterrupted()){
            try {
                server.addSocket(socket);
            } catch (InterruptedException e) {
                System.out.println(STR."Error from handleAddSocket class \{e.getMessage()}");
            }
        }
    }
}
