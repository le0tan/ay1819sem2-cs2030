/**
 * Server
 */
public class Server {

    public static final double DURATION_OF_SERVICE = 1.0;
    private double nextService;

    public int serveCustomer(Customer customer) {
        if(nextService <= customer.getTimeOfArrival()) {
            customer.serve(customer.getTimeOfArrival());
            nextService = customer.getTimeOfArrival() + DURATION_OF_SERVICE;
            return Event.SERVED;
        } else {
            return Event.LEAVES;
        }
    }
}