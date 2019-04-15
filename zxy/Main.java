/**
 * Reads inputs and starts <code>Simulator</code> to handle events.
 * The inputs are ten numbers, which are: (1) an integer seed for
 * <code>RandomGenerator</code>; (2) the total number of human servers; (3) the
 * total number of self-check counters; (4)max queue length; (5) the total number 
 * of customers; (6) a double arrival rate; (7) a double service time rate; 
 * (8) a double rest rate;(9) a double rest probability; and (10) a double 
 * greedy-customer-occuring probability.
 *
 * <p>The outputs are (1) the sequence of happened events printed out by the
 * Simulator; (2) statistics regarding average waiting time, total number 
 * of served customers, and total number of left customers.
 */

import java.util.Scanner; 
import cs2030.simulator.Simulator;

public class Main {
    
    /**
     * Reads inputs and starts <code>Simulator</code> to handle events.
     * The inputs are ten numbers, which are: (1) an integer seed for
     * <code>RandomGenerator</code>; (2) the total number of human servers; 
     * (3) the total number of self-check counters; (4)max queue length; (5) the 
     * total number of customers; (6) a double arrival rate; (7) a double service 
     * time rate; (8) a double rest rate;(9) a double rest probability; and (10) 
     * a double greedy-customer-occuring probability.
     *
     * <p>The outputs are (1) the sequence of happened events printed out by the
     * Simulator; (2) statistics regarding average waiting time, total number 
     * of served customers, and total number of left customers.
     *
     * @param args an array of String read from console
     */
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int seed = sc.nextInt();
        int totalServers = sc.nextInt();
        int totalScCounters = sc.nextInt();
        int queueLength = sc.nextInt();
        int totalCustomers = sc.nextInt();
        double arrivalRate = sc.nextDouble();
        double serviceRate = sc.nextDouble();
        double restRate = sc.nextDouble();
        double restProb = sc.nextDouble();
        double greedyProb = sc.nextDouble();

        Simulator simulator = new Simulator(totalCustomers, totalServers,
                totalScCounters, queueLength, restProb, greedyProb);
        simulator.createRandomGenerator(seed, arrivalRate, serviceRate,
                restRate);
        simulator.start();

        double totalWaitingTime = simulator.getTotalWaitingTime();
        int totalServed = simulator.getTotalServed();
        double aveWaitingTime = (totalServed == 0 ? 0.0 : 
                totalWaitingTime / totalServed);
        System.out.printf("[%.3f %d %d]\n", aveWaitingTime,  totalServed, 
                totalCustomers - totalServed);
    }   
}
