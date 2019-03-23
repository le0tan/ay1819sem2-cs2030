package cs2030.simulator;

public class Event {
    private double time;
    private Customer customer;
    private int eventType;
    private Server server;
    private boolean isCustomerEvent;


    /**
     * @return the isCustomerEvent
     */
    public boolean isCustomerEvent() {
        return isCustomerEvent;
    }

    public int eventType() {
        return this.eventType;
    }

    /**
     * Constructor for the event.
     * @param time time of the event
     * @param customerID customer involved in the event
     * @param eventType type of the event
     * @param server whom served the customer in this event (null if there isn't any)
     */
    public Event(double time, Customer customer, int eventType, Server server) {
        this.time = time;
        this.customer = customer;
        this.eventType = eventType;
        this.server = server;
        this.isCustomerEvent = true;
    }

    public Event(double time, int eventType, Server server) {
        this.time = time;
        this.eventType = eventType;
        this.server = server;
        this.isCustomerEvent = false;
    }

    /**
     * Output the eventlog as a string.
     * @return a string with event time, customer ID and event itself
     */
    @Override
    public String toString() {
        if(this.isCustomerEvent){
            return String.format("%.3f %s %s", time, customerToString(this.customer), statusToString());
        } else {
            return String.format("%.3f server %d %s", time, server.serverID, statusToString());
        }
    }

    /**
     * Format the status according to the sample output.
     * @return eventType + serverID (if necessary)
     */
    private String statusToString() {
        switch (eventType) {
            case Customer.DONE:
                return String.format("done serving by %s", serverToString(server));
            case Customer.ARRIVES:
                return "arrives";
            case Customer.LEAVES:
                return "leaves";
            case Customer.WAITS:
                return String.format("waits to be served by %s", serverToString(server));
            case Customer.SERVED:
                return String.format("served by %s", serverToString(server));
            case Server.SERVER_REST:
                return "rest";
            case Server.SERVER_BACK:
                return "back";
            default:
                return "error";
        }
    }

    private String serverToString(Server s) {
        if(s instanceof SelfCheckoutCounter) {
            return String.format("self-check %d", s.serverID);
        } else {
            return String.format("server %d", s.serverID);
        }
    }

    private String customerToString(Customer c) {
        if(c.isGreedy()) {
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