import java.util.PriorityQueue;
import java.util.Queue;
import java.lang.String;

/**
 * A class representing a server.
 */
public class Server {
    /**
     * The total number of customers served by all servers.
     */
    private static int servedCustomers = 0;
    /**
     * The total number of customers that left without being served.
     */
    private static int leftCustomers = 0;
    /**
     * The total waiting time for customers that have been served.
     */
    private static double waitingTime = 0;
    /**
     * The total number of servers.
     */
    private static int number = 0;
    /** 
     * The events that happened which is arranged in chronological order.
     */
    private static Queue<Event> event = new PriorityQueue<Event>();
    
    /**
     * The current customer that is being served by the server.
     */
    private Customer currentCustomer;
    /**
     * The customer that is waiting to be served by the server.
     */
    private Customer waitingCustomer;
    /**
     * The unique identity number of the server.
     */
    private int id;

    /**
     * Constructs a server.
     */
    public Server() {
        currentCustomer = null;
        waitingCustomer = null;
        id = ++number;
    }

    /**
     * Returns the customer that is waiting to be served by this server.
     * Returns null if there is no customer waiting.
     *
     * @return the customer that is waiting to be served by this server
     */
    public Customer getWaitingCustomer() {
        return waitingCustomer;
    }

    /**
     * Returns the earliest time that next service can start. 
     * The next service can start as the current service is done.
     * If there is no current service returns 0;
     * 
     * @return the earliest time that next service can start.
     */
    public double nextService() {
        if (currentCustomer == null) {
            return 0;
        } else {
            return currentCustomer.getFinishTime();
        }
    }

    /**
     * Returns <code>true</code> if the server can serve a given customer as the customer arrives.
     * The customer argument is the customer to be checked.
     *
     * @param  customer the customer to be checked.
     * @return <code>true</code> if the server can serve the customer as the customer arrives.
     */
    public boolean canServe(Customer customer) {
        return waitingCustomer == null && nextService() <= customer.getArriveTime();
    }

    /**
     * Returns <code>true</code> if another customer can wait to be served by this server.
     *
     * @return <code>true</code> if another customer can wait to be served by this server.
     */
    public boolean canWait() {
        return waitingCustomer == null;
    }

    /**
     * Serves the given customer at certain time.
     * The c argument is the customer to be served. The time argument is the time
     * at which the server starts to serve the customer.
     *
     * @param  c the customer to be served.
     * @param  time the time at which the server started to serve the customer.
     */
    public void serve(Customer c, double time) {
        currentCustomer = c;
        c.setServeTime(time);
        waitingCustomer = null;
        servedCustomers++;
        waitingTime += time - c.getArriveTime();
    }
    
    /**
     * Sets the given customer to be waiting for this server.
     * The c argument is the customer that is waiting.
     *
     * @param  c the customer that is waiting
     */
    public void setWait(Customer c) {
        waitingCustomer = c;
    }

    /**
     * Returns a string representation of the server.
     * The identity number of the server is converted to <code>String</code> to represent
     * the server.
     *
     * @return a string representation of the server.
     */
    @Override
    public String toString() {
        return id + "";
    }

    /**
     * Adds an arrives event with the given customer.
     * The c argument is the arriving customer.
     *
     * @param c the customer that arrives.
     */
    public static void addArrives(Customer c) {
        event.add(new Event(c.getArriveTime(), c, State.ARRIVES));
    }

    /**
     * Adds a served event with the given customer, time and server.
     * The t argument is the time at which the service starts.The c argument
     * is the customer to be served. The s argument is the server that serves 
     * the customer.
     *
     * @param t the time at which the service starts.
     * @param c the customer that arrives.
     * @param s the server that serves the customer.
     */
    public static void addServed(double t, Customer c, Server s) {
        event.add(new Event(t, c, State.SERVED, s));
        s.serve(c, t);
    }
    
    /**
     * Adds a waits event with the given customer, time and server.
     * The t argument is the time at which the customer starts to wait. The c 
     * argument is the customer that is waiting. The s argument is the server that the
     * customer is waiting for.
     *
     * @param t the time at which the service starts.
     * @param c the customer that is waiting.
     * @param s the server that the customer is waiting for.
     */
    public static void addWaits(double t, Customer c, Server s) {
        event.add(new Event(t, c, State.WAITS, s));
        s.setWait(c);
    }

    /**
     * Adds a leaves event with the given time, customer.
     * The t argument is the time at which the customer leaves. The c argument
     * is the customer that leaves.
     *
     * @param t the time at which the customer leaves.
     * @param c the customer that leaves.
     */
    public static void addLeaves(double t, Customer c) {
        event.add(new Event(t, c, State.LEAVES));
        leftCustomers++;
    }

    /**
     * Adds a done event with the given time, customer and server.
     * The t argument is the time at which the service is done. The c argument
     * is the customer that has been served. The s argument is the server that
     * served the customer.
     *
     * @param t the time at which the service is done.
     * @param c the customer that has been served.
     * @param s the server that served the customer.
     */
    public static void addDone(double t, Customer c, Server s) {
        event.add(new Event(t, c, State.DONE, s));
    }

    /**
     * Returns <code>true</code> if <code>Server</code> has another unprocessed event.
     *
     * @return <code>true</code> if <code>Server</code> has another unprocessed event.
     */
    public static boolean hasNextEvent() {
        return !event.isEmpty();
    }

    /** Returns the next event of <code>Server</code>.
     *
     * @return the next event of <code>Server</code>.
     */
    public static Event getNextEvent() {
        return event.poll();
    }

    /** 
     * Prints the statistics of <code>Server</code>, which includes the average
     * waiting time of served customers, the number of served customers and 
     * customers that leaves.
     */
    public static void printStatistics() {
        System.out.printf("[%.3f %d %d]\n", waitingTime / servedCustomers, 
                servedCustomers, leftCustomers);
    }

}
