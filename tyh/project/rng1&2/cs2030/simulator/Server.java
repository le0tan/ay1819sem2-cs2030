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
        Customer returnedCustomer = customer.clone();
        boolean shouldReturn = true;
        switch (customer.status) {
            case Customer.ARRIVES:
                if (customer.getTimeOfArrival() >= nextServiceTime) {
                    returnedCustomer.setStatus(Customer.SERVED);
                    returnedCustomer.setServer(this);
                } else if (!isWaiting) {
                    returnedCustomer.setStatus(Customer.WAITS);
                    returnedCustomer.setServer(this);
                } else {
                    returnedCustomer.setStatus(Customer.LEAVES);
                }
                break;
            case Customer.SERVED:
                isServing = true;
                if (isWaiting) {
                    isWaiting = false;
                }
                servingCustomer = customer;
                nextServiceTime = customer.getCurrentStatusTime() + DURATION_OF_SERVICE;
                returnedCustomer.setTimeOfService(customer.getCurrentStatusTime());
                returnedCustomer.setCurrentStatusTime(nextServiceTime);
                returnedCustomer.setStatus(Customer.DONE);
                break;
            case Customer.DONE:
                isServing = false;
                servingCustomer = null;
                shouldReturn = false;
                break;
            case Customer.WAITS:
                isWaiting = true;
                returnedCustomer.setStatus(Customer.SERVED);
                returnedCustomer.setCurrentStatusTime(nextServiceTime);
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