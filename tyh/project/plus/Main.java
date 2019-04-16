/**
 * Reads inputs and calls methods in <code>EventSimulator</code> to 
 * initialize and handle events.
 * 
 * <p>The inputs are ten numbers: 
 * (1) an integer seed for <code>RandomGenerator</code>; 
 * (2) the total number of human servers; 
 * (3) the total number of self-check counters; 
 * (4) max queue length; 
 * (5) the total number of customers; 
 * (6) a double arrival rate; 
 * (7) a double service time rate; 
 * (8) a double rest rate;
 * (9) a double rest probability; 
 * and (10) a double greedy customer probability.
 *
 * <p>The outputs are: 
 * (1) a sequence of events that are generated by <code>EventSimulator</code>
 * (2) statistics regarding average waiting time, total number 
 * of served customers, and total number of left customers.
 * 
 * @author Tan Yuanhong
 */

import java.util.Scanner;
import java.util.PriorityQueue;
import cs2030.simulator.EventSimulator;
import cs2030.simulator.Result;

public class Main {

    /**
     * Main method that runs the simulator.
     * 
     * @param args commandline arguments
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int seed = sc.nextInt();
        int numOfServers = sc.nextInt();
        int numOfSelfCheckout = sc.nextInt();
        int maxLength = sc.nextInt();
        int numOfCustomers = sc.nextInt();
        double arrivalRate = sc.nextDouble();
        double serviceRate = sc.nextDouble();
        double restingRate = sc.nextDouble();
        double pr = sc.nextDouble();
        double pg = sc.nextDouble();
        EventSimulator es = new EventSimulator(numOfServers, numOfSelfCheckout, seed, arrivalRate, 
                            serviceRate, restingRate, pr, pg, maxLength);
        for (int i = 0; i < numOfCustomers; i++) {
            es.addCustomer();
        }
        /**
         * An empty while loop to run nextStep() repeated until
         * no more events can be processed.
         */
        while (es.nextStep()) {
        }
        PriorityQueue<Result> result = es.getResult();
        while (!result.isEmpty()) {
            System.out.println(result.poll());
        }
        es.statistics.printStatistics();
        sc.close();
    }
}