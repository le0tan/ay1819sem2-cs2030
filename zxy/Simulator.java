/**
 * Generates, schedules, and prints out all events in a chronological order.
 * A <code>Simulator</code> will take in six parameters: (1)the total number of 
 * customers; (2)the total number of human servers; (3)the total number of 
 * self-check counters; (4)the max possible queue length; (5)the the probability 
 * of a server resting; (6) the probability of a greedy customer occurring. 
 * 
 * <p>The simulator contains a list of <code>Event</code>. Whenever a new 
 * event is triggered by previous events, it will be added to the list. Whenever
 * a event is handled, it will be removed from the list. The simulator start
 * working with a default event of time 0.0, a <code>Customer</code>, and the
 * <code>State</code> of <code>State.ARRIVES</code>. 
 * A <code>handleNextEvent</code> method  will handle events in the order of 
 * event happening time and according to their states. Whenever a event is
 * handled, that event will be printed out. The simulator finishes work 
 * when there is no more event in the schedule list.
 *
 * <p>The simulator also contains an array of <code>Server</code>. It adds all
 * servers when it is initialized. When handling the ARRIVES event, it will look
 * for a suitable server to server the arrived customer. If there is no suitable
 * server, the customer will leave immediately.
 *
 * <p>Within the simulator, a <code>RandomGenerator</code> will be created, 
 * given a seed, a arrival rate, a service rate, and  a resting rate as 
 * parameters. It will randomly generate customers' arrival times, their needed 
 * service times, and servers' resting durations.
 *
 * <p>The Simulator records the number of total served customers as well as
 * the total waiting time accumulated from each customer.
 *

 * <p>One assumption is that there will be at least one customer and one server. 
 * 
 * @author Zhang Xiaoyu
 */

package cs2030.simulator; 

import java.util.PriorityQueue;

public class Simulator {

    private PriorityQueue<Event> events;
    private Server[] servers;
    private int totalCustomers;
    private int totalHmServers;
    private int totalScCounters;
    private int totalServed;
    private double restProbability;
    private double greedyProbability;
    private double totalWaitingTime;
    private RandomGenerator generator;

    /**                                 
     * Sole constructor takes in six parameters.  
     *
     * @param totalCustomers    the total number of customers
     * @param totalHmServers    the total number of human servers
     * @param totalScCounters   the total number of self-check counters
     * @param queueLength       an int value for the maximum queue length
     * @param restProbability   a double parameter for the probability of resting
     * @param greedyProbability a double parameter for the probability of a 
     *                          greedy customer occurring
     */
    public Simulator(int totalCustomers, int totalHmServers, int totalScCounters,
            int queueLength, double restProbability, double greedyProbability) {
        this.totalCustomers = totalCustomers;
        this.totalHmServers = totalHmServers;
        this.totalScCounters = totalScCounters;
        this.restProbability = restProbability;
        this.greedyProbability = greedyProbability;
        Server.MAX_QUEUE_LENGTH = queueLength;
        this.events = new PriorityQueue<>(new EventComparator());
        addServers();
    }

    public int getTotalServed() {
        return totalServed;
    }

    public double getTotalWaitingTime() {
        return totalWaitingTime;
    }

    public void createRandomGenerator(int seed, double arrivalRate, 
            double serviceRate, double restRate) {
        generator = new RandomGenerator(seed, arrivalRate, serviceRate, 
                restRate);
    }

    /**
     * Uses an arrival event of the time 0.0 and a customer of id 1 as the 
     * starting event. 
     * The starting event will further trigger other events. Until the list
     * of events becomes empty, this method picks the first event in terms of
     * scheduled time, and calls <code>handleNextEvent</code> to handle the
     * picked event.
     */
    public void start() {
        addArrivalEvent(1, 0.0);
        while (!events.isEmpty()) {
            Event event = events.poll();
            System.out.println(event);
            handleNextEvent(event);
        }
    }
   
    /**
     * Add a new Event of ARRIVES state, given time and a customer either normal
     * or greedy. Whether a customer is greedy or not depends on whether the 
     * random number generated is smaller than the given greedy probability.
     *
     * @param customerId  an int represents the customer's id
     * @param time        a double value represents the customer's arrival time
     */
    private void addArrivalEvent(int customerId, double time) {
        if (generator.genCustomerType() < greedyProbability) {
            events.add(new Event(time, new GreedyCustomer(customerId, time), 
                        State.ARRIVES));
        } else {
            events.add(new Event(time, new Customer(customerId, time), 
                        State.ARRIVES));
        }
    }

    /**
     * Initiates an array of the given number of total human servers and the
     * given number of total self-check counters. This method adds 
     * <code>HumanServer</code> first and then <code>SelfcheckCounter</code>. All
     * added servers are tagged with an id number starting from 1, which
     * indicates the adding order.
     */
    private void addServers() {
        servers = new Server[totalHmServers + totalScCounters];
        for (int i = 0; i < totalHmServers; i++) {
            servers[i] = new HumanServer(i + 1);
        }
        for (int i = totalHmServers; i < servers.length; i++) {
            servers[i] = new SelfcheckCounter(i + 1);
        }
    }
    
