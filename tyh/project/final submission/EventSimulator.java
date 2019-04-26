package cs2030.simulator;

import java.util.PriorityQueue;
import java.util.List;
import java.util.ArrayList;

/**
 * The main logic for processing events. It implements two main
 * functionalities: initialization and processing events in a functional way.
 * 
 * <p>When processing and generating the <code>Event</code>, this simulator
 * always creates a new instance of <code>Event</code> because the 
 * <code>Event</code> class is immutable.
 * 
 * <p>However, since customers and servers should be stateful, 
 * and it looks necessary for servers to include a queue of customers, 
 * and <code>ServerEvent</code> has to depend on <code>Server</code>, 
 * we change the internal state of a <code>Server</code> in this class 
 * and pass the server information with <code>Event</code>, 
 * so that <code>Server</code> doesn't need to depend on <code>ServerEvent</code>
 * and <code>Customer</code> doesn't need to depend on <code>Server</code>
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
            Customer currentCustomer = new GreedyCustomer(currentTime, 
                () -> randomGenerator.genServiceTime());
            customers.add(currentCustomer);
            events.add(new CustomerEvent(currentTime, EventType.ARRIVES, currentCustomer, null));
            currentTime += randomGenerator.genInterArrivalTime();
        } else {
            Customer currentCustomer = new Customer(currentTime, 
                () -> randomGenerator.genServiceTime());
            customers.add(currentCustomer);
            events.add(new CustomerEvent(currentTime, EventType.ARRIVES, currentCustomer, null));
            currentTime += randomGenerator.genInterArrivalTime();
        }
    }

    /**
     * Method to be called when the server is back from rest.
     * 
     * @param server the server to be back
     * @return a new <code>CustomerEvent</code> of status <code>Event.SERVED</code>
     *          if the server is serving a customer in the waiting queue right
     *          after the break, <code>null</code> otherwise
     */
    public CustomerEvent beBack(Server server) {
        server.setIsResting(false);
        if (!server.waitingQueueIsEmpty()) {
            Customer next = server.pollFromWaitingQueue();
            return new CustomerEvent(server.getNextServiceTime(), EventType.SERVED, next, server);
        } else {
            return null;
        }
    }

    /**
     * This method takes a <code>CustomerEvent</code>, 
     * process the event and returns the following one if necessary.
     * 
     * @param server the serve chosen to serve the customer
     * @param customer the <code>CustomerEvent</code> that's processed
     * @return following <code>CustomerEvent</code> if there is one.
     */
    public CustomerEvent serve(Server server, CustomerEvent customer) {
        CustomerEvent returnedCustomer = null;
        boolean shouldReturn = true;
        switch (customer.getType()) {
            case ARRIVES:
                if (customer.getTime() >= server.getNextServiceTime()) {
                    returnedCustomer = customer.setType(EventType.SERVED).setServer(server);
                } else if (server.canWait()) {
                    returnedCustomer = customer.setType(EventType.WAITS).setServer(server);
                    server.addToWaitingQueue(customer.getCustomer());
                } else {
                    returnedCustomer = customer.setType(EventType.LEAVES).setServer(server);
                }
                break;
            case SERVED:
                server.setIsServing(true);
                double serviceTime = customer.getCustomer().getServiceTime();
                server.setNextServiceTime(customer.getTime() + serviceTime);
                customer.getCustomer().setTimeOfService(customer.getTime());
                customer.getCustomer().setDurationOfService(serviceTime);
                returnedCustomer = customer.setTime(
                    server.getNextServiceTime()).setType(EventType.DONE).setServer(server);
                break;
            case DONE:
                server.setIsServing(false);
                if (server.needRest 
                    && server.randomRestSupplier.get()
                        < server.getRestingProbability()) {
                    final double restingTime = server.restPeriodSupplier.get();
                    server.setNextServiceTime(server.getNextServiceTime() + restingTime);
                    server.setIsResting(true);
                    shouldReturn = false;
                } else {
                    if (!server.waitingQueueIsEmpty()) {
                        Customer current = server.pollFromWaitingQueue();
                        returnedCustomer = new CustomerEvent(server.getNextServiceTime(), 
                                            EventType.SERVED, current, server);
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
                toBeAdded = serve(servers[i], (CustomerEvent) currentEvent);
                arrivedAndNotLeft = true;
                break;
            }
        }
        if (!arrivedAndNotLeft) {
            if (!((CustomerEvent) currentEvent).getCustomer().isGreedy()) {
                for (int i = 0; i < Server.numOfServers; i++) {
                    if (servers[i].canWait()) {
                        toBeAdded = serve(servers[i], (CustomerEvent) currentEvent);
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
                    toBeAdded = serve(servers[chosenServer], (CustomerEvent) currentEvent);
                    arrivedAndNotLeft = true;
                }
            }
        }
        if (!arrivedAndNotLeft) {
            toBeAdded = new CustomerEvent(
                currentEvent.getTime(), 
                EventType.LEAVES, 
                ((CustomerEvent) currentEvent).getCustomer(),
                null);
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
        return this.beBack(((ServerEvent) currentEvent).getServer());
    }

    /**
     * Process SERVED or DONE event, return the event to be added when
     * necessary.
     * 
     * @param currentEvent the event that's being processed
     * @return event to be added, null if there isn't any
     */
    private Event processServedOrDoneEvent(Event currentEvent) {
        Event toBeAdded = serve(((CustomerEvent) currentEvent).getServer(), 
            (CustomerEvent) currentEvent);
        if (toBeAdded == null) {
            Server currentServer = ((CustomerEvent) currentEvent)
                                    .getServer();
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
                                            current.getServer());
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