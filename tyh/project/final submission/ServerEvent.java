package cs2030.simulator;

/**
 * Provides an implementation of <code>Event</code>
 * abstract class for events related with servers.
 * 
 * <p>In addition to instance fields specified in <code>Event</code>, 
 * we have additional <code>final</code> field named server for 
 * the server involved in the event.
 * 
 * @author Tan Yuanhong
 */

public class ServerEvent extends Event {

    protected final Server server;

    /**
     * Constructor for <code>ServerEvent</code>.
     * 
     * @param time time of event
     * @param type type of event
     * @param server the server
     */
    public ServerEvent(double time, EventType type, Server server) {
        super(time, type);
        this.server = server;
    }

    @Override
    public double getTime() {
        return this.time;
    }

    @Override
    public EventType getType() {
        return this.type;
    }

    /**
     * Getter for the <code>server</code> property of a <code>ServerEvent</code>.
     * 
     * @return the server involved in the event
     */
    public Server getServer() {
        return server;
    }
    
}