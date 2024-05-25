package servers;

import java.net.Socket;

public class ServerHandler implements Runnable{
    private Server server;
    private Socket socket; // should come from lb

    @Override
    public void run() {
    }
}
