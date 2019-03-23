package cs2030.simulator;

import java.util.Queue;
import java.util.LinkedList;

public class Server {

    public static final double DURATION_OF_SERVICE = 1.0;
    public static int numOfServers = 0;
    public int serverID;
    private boolean isServing;
    // private boolean isWaiting;
    private double nextServiceTime;
    private Customer servingCustomer;
    private Queue<Customer> waitingQueue;
    private int queueLength;

    /**
     * @param queueLength the maximum length of waiting queue
     * Constructor for Server object.
     * The ID of the server is self-incremental.
     */
    public Server(int queueLength) {
        isServing = false;
        // isWaiting = false;
        nextServiceTime = 0.0;
        servingCustomer = null;
        numOfServers++;
        serverID = numOfServers;
        waitingQueue = new LinkedList<Customer>();
        this.queueLength = queueLength;
    }

    public boolean canServeImmediately() {
        return !this.isServing;
    }

    public boolean canWait() {
        return this.waitingQueue.size() < this.queueLength;
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
                    returnedCustomer = customer.withStatus(Customer.SERVED).withServer(this);
                } else if (canWait()) {
                    returnedCustomer = customer.withStatus(Customer.WAITS).withServer(this);
                    this.waitingQueue.add(returnedCustomer);
                } else {
                    returnedCustomer = customer.withStatus(Customer.LEAVES);
                }
                break;
            case Customer.SERVED:
                isServing = true;
                // if (isWaiting) {
                //     isWaiting = false;
                // }
                servingCustomer = customer;
                double serviceTime = EventSimulator.randomGenerator.genServiceTime();
                nextServiceTime = customer.getCurrentStatusTime() + serviceTime;
                returnedCustomer = customer.withTimeOfService(customer.getCurrentStatusTime())
                                           .withCurrentStatusTime(nextServiceTime)
                                           .withStatus(Customer.DONE)
                                           .withDurationOfService(serviceTime);
                break;
            case Customer.DONE:
                isServing = false;
                if(!this.waitingQueue.isEmpty()) {
                    returnedCustomer = this.waitingQueue.poll().withStatus(Customer.SERVED)
                                               .withCurrentStatusTime(nextServiceTime);
                } else {
                    servingCustomer = null;
                    shouldReturn = false;
                }
                break;
            case Customer.WAITS:
                shouldReturn = false;
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