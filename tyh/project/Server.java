/**
 * Server
 */
public class Server {

    public static final double DURATION_OF_SERVICE = 1.0;
    private boolean isServing;
    private boolean isWaiting;
    private double nextServiceTime;
    private Customer servingCustomer;

    public Server() {
        isServing = false;
        isWaiting = false;
        nextServiceTime = 0.0;
        servingCustomer = null;
    }

    public boolean isServing() {
        return isServing;
    }

    public int serve(Customer customer) {
        switch (customer.status) {
            case Customer.ARRIVES:
                if(customer.getTimeOfArrival() >= nextServiceTime) {
                    customer.setStatus(Customer.SERVED);
                    return Customer.SERVED;
                } else if(!isWaiting) {
                    isWaiting = true;
                    customer.setStatus(Customer.WAITS);
                    return Customer.WAITS;
                } else {
                    customer.setStatus(Customer.LEAVES);
                    return Customer.LEAVES;
                }
            case Customer.SERVED:
                isServing = true;
                if(isWaiting) isWaiting = false;
                nextServiceTime = customer.getTimeOfArrival() + DURATION_OF_SERVICE;
                servingCustomer = customer;
                customer.setTimeOfService(customer.getTimeOfArrival());
                customer.setCurrentStatusTime(nextServiceTime);
                customer.setStatus(Customer.DONE);
                return Customer.DONE;
            case Customer.DONE:
                isServing = false;
                servingCustomer = null;
                return -1;
            default:
                return -1;
        }
    }
}