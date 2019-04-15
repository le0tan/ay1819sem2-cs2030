/**                                                                              
 * Encapsulates the state information of a Greedy Customer object. It inherits 
 * from <code>Customer</code>. It has almost the same attributes with              
 * <code>Customer</code> except that it overrides <code>toString()</code> method.
 *
 * @author Zhang Xiaoyu                                                          
 */

package cs2030.simulator;

public class GreedyCustomer extends Customer {

    public GreedyCustomer(int id, double arrivalTime) {
        super(id, arrivalTime);
    }

    /**
     * Represents the greedy customer as its id and "(greedy)".
     *
     * @return a string representation of this customer's information
     */
    @Override
    public String toString() {
        return id + "(greedy)";
    }
}

