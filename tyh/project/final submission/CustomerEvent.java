package cs2030.simulator;

/**
 * Provides an implementation of <code>Event</code>
 * abstract class for events related with customers.
 * 
 * <p>In addition to instance fields specified in <code>Event</code>, 
 * we have additional <code>final</code> field named customer for 
 * the customer involved in the event.
 * 
 * @author Tan Yuanhong
 */

public class CustomerEvent extends Event {

    protected final Customer customer;
    protected final Server server;

    /**
     * Constructor for a <code>CustomerEvent</code>.
     * 
     * @param time      time of event
     * @param type      event type
     * @param customer  customer involved in the event
     * @param server    server involved in the event
     */
    public CustomerEvent(double time, EventType type, Customer customer, Server server) {
        super(time, type);
        this.customer = customer;
        this.server = server;
    }

    /**
     * A functional implementation of setTime.
     * 
     * @param time  time of event
     * @return a new <code>CustomerEvent</code> object with the changed time
     */
    public CustomerEvent setTime(double time) {
        return new CustomerEvent(time, this.type, this.customer, this.server);
    }

    /**
     * A functional implementation of setType.
     * 
     * @param type  type of event
     * @return a new <code>CustomerEvent</code> object with the changed event type
     */
    public CustomerEvent setType(EventType type) {
        return new CustomerEvent(this.time, type, this.customer, this.server);
    }

    /**
     * A functional implementation of setServer.
     * 
     * @param server the server involved in this event
     * @return a new <code>CustomerEvent</code> object with the changed server
     */
    public CustomerEvent setServer(Server server) {
        return new CustomerEvent(this.time, this.type, this.customer, server);
    }

    @Override
    public double getTime() {
        return this.time;
    }

    @Override
    public EventType getType() {
        return this.type;
    }
    
    public Customer getCustomer() {
        return this.customer;
    }

    public Server getServer() {
        return this.server;
    }
}