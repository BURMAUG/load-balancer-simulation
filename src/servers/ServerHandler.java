package servers;

import java.net.Socket;

public class ServerHandler implements Runnable{
    private Server server;
    private Socket socket; // should come from lb

    public ServerHandler(Server server) {
        this.server = server;
    }

    @Override
    public void run() {
    }
}
