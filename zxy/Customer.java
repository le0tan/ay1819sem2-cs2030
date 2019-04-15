/**
 * Encapsulates the information of a Customer object. The information
 * includes a customer id and the needed service time.
 *
 * @author Zhang Xiaoyu
 */


package cs2030.simulator; 

class Customer {

    protected int id;
    protected double arrivalTime;
    protected double neededServiceTime;

    /**                                 
     * Sole constructor takes in the customer id.  
     *                                           
     * @param id  an integer id indicating the added sequence of customers.
     */
    public Customer(int id, double arrivalTime) {
        this.id = id;
        this.arrivalTime = arrivalTime;
    }

    public int getId() {
        return id;
    }

    public double getArrivalTime() {
        return arrivalTime;
    }

    public double getNeededServiceTime() {
        return neededServiceTime;
    }

    public void setNeededServiceTime(double time) {
        neededServiceTime = time;
    }

    /**
     * Represents the customer as its id.
     * 
     * @return a string representation of this customer's id
     */
    @Override
    public String toString() {
        return "" + id;
    }
}
