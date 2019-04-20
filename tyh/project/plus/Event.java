package cs2030.simulator;

/**
 * Created for <code>CustomerEvent</code> and <code>ServerEvent</code> 
 * so that we can store and compare them in a homogeneous array.
 * 
 * <p>It specifies two basic methods of an event: (1) <code>getTime</code>, 
 * (2) <code>getType</code> whose purpose are self-explanatory.
 * 
 * @author Tan Yuanhong
 */

public interface Event {

    /**
     * Getter for the time field.
     * 
     * @return a double representing time of the event
     */
    public double getTime();


    /**
     * Getter for the type of event.
     * 
     * @return an EventType representing the type of event.
     */
    public EventType getType();
    
}