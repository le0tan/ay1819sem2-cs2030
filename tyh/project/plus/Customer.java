package cs2030.simulator;

import cs2030.simulator.GreedyCustomer;

/**
 * Describes the status and basic information of
 * a <code>Customer</code>. 
 * 
 * <p>It has six private instance fields: timeOfArrival, timeOfService, 
 * durationOfService, customerID, server and isGreedy whose variable
 * names are self-explanatory.
 * 
 * <p>It also comes with a <code>public static</code> counter named
 * <code>numOfCustomers</code> which is used to keep track of
 * the total number of customers so that we can assign correct IDs for
 * them.
 * 
 * @author Tan Yuanhong
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
     * 
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

    /**
     * Getter for <code>isGreedy</code> property.
     * 
     * @return <code>isGreedy</code> property
     */
    public boolean isGreedy() {
        return (this instanceof GreedyCustomer);
    }
    

    /**
     * Returns the time of arrival.
     * 
     * @return time of arrival
     */
    public double getTimeOfArrival() {
        return timeOfArrival;
    }

    /**
     * Returns the time of service.
     * 
     * @return time of service
     */
    public double getTimeOfService() {
        return timeOfService;
    }

    /**
     * Returns the customer ID.
     * 
     * @return customer ID
     */
    public int getCustomerID() {
        return customerID;
    }

    /**
     * Returns whom served the customer (null if the customer is not served yet).
     * 
     * @return whom served the customer
     */
    public Server getServer() {
        return server;
    }

    /**
     * Returns the duration of service.
     * 
     * @return the duration of service.
     */
    public double getDurationOfService() {
        return durationOfService;
    }

    /**
     * Setter for server.
     * 
     * @param server the server to set
     */
    public void setServer(Server server) {
        this.server = server;
    }

    /**
     * Setter for time of service.
     * @param timeOfService the timeOfService to set
     */
    public void setTimeOfService(double timeOfService) {
        this.timeOfService = timeOfService;
    }

    /**
     * Setter for service duration.
     * @param durationOfService the durationOfService to set
     */
    public void setDurationOfService(double durationOfService) {
        this.durationOfService = durationOfService;
    }
}