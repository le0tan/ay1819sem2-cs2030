package cs2030.simulator;

import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

/**
 * The main logic for processing events.
 */

public class EventSimulator {
    private List<Customer> customers;
    private PriorityQueue<Result> result;
    private PriorityQueue<Event> events;
    private Server[] servers;
    public Statistics statistics;
    public static RandomGenerator randomGenerator;
    private double probabilityOfGreedy;
    double currentTime = 0.0;

    /**
     * Constructor for the <code>EventSimulator</code>.
     * @param numOfServers number of servers
     * @param numOfSelfCheckout number of self checkout counters
     * @param seed seed for <code>RandomGenerator</code>
     * @param lambda arrival rate
     * @param mu service rate
     * @param rho resting rate
     * @param pr probability of resting
     * @param pg probability of greedy customer
     * @param queueLength maximum length of waiting queue
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
            Customer currentCustomer = new Customer(currentTime, true);
            customers.add(currentCustomer);
            events.add(new CustomerEvent(currentTime, Event.ARRIVES, currentCustomer));
            currentTime += randomGenerator.genInterArrivalTime();
        } else {
            Customer currentCustomer = new Customer(currentTime);
            customers.add(currentCustomer);
            events.add(new CustomerEvent(currentTime, Event.ARRIVES, currentCustomer));
            currentTime += randomGenerator.genInterArrivalTime();
        }
    }

    /**
     * Go to the next step of the simulation.
     * @return true if this step is successfully taken
     */
    public boolean nextStep() {
        if (events.isEmpty()) {
            return false;
        } else {
            Event currentEvent = events.poll();
            Event toBeAdded = null;
            if (currentEvent.getType() != Event.BACK) {
                CustomerEvent current = (CustomerEvent) currentEvent;
                Result prevResult = new Result(current.getTime(),
                                            current.getCustomer(),
                                            current.getType(),
                                            current.getCustomer().getServer());
                result.add(prevResult);
            }

            // ARRIVE event
            if (currentEvent.getType() == Event.ARRIVES) {
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
                        Event.LEAVES, 
                        ((CustomerEvent) currentEvent).getCustomer());
                }
            // LEAVE or WAIT event
            } else if (currentEvent.getType() == Event.LEAVES
                    || currentEvent.getType() == Event.WAITS) {
                // Server doesn't need to deal with LEAVES and WAITS events
                return true;
            // BACK event
            } else if (currentEvent.getType() == Event.BACK) {
                toBeAdded = ((ServerEvent) currentEvent).getServer().beBack();
            // SERVED or DONE event
            } else {
                if (currentEvent.getType() == Event.BACK) {
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
                                            Server.SERVER_REST, 
                                            currentServer));
                            result.add(new Result(currentServer.getBackTime(), 
                                            Server.SERVER_BACK, 
                                            currentServer));
                            events.add(new ServerEvent(currentServer.getBackTime(), 
                                            Event.BACK, 
                                            ((CustomerEvent) currentEvent)
                                                .getCustomer()
                                                .getServer()));
                        }
                    }
                }
            }
            if (toBeAdded != null) {
                if (toBeAdded.getType() == Event.DONE) {
                    statistics.numOfCustomersServed++;
                    statistics.totalWaitingTime += 
                        (((CustomerEvent) toBeAdded)
                            .getCustomer()
                            .getTimeOfService() 
                        - 
                        ((CustomerEvent) toBeAdded)
                            .getCustomer()
                            .getTimeOfArrival());
                } else if (toBeAdded.getType() == Event.LEAVES) {
                    statistics.numOfCustomersLeft++;
                }
                events.add(toBeAdded);
            }

            return true;
        }
    }

    /**
     * Get the output result.
     * @return a priority queue storing the events as Customer objects
     */
    public PriorityQueue<Result> getResult() {
        return result;
    }
}