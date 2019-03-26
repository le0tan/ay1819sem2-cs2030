package cs2030.simulator;

import java.util.Queue;
import java.util.LinkedList;

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
        nextServiceTime = 0.0;
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
    public CustomerEvent serve(CustomerEvent customer) {
        CustomerEvent returnedCustomer = null;
        boolean shouldReturn = true;
        switch (customer.getType()) {
            case Event.ARRIVES:
                if (customer.getTime() >= nextServiceTime) {
                    customer.getCustomer().setServer(this);
                    returnedCustomer = new CustomerEvent(customer.getTime(), Event.SERVED, customer.getCustomer());
                } else if (canWait()) {
                    customer.getCustomer().setServer(this);
                    returnedCustomer = new CustomerEvent(customer.getTime(), Event.WAITS, customer.getCustomer());
                    this.waitingQueue.add(customer.getCustomer());
                } else {
                    returnedCustomer = new CustomerEvent(customer.getTime(), Event.LEAVES, customer.getCustomer());
                }
                break;
            case Event.SERVED:
                isServing = true;
                double serviceTime = EventSimulator.randomGenerator.genServiceTime();
                nextServiceTime = customer.getTime() + serviceTime;
                customer.getCustomer().setTimeOfService(customer.getTime());
                customer.getCustomer().setDurationOfService(serviceTime);
                returnedCustomer = new CustomerEvent(nextServiceTime, Event.DONE, customer.getCustomer());
                break;
            case Event.DONE:
                isServing = false;
                if(this.needRest && EventSimulator.randomGenerator.genRandomRest() < this.restingProbability) {
                    final double restingTime = EventSimulator.randomGenerator.genRestPeriod();
                    this.nextServiceTime += restingTime;
                    this.isResting = true;
                    shouldReturn = false;
                } else {
                    if(!this.waitingQueue.isEmpty()) {
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


    public CustomerEvent beBack() {
        isBack();
        if(!this.waitingQueue.isEmpty()) {
            Customer a = this.waitingQueue.poll();
            return new CustomerEvent(nextServiceTime, Event.SERVED, a);
        } else {
            return null;
        }
    }
}