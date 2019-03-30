package cs2030.simulator;

import java.util.Queue;
import java.util.LinkedList;

/**
 * Server that serves customers.
 */

public class Server {

    public static final int SERVER_REST = -1;
    public static final int SERVER_BACK = -2;
    public static int numOfServers = 0;
    public int serverID;
    private boolean isServing;
    private double nextServiceTime;
    private Queue<Customer> waitingQueue;
    private int queueLength;
    private double restingProbability;
    private boolean isResting;
    protected boolean needRest;

    /**
     * Constructor for <code>Server</code>.
     * @param queueLength length of waiting queue
     * @param restingProbability probability of resting
     */
    public Server(int queueLength, double restingProbability) {
        isServing = false;
        nextServiceTime = 0.0;
        numOfServers++;
        serverID = numOfServers;
        waitingQueue = new LinkedList<Customer>();
        this.queueLength = queueLength;
        this.restingProbability = restingProbability;
        this.isResting = false;
        this.needRest = true;
    }

    /**
     * Reportor for whether the server can server right away.
     * @param time current time
     * @return a boolean describing whether the server can server right away
     */
    public boolean canServeImmediately(double time) {
        return !this.isServing && nextServiceTime <= time;
    }

    /**
     * Reportor for whether the server can accept a waiting customer.
     * @return true if it can accept one more waiting customer, false otherwise
     */
    public boolean canWait() {
        return this.waitingQueue.size() < this.queueLength;
    }

    /**
     * Getter for the current queue length.
     * @return the length of waiting queue
     */
    public int getQueueLength() {
        return this.waitingQueue.size();
    }

    /**
     * Getter for <code>isResting</code> property.
     */
    public void isBack() {
        this.isResting = false;
    }

    /**
     * Method to be called when the server is back from rest.
     * @return a new <code>CustomerEvent</code> of status <code>Event.SERVED</code>
     *          if the server is serving a customer in the waiting queue right
     *          after the break, <code>null</code> otherwise
     */
    public CustomerEvent beBack() {
        isBack();
        if (!this.waitingQueue.isEmpty()) {
            Customer a = this.waitingQueue.poll();
            return new CustomerEvent(nextServiceTime, Event.SERVED, a);
        } else {
            return null;
        }
    }

    /**
     * Getter for <code>isResting</code> property.
     * @return a boolean <code>isResting</code>
     */
    public boolean isResting() {
        return this.isResting;
    }


    /**
     * Getter for <code>getBackTime</code> property.
     * @return a boolean <code>getBackTime</code>
     */
    public double getBackTime() {
        return this.nextServiceTime;
    }

    /**
     * This method takes a <code>CustomerEvent</code>, 
     * process the event and returns the following one if necessary.
     * @param customer the <code>CustomerEvent</code> that's processed
     * @return following <code>CustomerEvent</code> if there is one.
     */
    public CustomerEvent serve(CustomerEvent customer) {
        CustomerEvent returnedCustomer = null;
        boolean shouldReturn = true;
        switch (customer.getType()) {
            case Event.ARRIVES:
                if (customer.getTime() >= nextServiceTime) {
                    customer.getCustomer().setServer(this);
                    returnedCustomer = new CustomerEvent(
                                            customer.getTime(), 
                                            Event.SERVED, 
                                            customer.getCustomer());
                } else if (canWait()) {
                    customer.getCustomer().setServer(this);
                    returnedCustomer = new CustomerEvent(
                                            customer.getTime(), 
                                            Event.WAITS, 
                                            customer.getCustomer());
                    this.waitingQueue.add(customer.getCustomer());
                } else {
                    returnedCustomer = new CustomerEvent(
                                            customer.getTime(), 
                                            Event.LEAVES, 
                                            customer.getCustomer());
                }
                break;
            case Event.SERVED:
                isServing = true;
                double serviceTime = EventSimulator.randomGenerator.genServiceTime();
                nextServiceTime = customer.getTime() + serviceTime;
                customer.getCustomer().setTimeOfService(customer.getTime());
                customer.getCustomer().setDurationOfService(serviceTime);
                returnedCustomer = new CustomerEvent(
                                        nextServiceTime, 
                                        Event.DONE, 
                                        customer.getCustomer());
                break;
            case Event.DONE:
                isServing = false;
                if (this.needRest 
                    && EventSimulator.randomGenerator.genRandomRest() 
                        < this.restingProbability) {
                    final double restingTime = EventSimulator.randomGenerator.genRestPeriod();
                    this.nextServiceTime += restingTime;
                    this.isResting = true;
                    shouldReturn = false;
                } else {
                    if (!this.waitingQueue.isEmpty()) {
                        Customer a = this.waitingQueue.poll();
                        returnedCustomer = new CustomerEvent(nextServiceTime, Event.SERVED, a);
                        a.setTimeOfService(customer.getTime());
                    } else {
                        shouldReturn = false;
                    }
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