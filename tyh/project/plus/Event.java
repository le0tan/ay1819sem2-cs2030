/**
 * Created for <code>CustomerEvent</code> and <code>ServerEvent</code> 
 * so that we can store and compare them in a homogeneous array.
 * 
 * <p>It defines the constants for different event types, and these
 * values are chosen carefully so that they properly represents 
 * the processing priority.
 * 
 * <p>It also specifies two basic methods of an event: (1) <code>getTime</code>, 
 * (2) <code>getType</code> whose purpose are self-explanatory.
 * 
 * @author Tan Yuanhong
 */

package cs2030.simulator;

public interface Event {

    /**
     * Event types.
     */
    public static final int SERVER_BACK = -2;
    public static final int SERVER_REST = -1;
    public static final int ARRIVES = 0;
    public static final int DONE = 1;
    public static final int LEAVES = 2;
    public static final int WAITS = 3;
    public static final int SERVED = 4;
    public static final int BACK = 5;

    public double getTime();

    public int getType();
    
}