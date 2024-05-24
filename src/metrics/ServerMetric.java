package metrics;

import servers.Server;

public record ServerMetric(boolean isFull, boolean isHalfFull, Server server) {
    public static void serverHealth(Server server){

    }
}
