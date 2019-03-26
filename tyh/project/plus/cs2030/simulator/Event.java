package cs2030.simulator;

/**
 * Event
 */
public interface Event {

    // Event types
    public static final int DONE = 1;
    public static final int LEAVES = 2;
    public static final int WAITS = 3;
    public static final int SERVED = 4;
    public static final int ARRIVES = 0;
    public static final int BACK = 5;

    public double getTime();
    public int getType();

}