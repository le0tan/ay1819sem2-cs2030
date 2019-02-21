import java.util.*;

/**
 * EventSimulator
 */
public class EventSimulator {
    private PriorityQueue<Customer> customers;
    private PriorityQueue<Customer> result;
    private Server server;
    public Statistics statistics;

    public EventSimulator() {
        customers = new PriorityQueue<>();
        result = new PriorityQueue<>();
        server = new Server();
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
            result.add(currentCustomer);
            Customer toBeAdded = server.serve(currentCustomer);
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