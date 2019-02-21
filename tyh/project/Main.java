import java.util.*;
/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EventSimulator es = new EventSimulator();
        while(sc.hasNext()) {
            Customer customer = new Customer(sc.nextDouble());
            Event event = new Event(customer.getTimeOfArrival(), customer.getID(), Event.ARRIVES);
            es.addEvent(event);
        }
        while(es.getNextEvent()) {}
        // es.printQueue();
        System.out.println("Number of customers: " + Customer.getNumOfCustomers());
        sc.close();
    }
}