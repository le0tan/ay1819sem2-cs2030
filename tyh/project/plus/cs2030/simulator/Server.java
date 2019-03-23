package cs2030.simulator;

import java.util.Queue;
import java.util.LinkedList;

public class Server {

    public static final int SERVER_REST = -1;
    public static final int SERVER_BACK = -2;
    public static int numOfServers = 0;
    public int serverID;
    private boolean isServing;
    // private boolean isWaiting;
    private double nextServiceTime;
    // private Customer servingCustomer;
    private Queue<Customer> waitingQueue;
    private int queueLength;
    private double restingProbability;
    private boolean isResting;
    protected boolean needRest;

    public void isBack() {
        this.isResting = false;
    }

    /**
     * @param queueLength the maximum length of waiting queue
     * Constructor for Server object.
     * The ID of the server is self-incremental.
     */
    public Server(int queueLength, double restingProbability) {
        isServing = false;
        // isWaiting = false;
        nextServiceTime = 0.0;
        // servingCustomer = null;
        numOfServers++;
        serverID = numOfServers;
        waitingQueue = new LinkedList<Customer>();
        this.queueLength = queueLength;
        this.restingProbability = restingProbability;
        this.isResting = false;
        this.needRest = true;
    }

    public boolean canServeImmediately(double time) {
        return !this.isServing && nextServiceTime <= time;
    }

    public boolean canWait() {
        return this.waitingQueue.size() < this.queueLength;
    }

    public int getQueueLength() {
        return this.waitingQueue.size();
    }

    public boolean isResting() {
        return this.isResting;
    }

    public double getBackTime() {
        return this.nextServiceTime;
    }

    /**
     * Serve a customer, return the served customer if successful.
     * @return the customer that's served (with changed status and properties)
     */
    public Customer serve(Customer customer) {
        // System.out.printf("server %d next time: %.3f\n", this.serverID, nextServiceTime);
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
                // servingCustomer = customer;
                double serviceTime = EventSimulator.randomGenerator.genServiceTime();
                nextServiceTime = customer.getCurrentStatusTime() + serviceTime;
                returnedCustomer = customer.withTimeOfService(customer.getCurrentStatusTime())
                                           .withCurrentStatusTime(nextServiceTime)
                                           .withStatus(Customer.DONE)
                                           .withDurationOfService(serviceTime);
                break;
            case Customer.DONE:
                isServing = false;
                if(this.needRest && EventSimulator.randomGenerator.genRandomRest() < this.restingProbability) {
                    final double restingTime = EventSimulator.randomGenerator.genRestPeriod();
                    this.nextServiceTime += restingTime;
                    this.isResting = true;
                    shouldReturn = false;
                } else {
                    if(!this.waitingQueue.isEmpty()) {
                        Customer a = this.waitingQueue.poll();
                        // System.out.printf("%d waited at %.3f to be served at %.3f\n", a.getCustomerID(), a.getCurrentStatusTime(), nextServiceTime);
                        returnedCustomer = a.withStatus(Customer.SERVED)
                                                   .withCurrentStatusTime(nextServiceTime);
                    } else {
                        // servingCustomer = null;
                        shouldReturn = false;
                    }
                }
                break;
            case Customer.BACK:
                // System.out.println("back");
                isBack();
                if(!this.waitingQueue.isEmpty()) {
                    Customer a = this.waitingQueue.poll();
                    // System.out.printf("%d waited at %.3f to be served at %.3f\n", a.getCustomerID(), a.getCurrentStatusTime(), nextServiceTime);
                    returnedCustomer = a.withStatus(Customer.SERVED)
                                            .withCurrentStatusTime(nextServiceTime);
                } else {
                    shouldReturn = false;
                }
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