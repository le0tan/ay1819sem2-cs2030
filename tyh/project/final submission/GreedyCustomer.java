package cs2030.simulator;

import java.util.function.Supplier;

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
     * @param time                  the time of arrival
     * @param serviceTimeSupplier   supplier for service time
     */
    public GreedyCustomer(double time, Supplier<Double> serviceTimeSupplier) {
        super(time, serviceTimeSupplier);
    }

}