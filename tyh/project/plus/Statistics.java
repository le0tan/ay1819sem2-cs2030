/**
 * A class dealing with storing, processing and outputing statistics.
 * 
 * <p>All its three fields are public because <code>EventSimulator</code> needs
 * to change the statistics' values:
 * (1) an int numOfCustomersServed
 * (2) an int numOfCustomersLeft
 * (3) a double totalWaitingTime
 * 
 * @author Tan Yuanhong
 */

package cs2030.simulator;

public class Statistics {

    public int numOfCustomersServed;
    public int numOfCustomersLeft;
    public double totalWaitingTime;

    /**
     * Constructor for Statistics object.
     * Initializing the properties.
     */
    public Statistics() {
        numOfCustomersLeft = 0;
        numOfCustomersLeft = 0;
        totalWaitingTime = 0.0;
    }

    /**
     * Prints out the statistics with required formats.
     */
    public void printStatistics() {
        System.out.printf("[%.3f %d %d]\n", 
                        numOfCustomersServed == 0 ? 0.0 : totalWaitingTime / numOfCustomersServed, 
                        numOfCustomersServed,
                        numOfCustomersLeft);
    }
}