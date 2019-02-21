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

    public Customer serve(Customer customer) {
        Customer returnedCustomer = customer.clone();
        boolean shouldReturn = true;
        switch (customer.status) {
            case Customer.ARRIVES:
                if(customer.getTimeOfArrival() >= nextServiceTime) {
                    returnedCustomer.setStatus(Customer.SERVED);
                } else if(!isWaiting) {
                    returnedCustomer.setStatus(Customer.WAITS);
                } else {
                    returnedCustomer.setStatus(Customer.LEAVES);
                }
                break;
            case Customer.SERVED:
                isServing = true;
                if(isWaiting) isWaiting = false;
                servingCustomer = customer;
                nextServiceTime = customer.getCurrentStatusTime() + DURATION_OF_SERVICE;
                returnedCustomer.setTimeOfService(customer.getCurrentStatusTime());
                returnedCustomer.setCurrentStatusTime(nextServiceTime);
                returnedCustomer.setStatus(Customer.DONE);
                break;
            case Customer.DONE:
                isServing = false;
                servingCustomer = null;
                shouldReturn = false;
                break;
            case Customer.WAITS:
                isWaiting = true;
                returnedCustomer.setStatus(Customer.SERVED);
                returnedCustomer.setCurrentStatusTime(nextServiceTime);
                break;
            case Customer.LEAVES:
                shouldReturn = false;
                break;
            default:
                System.out.println("Uncaught status");
                shouldReturn = false;
                break;
        }
        if(shouldReturn) {
            return returnedCustomer;
        } else {
            return null;
        }
    }
}