package cs2030.simulator;

import java.util.Queue;
import java.util.function.Supplier;
import java.util.LinkedList;

/**
 * Server that stores its own state and the queuing customers.
 * Expose a group of getters and setters to change its state
 * in <code>EventSimulator</code>.
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
 * 
 * <p>It has three protected field: 
 * (1) a boolean needRest, because <code>SelfCheckoutCounter</code> 
 * needs to inherit it from this class.
 * (2) randomRestSupplier
 * (3) restPeriodSupplier
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
    protected Supplier<Double> randomRestSupplier;
    protected Supplier<Double> restPeriodSupplier;
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

    public int getServerID() {
        return serverID;
    }

    public int getQueueLength() {
        return this.waitingQueue.size();
    }

    public void addToWaitingQueue(Customer customer) {
        this.waitingQueue.add(customer);
    }

    public Customer pollFromWaitingQueue() {
        return this.waitingQueue.poll();
    }

    public boolean waitingQueueIsEmpty() {
        return this.waitingQueue.isEmpty();
    }

    public void setIsServing(boolean val) {
        this.isServing = val;
    }

    public void setIsResting(boolean val) {
        this.isResting = val;
    }

    public double getRestingProbability() {
        return this.restingProbability;
    }

    public double getNextServiceTime() {
        return this.nextServiceTime;
    }

    public void setNextServiceTime(double time) {
        this.nextServiceTime = time;
    }

    public boolean isResting() {
        return this.isResting;
    }

    public double getBackTime() {
        return this.nextServiceTime;
    }

}