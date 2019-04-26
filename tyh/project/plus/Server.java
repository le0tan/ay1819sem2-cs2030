package cs2030.simulator;

import java.util.Queue;
import java.util.function.Supplier;
import java.util.LinkedList;

/**
 * Server that takes in a <code>CustomerEvent</code>, change its state (if necessary),
 * and output the subsequent event (if any).
 * 
 * <p>It has one <code>public static</code> field: numOfServers that keeps track
 * of the number of servers so that we can assign IDs properly.
 * 
 * <p>It has eight private fields:
 * (1) an int serverID
 * (2) a boolean isServing indicating its availability
 * (3) a double nextServiceTime
 * (4) a Queue waitingQueue with customers in queue
 * (5) a double restingProbability
 * (6) a boolean isResting also indicating its availability
 * (7)(8) two suppliers for generating random rest time and rest period
 * 
 * <p>It has one protected field: a boolean needRest, 
 * because <code>SelfCheckoutCounter</code> needs to inherit it 
 * from this class.
 * 
 * @author Tan Yuanhong
 */

public class Server {

    public static int numOfServers = 0;
    private int serverID;
    private boolean isServing;
    private double nextServiceTime;
    private Queue<Customer> waitingQueue;
    private int queueLength;
    private double restingProbability;
    private boolean isResting;
    private Supplier<Double> randomRestSupplier;
    private Supplier<Double> restPeriodSupplier;
    protected boolean needRest;

    /**
     * Constructor for <code>Server</code>.
     * 
     * @param queueLength           length of waiting queue
     * @param restingProbability    probability of resting
     * @param randomRestSupplier    supplier for random rest time
     * @param restPeriodSupplier    supplier for random rest period
     */
    public Server(int queueLength, double restingProbability, 
        Supplier<Double> randomRestSupplier,
        Supplier<Double> restPeriodSupplier) {
        isServing = false;
        nextServiceTime = 0.0;
        numOfServers++;
        serverID = numOfServers;
        waitingQueue = new LinkedList<Customer>();
        this.queueLength = queueLength;
        this.restingProbability = restingProbability;
        this.isResting = false;
        this.needRest = true;
        this.randomRestSupplier = randomRestSupplier;
        this.restPeriodSupplier = restPeriodSupplier;
    }

    /**
     * Reportor for whether the server can server right away.
     * 
     * @param time current time
     * @return a boolean describing whether the server can server right away
     */
    public boolean canServeImmediately(double time) {
        return !this.isServing && nextServiceTime <= time;
    }

    /**
     * Reportor for whether the server can accept a waiting customer.
     * 
     * @return true if it can accept one more waiting customer, false otherwise
     */
    public boolean canWait() {
        return this.waitingQueue.size() < this.queueLength;
    }

    /**
     * Getter for server's ID.
     * 
     * @return the serverID
     */
    public int getServerID() {
        return serverID;
    }

    /**
     * Getter for the current queue length.
     * 
     * @return the length of waiting queue
     */
    public int getQueueLength() {
        return this.waitingQueue.size();
    }

    /**
     * Method to be called when the server is back from rest.
     * 
     * @return a new <code>CustomerEvent</code> of status <code>Event.SERVED</code>
     *          if the server is serving a customer in the waiting queue right
     *          after the break, <code>null</code> otherwise
     */
    public CustomerEvent beBack() {
        this.isResting = false;
        if (!this.waitingQueue.isEmpty()) {
            Customer next = this.waitingQueue.poll();
            return new CustomerEvent(nextServiceTime, EventType.SERVED, next);
        } else {
            return null;
        }
    }

    /**
     * Getter for <code>isResting</code> property.
     * 
     * @return a boolean <code>isResting</code>
     */
    public boolean isResting() {
        return this.isResting;
    }


    /**
     * Getter for <code>getBackTime</code> property.
     * 
     * @return a boolean <code>getBackTime</code>
     */
    public double getBackTime() {
        return this.nextServiceTime;
    }

    /**
     * This method takes a <code>CustomerEvent</code>, 
     * process the event and returns the following one if necessary.
     * 
     * @param customer the <code>CustomerEvent</code> that's processed
     * @return following <code>CustomerEvent</code> if there is one.
     */
    public CustomerEvent serve(CustomerEvent customer) {
        CustomerEvent returnedCustomer = null;
        boolean shouldReturn = true;
        switch (customer.getType()) {
            case ARRIVES:
                if (customer.getTime() >= nextServiceTime) {
                    customer.getCustomer().setServer(this);
                    returnedCustomer = customer.setType(EventType.SERVED);
                } else if (canWait()) {
                    customer.getCustomer().setServer(this);
                    returnedCustomer = customer.setType(EventType.WAITS);
                    this.waitingQueue.add(customer.getCustomer());
                } else {
                    returnedCustomer = customer.setType(EventType.LEAVES);
                }
                break;
            case SERVED:
                isServing = true;
                double serviceTime = customer.getCustomer().getServiceTime();
                nextServiceTime = customer.getTime() + serviceTime;
                customer.getCustomer().setTimeOfService(customer.getTime());
                customer.getCustomer().setDurationOfService(serviceTime);
                returnedCustomer = customer.setTime(nextServiceTime).setType(EventType.DONE);
                break;
            case DONE:
                isServing = false;
                if (this.needRest 
                    && this.randomRestSupplier.get()
                        < this.restingProbability) {
                    final double restingTime = this.restPeriodSupplier.get();
                    this.nextServiceTime += restingTime;
                    this.isResting = true;
                    shouldReturn = false;
                } else {
                    if (!this.waitingQueue.isEmpty()) {
                        Customer current = this.waitingQueue.poll();
                        returnedCustomer = new CustomerEvent(nextServiceTime, 
                                            EventType.SERVED, current);
                        current.setTimeOfService(customer.getTime());
                    } else {
                        shouldReturn = false;
                    }
                }
                break;
            default:
                System.err.println("Uncaught status");
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