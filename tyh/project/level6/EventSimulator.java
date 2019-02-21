import java.util.*;

/**
 * EventSimulator
 */
public class EventSimulator {
    private PriorityQueue<Customer> customers;
    private PriorityQueue<Customer> result;
    // private Server server;
    private Server[] servers;
    public Statistics statistics;

    public EventSimulator(int numOfServers) {
        customers = new PriorityQueue<>();
        result = new PriorityQueue<>();
        servers = new Server[numOfServers];
        for(int i=0;i<numOfServers;i++) {
            servers[i] = new Server();
        }
        statistics = new Statistics();
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public boolean nextStep() {
        if(customers.isEmpty()) {
            return false;
        } else {
            Customer currentCustomer = customers.poll();
            Customer toBeAdded = null;
            result.add(currentCustomer);

            if(currentCustomer.status == Customer.ARRIVES) {
                boolean arrivedAndNotLeft = false;
                for(int i=0;i<Server.numOfServers;i++) {
                    if(!servers[i].isServing()) {
                        toBeAdded = servers[i].serve(currentCustomer);
                        arrivedAndNotLeft = true;
                        break;
                    }
                }
                if(!arrivedAndNotLeft) {
                    for(int i=0;i<Server.numOfServers;i++) {
                        if(!servers[i].isWaiting()) {
                            toBeAdded = servers[i].serve(currentCustomer);
                            arrivedAndNotLeft = true;
                            break;
                        }
                    }
                }
                if(!arrivedAndNotLeft) {
                    toBeAdded = currentCustomer.clone();
                    toBeAdded.setStatus(Customer.LEAVES);
                }
            } else {
                if(currentCustomer.getServer() != null) {
                    toBeAdded = currentCustomer.getServer().serve(currentCustomer);
                }
            }

            if(toBeAdded != null) {
                if(toBeAdded.status == Customer.DONE) {
                    statistics.numOfCustomersServed++;
                    statistics.totalWaitingTime += (toBeAdded.getTimeOfService() 
                                                    - toBeAdded.getTimeOfArrival());
                } else if(toBeAdded.status == Customer.LEAVES) {
                    statistics.numOfCustomersLeft++;
                }
                customers.add(toBeAdded);
            }

            return true;
        }
    }

    public PriorityQueue<Customer> getResult() {
        return result;
    }
}