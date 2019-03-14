package cs2030.simulator;

import java.util.PriorityQueue;

public class EventSimulator {
    private PriorityQueue<Customer> customers;
    private PriorityQueue<Event> result;
    // private Server server;
    private Server[] servers;
    public Statistics statistics;
    public static RandomGenerator randomGenerator;
    double currentTime = 0.0;

    /**
     * Constructor for the event simulator.
     * Initializing the properties and create a proper-sized
     * array for the servers.
     * @param numOfServers number of servers
     */
    public EventSimulator(int numOfServers, int seed, double lambda, double mu) {
        customers = new PriorityQueue<>();
        result = new PriorityQueue<>(new EventComparator());
        servers = new Server[numOfServers];
        for (int i = 0; i < numOfServers; i++) {
            servers[i] = new Server();
        }
        statistics = new Statistics();
        randomGenerator = new RandomGenerator(seed, lambda, mu);
    }


    /**
     * Add a new customer to the simulator.
     */
    public void addCustomer() {
        customers.add(new Customer(currentTime));
        // customers.add(new Customer(currentTime, Server.DURATION_OF_SERVICE));
        currentTime += randomGenerator.genInterArrivalTime();
    }

    /**
     * Go to the next step of the simulation.
     * @return true if this step is successfully taken
     */
    public boolean nextStep() {
        if (customers.isEmpty()) {
            return false;
        } else {
            Customer currentCustomer = customers.poll();
            Customer toBeAdded = null;
            result.add(new Event(currentCustomer.getCurrentStatusTime(),
                                 currentCustomer.getCustomerID(),
                                 currentCustomer.status,
                                 currentCustomer.getServer()));

            if (currentCustomer.status == Customer.ARRIVES) {
                boolean arrivedAndNotLeft = false;
                for (int i = 0; i < Server.numOfServers; i++) {
                    if (!servers[i].isServing()) {
                        toBeAdded = servers[i].serve(currentCustomer);
                        arrivedAndNotLeft = true;
                        break;
                    }
                }
                if (!arrivedAndNotLeft) {
                    for (int i = 0; i < Server.numOfServers; i++) {
                        if (!servers[i].isWaiting()) {
                            toBeAdded = servers[i].serve(currentCustomer);
                            arrivedAndNotLeft = true;
                            break;
                        }
                    }
                }
                if (!arrivedAndNotLeft) {
                    toBeAdded = currentCustomer.withStatus(Customer.LEAVES);
                }
            } else {
                if (currentCustomer.getServer() != null) {
                    toBeAdded = currentCustomer.getServer().serve(currentCustomer);
                }
            }

            if (toBeAdded != null) {
                if (toBeAdded.status == Customer.DONE) {
                    statistics.numOfCustomersServed++;
                    statistics.totalWaitingTime += (toBeAdded.getTimeOfService() 
                                                    - toBeAdded.getTimeOfArrival());
                } else if (toBeAdded.status == Customer.LEAVES) {
                    statistics.numOfCustomersLeft++;
                }
                customers.add(toBeAdded);
            }

            return true;
        }
    }

    /**
     * Get the output result.
     * @return a priority queue storing the events as Customer objects
     */
    public PriorityQueue<Event> getResult() {
        return result;
    }
}