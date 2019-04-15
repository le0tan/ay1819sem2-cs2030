/**
 * Encapsulates the state information of a Server object. The information
 * includes a server id, the available time for next service, whether  a server 
 * is resting, and a list of customers queuing at this server. 
 *
 * @author Zhang Xiaoyu
 */

package cs2030.simulator; 

import java.util.Queue;
import java.util.LinkedList;

public class Server {

    protected static int MAX_QUEUE_LENGTH;
    protected int id;
    protected double availableTime;
    protected boolean isRest;
    protected Queue<Customer> customerQueue;

    /**
     * Sole constructor takes in the server id.
     *
     * @param id  an integer id indicating the added sequence of servers and
     *            the afterwards searching sequence for available servers
     */
    public Server(int id) {
        this.id = id;
        this.customerQueue = new LinkedList<>();
    }

    public int getId() {
        return id;
    }

    public double getAvailableTime() {
        return availableTime;
    }

    public Customer getNextCustomer() {
        return customerQueue.isEmpty() ? null : customerQueue.poll();
    }

    public boolean isRest() {
        return isRest;
    }

    public Queue<Customer> getCustomerQueue() {
        return customerQueue;
    }

    public int getQueueLen() {
        return customerQueue.size();
    }

    public boolean isAvailable(double givenTime) {
        return availableTime <= givenTime;
    }

    public boolean canQueue() {
        return customerQueue.size() < MAX_QUEUE_LENGTH;
    }

    public void queueCustomer(Customer customer) {
        customerQueue.offer(customer); 
    }

    /**
     * Updates this server's available time for next service.
     *
     * @param customer     a <code>Customer</code> being served by this server
     * @param startTime    a double time when this server starts to serve the
     *                     customer
     */
    public void serveCustomer(Customer customer, double startTime) {
        this.availableTime = customer.getNeededServiceTime() + startTime;
    }
   
    public void rest() {
        isRest = true;
    }

    public void back() {
        isRest = false;
    }


    /**
     * Represents the server as its id.
     * 
     * @return a string representation of this server's id
     */
    @Override 
    public String toString() {
        return "" + id;
    }
}
