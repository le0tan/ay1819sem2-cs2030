package cs2030.simulator;

/**
 * ServerEvent
 */
public class ServerEvent implements Event {

    private double time;
    private int type;
    private Server server;

    public ServerEvent(double time, int type, Server server) {
        this.time = time;
        this.type = type;
        this.server = server;
    }

    @Override
    public double getTime() {
        return this.time;
    }

    @Override
    public int getType() {
        return this.type;
    }

    /**
     * @return the server
     */
    public Server getServer() {
        return server;
    }
    
}