package cs2030.simulator;

public class Server {

    public static final double DURATION_OF_SERVICE = 1.0;
    public static int numOfServers = 0;
    public int serverID;
    private boolean isServing;
    private boolean isWaiting;
    private double nextServiceTime;
    private Customer servingCustomer;

    /**
     * Constructor for Server object.
     * The ID of the server is self-incremental.
     */
    public Server() {
        isServing = false;
        isWaiting = false;
        nextServiceTime = 0.0;
        servingCustomer = null;
        numOfServers++;
        serverID = numOfServers;
    }

    /**
     * Getter for isServing property.
     * @return whether the server is serving
     */
    public boolean isServing() {
        return isServing;
    }

    /**
     * Getter for isWaiting property.
     * @return whether there is a customer waiting for this server
     */
    public boolean isWaiting() {
        return isWaiting;
    }

    /**
     * Serve a customer, return the served customer if successful.
     * @return the customer that's served (with changed status and properties)
     */
    public Customer serve(Customer customer) {
        Customer returnedCustomer = customer;
        boolean shouldReturn = true;
        switch (customer.status) {
            case Customer.ARRIVES:
                if (customer.getTimeOfArrival() >= nextServiceTime) {
                    returnedCustomer = customer.setStatus(Customer.SERVED).setServer(this);
                } else if (!isWaiting) {
                    returnedCustomer = customer.setStatus(Customer.WAITS).setServer(this);
                } else {
                    returnedCustomer = customer.setStatus(Customer.LEAVES);
                }
                break;
            case Customer.SERVED:
                isServing = true;
                if (isWaiting) {
                    isWaiting = false;
                }
                servingCustomer = customer;
                nextServiceTime = customer.getCurrentStatusTime() + customer.getDurationOfService();
                returnedCustomer = customer.setTimeOfService(customer.getCurrentStatusTime()).setCurrentStatusTime(nextServiceTime).setStatus(Customer.DONE);
                break;
            case Customer.DONE:
                isServing = false;
                servingCustomer = null;
                shouldReturn = false;
                break;
            case Customer.WAITS:
                isWaiting = true;
                returnedCustomer = customer.setStatus(Customer.SERVED).setCurrentStatusTime(nextServiceTime);
                break;
            case Customer.LEAVES:
                shouldReturn = false;
                break;
            default:
                System.out.println("Uncaught status");
                shouldReturn = false;
                break;
        }
        if (shouldReturn) {
            return returnedCustomer;
        } else {
            return null;
        }
    }
}