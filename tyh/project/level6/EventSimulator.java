import java.util.PriorityQueue;

public class EventSimulator {
    private PriorityQueue<Customer> customers;
    private PriorityQueue<Event> result;
    // private Server server;
    private Server[] servers;
    public Statistics statistics;

    /**
     * Constructor for the event simulator.
     * Initializing the properties and create a proper-sized
     * array for the servers.
     * @param numOfServers number of servers
     */
    public EventSimulator(int numOfServers) {
        customers = new PriorityQueue<>();
        result = new PriorityQueue<>();
        servers = new Server[numOfServers];
        for (int i = 0; i < numOfServers; i++) {
            servers[i] = new Server();
        }
        statistics = new Statistics();
    }

    /**
     * Add a customer to the simulator. (i.e. it arrives)
     * @param customer object representing this customer (i.e. it has status "arrives")
     */
    public void addCustomer(Customer customer) {
        customers.add(customer);
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
                    toBeAdded = currentCustomer.clone();
                    toBeAdded.setStatus(Customer.LEAVES);
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