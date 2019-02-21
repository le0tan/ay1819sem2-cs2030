/**
 * Statistics
 */
public class Statistics {

    public int numOfCustomersServed;
    public int numOfCustomersLeft;
    public double totalWaitingTime;

    public Statistics() {
        numOfCustomersLeft = 0;
        numOfCustomersLeft = 0;
        totalWaitingTime = 0.0;
    }

    public void printStatistics() {
        System.out.printf("[%.3f %d %d]\n", 
        totalWaitingTime / numOfCustomersServed, numOfCustomersServed, numOfCustomersLeft);
    }
}