package cs2030.simulator;

import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

/**
 * The main logic for processing events. It implements two main
 * functions: initialization and processing events in a functional way.
 * 
 * <p>It has one <code>public static</code> field called randomGenerator, 
 * which is assigned an instance of <code>RandomGenerator</code> with
 * proper parameters when this class itself is initialized with constructor.
 * 
 * <p>It has one <code>public</code> instance field called statistics, 
 * which stores all relavant statistics happened in the simulation.
 * 
 * <p>It has six private instance fields: customers (a list storing all customers), 
 * result (a priority queue storing the records of events), events (a priority 
 * queue where events go in and out with accordance to the processing rules), 
 * servers (an array storing the servers), probabilityOfGreedy and currentTime.
 * 
 * @author Tan Yuanhong
 */

public class EventSimulator {

    public static RandomGenerator randomGenerator;

    public Statistics statistics;

    private List<Customer> customers;
    private PriorityQueue<Result> result;
    private PriorityQueue<Event> events;
    private Server[] servers;
    private double probabilityOfGreedy;
    private double currentTime = 0.0;

    /**
     * Constructor for the <code>EventSimulator</code>.
     * 
     * @param numOfServers      number of servers
     * @param numOfSelfCheckout number of self checkout counters
     * @param seed              seed for <code>RandomGenerator</code>
     * @param lambda            arrival rate
     * @param mu                service rate
     * @param rho               resting rate
     * @param pr                probability of resting
     * @param pg                probability of greedy customer
     * @param queueLength       maximum length of waiting queue
     */
    public EventSimulator(int numOfServers, int numOfSelfCheckout, 
                          int seed, double lambda, double mu, double rho, 
                          double pr, double pg, int queueLength) {
        customers = new ArrayList<>();
        result = new PriorityQueue<>(new ResultComparator());
        events = new PriorityQueue<>(new EventComparator());
        servers = new Server[numOfServers + numOfSelfCheckout];
        this.probabilityOfGreedy = pg;
        for (int i = 0; i < numOfServers; i++) {
            servers[i] = new Server(queueLength, pr);
        }
        for (int i = numOfServers; i < numOfServers + numOfSelfCheckout; i++) {
            servers[i] = new SelfCheckoutCounter(queueLength, pr);
        }
        statistics = new Statistics();
        randomGenerator = new RandomGenerator(seed, lambda, mu, rho);
    }


    /**
     * Add a new customer to the simulator.
     */
    public void addCustomer() {
        if (randomGenerator.genCustomerType() < this.probabilityOfGreedy) {
            Customer currentCustomer = new GreedyCustomer(currentTime);
            customers.add(currentCustomer);
            events.add(new CustomerEvent(currentTime, EventType.ARRIVES, currentCustomer));
            currentTime += randomGenerator.genInterArrivalTime();
        } else {
            Customer currentCustomer = new Customer(currentTime);
            customers.add(currentCustomer);
            events.add(new CustomerEvent(currentTime, EventType.ARRIVES, currentCustomer));
            currentTime += randomGenerator.genInterArrivalTime();
        }
    }

