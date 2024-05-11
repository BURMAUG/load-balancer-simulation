package clients;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Random;

public class ClientHandler extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10_000; i++){
            try(Socket client = new Socket("localhost", 2000)){
                DataInputStream receivePort = new DataInputStream(client.getInputStream());
                int serverPort = receivePort.readInt();
                System.out.println(STR."\{serverPort}");
                try(Socket ss = new Socket("localhost", serverPort)){
                    DataOutputStream dataOutputStream = new DataOutputStream(ss.getOutputStream());
                    double diameter = genDiameter();
                    dataOutputStream.writeDouble(diameter);

                    // Answer
                    DataInputStream radiusInputStream = new DataInputStream(ss.getInputStream());
                    System.out.println(STR."\{i+1}. The diameter of a circle \{diameter} has a radius \{radiusInputStream.readDouble()}");
                    Thread.sleep(0);
                } catch (InterruptedException _) {

                }
            } catch (IOException e) {
                System.out.println(STR."Error from ClientHandler \{e.getMessage()}");
            }
        }
    }

    private double genDiameter() {
        return new Random().nextDouble();
    }
}