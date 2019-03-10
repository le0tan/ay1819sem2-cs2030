import java.lang.String;
import java.lang.StringBuilder;

/**
 * A class representing an event.
 */
public class Event implements Comparable<Event> {
    /**
     * The time that the event happens.
     */
    private double time;
    /**
     * The customer of the event.
     */
    private Customer customer;
    /**
     * The state of the event.
     */
    private State state;
    /**
     * The server associates with the event.
     * Set to be null if this event does not involve any server.
     */
    private Server server;

    /**
     * Constructs a <code>Event</code> object by specifying the time, customer
     * and state of the event.
     * The state should be <code>ARRIVES</code> or <code>LEAVES</code>.
     *
     * @param time the time that the event happens.
     * @param customer the customer of the event.
     * @param state the state of the event.
     */
    public Event(double time, Customer customer, State state) {
        this.time = time;
        this.customer = customer;
        this.state = state;
        this.server = null;
    }

    /**
     * Constructs a <code>Event</code> object by specifying the time, customer,
     * state and server of the event.
     * The state should be <code>SERVED</code>, <code>DONE</code> or <code>WAITS</code>.
     *
     * @param time the time that the event happens.
     * @param customer the customer of the event.
     * @param state the state of the event.
     * @param server the server associates with the event
     */
    public Event(double time, Customer customer, State state, Server server) {
        this(time,customer,state);
        this.server = server;
    }

    /**
     * Returns the state of the event.
     *
     * @return the state of the event.
     */
    public State getState() {
        return state;
    }

    /**
     * Returns the time of the event.
     *
     * @return the time of the event.
     */
    public double getTime() {
        return time;
    }
    
    /**
     * Returns the customer invovled in the event.
     *
     * @return the customer invovled in the event.
     */
    public Customer getCustomer() {
        return customer;
    }

    /**
     * Returns the server associates with the event.
     *
     * @return the server associates with the event.
     */
    public Server getServer() {
        return server;
    }

    /**
     * Returns a string representation of the event.
     * The string consists of time, customer, state and server if applicable.
     * 
     * @return a string representation of the event.
     */
    @Override
    public String toString() {
        StringBuilder strb = new StringBuilder(String.format("%.3f ",time) 
                + customer + " " + state);
        if (server != null) {
            switch (state) {
                case SERVED:
                    strb.append(" by " + server);
                    break;
                case WAITS:
                    strb.append(" to be served by " + server);
                    break;
                case DONE:
                    strb.append(" serving by " + server);
                    break;
                default:
            }
        }
        return strb.toString();
    }
    
    /**
     * Compares this <code>Event</code> object with the specified <code>Event
     * </code> object.
     *
     * @param   e the event to be compared to this <code>Event</code> object.
     * @return  the value 0 if the two event happens at the same time and the 
     *          customer of the two events has the same id; a value less than 
     *          0 if this event happens before the given argument or they 
     *          happens at the same time and the customer of this event has a 
     *          smaller id; a value greater than 0 if this event happens after
     *          the given argument or they happens at the same time and the 
     *          customer of this event has a bigger id.
     */
    public int compareTo(Event e) { 
        if (this.time != e.time) {
            return Double.compare(this.time, e.time);
        } else {
            return Integer.compare(this.customer.getId(), e.customer.getId());
        }
    }
}

