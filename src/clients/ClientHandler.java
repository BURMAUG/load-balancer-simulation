package clients;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ClientHandler extends Thread {
    private final Lock lock = new ReentrantLock();
    private final Client clientAddr;

    public ClientHandler(Client clientAddr) {
        this.clientAddr = clientAddr;
    }

    private double genDiameter() {
        return new Random().nextDouble();
    }

    @Override
    public void run() {
        for (int i = 0; i < 1_000; i++){
            lock.lock();
            try(Socket client = new Socket(clientAddr.getHOST(), clientAddr.getLOAD_BALANCER_PORT())){
                DataInputStream receivePort = new DataInputStream(client.getInputStream());
                int serverPort = receivePort.readInt();
                System.out.println(STR."Receiverd port \{serverPort}");
                try(Socket ss = new Socket("localhost", serverPort)){
                    DataOutputStream dataOutputStream = new DataOutputStream(ss.getOutputStream());
                    double diameter = genDiameter();
                    dataOutputStream.writeDouble(diameter);

                    // Radius of the given circles diameter
                    DataInputStream radiusInputStream = new DataInputStream(ss.getInputStream());
                    System.out.println(STR."\{i+1}. The diameter of a circle \{diameter} has a radius \{radiusInputStream.readDouble()}");
                }
            } catch (IOException e) {
                System.out.println(STR."Error from ClientHandler \{e.getMessage()}");
                e.printStackTrace();
            } finally {
                lock.unlock();
            }
        }
    }
}