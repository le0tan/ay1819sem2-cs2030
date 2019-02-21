/**
 * Customer
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

    public Customer(double time) {
        timeOfArrival = time;
        timeOfService = -1.0;
        currentStatusTime = time;
        status = ARRIVES;
        numOfCustomers++;
        customerID = numOfCustomers;
    }

    public Customer(double arrival, double service, double statustime, int id, int status) {
        this.timeOfArrival = arrival;
        this.timeOfService = service;
        this.currentStatusTime = statustime;
        this.customerID = id;
        this.status = status;
    }

    @Override
    public int compareTo(Customer o) {
        if(this.currentStatusTime != o.getCurrentStatusTime()) {
            return (this.currentStatusTime - o.getCurrentStatusTime()) < 0 ? -1 : 1;
        } else {
            if(this.customerID - o.getCustomerID() == 0) {
                return this.status - o.status;
            } else {
                return this.customerID - o.getCustomerID();
            }
        }
    }

    @Override
    public String toString() {
        return String.format("%.3f %d %s", currentStatusTime, customerID, statusToString());
    }

    private String statusToString() {
        switch (status) {
            case DONE:
                return "done";
            case ARRIVES:
                return "arrives";
            case LEAVES:
                return "leaves";
            case WAITS:
                return "waits";
            case SERVED:
                return "served";
            default:
                return "error";
        }
    }

    public Customer clone() {
        return new Customer(timeOfArrival, timeOfService, currentStatusTime, customerID, status);
    }

    public double getTimeOfArrival() {
        return timeOfArrival;
    }

    public double getTimeOfService() {
        return timeOfService;
    }

    public double getCurrentStatusTime() {
        return currentStatusTime;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setTimeOfService(double time) {
        timeOfService = time;
    }

    public void setCurrentStatusTime(double time) {
        currentStatusTime = time;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}