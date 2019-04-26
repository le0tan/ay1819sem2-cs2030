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
            servers[i] = new Server(queueLength, pr, 
                            () -> randomGenerator.genRandomRest(), 
                            () -> randomGenerator.genRestPeriod());
        }
        for (int i = numOfServers; i < numOfServers + numOfSelfCheckout; i++) {
            servers[i] = new SelfCheckoutCounter(queueLength, pr,
                            () -> randomGenerator.genRandomRest(), 
                            () -> randomGenerator.genRestPeriod());
        }
        statistics = new Statistics();
        randomGenerator = new RandomGenerator(seed, lambda, mu, rho);
    }


    /**
     * Add a new customer to the simulator.
     */
    public void addCustomer() {
        if (randomGenerator.genCustomerType() < this.probabilityOfGreedy) {
            Customer currentCustomer = new GreedyCustomer(currentTime, () -> randomGenerator.genServiceTime());
            customers.add(currentCustomer);
            events.add(new CustomerEvent(currentTime, EventType.ARRIVES, currentCustomer));
            currentTime += randomGenerator.genInterArrivalTime();
        } else {
            Customer currentCustomer = new Customer(currentTime, () -> randomGenerator.genServiceTime());
            customers.add(currentCustomer);
            events.add(new CustomerEvent(currentTime, EventType.ARRIVES, currentCustomer));
            currentTime += randomGenerator.genInterArrivalTime();
        }
    }

    /**
     * Process ARRIVE event, return the event to be added when
     * necessary.
     * 
     * @param currentEvent the event that's being processed
     * @return event to be added, null if there isn't any
     */
    private Event processArriveEvent(Event currentEvent) {
        boolean arrivedAndNotLeft = false;
        Event toBeAdded = null;
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
        return toBeAdded;
    }

    /**
     * Process BACK event.
     * 
     * @param currentEvent the event that's being processed
     * @return event to be added
     */
    private Event processBackEvent(Event currentEvent) {
        return ((ServerEvent) currentEvent).getServer().beBack();
    }

    /**
     * Process SERVED or DONE event, return the event to be added when
     * necessary.
     * 
     * @param currentEvent the event that's being processed
     * @return event to be added, null if there isn't any
     */
    private Event processServedOrDoneEvent(Event currentEvent) {
        Event toBeAdded = ((CustomerEvent) currentEvent).getCustomer()
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
        return toBeAdded;
    }

    /**
     * Update the statistics based on the newly added event.
     * 
     * @param toBeAdded the newly added event
     */
    private void updateStatistics(Event toBeAdded) {
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

            switch (currentEvent.getType()) {
                case ARRIVES:
                    toBeAdded = processArriveEvent(currentEvent);
                    break;
                case LEAVES:
                    return true;
                case WAITS:
                    return true;
                case BACK:
                    toBeAdded = processBackEvent(currentEvent);
                    break;
                case SERVED:
                    toBeAdded = processServedOrDoneEvent(currentEvent);
                    break;
                case DONE:
                    toBeAdded = processServedOrDoneEvent(currentEvent);
                    break;
                default:
                    throw new IllegalArgumentException("Found illegal type of event");
            }
            
            if (toBeAdded != null) {
                updateStatistics(toBeAdded);
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