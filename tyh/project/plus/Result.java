package cs2030.simulator;

/**
 * A immutable class for storing results.
 */

public class Result {
    private final double time;
    private final Customer customer;
    private final int eventType;
    private final Server server;
    private final boolean isCustomerEvent;


    /**
     * Getter for <code>isCustomerEvent</code> property.
     * @return the isCustomerEvent
     */
    public boolean isCustomerEvent() {
        return isCustomerEvent;
    }

    /**
     * Constructor for <code>Result</code> that involves a customer.
     * @param time time of the event
     * @param customer customer involved in the event
     * @param eventType type of the event
     * @param server whom served the customer in this event (null if there isn't any)
     */
    public Result(double time, Customer customer, int eventType, Server server) {
        this.time = time;
        this.customer = customer;
        this.eventType = eventType;
        this.server = server;
        this.isCustomerEvent = true;
    }

    /**
     * Constructore for <code>Result</code> that only involves the server.
     * @param time time of event
     * @param eventType type of event
     * @param server server
     */
    public Result(double time, int eventType, Server server) {
        this.time = time;
        this.eventType = eventType;
        this.server = server;
        this.isCustomerEvent = false;
        this.customer = null;
    }

    /**
     * Output the eventlog as a string.
     * @return a string with event time, customer ID and event itself
     */
    @Override
    public String toString() {
        if (this.isCustomerEvent) {
            return String.format("%.3f %s %s", 
                                    time, 
                                    customerToString(this.customer), 
                                    statusToString());
        } else {
            return String.format("%.3f server %d %s", time, server.serverID, statusToString());
        }
    }

    /**
     * Formatter for the status according to the sample output.
     * @return a status string
     */
    private String statusToString() {
        switch (eventType) {
            case Event.DONE:
                return String.format("done serving by %s", serverToString(server));
            case Event.ARRIVES:
                return "arrives";
            case Event.LEAVES:
                return "leaves";
            case Event.WAITS:
                return String.format("waits to be served by %s", serverToString(server));
            case Event.SERVED:
                return String.format("served by %s", serverToString(server));
            case Server.SERVER_REST:
                return "rest";
            case Server.SERVER_BACK:
                return "back";
            default:
                return "error";
        }
    }

    /**
     * Formatter for the server information.
     * @param s the server
     * @return a string of server's description
     */
    private String serverToString(Server s) {
        if (s instanceof SelfCheckoutCounter) {
            return String.format("self-check %d", s.serverID);
        } else {
            return String.format("server %d", s.serverID);
        }
    }

    /**
     * Formatter for the customer information.
     * @param c the customer
     * @return a string of customer's description
     */
    private String customerToString(Customer c) {
        if (c.isGreedy()) {
            return String.format("%d(greedy)", c.getCustomerID());
        } else {
            return Integer.toString(c.getCustomerID());
        }
    }

    /**
     * Getter for time of the event.
     * @return time
     */
    public double getTime() {
        return time;
    }

    /**
     * Getter for customer of the event.
     * @return customerID
     */
    public int getCustomerID() {
        return customer.getCustomerID();
    }

    /**
     * Getter for type of the event.
     * @return eventType
     */
    public int getEventType() {
        return eventType;
    }
}