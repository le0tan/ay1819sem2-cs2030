package cs2030.simulator;

/**
 * Customer class describes the status and basic information of
 * a customer. It also can be stored as a description of an event
 * related with that customer.
 */
public class Customer {

    public static int numOfCustomers = 0;
    private double timeOfArrival;
    private double timeOfService;
    private double durationOfService;
    private int customerID;
    private Server server;
    private boolean isGreedy;

    /**
     * Constructor for initializing the customer array.
     * @param time the time of arrival
     */
    public Customer(double time) {
        this.timeOfArrival = time;
        this.timeOfService = -1.0;
        numOfCustomers++;
        this.customerID = numOfCustomers;
        this.server = null;
        this.isGreedy = false;
    }

    public Customer(double time, boolean greedy) {
        this.timeOfArrival = time;
        this.timeOfService = -1.0;
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
                    int id, Server server, double duration, boolean greedy) {
        this.timeOfArrival = arrival;
        this.timeOfService = service;
        this.customerID = id;
        this.server = server;
        this.durationOfService = duration;
        this.isGreedy = greedy;
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
     * @param server the server to set
     */
    public void setServer(Server server) {
        this.server = server;
    }

    /**
     * @param timeOfService the timeOfService to set
     */
    public void setTimeOfService(double timeOfService) {
        this.timeOfService = timeOfService;
    }

    /**
     * @param durationOfService the durationOfService to set
     */
    public void setDurationOfService(double durationOfService) {
        this.durationOfService = durationOfService;
    }
}