    /**
     * Go to the next step of the simulation.
     * 
     * @return true if this step is successfully taken
     */
    public boolean nextStep() {
        if (events.isEmpty()) {
            return false;
        } else {
            Event currentEvent = events.poll();
            Event toBeAdded = null;
            if (currentEvent.getType() != EventType.BACK) {
                CustomerEvent current = (CustomerEvent) currentEvent;
                Result prevResult = new Result(current.getTime(),
                                            current.getCustomer(),
                                            current.getType(),
                                            current.getCustomer().getServer());
                result.add(prevResult);
            }
            /**
             * ARRIVE event
             **/
            if (currentEvent.getType() == EventType.ARRIVES) {
                boolean arrivedAndNotLeft = false;
                for (int i = 0; i < Server.numOfServers; i++) {
                    if (servers[i].canServeImmediately(currentEvent.getTime())) {
                        toBeAdded = servers[i].serve((CustomerEvent) currentEvent);
                        arrivedAndNotLeft = true;
                        break;
                    }
                }
                if (!arrivedAndNotLeft) {
                    if (!((CustomerEvent) currentEvent).getCustomer().isGreedy()) {
                        for (int i = 0; i < Server.numOfServers; i++) {
                            if (servers[i].canWait()) {
                                toBeAdded = servers[i].serve((CustomerEvent) currentEvent);
                                arrivedAndNotLeft = true;
                                break;
                            }
                        }
                    } else {
                        int chosenServer = -1;
                        for (int i = 0; i < Server.numOfServers; i++) {
                            if (servers[i].canWait()) {
                                if (chosenServer == -1) {
                                    chosenServer = i;
                                } else {
                                    if (servers[i].getQueueLength() 
                                        < 
                                        servers[chosenServer].getQueueLength()) {
                                        chosenServer = i;
                                    }
                                }
                            }
                        }
                        if (chosenServer != -1) {
                            toBeAdded = servers[chosenServer].serve((CustomerEvent) currentEvent);
                            arrivedAndNotLeft = true;
                        }
                    }
                }
                if (!arrivedAndNotLeft) {
                    toBeAdded = new CustomerEvent(
                        currentEvent.getTime(), 
                        EventType.LEAVES, 
                        ((CustomerEvent) currentEvent).getCustomer());
                }
            /**
             * LEAVE or WAIT event
             */
            } else if (currentEvent.getType() == EventType.LEAVES
                    || currentEvent.getType() == EventType.WAITS) {
                /**
                 * Server doesn't need to deal with LEAVES and WAITS events
                 */
                return true;
            /**
             * BACK event
             */
            } else if (currentEvent.getType() == EventType.BACK) {
                toBeAdded = ((ServerEvent) currentEvent).getServer().beBack();
            /**
             * SERVED or DONE event
             */
            } else {
                if (currentEvent.getType() == EventType.BACK) {
                    toBeAdded = ((ServerEvent) currentEvent).getServer().beBack();
                } else {
                    toBeAdded = ((CustomerEvent) currentEvent).getCustomer()
                                    .getServer()
                                    .serve((CustomerEvent) currentEvent);
                    if (toBeAdded == null) {
                        Server currentServer = ((CustomerEvent) currentEvent)
                                                .getCustomer().getServer();
                        if (currentServer.isResting()) {
                            result.add(new Result(currentEvent.getTime(), 
                                            EventType.SERVER_REST, 
                                            currentServer));
                            result.add(new Result(currentServer.getBackTime(), 
                                            EventType.SERVER_BACK, 
                                            currentServer));
                            events.add(new ServerEvent(currentServer.getBackTime(), 
                                            EventType.BACK, 
                                            ((CustomerEvent) currentEvent)
                                                .getCustomer()
                                                .getServer()));
                        }
                    }
                }
            }
            if (toBeAdded != null) {
                if (toBeAdded.getType() == EventType.DONE) {
                    statistics.numOfCustomersServed++;
                    statistics.totalWaitingTime += 
                        (((CustomerEvent) toBeAdded)
                            .getCustomer()
                            .getTimeOfService() 
                        - 
                        ((CustomerEvent) toBeAdded)
                            .getCustomer()
                            .getTimeOfArrival());
                } else if (toBeAdded.getType() == EventType.LEAVES) {
                    statistics.numOfCustomersLeft++;
                }
                events.add(toBeAdded);
            }

            return true;
        }
    }

    /**
     * Process the events until no new events can be created.
     */
    public void simulate() {
        while (nextStep()) { }
    }

    /**
     * Get the output result.
     * 
     * @return a priority queue storing the events as Customer objects
     */
    public PriorityQueue<Result> getResult() {
        return result;
    }
}