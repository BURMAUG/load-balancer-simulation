import clients.Client;
import clients.ClientHandler;
import lb.LoadBalancers;
import lb.Property;

import java.io.IOException;

import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public static void main(String[] args) throws IOException{
    ExecutorService service = Executors.newFixedThreadPool(3);
    Property property = new Property(new Properties());

    LoadBalancers loadBalancers = new LoadBalancers(property.prepareServers(), property);
    service.submit(loadBalancers);
    service.submit(new ClientHandler(new Client("Burmau")));
    service.shutdown();
}
