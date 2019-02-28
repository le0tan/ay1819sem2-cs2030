
public class Event implements Comparable<Event> {
    private double time;
    private int customerID;
    private int eventType;
    private Server server;


    /**
     * Constructor for the event.
     * @param time time of the event
     * @param customerID customer involved in the event
     * @param eventType type of the event
     * @param server whom served the customer in this event (null if there isn't any)
     */
    public Event(double time, int customerID, int eventType, Server server) {
        this.time = time;
        this.customerID = customerID;
        this.eventType = eventType;
        this.server = server;
    }

    /**
     * Output the eventlog as a string.
     * @return a string with event time, customer ID and event itself
     */
    @Override
    public String toString() {
        return String.format("%.3f %d %s", time, customerID, statusToString());
    }

    /**
     * Format the status according to the sample output.
     * @return eventType + serverID (if necessary)
     */
    private String statusToString() {
        switch (eventType) {
            case Customer.DONE:
                return String.format("done serving by %d", server.serverID);
            case Customer.ARRIVES:
                return "arrives";
            case Customer.LEAVES:
                return "leaves";
            case Customer.WAITS:
                return String.format("waits to be served by %d", server.serverID);
            case Customer.SERVED:
                return String.format("served by %d", server.serverID);
            default:
                return "error";
        }
    }

    /**
     * Implementation for Comparable interface.
     * @param o the other Event that's being compared to
     * @return negative int if this < o, 0 if this == o, positive int if this > o
     */
    @Override
    public int compareTo(Event o) {
        if (this.time != o.getTime()) {
            return (this.time - o.getTime()) < 0 ? -1 : 1;
        } else {
            if (this.customerID - o.getCustomerID() == 0) {
                return this.getEventType() - o.getEventType();
            } else {
                return this.customerID - o.getCustomerID();
            }
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
        return customerID;
    }

    /**
     * Getter for type of the event.
     * @return eventType
     */
    public int getEventType() {
        return eventType;
    }
}