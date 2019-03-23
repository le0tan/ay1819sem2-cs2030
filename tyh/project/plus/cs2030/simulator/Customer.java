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
    public static final int BACK = 5;
    public static int numOfCustomers = 0;
    private double timeOfArrival;
    private double timeOfService;
    private double currentStatusTime;
    private double durationOfService;
    private int customerID;
    public int status;
    private Server server;
    private boolean isGreedy;

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
        this.isGreedy = false;
    }

    public Customer(double time, boolean greedy) {
        this.timeOfArrival = time;
        this.timeOfService = -1.0;
        this.currentStatusTime = time;
        this.status = ARRIVES;
        numOfCustomers++;
        this.customerID = numOfCustomers;
        this.server = null;
        this.isGreedy = greedy;
    }

    public boolean isGreedy() {
        return this.isGreedy;
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
                    double statustime, int id, int status, Server server, double duration,
                    boolean greedy) {
        this.timeOfArrival = arrival;
        this.timeOfService = service;
        this.currentStatusTime = statustime;
        this.customerID = id;
        this.status = status;
        this.server = server;
        this.durationOfService = duration;
        this.isGreedy = greedy;
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
     * Returns the duration of service.
     * @return the duration of service.
     */
    public double getDurationOfService() {
        // System.out.println(durationOfService);
        return durationOfService;
    }

    /**
     * Add the time of service to current customer,
     * returns the new customer object.
     * @param time of service
     * @return the new Customer object
     */
    public Customer withTimeOfService(double time) {
        return new Customer(this.timeOfArrival, time, 
                            this.currentStatusTime, this.customerID, 
                            this.status, this.server, this.durationOfService,
                            this.isGreedy);
    }

    /**
     * Add the current status time to current customer,
     * returns the new customer object.
     * @param time current status time
     * @return the new Customer object
     */
    public Customer withCurrentStatusTime(double time) {
        return new Customer(this.timeOfArrival, this.timeOfService, 
                            time, this.customerID, this.status, 
                            this.server, this.durationOfService,
                            this.isGreedy);
    }

    /**
     * Add the status to current customer,
     * returns the new customer object.
     * @param status status of the new customer object
     * @return the new Customer object
     */
    public Customer withStatus(int status) {
        return new Customer(this.timeOfArrival, this.timeOfService, 
                            this.currentStatusTime, this.customerID, 
                            status, this.server, this.durationOfService,
                            this.isGreedy);
    }

    /**
     * Add the server to current customer,
     * returns the new customer object.
     * @param server server of the new customer object
     * @return the new Customer object
     */
    public Customer withServer(Server server) {
        return new Customer(this.timeOfArrival, this.timeOfService, 
                            this.currentStatusTime, this.customerID, 
                            this.status, server, this.durationOfService,
                            this.isGreedy);
    }

    /**
     * Add the duration of service to current customer,
     * returns the new customer object.
     * @param time duration of service
     * @return the new Customer object
     */
    public Customer withDurationOfService(double time) {
        return new Customer(this.timeOfArrival, this.timeOfService, 
                            this.currentStatusTime, this.customerID, 
                            this.status, this.server, time, this.isGreedy);
    }
}