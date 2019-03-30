package cs2030.simulator;

/**
 * CustomerEvent.
 */
public class CustomerEvent implements Event {

    private double time;
    private int type;
    private Customer customer;

    /**
     * Constructor for a <code>CustomerEvent</code>.
     * @param time time of event
     * @param type event type
     * @param customer customer involved in the event
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
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }
}