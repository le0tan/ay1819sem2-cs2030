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
    private double durationOfService;
    private int customerID;
    public int status;
    private Server server;

    /**
     * Constructor for initializing the customer array.
     * @param time the time of arrival
     */
    public Customer(double time) {
        this.timeOfArrival = time;
        this.timeOfService = -1.0;
        this.currentStatusTime = time;
        this.status = ARRIVES;
        numOfCustomers++;
        this.customerID = numOfCustomers;
        this.server = null;
        // this.durationOfService = duration;
        // System.out.println(duration);
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
                    double statustime, int id, int status, Server server, double duration) {
        this.timeOfArrival = arrival;
        this.timeOfService = service;
        this.currentStatusTime = statustime;
        this.customerID = id;
        this.status = status;
        this.server = server;
        this.durationOfService = duration;
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
                            currentStatusTime, customerID, status, server, durationOfService);
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

    public double getDurationOfService() {
        // System.out.println(durationOfService);
        return durationOfService;
    }

    /**
     * Returns the time of service.
     * @param time of service
     */
    public Customer setTimeOfService(double time) {
        return new Customer(this.timeOfArrival, time, this.currentStatusTime, this.customerID, this.status, this.server, this.durationOfService);
    }

    /**
     * Set the time of the event.
     * @param time of the event
     */
    public Customer setCurrentStatusTime(double time) {
        return new Customer(this.timeOfArrival, this.timeOfService, time, this.customerID, this.status, this.server, this.durationOfService);
    }

    /**
     * Set the status of the customer.
     * @param status of the customer
     */
    public Customer setStatus(int status) {
        return new Customer(this.timeOfArrival, this.timeOfService, this.currentStatusTime, this.customerID, status, this.server, this.durationOfService);
    }

    /**
     * Set the server that serves the customer.
     * @param server that serves the customer
     */
    public Customer setServer(Server server) {
        return new Customer(this.timeOfArrival, this.timeOfService, this.currentStatusTime, this.customerID, this.status, server, this.durationOfService);
    }

    public Customer setDurationOfService(double time) {
        return new Customer(this.timeOfArrival, this.timeOfService, this.currentStatusTime, this.customerID, this.status, this.server, time);
    }
}