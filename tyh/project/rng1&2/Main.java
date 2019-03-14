import java.util.Scanner;
import java.util.PriorityQueue;
import cs2030.simulator.EventSimulator;
import cs2030.simulator.Customer;
import cs2030.simulator.Event;

public class Main {

    /**
     * Main method that runs the simulator.
     * @param args commandline arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        EventSimulator es = new EventSimulator(sc.nextInt());
        while (sc.hasNext()) {
            es.addCustomer(new Customer(sc.nextDouble()));
        }
        while (es.nextStep()) {
        }
        PriorityQueue<Event> result = es.getResult();
        while (!result.isEmpty()) {
            System.out.println(result.poll());
        }
        es.statistics.printStatistics();
        sc.close();
    }
}