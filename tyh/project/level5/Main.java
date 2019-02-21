import java.util.*;
/**
 * Main
 */
public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EventSimulator es = new EventSimulator();
        while(sc.hasNext()) {
            es.addCustomer(new Customer(sc.nextDouble()));
        }
        while(es.nextStep()) {}
        PriorityQueue<Customer> result = es.getResult();
        while(!result.isEmpty()) {
            System.out.println(result.poll());
        }
        es.statistics.printStatistics();
    }
}