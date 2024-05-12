package clients;

public record Client(String clientName) {

    public String getHOST() {
        return "localhost";
    }

    public Integer getLOAD_BALANCER_PORT() {
        return 2000;
    }
}