package servers;

import java.net.Socket;

public class HandleTakeSocket implements Runnable{
    private final Server server;
    private final Socket socket;

    public HandleTakeSocket(Server server, Socket socket) {
        this.server = server;
        this.socket = socket;
    }

    @Override
    public void run() {
//        while (true){
//            server.addSocket(socket);
//        }
    }
}
