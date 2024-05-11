package servers;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class Server {
    private int port;

    public Server(int port) {
        this.port = port;
    }

    public int getPort() {
        return port;
    }


    public void calculateRadiusBasedOnGivenDiameterOfCircle(DataInputStream dataInputStream, Socket client) throws IOException {
        double d = dataInputStream.readDouble();
        DataOutputStream outputStream = new DataOutputStream(client.getOutputStream());
        outputStream.writeDouble(d / 2);
    }
}