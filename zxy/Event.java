/**
 * Encapsulates the information of a Event object associated with customers'
 * and servers' behaviours. The complete information includes the event happening 
 * time a <code>Customer</code>, a <code>Server</code>, and the 
 * <code>State</code>, while some events do not contain a customer or a server.
 *
 * @Author Zhang Xiaoyu
 */

package cs2030.simulator; 

public class Event {

    double time;
    Customer customer;
    Server server;
    State state;

    /**
     * Event constructor specifying event happening time, customer, and
     * the event's state.
     * 
     * @param time        a double-precise time at which a event will happen
     * @param customer    a <code>Customer</code>
     * @param state       a enum variable of <code>State</code>. It can be the
     *                    value of ARRIVES, SERVED, WAITS, LEAVES, or DONE.
     */
    public Event(double time, Customer customer, State state) {
        this.time = time;
        this.customer = customer;
        this.state = state;
    }
    
    /**
     * Event constructor specifying event happening time, customer, and
     * the event's state.
     * 
     * @param time        a double-precise time at which a event will happen
     * @param server      a <code>Server</code>
     * @param state       a enum variable of <code>State</code>. It can be
     *                    either SERVER_REST or SERVER_BACK
     */
    public Event(double time, Server server, State state) {
        this.time = time;
        this.server = server;
        this.state = state;
    }
    
    /**
     * Event constructor specifying event happening time, a customer, a server,
     * and the event's state.
     *
     * @param time        a double-precise time at which a event will happen
     * @param customer    a <code>Customer</code>
     * @param server      a <code>Server</code>
     * @param state       a enum variable of <code>State</code>. It can be the
     *                    value of ARRIVES, SERVED, WAITS, LEAVES, or DONE.
     */
    public Event(double time, Customer customer, Server server, State state) {
        this(time, customer, state);
        this.server = server;
    }

    public Customer getCustomer() {
        return customer;
    }

    public Server getServer() {
        return server;
    }

    public double getTime() { 
        return time;
    }

    public State getState() { 
        return state;
    }

    /**
     * Formats the event's information according to its state. The time is
     * formatted to have three decimals.
     *
     * @return a string representation of the contents of the specified time,
     *         customer id, server id, and the event state. Some events may not
     *         contain customer id or server id.
     */
    @Override
    public String toString() {
        switch (state) {
            case ARRIVES:
                return String.format("%.3f %s arrives", time, customer);
            case SERVED:
                return String.format("%.3f %s served by %s", 
                        time, customer, server);
            case WAITS:
                return String.format("%.3f %s waits to be served by %s", 
                        time, customer, server);
            case DONE:
                return String.format("%.3f %s done serving by %s", 
                        time, customer, server);
            case SERVER_REST:
                return String.format("%.3f %s rest", time, server);
            case SERVER_BACK:
                return String.format("%.3f %s back", time, server);
            default:
                return String.format("%.3f %s leaves", time, customer);
        }
    }
}
