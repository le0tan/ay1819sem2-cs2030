package cs2030.simulator;

/**
 * CustomerEvent
 */
public class CustomerEvent implements Event {

    private double time;
    private int type;
    private Customer customer;

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
     * @return the customer
     */
    public Customer getCustomer() {
        return customer;
    }
}