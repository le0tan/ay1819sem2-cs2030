package cs2030.simulator;

import java.util.PriorityQueue;

public class EventSimulator {
    private PriorityQueue<Customer> customers;
    private PriorityQueue<Event> result;
    // private Server server;
    private Server[] servers;
    public Statistics statistics;
    public static RandomGenerator randomGenerator;
    private double probabilityOfGreedy;
    double currentTime = 0.0;

    /**
     * Constructor for the event simulator.
     * Initializing the properties and create a proper-sized
     * array for the servers.
     * @param numOfServers number of servers
     */
    public EventSimulator(int numOfServers, int numOfSelfCheckout, int seed, double lambda, double mu, double rho, double Pr, double Pg, int queueLength) {
        customers = new PriorityQueue<>();
        result = new PriorityQueue<>(new EventComparator());
        servers = new Server[numOfServers+numOfSelfCheckout];
        this.probabilityOfGreedy = Pg;
        for (int i = 0; i < numOfServers; i++) {
            servers[i] = new Server(queueLength, Pr);
        }
        for(int i=numOfServers;i<numOfServers+numOfSelfCheckout;i++) {
            servers[i] = new SelfCheckoutCounter(queueLength, Pr);
        }
        statistics = new Statistics();
        randomGenerator = new RandomGenerator(seed, lambda, mu, rho);
    }


    /**
     * Add a new customer to the simulator.
     */
    public void addCustomer() {
        if(randomGenerator.genCustomerType() < this.probabilityOfGreedy) {
            customers.add(new Customer(currentTime, true));
            currentTime += randomGenerator.genInterArrivalTime();
        } else {
            customers.add(new Customer(currentTime));
            currentTime += randomGenerator.genInterArrivalTime();
        }
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
            if(currentCustomer.status != Customer.BACK)
            result.add(new Event(currentCustomer.getCurrentStatusTime(),
                                 currentCustomer,
                                 currentCustomer.status,
                                 currentCustomer.getServer()));

            // ARRIVE event
            if (currentCustomer.status == Customer.ARRIVES) {
                boolean arrivedAndNotLeft = false;
                for (int i = 0; i < Server.numOfServers; i++) {
                    if (servers[i].canServeImmediately(currentCustomer.getCurrentStatusTime())) {
                        toBeAdded = servers[i].serve(currentCustomer);
                        arrivedAndNotLeft = true;
                        break;
                    }
                }
                if (!arrivedAndNotLeft) {
                    if(!currentCustomer.isGreedy()) {
                        for (int i = 0; i < Server.numOfServers; i++) {
                            if (servers[i].canWait()) {
                                toBeAdded = servers[i].serve(currentCustomer);
                                arrivedAndNotLeft = true;
                                break;
                            }
                        }
                    } else {
                        int chosenServer = -1;
                        for(int i=0;i<Server.numOfServers;i++) {
                            if(servers[i].canWait()) {
                                if(chosenServer == -1) {
                                    chosenServer = i;
                                } else {
                                    if(servers[i].getQueueLength() < servers[chosenServer].getQueueLength()) {
                                        chosenServer = i;
                                    }
                                }
                            }
                        }
                        if(chosenServer != -1) {
                            toBeAdded = servers[chosenServer].serve(currentCustomer);
                            arrivedAndNotLeft = true;
                        }
                    }
                }
                if (!arrivedAndNotLeft) {
                    toBeAdded = currentCustomer.withStatus(Customer.LEAVES);
                }
            // LEAVE or WAIT event
            } else if(currentCustomer.status == Customer.LEAVES
                    || currentCustomer.status == Customer.WAITS) {
                // Server doesn't need to deal with LEAVES and WAITS events
                return true;
            // SERVED or DONE event
            } else {
                if(currentCustomer.status == Customer.BACK) {
                    toBeAdded = currentCustomer.getServer().serve(currentCustomer);
                } else {
                    toBeAdded = currentCustomer.getServer().serve(currentCustomer);
                    if(toBeAdded == null) {
                        Server currentServer = currentCustomer.getServer();
                        if(currentServer.isResting()) {
                            result.add(new Event(currentCustomer.getCurrentStatusTime(), Server.SERVER_REST, currentServer));
                            result.add(new Event(currentServer.getBackTime(), Server.SERVER_BACK, currentServer));
                            customers.add(new Customer(0.0, 0.0, currentServer.getBackTime(), 0, Customer.BACK, currentCustomer.getServer(), 0.0, false));
                            // currentServer.isBack();
                        }
                    }
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