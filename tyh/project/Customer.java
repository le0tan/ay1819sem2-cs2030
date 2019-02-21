/**
 * Customer
 */
public class Customer {

    public static final int ARRIVES = 0;
    public static final int SERVED = 1;
    public static final int WAITS = 2;
    public static final int LEAVES = 3;
    public static final int DONE = 4;
    private double timeOfArrival;
    private double timeOfService;
    private double currentStatusTime;
    public int status;

    public Customer(double time) {
        timeOfArrival = time;
        timeOfService = -1.0;
        currentStatusTime = time;
        status = 0;
    }

    public double getTimeOfArrival() {
        return timeOfArrival;
    }

    public double getTimeOfService() {
        return timeOfService;
    }

    public void setTimeOfService(double time) {
        timeOfService = time;
    }

    public void setCurrentStatusTime(double time) {
        currentStatusTime = time;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}