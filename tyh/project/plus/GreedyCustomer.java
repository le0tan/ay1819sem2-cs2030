package cs2030.simulator;

import cs2030.simulator.Customer;

/**
 * A child class of <code>Customer</code> that is greedy (always 
 * choose to wait in the shortest queue.)
 * 
 * @author Tan Yuanhong
 */

public class GreedyCustomer extends Customer {

    /**
     * Constructor for GreedyCustomer.
     * 
     * @param time the time of arrival
     */
    public GreedyCustomer(double time) {
        super(time);
    }

}