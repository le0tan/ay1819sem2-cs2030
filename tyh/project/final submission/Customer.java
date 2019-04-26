package cs2030.simulator;

import java.util.function.Supplier;

/**
 * Describes the status and basic information of
 * a <code>Customer</code>. 
 * 
 * <p>Note that <code>Customer</code> class is not written in an immutable
 * way because in this design the simulator is actually processing
 * <code>Event</code> instead of <code>Customer</code>. This class 
 * is more like a log for the information about a customer.
 * 
 * <p>It has five private instance fields: timeOfArrival, timeOfService, 
 * durationOfService, and customerID whose variable
 * names are self-explanatory. And one Supplier for service time, 
 * which is passed in during the creation of this object, in 
 * <code>EventSimulator</code> class.
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
    private Supplier<Double> serviceTimeSupplier;

    /**
     * Constructor for initializing the <code>Customer</code> array 
     * in <code>addCustomer()</code> method in <code>EventSimulator</code>
     * class.
     * 
     * @param time the time of arrival
     */
    public Customer(double time, Supplier<Double> serviceTimeSupplier) {
        this.timeOfArrival = time;
        this.timeOfService = -1.0;
        numOfCustomers++;
        this.customerID = numOfCustomers;
        this.serviceTimeSupplier = serviceTimeSupplier;
    }

    /**
     * Generate the service time from the random generator.
     * 
     * @return service time for this customer
     */
    public double getServiceTime() {
        return this.serviceTimeSupplier.get();
    }

    /**
     * Check if the current customer is greedy.
     * 
     * @return true if it is greedy, false otherwise.
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
     * Returns the duration of service.
     * 
     * @return the duration of service.
     */
    public double getDurationOfService() {
        return durationOfService;
    }


    /**
     * Setter for time of service.
     * 
     * @param timeOfService the timeOfService to set
     */
    public void setTimeOfService(double timeOfService) {
        this.timeOfService = timeOfService;
    }

    /**
     * Setter for service duration.
     * 
     * @param durationOfService the durationOfService to set
     */
    public void setDurationOfService(double durationOfService) {
        this.durationOfService = durationOfService;
    }
}