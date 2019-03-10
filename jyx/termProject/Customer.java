import java.lang.String;

/**
 * A class representing a customer.
 */
public class Customer {
    /**
     * The total number of customers.
     */
    private static int number = 0;
    /**
     * The unique identity number for the customer.
     */
    private int id;
    /**
     * The time when the customer arrives.
     */
    private double arriveTime;
    /**
     * The time when the customer is served.
     * Set to be -1 if it is not served yet.
     */
    private double serveTime;

    /**
     * Constructs a new <code>Customer</code> specifying the arriving time of the customer.
     * The time argument is the time at which the customer arrives. 
     *
     * @param time the time at which the customer arrives.
     */
    public Customer(double time) {
        this.id = ++number;
        arriveTime = time;
        serveTime = -1;
    }

    /**
     * Returns the unique identity number of the customer.
     *
     * @return the unique identity number of the customer.
     */
    public int getId() {
        return id;
    }
    
    /**
     * Sets the time of the customer getting served to be the given time.
     * The time argument is the time at which the customer gets served.
     *
     * @param time the time at which the customer gets served.
     */
    public void setServeTime(double time) {
        serveTime = time;
    }
    
    /**
     * Returns the time at which the customer arrives.
     *
     * @return the time at which the customer arrives.
     */
    public double getArriveTime() {
        return arriveTime;
    }
    
    /**
     * Returns the time at which the service of the customer finished.
     *
     * @return the time at which the service of the customer finished.
     */
    public double getFinishTime() {
        return serveTime + 1;
    }
    
    /**
     * Prints the number of customers.
     */
    public static void printNumber() {
        System.out.println("Number of customers: " + number);
    }

    /**
     * Returns a string representation of the customer.
     * The identity number of the customer is converted to <code>String</code> to represent
     * the customer.
     *
     * @return a string representation of the customer.
     */
    @Override
    public String toString() {
        return id + "";
    }
}
