package clients;

public class Client{
    private String clientName;
    private final String HOST = "localhost";
    private final Integer LOAD_BALANCER_PORT = 2000;

    public Client(String clientName) {
        this.clientName = clientName;
    }

    public String getClientName() {
        return clientName;
    }

    public String getHOST() {
        return HOST;
    }

    public Integer getLOAD_BALANCER_PORT() {
        return LOAD_BALANCER_PORT;
    }
}