package cs2030.simulator;

/**
 * Customer class describes the status and basic information of
 * a customer. It also can be stored as a description of an event
 * related with that customer.
 */
public class Customer implements Comparable<Customer> {

    public static final int DONE = 1;
    public static final int LEAVES = 2;
    public static final int WAITS = 3;
    public static final int SERVED = 4;
    public static final int ARRIVES = 0;
    public static int numOfCustomers = 0;
    private double timeOfArrival;
    private double timeOfService;
    private double currentStatusTime;
    private int customerID;
    public int status;
    private Server server;

    /**
     * Constructor for initializing the customer array.
     * @param time the time of arrival
     */
    public Customer(double time) {
        timeOfArrival = time;
        timeOfService = -1.0;
        currentStatusTime = time;
        status = ARRIVES;
        numOfCustomers++;
        customerID = numOfCustomers;
        server = null;
    }

    /**
     * Constructor for cloning the customer.
     * @param arrival time of arrival
     * @param service time of service
     * @param statustime current status time
     * @param id customer ID
     * @param status customer status
     * @param server the server which serves this customer (null if it's not served)
     */
    public Customer(double arrival, double service, 
                    double statustime, int id, int status, Server server) {
        this.timeOfArrival = arrival;
        this.timeOfService = service;
        this.currentStatusTime = statustime;
        this.customerID = id;
        this.status = status;
        this.server = server;
    }

    /**
     * Implementation for Comparable interface.
     * @param o the other Customer that's being compared to
     * @return negative int if this < o, 0 if this == o, positive int if this > o
     */
    @Override
    public int compareTo(Customer o) {
        if (this.currentStatusTime != o.getCurrentStatusTime()) {
            return (this.currentStatusTime - o.getCurrentStatusTime()) < 0 ? -1 : 1;
        } else {
            if (this.customerID - o.getCustomerID() == 0) {
                return this.status - o.status;
            } else {
                return this.customerID - o.getCustomerID();
            }
        }
    }
    
    /**
     * For saving Customer object as an event in the final output,
     * we need to clone the object itself.
     * @return a cloned Customer object
     */
    public Customer clone() {
        return new Customer(timeOfArrival, timeOfService, 
                            currentStatusTime, customerID, status, server);
    }

    /**
     * Returns the time of arrival.
     * @return time of arrival
     */
    public double getTimeOfArrival() {
        return timeOfArrival;
    }

    /**
     * Returns the time of service.
     * @return time of service
     */
    public double getTimeOfService() {
        return timeOfService;
    }

    /**
     * Returns the time of the event.
     * @return time of the event
     */
    public double getCurrentStatusTime() {
        return currentStatusTime;
    }

    /**
     * Returns the customer ID.
     * @return customer ID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Returns whom served the customer (null if the customer is not served yet).
     * @return whom served the customer
     */
    public Server getServer() {
        return server;
    }

    /**
     * Returns the time of service.
     * @param time of service
     */
    public void setTimeOfService(double time) {
        timeOfService = time;
    }

    /**
     * Set the time of the event.
     * @param time of the event
     */
    public void setCurrentStatusTime(double time) {
        currentStatusTime = time;
    }

    /**
     * Set the status of the customer.
     * @param status of the customer
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Set the server that serves the customer.
     * @param server that serves the customer
     */
    public void setServer(Server server) {
        this.server = server;
    }
}