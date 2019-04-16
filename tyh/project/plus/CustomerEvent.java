/**
 * Provides an implementation of <code>Event</code>
 * interface for events related with customers.
 * 
 * <p>Three basic instance fields are (1) time (of the event), (2) type (of the 
 * event), (3) customer (involved in the event). Note that <code>type</code> is
 * of <code>int</code> type because it will be assigned with values from constants
 * from <code>Event</code> class that represents different types of events.
 * 
 * @author Tan Yuanhong
 */

package cs2030.simulator;

public class CustomerEvent implements Event {

    private double time;
    private int type;
    private Customer customer;

    /**
     * Constructor for a <code>CustomerEvent</code>.
     * 
     * @param time      time of event
     * @param type      event type
     * @param customer  customer involved in the event
     */
    public CustomerEvent(double time, int type, Customer customer) {
        this.time = time;
        this.type = type;
        this.customer = customer;
    }

    @Override
    public double getTime() {
        return this.time;
    }

    @Override
    public int getType() {
        return this.type;
    }
    
    /**
     * Getter for the customer.
     * 
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }
}