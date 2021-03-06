import java.util.Scanner;
import java.util.PriorityQueue;
import cs2030.simulator.EventSimulator;
import cs2030.simulator.Customer;
import cs2030.simulator.Event;
import cs2030.simulator.Result;
// import cs2030.simulator.RandomGenerator;

// new RandomGenerator(int seed, double lambda, double mu)
// lambda = arrival rate; mu = service rate;
// genInterArrivalTime()
// genServiceTime()

public class Main {

    /**
     * Main method that runs the simulator.
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
        double probabilityOfResting = sc.nextDouble();
        EventSimulator es = new EventSimulator(numOfServers, numOfSelfCheckout, seed, arrivalRate, 
                            serviceRate, restingRate, probabilityOfResting, maxLength);
        for (int i = 0; i < numOfCustomers; i++) {
            es.addCustomer();
        }
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