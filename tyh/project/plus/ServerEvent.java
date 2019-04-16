/**
 * Provides an implementation of <code>Event</code>
 * interface for events related with servers.
 * 
 * <p>Three basic instance fields are (1) time (of the event), (2) type (of the 
 * event), (3) server (involved in the event). Note that <code>type</code> is
 * of <code>int</code> type because it will be assigned with values from constants
 * from <code>Event</code> class that represents different types of events.
 * 
 * @author Tan Yuanhong
 */

package cs2030.simulator;

public class ServerEvent implements Event {

    private double time;
    private int type;
    private Server server;

    /**
     * Constructor for <code>ServerEvent</code>.
     * @param time time of event
     * @param type type of event
     * @param server the server
     */
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
     * Getter for the <code>server</code> property of a <code>ServerEvent</code>.
     * @return the server
     */
    public Server getServer() {
        return server;
    }
    
}