    /**
     * Handles given event according to its <code>State</code>. Seven states are
     * <code>State.ARRIVES</code>, <code>State.SERVED</code>,
     * <code>State.WAITS</code>, <code>State.LEAVES</code>,
     * <code>State.DONE</code>, <code>State.SERVER_REST</code>, and
     * <code>State.SERVER_BACK</code>.
     * 
     * <p>For the ARRIVES state: 
     * If there is an idle <code>Server</code>, this method will add a 
     * new <code>Event</code> of State.SERVED. 
     * Otherwise, if there is a server whose queue length is smaller than the
     * given max queue length, this method will add a new event of State.WAITS,
     * and add that customer to the according queue. In addition, a normal
     * <code>Customer</code> will join the first available queue while a
     * <code>GreedyCustomer</code> will join the first available queue of minimal
     * queue length.
     * Otherwise, this method will add a new event of State.LEAVES.
     * If there still are coming customers, namely, the current customer id is 
     * less than to given number of total customers, an additional ARRIVES event 
     * will be generated.
     *
     * <p>For the SERVED state: 
     * This method will add a new event of State.DONE. A randomly generated
     * needed service time is specified for the customer. Meanwhile, it will add 
     * the waiting time of the being served customer to the total waiting time, 
     * and update the total number of served customers will be updated. 
     * 
     * <p>For the DONE state:
     * If the server is a human server, this method first decides whether the
     * server needs to rest based on whether the randomly generated number is
     * smaller than the given rest probability. 
     * If the human server does not
     * need to rest or the server is a self-check counter, it will add a new
     * event of SERVED for next queuing customer if there is any.
     *
     * <p>For the SERVER_REST state:
     * This method will add a BACK event of randomly generated resting duration.
     *
     * <p>For the SERVER_BACK:
     * This method will make the server back to work, and add a new
     * event of SERVED for next queuing customer if there is any.
     *
     * <p>Both WAITS and LEAVES events are handled, namely, printed out, 
     * without any influence on other events.
     *
     * @param event  a <code>Event</code> identified with event happenning time, 
     *               a <code>Customer</code>, a <code>Server</code> (optional),
     *               <code>State</code>.
     */
    private void handleNextEvent(Event event) {
        double time = event.getTime();
        Customer customer = event.getCustomer();
        Server server = event.getServer();
        State state = event.getState();

        switch (state) {
            case ARRIVES:
                int currId = customer.getId();
                Server idleServer = findIdleServer(time);
                if (idleServer != null) {
                    events.add(new Event(time, customer, idleServer, 
                                State.SERVED));
                } else {
                    Server canQueueServer = customer instanceof GreedyCustomer ?
                        findMinQueueServer() : findCanQueueServer();
                    if (canQueueServer != null) {
                        events.add(new Event(time, customer, canQueueServer, 
                                    State.WAITS));
                        canQueueServer.queueCustomer(customer);
                    } else {
                        events.add(new Event(time, customer, State.LEAVES));
                    }
                }
                if (currId < totalCustomers) {
                    currId++;
                    double newTime = event.getTime() +
                        generator.genInterArrivalTime();
                    addArrivalEvent(currId, newTime);
                }
                break;
            case SERVED:
                customer.setNeededServiceTime(generator.genServiceTime());
                totalServed++;
                totalWaitingTime += (time - customer.getArrivalTime());
                server.serveCustomer(customer, time);
                events.add(new Event(server.getAvailableTime(), customer, server,
                            State.DONE));
                break;
            case DONE:
                if (!(server instanceof SelfcheckCounter) && 
                        generator.genRandomRest() < restProbability) {
                    server.rest();
                    events.add(new Event(time, server, State.SERVER_REST));
                } else {
                    Customer nextCustomer = server.getNextCustomer();
                    if (nextCustomer != null) {
                        events.add(new Event(time, nextCustomer, 
                                    server, State.SERVED));
                    }
                }
                break;
            case SERVER_REST:
                double backTime = generator.genRestPeriod() + time;
                events.add(new Event(backTime, server, State.SERVER_BACK));
                break;
            case SERVER_BACK:
                server.back();
                Customer nextCustomer = server.getNextCustomer();
                if (nextCustomer != null) {
                    events.add(new Event(time, nextCustomer, 
                                server, State.SERVED));
                }
                break;
            default:
        }
    }

    /**
     * Checks through the whole array of all servers to find the first
     * available server.
     *
     * @param  arrivalTime a double-precise time at which a customer arrives
     * @return             a <code>Server</code> indicating the available server
     *                     with smallest id or null if there is no such server
     */
    private Server findIdleServer(double arrivalTime) {
        for (int i = 0; i < servers.length; i++) {
            if (!servers[i].isRest() && servers[i].isAvailable(arrivalTime)) {
                return servers[i];
            }
        }
        return null;
    }

    /**
     * Checks through the whole array of all servers to find the first
     * server whose queue length is smaller than the given max length.
     *
     * @return a <code>Server</code> that a customer can queue at or null if
     *         there is no such server
     */
    public Server findCanQueueServer() {
        for (int i = 0; i < servers.length; i++) {
            if (servers[i].canQueue()) {
                return servers[i];
            }
        }
        return null;
    }

    /**
     * Checks through the whole array of all servers to find the first
     * server whose queue length is shortest.
     *
     * @return a <code>Server</code> of shortest length that a customer can queue 
     *         at or null if there is no such server
     */ 
    public Server findMinQueueServer() {
        Server minQueueServer = servers[0];
        for (int i = 1; i < servers.length; i++) {
            if (servers[i].getQueueLen() < minQueueServer.getQueueLen()) {
                minQueueServer = servers[i];
            }
        }
        if (minQueueServer.canQueue()) {
            return minQueueServer;
        } else {
            return null;
        }
    }
}
