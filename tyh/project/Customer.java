/**
 * Customer
 */
public class Customer {

    private static int numOfCustomers = 0;
    private double timeOfArrival;
    private int customerID;
    private boolean served;
    private Double timeOfService;

    public Customer(double time) {
        numOfCustomers++;
        customerID = numOfCustomers;
        timeOfArrival = time;
        served = false;
        timeOfService = null;
    }

    public static int getNumOfCustomers() {
        return numOfCustomers;
    }

    public double getTimeOfArrival() {
        return timeOfArrival;
    }

    public int getID() {
        return customerID;
    }

    public void serve(double time) {
        served = true;
        timeOfService = time;
    }
}